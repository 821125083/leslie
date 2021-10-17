package com.leslie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leslie.mapper.DiaryMapper;
import com.leslie.pojo.Diary;
import com.leslie.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public Integer addNewDiary(Diary diary) {
        diary.setCreateDate(new Date());
        return diaryMapper.insert(diary);
    }

    @Override
    public List<Diary> queryDiary(Integer pageSize, Integer pageNum) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_date");

        return diaryMapper.selectList(queryWrapper);
    }

}
