package com.leslie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leslie.pojo.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryMapper extends BaseMapper<Diary> {
}
