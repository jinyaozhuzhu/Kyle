package com.imd.service;

import com.github.pagehelper.PageInfo;
import com.imd.entity.CareerInfo;
import com.imd.util.MyPageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by jinyao on 2017/4/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CareerInfoServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerInfoService.class);

    @Autowired
    private CareerInfoService careerInfoService;

    @Test
    public void findById() throws Exception {
        CareerInfo careerInfo = careerInfoService.findById(2);
        LOGGER.info("careerInfo = {}" + careerInfo);
    }

    @Test
    public void findList() throws Exception {
        CareerInfo careerInfo = new CareerInfo();
        PageInfo<CareerInfo> pageInfo = careerInfoService.findList(careerInfo, 1, 10);
        LOGGER.info("pageInfo={}" + pageInfo);
    }

    @Test
    public void findByKeyWord() throws Exception {
        String keyWord = "公司";
        MyPageInfo<CareerInfo> pageInfo = careerInfoService.findByKeyWord(keyWord, 2, 10);
        LOGGER.info("pageInfo={}"+pageInfo);
    }

}