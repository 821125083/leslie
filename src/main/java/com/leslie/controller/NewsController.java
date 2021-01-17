package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.leslie.cons.Const;
import com.leslie.mapper.NewsMapper;
import com.leslie.pojo.News;
import com.leslie.service.NewsService;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.NewsQueryVo;
import com.leslie.vo.ResultVO;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("news")
@CrossOrigin
public class NewsController {



    @Autowired
    private NewsService newsService;

    @Autowired
    private RestHighLevelClient esClient;

    /**
     * 将最新消息写进数据库
     * @return
     */
    @RequestMapping("todayNews")
    @ResponseBody
    public ResultVO news(){
        HashMap<String, Object> result = new HashMap<>();
        List<News> newsList = this.queryRecentNews();
        newsService.insertNews(newsList);
        // 保存近数据库
        result.put("list", newsList);
        // 写进es

        return ResultVO.success(result);
    }

    private List<News> queryRecentNews(){
        String remoteData = RemoteUtils.getRemoteData(Const.newsUrl);
        List<News> newsList = JSONArray.parseArray(remoteData.substring(20, remoteData.length() - 1), News.class);
        return newsList;
    }



    @RequestMapping("queryNewsByDate/{date}")
    @ResponseBody
    public ResultVO queryNewsByDate(@PathVariable Date date){
        HashMap<String, Object> hashMap = new HashMap<>();
        // TODO
        System.out.println(esClient);
        return ResultVO.success(hashMap);
    }

    /**
     *
     * @param newsQueryVo 搜索的关键字
     * @return 结果集
     */
    @RequestMapping("queryNewsByPage")
    @ResponseBody
    public ResultVO queryNewsByPage(@RequestBody NewsQueryVo newsQueryVo){
        System.out.println(newsQueryVo);
        HashMap<String, Object> result = new HashMap<>();
        if (newsQueryVo.getPageNum() != null && newsQueryVo.getPageNum() <= 0){
            newsQueryVo.setPageNum(1);
        }

        if (newsQueryVo.getPageSize() != null && newsQueryVo.getPageSize() <= 0){
            newsQueryVo.setPageSize(Const.DEFAULT_PAGE_SIZE);
        }

        List<News> news = newsService.queryNewsByPage(newsQueryVo.getPageNum(), newsQueryVo.getPageSize(), newsQueryVo.getSearchText());
        result.put("newsList", news);
        return ResultVO.success(result);
    }

    /**
     *
     * @return 向 ES 批量添加 数据
     * @throws Exception
     */
    @RequestMapping("test")
    @ResponseBody
    public ResultVO testNews() throws Exception{
        List<News> newsList = newsService.queryAll();
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(new TimeValue(2, TimeUnit.SECONDS));
        for (News news : newsList) {
            bulkRequest.add(new IndexRequest("news").source(JSON.toJSONString(news), XContentType.JSON));
        }
        esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return ResultVO.success();
    }

}
