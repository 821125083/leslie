package com.leslie.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.NewsMapper;
import com.leslie.pojo.News;
import com.leslie.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.query.QuerySearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private RestHighLevelClient esClient;



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

    @Override
    public List<News> queryNewsByPage(Integer pageNum, Integer pageSize, String searchText) {
        List<News> list = new ArrayList<>();

        SearchRequest request = new SearchRequest("news");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        builder.size(pageSize);
        builder.from(pageNum);
        if (StringUtils.isNotBlank(searchText)){
            // 根据 title 条件查询
            builder.query(new MatchQueryBuilder("title",searchText));
//            builder.query(QueryBuilders.termQuery("title", searchText));
        }else {
            // 没有查询条件
            builder.query(QueryBuilders.matchAllQuery());
        }
        request.source(builder);
        SearchResponse response = null;
        try {
            // 从 elasticSearch 查找数据
             response = esClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                list.add(JSON.parseObject(hit.getSourceAsString(), News.class));
            }
        } catch (IOException e) {
            // elasticSearch 没连接上 呵呵 从数据库中查找
            QueryWrapper<News> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("publish_time");
            e.printStackTrace();
            list = newsMapper.selectList(wrapper);
        }
        return list;
    }

    @Override
    public List<News> queryNotAll() {
        return newsMapper.queryNotAll();
    }
}
