package com.imd.controller;

import com.github.pagehelper.PageInfo;
import com.imd.entity.CareerInfo;
import com.imd.service.CareerInfoService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by jinyao on 2017/4/15.
 */
@Controller
@RequestMapping("/career")
public class CareerInfoController {

    @Autowired
    private CareerInfoService careerInfoService;

    @GetMapping("/findById/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        CareerInfo career = careerInfoService.findById(id);
        model.addAttribute("career", career);
        return "detail";
    }

    @GetMapping(value = {"/findList", ""})
    public String findList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize,
                           CareerInfo careerInfo,
                           Model model
    ) {
        PageInfo<CareerInfo> page = careerInfoService.findList(careerInfo, pageNum, pageSize);
        String school = careerInfo.getSchool();
        model.addAttribute("school", school);
        model.addAttribute("page", page);
        return "index";
    }

    @RequestMapping("/findByKeyWord")
    public String findByKeyWord(@RequestParam(value = "keyWord") String keyWord,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize,
                                Model model
    ) throws IOException, ParseException {
        PageInfo<CareerInfo> page = careerInfoService.findByKeyWord(keyWord, pageNum, pageSize);
        model.addAttribute("keyWord", keyWord);
        model.addAttribute("page", page);
        return "result";
    }
}
