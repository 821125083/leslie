package com.leslie.controller;

import com.leslie.pojo.Diary;
import com.leslie.service.DiaryService;
import com.leslie.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("diary")
@ResponseBody
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @PostMapping("submitDiary")
    @CrossOrigin
    public ResultVO submitDiary(@RequestBody Diary diary){
        Assert.hasText(diary.getTitle(), "title不能为空！");
        Assert.hasText(diary.getContext(), "context不能为空！");
        diaryService.addNewDiary(diary);

        return ResultVO.success();
    }

    @GetMapping("queryDiary/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultVO queryDiary(@PathVariable("pageSize") Integer pageSize,@PathVariable("pageNum") Integer pageNum){
        if (pageNum == null || pageNum == 0){
            pageNum = 1;
        }
        return ResultVO.success(diaryService.queryDiary(pageSize,pageNum));
    }

}
