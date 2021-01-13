package com.leslie.service;

import com.leslie.pojo.News;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NewsService {
    void insertNews(List<News> newsList);

    List<News> queryAll();
}
