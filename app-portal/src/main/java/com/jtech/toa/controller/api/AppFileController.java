package com.jtech.toa.controller.api;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.xiaoleilu.hutool.date.DateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jtech.marble.StringPool;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.file.FilePackage;
import com.jtech.toa.entity.mail.MailFile;
import com.jtech.toa.model.AppConstant;
import com.jtech.toa.model.app.AppFile;
import com.jtech.toa.model.dto.DeleteData;
import com.jtech.toa.model.dto.InsertData;
import com.jtech.toa.model.dto.files.AppFileMarket;
import com.jtech.toa.model.dto.files.AppFilePackages;
import com.jtech.toa.model.dto.files.AppFileSeries;
import com.jtech.toa.model.query.FileQuery;
import com.jtech.toa.service.MailService;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileSeriesService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.mail.IMailFileService;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api/file")
public class AppFileController {
    private final IFileService fileService;
    private final IFilePackageService filePackageService;
    private final IFileSeriesService fileSeriesService;
    private final IFileMarketDetailService fileMarketService;
    private final IMailFileService mailFileService;
    private final MailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppFileController.class);
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public AppFileController(IFileService fileService,
                             IFilePackageService filePackageService,
                             IFileSeriesService seriesService,
                             IFileMarketDetailService fileMarketService,
                             IMailFileService mailFileService,
                             MailService mailService) {
        this.fileService = fileService;
        this.filePackageService = filePackageService;
        this.fileSeriesService = seriesService;
        this.fileMarketService = fileMarketService;
        this.mailFileService = mailFileService;
        this.mailService = mailService;
    }

    @GetMapping("/files")
    public ResponseEntity getFileList(@RequestUser RequestSubject user,
                                      @RequestParam(value = "type",defaultValue = "1")int type){
        return ResponseEntity.ok(fileService.findTenFileListByUser(user.getId(),type));
    }

    @GetMapping("/files/pages")
    public ResponseEntity getFileListPage(@RequestUser RequestSubject user,
                                          @RequestParam(value="last",defaultValue = "0",required = false) int last,
                                          @RequestParam(required = false)String q){
        FileQuery query = JSON.parseObject(q,FileQuery.class);
        if(query == null){
            query = new FileQuery();
        }
        return ResponseEntity.ok(fileService.findTenFileListByUser(user.getId(),last,query));
    }

    @GetMapping("/list")
    public ResponseEntity findFile(@RequestUser RequestSubject user,int lastId) {
        List<File> fileList = fileService.findPageFileListByUser(user.getId(),lastId);
        final List<AppFile> appFileList = Lists.newArrayList();
        fileList.forEach(x -> {
                    x.setUrl(fileService.medialUrl(x.getUrl()));
                    appFileList.add(new AppFile(x));
                }
        );
        return ResponseEntity.ok(appFileList);
    }

    @GetMapping("/packages/list")
    public ResponseEntity findFilePackages(int lastId){
        JSONArray jsonArray = new JSONArray();
        if(0==lastId){
            DeleteData deleteData = new DeleteData();
            deleteData.setKey("id");
            deleteData.setSort(1);
            deleteData.setTable(AppConstant.FilesPackages);
            deleteData.setType(DeleteData.Type.NotEq);
            deleteData.setValue("0");
            deleteData.setKey("id");
            deleteData.setVtype(DeleteData.Vtype.Single);
            JSONObject delete = new JSONObject();
            delete.put("dataDelete",deleteData);
            jsonArray.add(delete);
        }
        List<AppFilePackages> list = filePackageService.findFilePakages(lastId);
        InsertData<AppFilePackages> insertData = new InsertData<>(AppConstant.FilesPackages,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/packages")
    public ResponseEntity findPackageList(){
        JSONArray jsonArray = new JSONArray();
        DeleteData deleteData = new DeleteData();
        deleteData.setVtype(DeleteData.Vtype.Single);
        deleteData.setSort(1);
        deleteData.setValue("0");
        deleteData.setKey("id");
        deleteData.setTable(AppConstant.Packages);
        deleteData.setType(DeleteData.Type.NotEq);
        List<FilePackage> packageList = filePackageService.sceneData();

        InsertData insertData = new InsertData(AppConstant.Packages,buildPackageTree(packageList),2);

        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);

        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    private List<FilePackage> buildPackageTree(List<FilePackage> treeNodes) {
        List<FilePackage> trees = Lists.newArrayList();
        for (FilePackage treeNode : treeNodes) {
            if (0==treeNode.getParent()) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    private FilePackage findChildren(FilePackage treeNode,List<FilePackage> treeNodes) {
        for (FilePackage it : treeNodes) {
            if(treeNode.getId().equals(it.getParent())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(Lists.newArrayList());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    @GetMapping("/series/list")
    public ResponseEntity findFileSeries(int lastId){
        JSONArray jsonArray = new JSONArray();
        if(0==lastId){
            DeleteData deleteData = new DeleteData();
            deleteData.setKey("id");
            deleteData.setSort(1);
            deleteData.setTable(AppConstant.FileSeries);
            deleteData.setType(DeleteData.Type.NotEq);
            deleteData.setValue("0");
            deleteData.setVtype(DeleteData.Vtype.Single);

            JSONObject delete = new JSONObject();
            delete.put("dataDelete",deleteData);
            jsonArray.add(delete);
        }
        List<AppFileSeries> list = fileSeriesService.selectAppFileSeries(lastId);
        InsertData insertData = new InsertData(AppConstant.FileSeries,list,2);

        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/markets")
    public ResponseEntity findAppMarkets(){
        List<AppFileMarket> list = fileMarketService.findAppMarketList();
        JSONArray jsonArray = new JSONArray();
        DeleteData deleteData = new DeleteData();
        deleteData.setKey("id");
        deleteData.setSort(1);
        deleteData.setTable(AppConstant.FileMarket);
        deleteData.setType(DeleteData.Type.NotEq);
        deleteData.setValue("0");
        deleteData.setVtype(DeleteData.Vtype.Single);

        JSONObject delete = new JSONObject();
        delete.put("dataDelete",deleteData);
        jsonArray.add(delete);

        InsertData<AppFileMarket> insertData = new InsertData<>(AppConstant.FileMarket,list,2);
        JSONObject insert = new JSONObject();
        insert.put("dataInsert",insertData);
        jsonArray.add(insert);
        return ResponseEntity.ok(jsonArray);
    }

    @GetMapping("/view")
    public ResponseEntity viewFile(String id, String type) {
        Map<String, String> map = Maps.newHashMap();
        map.put("peviewStatus", "1");
        map.put("pdfUrl", "http://192.168.1.4:9091/file/view?id=1");
        return ResponseEntity.ok(map);
    }

    /**
     * 发送邮件
     * @param fileIds 文件id
     * @param receiverMail 接收人
     * @param subject 邮件主题
     * @param validTime 有效期
     * @return
     */
    @GetMapping("/sendMail")
    public ResponseEntity sendMail(@RequestParam("fileIds") String fileIds,@RequestParam("receiverMail") String receiverMail,
                                   @RequestParam("subject") String subject,@RequestParam("validTime") String validTime,
                                   HttpServletRequest request){
        LOGGER.info("app file sendMail,fileIds="+fileIds+",receiverMail="
                    +receiverMail+",subject="+subject+",validTime="+validTime);
        try {
            if (!Strings.isNullOrEmpty(fileIds) && !Strings.isNullOrEmpty(receiverMail)
                    && !Strings.isNullOrEmpty(subject) && !Strings.isNullOrEmpty(validTime)) {
                String[] fileIdArray = fileIds.split(",");
                List<Integer> fileIdList = Lists.newArrayList();
                if (fileIdArray.length > 0) {
                    for (int i = 0; i < fileIdArray.length; i++) {
                        fileIdList.add(Integer.parseInt(fileIdArray[i]));
                    }
                }
                if (!fileIdList.isEmpty()) {
                    List<File> files = fileService.selectBatchIds(fileIdList);
                    if (CollectionUtils.isNotEmpty(files)) {
                        Map<String, Object> model = new HashMap<>(3);
                        long now = System.currentTimeMillis();
                        long expired = now + 24 * 3600 * 1000 * Long.valueOf(validTime);
//                long expired = now + oaProperties.getMailExpiredTime() * 1000;

                        List<MailFile> mailFiles = Lists.newArrayList();
                        files.forEach(file -> {
                            MailFile mailFile = new MailFile();
                            mailFile.setName(file.getName());
                            String url = file.getUrl();
                            if (file.getType() == 999) {
                                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                    url = "http://" + url;
                                }
                            } else {
                                url = "/file/view?id=" + file.getId();
                            }
                            mailFile.setUrl(url);
                            mailFile.setCreateTime(new DateTime(now));
                            mailFile.setExpiredTime(new DateTime(expired));
                            mailFile.setSendMail(from);
                            mailFile.setAcceptMail(receiverMail);
                            mailFile.setMailSubject(subject);
                            mailFiles.add(mailFile);
                        });

                        // 保存邮件
                        mailFileService.save(mailFiles);
                        // 发送邮件
                        model.put("mailFiles", mailFiles);
                        model.put("deployServerPath", mailService.getDeployServerPath(request));
                        mailService.sendHtmlMailUsingFreeMarker(from, receiverMail, subject, model);
                    }
                    return ResponseEntity.ok("send success.");
                }
            } else {
                return ResponseEntity.ok("send fail,missing parameter.");
            }
        }catch(Exception e){
            return ResponseEntity.ok("send fail,"+e.getMessage()+".");
        }
        return ResponseEntity.ok(StringPool.EMPTY);
    }

}
