package com.jtech.toa.controller.sys.rest;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.jtech.marble.datatables.DataTablesPagination;
import com.jtech.marble.datatables.DataTablesUtils;
import com.jtech.marble.datatables.domain.DataTablesInput;
import com.jtech.marble.datatables.domain.DataTablesOutput;
import com.jtech.marble.error.ErrorModel;
import com.jtech.toa.config.properties.OaProperties;
import com.jtech.toa.constants.ProductCode;
import com.jtech.toa.entity.system.Attachment;
import com.jtech.toa.entity.system.Exhibition;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.system.IAttachmentService;
import com.jtech.toa.service.system.IExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/exhibition/rest")
public class ExhibitionRestController {

    private final IExhibitionService exhibitionService;
    private final IAttachmentService attachmentService;

    @Autowired
    public ExhibitionRestController(IExhibitionService exhibitionService, IAttachmentService attachmentService) {
        this.exhibitionService = exhibitionService;
        this.attachmentService = attachmentService;
    }

    /**
     * 轮播图列表
     *
     * @return 轮播图列表
     */
    @PostMapping("/list")
    public DataTablesOutput<Exhibition> bannerList(
            @Valid @RequestBody DataTablesInput dataTablesInput
    ) {

        DataTablesPagination dataTablesPagination = DataTablesUtils.getPageable(dataTablesInput);
        final Pagination pagination = dataTablesPagination.getPagination();
        final List<Sort.Order> orders = dataTablesPagination.getOrders();
        final Page<Exhibition> requestPage = new Page<>(
                pagination.getCurrent(),
                pagination.getSize(),
                pagination.getOrderByField()
        );

        this.exhibitionService.selectFourList(requestPage);
        return DataTablesUtils.buildOut(dataTablesInput, requestPage);
    }

    @GetMapping("/delete")
    public ResponseEntity delete(Integer id) {
        boolean ok = exhibitionService.deleteById(id);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("系统出错").createErrorModel());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity save(Long attachmentId, String url, Integer orders, Integer type) {
        Attachment attachment = attachmentService.selectById(attachmentId);
        Exhibition exhibition = new Exhibition();
        exhibition.setAttachment(attachmentId);
        exhibition.setUrl(url);
        exhibition.setOrders(orders);
        exhibition.setType(type);
        exhibition.setImg(attachment.getFileName());
        exhibition.setImageUrl(attachmentService.medialUrl(attachment.getFileUrl()));
        boolean ok = exhibitionService.insert(exhibition);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder()
                    .setCode(ProductCode.SYSTEM_INSIDE_ERROR)
                    .setMessage("系统出错").createErrorModel());
        }
        return ResponseEntity.ok().build();
    }
}
