package com.jtech.toa.controller.seriesproduct;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import com.jtech.marble.StringPool;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Certification;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.file.File;
import com.jtech.toa.entity.product.Price;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.entity.product.SeriesStandard;
import com.jtech.toa.entity.product.Spare;
import com.jtech.toa.model.query.SeriesQuery;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.service.ICertificationService;
import com.jtech.toa.service.IConfigurationService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.file.IFileSecurityRoleService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.product.IBoxService;
import com.jtech.toa.service.product.IParamsService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISeriesStandardService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.ISpecialService;
import com.jtech.toa.service.system.IRoleService;
import com.jtech.toa.user.model.vo.TreeDataVO;

/**
 * <p> </p>
 *
 * @author ruili
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class SeriesProductController {
    private final ISeriesStandardService seriesStandardService;
    private final ISpareService spareService;
    private final IParamsService paramsService;
    private final IProductService productService;
    private final IBoxService boxService;
    private final IPriceService priceService;
    private final ISpecialService specialService;
    private final IConfigurationService configurationService;
    private final ISeriesService seriesService;
    private final ICertificationService certificationService;
    private final IDictService dictService;
    private final IFileService fileService;
    private final IRoleService roleService;
    private final IFileSecurityRoleService fileSecurityRoleService;
    @Value("${oa.media-url}")
    private String mediaUrl;

    private List<String> offices=Lists.newArrayList("doc","docx","xls","xlsx","ppt","pptx");
    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesProductController.class);


    @Autowired
    public SeriesProductController(ISeriesStandardService seriesStandardService, ISeriesService seriesService,
                                   ISpareService spareService,
                                   IFileService fileService, IParamsService paramsService,
                                   IProductService productService, IBoxService boxService,
                                   IPriceService priceService, ISpecialService specialService,
                                   IConfigurationService configurationService,
                                   ICertificationService certificationService, IDictService dictService,
                                   IRoleService roleService, IFileSecurityRoleService fileSecurityRoleService) {
        this.seriesStandardService = seriesStandardService;
        this.seriesService = seriesService;
        this.spareService = spareService;
        this.paramsService = paramsService;
        this.productService = productService;
        this.boxService = boxService;
        this.priceService = priceService;
        this.specialService = specialService;
        this.configurationService = configurationService;
        this.certificationService = certificationService;
        this.dictService = dictService;
        this.fileService= fileService;
        this.roleService = roleService;
        this.fileSecurityRoleService = fileSecurityRoleService;
    }



    @GetMapping(value = "/seriesproduct")
    public String importIndex() {
        return "/seriesproduct/list";
    }

    @GetMapping("/sp/spare/count")
    public String item(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("seriesId", id);
        }
        return "/seriesproduct/count/item";
    }

    @GetMapping("/sp/file")
    public String file(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("fid", id);
        }
        return "/seriesproduct/file/list";
    }

    @GetMapping("/sp/panel")
    public String panel(@RequestParam("id") int id, Model model) {
        if (id != 0) {
            model.addAttribute("series", id);
        }
        return "/seriesproduct/panel/list";
    }

    @GetMapping("/file/view")
    public String view(int id, Model model, @RequestUser RequestSubject user) throws Exception {
        File file = fileService.selectById(id);
        if(null==file){
            throw new Exception("File Not Exists");
        }
        if((file.getSecuritys()!=1&&file.getSecuritys()!=2)){
            if (!fileSecurityRoleService.checkFileRoleReadAble(id,user.getId())) {
                throw new Exception("沒有权限");
            }
        }
        final String url = StringUtils.replace(file.getUrl(), StringPool.BACK_SLASH, java.io.File.separator);
        model.addAttribute("url", fileService.medialUrl(url));
        model.addAttribute("type", file.getExtend());
        String type=file.getExtend().toLowerCase();
        model.addAttribute("show",offices.contains(type)?1:0);
        if(type.equals("doc") || type.equals("docx")){
            model.addAttribute("color","#ffffff");
        }else if(type.equals("ppt") || type.equals("pptx")){
            model.addAttribute("color","#383a3c");
        }else if (type.equals("png") || type.equals("jpg") || type.equals("gif") || type.equals("jpeg")){
            return "/seriesproduct/file/viewImg";
        }
        if(type.equals("xls") || type.equals("xlsx")){
            model.addAttribute("color","#ffffff");
        }

        return "/seriesproduct/file/view";
    }

    @GetMapping("/series/count/years")
    public String years(Integer standardId, Model model) {
        if (standardId > 0) {
            SeriesStandard standard = seriesStandardService.selectById(standardId);
            if (standard != null) {
                Integer seriesId = standard.getSeries();
                Integer sid = standard.getSpare();
                if (seriesId != null && sid != null) {
                    Series series = seriesService.selectById(seriesId);
                    Spare spare = spareService.selectById(sid);
                    model.addAttribute("years", getYears(standard));
                    model.addAttribute("spare", spare);
                    model.addAttribute("standard", standard);
                    model.addAttribute("series", series);
                }
            }
        }
        return "/seriesproduct/count/years";
    }

    @GetMapping("/sp/tab")
    public String tob(@RequestParam("id") int id, Model model, @RequestUser RequestSubject user) {
        Product product = productService.selectByIdAndLang(id, user.getLanguage());
        Price price = priceService.selectByProductAndArea(product.getId(), user.getArea());
        model.addAttribute("params",  paramsService.selectParamsByProductId(id));
        model.addAttribute("box", boxService.selectById(product.getBox()));
        model.addAttribute("product", product);
        model.addAttribute("productId", product.getId());
        model.addAttribute("price", price);
        model.addAttribute("requests", specialService.selectById(product.getRequest()));
        model.addAttribute("configurations", configurationService.selectByIdAndLang(product.getConfiguration(), user.getLanguage()));
        model.addAttribute("seriesName", seriesService.selectById(product.getSeries()));
        List<Certification> certifications = certificationService.selectByCode(product.getCertification());
        if (price != null) {
            model.addAttribute("moneyUnit", new SapRate().getKeyCode().get(price.getUnit()));
        }
        if (certifications.size() > 0) {
            model.addAttribute("certificationName", certificationService.selectByCode(product.getCertification()).get(0));
        }
        Dict dicts = dictService.selectDictByCode("product_type", product.getType(),user.getLanguage());
        if (dicts == null) {
            dicts = new Dict();
        }
        model.addAttribute("productType",dicts);
        Dict dictList = dictService.selectDictByCode("product_status", product.getType(),user.getLanguage());
        if (dictList == null) {
            dictList = new Dict();
        }
        model.addAttribute("productStatus", dictList);
        return "seriesproduct/panel/add";
    }


    /**
     * 返回产品系列树
     *
     * @return 产品系列树
     */
    @GetMapping("/api/series/tree")
    public ResponseEntity series(@RequestUser RequestSubject user, String name) {
        final String lang = user.getLanguage();
        final SeriesQuery query = new SeriesQuery();
        query.setLang(lang);
        query.setName(name);
        final List<ZTreeVO<TreeDataVO>> treeVOS = seriesService.findAllToTree(query);

        List<ZTreeVO<TreeDataVO>> parents = Lists.newArrayList();

//        if (CollectionUtil.isEmpty(treeVOS)) {
//            ErrorModel errorModel = ErrorModel.builder()
//                    .setCode(UserCode.ORGAZITION_LIKE_NAME_IS_EMPTY)
//                    .setMessage("未找到系列").createErrorModel();
//            return ResponseEntity.badRequest().body(errorModel);
//        }
        parents.addAll(treeVOS);
        return ResponseEntity.ok(parents);
    }
    /**
     * 返回产品系列
     * @return 产品系列
     */
    @GetMapping("/api/series/data")
    public ResponseEntity seriesData(@RequestUser RequestSubject user, String name) {
        final String lang = user.getLanguage();
        final SeriesQuery query = new SeriesQuery();
        query.setLang(lang);
        query.setName(name);
        return ResponseEntity.ok(seriesService.findAll(query));
    }

    private List<Map<String, Object>> getYears(SeriesStandard standard) {
        List<Map<String, Object>> years = Lists.newArrayList();
        Map<String, Object> year2 = Maps.newHashMap();
        year2.put("year", 2);
        year2.put("counts", standard.getCountsTwo());
        year2.put("unitType", standard.getCalTypeTwo());
        year2.put("spel", standard.getSpelTwo());
        Map<String, Object> year3 = Maps.newHashMap();
        year3.put("year", 3);
        year3.put("counts", standard.getCountsThree());
        year3.put("unitType", standard.getCalTypeThree());
        year3.put("spel", standard.getSpelThree());
        Map<String, Object> year4 = Maps.newHashMap();
        year4.put("year", 4);
        year4.put("counts", standard.getCountsFour());
        year4.put("unitType", standard.getCalTypeFour());
        year4.put("spel", standard.getSpelFour());
        Map<String, Object> year5 = Maps.newHashMap();
        year5.put("year", 5);
        year5.put("counts", standard.getCountsFive());
        year5.put("unitType", standard.getCalTypeFive());
        year5.put("spel", standard.getSpelFive());
        years.add(year2);
        years.add(year3);
        years.add(year4);
        years.add(year5);
        return years;
    }

    @GetMapping("/viewOfferFile")
    public String viewOfferFile(@RequestParam("fileName")String fileName,
                                Model model){
        LOGGER.debug("view file,fileName:"+fileName);
        String filetType = "";
        if(StringUtils.isNotEmpty(fileName)){
            filetType = fileName.substring(fileName.lastIndexOf(".")+1);
            LOGGER.debug("filetType:"+filetType);
        }
        String viewUrl = this.mediaUrl+"/template/export/"+fileName;
        model.addAttribute("url", viewUrl);
        model.addAttribute("type", filetType);
        String type=filetType.toLowerCase();
        model.addAttribute("show",offices.contains(type)?1:0);
        if(type.equals("doc") || type.equals("docx")){
            model.addAttribute("color","#ffffff");
        }else if(type.equals("ppt") || type.equals("pptx")){
            model.addAttribute("color","#383a3c");
        }else if (type.equals("png") || type.equals("jpg") || type.equals("gif") || type.equals("jpeg")){
            return "/seriesproduct/file/viewImg";
        }
        return "/seriesproduct/file/view";
    }
}
