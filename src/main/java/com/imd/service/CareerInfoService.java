package com.imd.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imd.dao.CareerInfoDao;
import com.imd.entity.CareerInfo;
import com.imd.lucene.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by jinyao on 2017/4/15.
 */
@Service
public class CareerInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CareerInfoService.class);

    @Autowired
    private CareerInfoDao careerInfoDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Searcher searcher;

    /**
     * 根据id查询，用redis作缓存
     *
     * @param id
     * @return
     */
    public CareerInfo findById(Integer id) {
        String key = "Career_" + id;
        CareerInfo careerInfo;
        ValueOperations<String, CareerInfo> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            careerInfo = valueOperations.get(key);
            LOGGER.info("from Cache");
            return careerInfo;
        }
        careerInfo = careerInfoDao.findById(id);
        valueOperations.set(key, careerInfo, 100, TimeUnit.SECONDS);
        LOGGER.info("from database");
        return careerInfo;
    }

    /**
     * 查出所有数据，用于简历索引
     *
     * @return
     */
    public List<CareerInfo> findAllList() {
        return careerInfoDao.findAllList();
    }

    /**
     * 条件查询，从数据库中查询
     *
     * @param careerInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CareerInfo> findList(CareerInfo careerInfo,
                                         Integer pageNum,
                                         Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CareerInfo> careerInfos = careerInfoDao.findList(careerInfo);
        return new PageInfo<>(careerInfos);
    }

    /**
     * 根据关键字查询,并使用redis做缓存
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CareerInfo> findByKeyWord(String keyWord, Integer pageNum,
                                              Integer pageSize) throws IOException, ParseException {
        ValueOperations<String, List<String>> valueOperations = redisTemplate.opsForValue();
        List<String> ids;
        if (redisTemplate.hasKey(keyWord)) {
            ids = valueOperations.get(keyWord);
            LOGGER.info("from Cache {}",ids);
        } else {
            ids= searcher.dimQuery(keyWord);
            valueOperations.set(keyWord, ids, 2, TimeUnit.HOURS);
            LOGGER.info("from database {}",ids);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CareerInfo> careerInfos = careerInfoDao.findByIds(ids);
        return new PageInfo<>(careerInfos);
    }
}
