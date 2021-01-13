package com.leslie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.NewsMapper;
import com.leslie.pojo.News;
import com.leslie.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void insertNews(List<News> newsList) {
        for (News news : newsList) {
            newsMapper.insert(news);
        }
    }

    @Override
    public List<News> queryAll() {

        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("publish_time");
        return newsMapper.selectList(queryWrapper);
    }
}
