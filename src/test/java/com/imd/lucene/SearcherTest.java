package com.imd.lucene;

import com.imd.entity.CareerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * 测试类
 * Created by jinyao on 2017/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearcherTest {

    @Autowired
    private Searcher searcher;

    @Test
    public void dimQuery() throws Exception {
        String keyWord = "天津兴田湿地生态环境科技有限公司";
        List<CareerInfo> CareerInfos = searcher.dimQuery(keyWord);
        System.out.println("CareerInfos" + CareerInfos);
    }

}