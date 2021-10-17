package com.leslie.service;

import com.leslie.pojo.Diary;

import java.util.List;

public interface DiaryService {
    Integer addNewDiary(Diary diary);

    List<Diary> queryDiary(Integer pageSize, Integer pageNum);
}
