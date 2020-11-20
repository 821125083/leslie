package com.leslie.controller;

import com.leslie.cons.Const;
import com.leslie.pojo.Trend;
import com.leslie.service.Cov19Service;
import com.leslie.utils.AlibabaUtils;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Cov19Alibaba")
@CrossOrigin
public class AlibabaCov19CnController {

    @Autowired
    private Cov19Service cov19Service;

    @RequestMapping("Ali")
    public String alibabaTest(){
        return AlibabaUtils.requestAlibabaData();
    }

    @RequestMapping("Cov19TrendLineChart")
    public ResultVO trendCnList(){
        return ResultVO.success(cov19Service.cov19TrendLineChart());
    }
}
