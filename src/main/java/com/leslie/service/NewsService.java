package com.leslie.service;

import com.leslie.pojo.News;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NewsService {
    void insertNews(List<News> newsList);

    List<News> queryAll();

    List<News> queryNewsByPage(Integer pageNum, Integer pageSize, String searchText);
}
