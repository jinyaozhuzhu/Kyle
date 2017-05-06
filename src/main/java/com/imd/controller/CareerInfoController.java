package com.imd.controller;

import com.github.pagehelper.PageInfo;
import com.imd.entity.CareerInfo;
import com.imd.service.CareerInfoService;
import com.imd.util.MyPageInfo;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 * Created by jinyao on 2017/4/15.
 */
@RestController
@RequestMapping("/career")
public class CareerInfoController {

    @Autowired
    private CareerInfoService careerInfoService;

    @GetMapping("/findById/{id}")
    public CareerInfo findById(@PathVariable Integer id) {
        return careerInfoService.findById(id);
    }

    @GetMapping("/findList")
    public PageInfo<CareerInfo> findList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageNum", defaultValue = "10") Integer pageSize
    ) {
        return careerInfoService.findList(new CareerInfo(), pageNum, pageSize);
    }

    @GetMapping("/findByKeyWord")
    public MyPageInfo<CareerInfo> findByKeyWord(@RequestParam(value = "keyWord") String keyWord,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) throws IOException, ParseException {
        return careerInfoService.findByKeyWord(keyWord, pageNum, pageSize);
    }
}
