/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.controller.product.rest;

import com.jtech.toa.service.product.IStandardService;
import com.jtech.toa.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * @author BOGON
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("/api/spare/count")
public class SpareCountRestController {
    private final IUserService userService;
    private final IStandardService standardService;


    @Autowired
    public SpareCountRestController(IUserService userService,
                                    IStandardService standardService) {
        this.userService = userService;
        this.standardService = standardService;
    }

    @PostMapping("/save")
    public ResponseEntity save(Integer productId, String standardIds, Integer type) {
        boolean isOk = standardService.addStandard(standardIds, productId, type);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/year/save")
    public ResponseEntity yearSave(String yearsJsonStr, Integer standardId,Integer auto) {
        boolean isOk = standardService.saveByStandard(standardId, yearsJsonStr,auto);
        if (isOk) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }
}
