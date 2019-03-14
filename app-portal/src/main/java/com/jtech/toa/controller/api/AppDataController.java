package com.jtech.toa.controller.api;

import com.jtech.toa.auth.handler.annotation.RequestUser;
import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.model.app.dto.AddDbDataDto;
import com.jtech.toa.service.AppInitDbService;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.file.IFileMarketDetailService;
import com.jtech.toa.service.file.IFilePackageService;
import com.jtech.toa.service.file.IFileSeriesService;
import com.jtech.toa.service.file.IFileService;
import com.jtech.toa.service.offer.IOfferService;
import com.jtech.toa.service.product.IPriceService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.product.ISpareService;
import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.user.service.IDepartmentService;

import com.jtech.toa.user.service.IDepartmentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> </p>
 *
 * @author JiTong
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/data")
public class AppDataController {
    private final ICustomerService customerService;
    private final IDictService dictService;
    private final ISysExchangeService exchangeService;
    private final IProductService productService;
    private final ISpareService spareService;
    private final IPriceService priceService;
    private final IStandardService standardService;
    private final IDepartmentService departmentService;
    private final ISeriesService seriesService;
    private final IFileSeriesService fileSeriesService;
    private final IFilePackageService filePackageService;
    private final IFileMarketDetailService fileMarketDetailService;
    private final IOfferService offerService;
    private final IFileService fileService;
    private final IDepartmentUserService departmentUserService;

    public AppDataController(ICustomerService customerService,
                             IDictService dictService,
                             ISysExchangeService exchangeService,
                             IProductService productService,
                             ISpareService spareService,
                             IPriceService priceService,
                             IStandardService standardService,
                             IDepartmentService departmentService,
                             ISeriesService seriesService,
                             IFileSeriesService fileSeriesService,
                             IFilePackageService filePackageService,
                             IFileMarketDetailService fileMarketDetailService,
                             IOfferService offerService,
                             IFileService fileService, IDepartmentUserService departmentUserService){
        this.customerService = customerService;
        this.dictService = dictService;
        this.exchangeService = exchangeService;
        this.productService = productService;
        this.spareService = spareService;
        this.priceService = priceService;
        this.standardService = standardService;
        this.departmentService = departmentService;
        this.seriesService = seriesService;
        this.fileSeriesService = fileSeriesService;
        this.filePackageService = filePackageService;
        this.fileMarketDetailService = fileMarketDetailService;
        this.offerService = offerService;
        this.fileService = fileService;
        this.departmentUserService = departmentUserService;
    }

    @GetMapping("/init/db")
    public ResponseEntity initDB(@RequestUser RequestSubject user){
        AppInitDbService service = new AppInitDbService(customerService, dictService, exchangeService,
                productService, spareService,priceService,standardService,departmentService,
                seriesService,fileSeriesService,filePackageService,fileMarketDetailService,
                offerService,fileService, departmentUserService);

        List list = service.createSQLiteDb(user);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add/db")
    public ResponseEntity addDB(@RequestUser RequestSubject user,@RequestBody AddDbDataDto addDbDataDto){
        AppInitDbService service = new AppInitDbService(customerService, dictService, exchangeService,
                productService, spareService,priceService,standardService,departmentService,
                seriesService,fileSeriesService,filePackageService,fileMarketDetailService,offerService,fileService, departmentUserService);

        return ResponseEntity.ok(service.addSQLiteDb(user, addDbDataDto));
    }
}
