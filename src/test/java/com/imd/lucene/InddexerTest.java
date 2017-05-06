package com.imd.lucene;

import com.imd.entity.CareerInfo;
import com.imd.service.CareerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 * Created by jinyao on 2017/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InddexerTest {

    @Autowired
    private CareerInfoService careerInfoService;

    @Autowired
    private Indexer indexer;

    @Test
    public void indexWriter() throws Exception {
        List<CareerInfo> careerInfos = careerInfoService.findAllList();
        indexer.indexWriter(careerInfos);
    }

}