/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.service.impl.product;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.jtech.marble.StringPool;
import com.jtech.marble.util.CollectionUtil;
import com.jtech.marble.vo.ZTreeVO;
import com.jtech.toa.dao.SeriesMapper;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.model.dto.products.AppSeries;
import com.jtech.toa.model.dto.products.SeriesDto;
import com.jtech.toa.model.query.SeriesQuery;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.service.system.IAttachmentService;
import com.jtech.toa.user.model.vo.TreeDataVO;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Service
public class SeriesServiceImpl extends ServiceImpl<SeriesMapper, Series> implements ISeriesService {
    @Autowired
    IAttachmentService attachmentService;
    /**
     * 查找所有的产品系列
     */
    @Override
    public List<SeriesDto> findSeriesList() {
        List<Series> lists = baseMapper.findSeriesList(null);
        Map<Integer, SeriesDto> map = Maps.newHashMap();
        for (Series series : lists) {
            SeriesDto dto = new SeriesDto(series);
            if(!StringUtils.isEmpty(dto.getImage())){
                dto.setImage(attachmentService.medialUrl(dto.getImage()));
            }
            map.put(dto.getId(), dto);
        }

        List<SeriesDto> result = Lists.newArrayList();
        for (SeriesDto dto : map.values()) {
            if (0 != dto.getParent()) {
                map.get(dto.getParent()).getChildren().add(dto);
            } else {
                result.add(dto);
            }
        }
        return result;
    }

    /**
     * 通过产品线查询产品系列
     */
    @Override
    public List<SeriesDto> findSeriesList(Integer line, Integer area) {
        List<Series> lists = baseMapper.findSeriesListByLineAndArea(line, area);
        Map<Integer, SeriesDto> map = Maps.newHashMap();
        for (Series series : lists) {
            SeriesDto dto = new SeriesDto(series);
            if(!StringUtils.isEmpty(dto.getImage())){
                dto.setImage(attachmentService.medialUrl(dto.getImage()));
            }
            map.put(dto.getId(), dto);
        }

        List<SeriesDto> result = Lists.newArrayList();
        for (SeriesDto dto : map.values()) {
            if (0 != dto.getParent()) {
                map.get(dto.getParent()).getChildren().add(dto);
            } else {
                result.add(dto);
            }
        }
        Collections.sort(result);
        return result;
    }
    /**
     * 通过产品线查询产品系列所有数据
     */
    @Override
    public List<SeriesDto> findSeriesList(Integer line  ) {
        List<Series> lists = baseMapper.findSeriesListByLineAll(line);
        Map<Integer, SeriesDto> map = Maps.newHashMap();
        for (Series series : lists) {
            SeriesDto dto = new SeriesDto(series);
            if(!StringUtils.isEmpty(dto.getImage())){
                dto.setImage(attachmentService.medialUrl(dto.getImage()));
            }
            map.put(dto.getId(), dto);
        }

        List<SeriesDto> result = Lists.newArrayList();
        for (SeriesDto dto : map.values()) {
            if (0 != dto.getParent()) {
                map.get(dto.getParent()).getChildren().add(dto);
            } else {
                result.add(dto);
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * 查找所有的产品系列
     */
    @Override
    public List<AppSeries> findApiSeriesList() {
        List<Series> lists = baseMapper.findSeriesList(null);
        Map<Integer, AppSeries> map = Maps.newHashMap();
        for (Series series : lists) {
            AppSeries dto = new AppSeries(series);
            if(!StringUtils.isEmpty(dto.getImage())){
                dto.setImage(attachmentService.medialUrl(dto.getImage()));
            }
            map.put(dto.getId(), dto);
        }

        List<AppSeries> result = Lists.newArrayList();
        Map<Integer,List<AppSeries>> childrens = Maps.newHashMap();
        for (AppSeries dto : map.values()) {
            if (0 != dto.getParent()) {
                if(childrens.get(dto.getParent()) == null){
                    childrens.put(dto.getParent(), Lists.newArrayList(dto));
                }else{
                    childrens.get(dto.getParent()).add(dto);
                }
            } else {
                result.add(dto);
            }
        }
        for (AppSeries dto : map.values()) {
            if(childrens.get(dto.getId()) != null){
                dto.setChildren(JSONObject.toJSONString(childrens.get(dto.getId())));
            }
        }
        return result;
    }

    /**
     * 查找某个产品线下的系列列表
     */
    @Override
    public List<Series> findSeriesList(int line) {
        return baseMapper.findSeriesListByLine(line);
    }

    /**
     * 通过id查找对应的系列
     */
    @Override
    public Series findSeriesById(int series) {
        return baseMapper.findSeriesById(series);
    }

    @Override
    public List<ZTreeVO<TreeDataVO>> findAllToTree(SeriesQuery query) {
        List<Series> seriesList = baseMapper.findAllSeries(query);
        if (CollectionUtil.isEmpty(seriesList)) {
            return Collections.emptyList();
        }
        return listToZtree(seriesList);
    }

    @Override
    public List<Series> findAll(SeriesQuery query) {
        return baseMapper.findAllSeries(query);
    }

    @Override
    public List<Integer> findByParentName(String seriesName) {
        if (Strings.isNullOrEmpty(seriesName)) {
            return Collections.EMPTY_LIST;
        }
        String[] names = seriesName.split(StringPool.SLASH);
        List<Integer> ids = baseMapper.selectAllByParentNames(names);
        return ids;
    }

    @Override
    public void findSeriesByPage(Page<SeriesDto> requestPage, SeriesQuery query) {
        List<SeriesDto> seriesDtos = baseMapper.selectSeriesListByPage(requestPage, query);
        requestPage.setRecords(seriesDtos);
    }

    @Override
    public void selectSeriesImgListByPage(Page<SeriesDto> requestPage, SeriesQuery query) {
        List<SeriesDto> seriesDtos = baseMapper.selectSeriesImgListByPage(requestPage, query);
        requestPage.setRecords(seriesDtos);
    }

    @Override
    public List<Series> getByProductId(Integer productId) {
        return baseMapper.getByProductId(productId);
    }

    @Override
    public List<SeriesDto> findParentSeriesList() {
        List<SeriesDto> list =  baseMapper.findParentSeries();
        for (SeriesDto dto:list) {
            dto.setImage(attachmentService.medialUrl(dto.getImage()));
        }
        return list;
    }

    private static List<ZTreeVO<TreeDataVO>> listToZtree(List<Series> seriesList) {
        final int seriesSize = seriesList.size();
        final List<ZTreeVO<TreeDataVO>> zTreeVOList = Lists
                .newArrayListWithCapacity(seriesSize);
        for (Series series : seriesList) {
            final int parentId = MoreObjects.firstNonNull(series.getParent(), 0);
            TreeDataVO treeDataVO = new TreeDataVO();
            treeDataVO.setId(series.getId());
//            treeDataVO.setCode(series.getCode());
            treeDataVO.setName(series.getName());
            treeDataVO.setParentId(parentId);
            treeDataVO.setEnableProduct(series.getEnableProduct());
            final ZTreeVO<TreeDataVO> zTreeVO = ZTreeVO.<TreeDataVO>builder()
                    .data(treeDataVO)
                    .id("00" + series.getId())
                    .pid(parentId == 0 ? StringPool.ZERO : "00" + parentId)
                    .name(series.getName())
                    .build();
            zTreeVOList.add(zTreeVO);
        }
        return zTreeVOList;
    }
}
