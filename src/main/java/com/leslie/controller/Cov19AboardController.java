package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leslie.cons.Const;
import com.leslie.pojo.Continent;
import com.leslie.pojo.Country;
import com.leslie.service.Cov19Service;
import com.leslie.utils.RemoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/Cov19Ab")
@CrossOrigin
public class Cov19AboardController {

    @Autowired
    private Cov19Service cov19Service;

    /**
     * 全球疫情
     * @return
     */
    @RequestMapping("realTimeRecord")
    public String getRealTimeRecord(){
        HashMap<String, String> params = new HashMap<>();
        HashMap<String, String> header = new HashMap<>();
        header.put("authoration","apicode");
        header.put("apicode",Const.apiCode);
        //获得相应数据
        String remoteData = RemoteUtils.getRemoteData(Const.ABUrl, params, header);
        JSONObject continent = (JSONObject)JSON.parseObject(remoteData).get("data");

        List<Continent> continents = JSONObject.parseArray(continent.get("continent").toString(), Continent.class);

        for (Continent continent1 : continents) {
            System.out.println(continent1);
        }

        return remoteData;
    }

}
