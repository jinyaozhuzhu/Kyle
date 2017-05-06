package com.imd.dao;

import com.imd.entity.CareerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jinyao on 2017/4/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CareerInfoDaoTest {


    private final static Logger LOGGER = LoggerFactory.getLogger(CareerInfoDaoTest.class);


    @Autowired
    private CareerInfoDao careerInfoDao;

    @Test
    public void findList() throws Exception {
        CareerInfo careerInfo =  new CareerInfo();
//        careerInfo.setId(1);
        careerInfo.setTitle("天津");
        careerInfo.setContent("Content");
        List<CareerInfo> careerInfos = careerInfoDao.findList(careerInfo);
        LOGGER.info("careerInfos={}",careerInfos);
    }

    @Test
    public void findById() throws Exception {
        CareerInfo careerInfo = careerInfoDao.findById(3);
        LOGGER.info("careerInfo={}",careerInfo);
    }

}