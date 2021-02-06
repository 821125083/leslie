package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper extends BaseMapper<News> {

    /**
     * 只查询 标题、作者名称、出版日期
     * @return
     */
    @Select("select title,author_name,publish_time from L_news order by publish_time desc")
    List<News> queryNotAll();
}
