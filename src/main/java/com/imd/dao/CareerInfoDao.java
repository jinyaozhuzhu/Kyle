package com.imd.dao;

import com.imd.entity.CareerInfo;

import java.util.List;

/**
 *
 * Created by jinyao on 2017/4/15.
 */
public interface CareerInfoDao{

    List<CareerInfo> findList(CareerInfo careerInfo);

    CareerInfo findById(Integer id);

    List<CareerInfo> findAllList();
}
