package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leslie.cons.Const;
import com.leslie.pojo.News;
import com.leslie.service.NewsService;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.ResultVO;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("news")
@CrossOrigin
public class NewsController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private NewsService newsService;

    @RequestMapping("todayNews")
    @ResponseBody
    public ResultVO news(){
        String remoteData = RemoteUtils.getRemoteData(Const.newsUrl);
        HashMap<String, Object> result = new HashMap<>();
        System.out.println(remoteData);
        List<News> newsList = JSONArray.parseArray(remoteData.substring(20, remoteData.length() - 1), News.class);
        newsService.insertNews(newsList);
        result.put("list", newsList);
        return ResultVO.success(result);
    }

    @RequestMapping("queryNewsByDate/{date}")
    @ResponseBody
    public ResultVO queryNewsByDate(@PathVariable Date date){
        HashMap<String, Object> hashMap = new HashMap<>();
        //

        //
        return ResultVO.success(hashMap);
    }

    @RequestMapping("queryAllNews")
    @ResponseBody
    public ResultVO queryAllNews(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("newsList", newsService.queryAll());
        return ResultVO.success(result);
    }
}
