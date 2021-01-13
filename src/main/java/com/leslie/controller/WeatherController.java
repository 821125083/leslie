package com.leslie.controller;

import com.alibaba.fastjson.JSON;
import com.leslie.cons.Const;
import com.leslie.pojo.Message;
import com.leslie.pojo.Weather;
import com.leslie.service.MessageService;
import com.leslie.utils.RemoteUtils;
import com.leslie.vo.ResultVO;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("weather")
@CrossOrigin
public class WeatherController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RestHighLevelClient client;

    @RequestMapping("initWeatherData")
    public ResultVO initWeatherData(){

        //获得广东的天气
        String remoteData = RemoteUtils.getRemoteData(Const.weatherApi.concat(Const.GUANGZHOUWEATHERCODE.toString()));

        String weather = JSON.parseObject(JSON.parseObject(remoteData).get("data").toString()).get("forecast").toString();

        List<Weather> weathers = JSON.parseArray(weather, Weather.class);

        return ResultVO.success(weathers);
    }

    @RequestMapping("commitMessage")
    public void commitMessage(HttpServletRequest request, @RequestBody Map<String,Object> map){
        String message = map.get("message").toString();
        Message mes = new Message();
        mes.setMessage(message.replace("=",""));
        mes.setCreateDate(new Date());
        messageService.insertMessage(mes);
    }


}
