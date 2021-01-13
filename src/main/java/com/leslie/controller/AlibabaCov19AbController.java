package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("AbCov19")
@ResponseBody
@Controller
@CrossOrigin
public class AlibabaCov19AbController {

    public ResultVO initAllData(){

        JSONObject jsonObject = JSON.parseObject(RemoteUtils.getRemoteData(Const.ABURL));

        return ResultVO.success();
    }
}
