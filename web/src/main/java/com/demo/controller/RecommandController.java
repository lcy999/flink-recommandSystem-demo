package com.demo.controller;

import com.demo.dto.ProductDto;
import com.demo.service.KafkaService;
import com.demo.service.RecommandService;
import com.demo.util.Result;
import com.demo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class RecommandController {

    @Autowired
    RecommandService recommandService;

    @Autowired
    KafkaService kafkaService;




    /**
     * 返回推荐页面
     * @param userId
     * @return
     * @throws IOException
     */
    @GetMapping("/recommand")
    public String recommandByUserId(@RequestParam("userId") String userId,
                                    Model model) throws IOException {


        recommandService.queryModelInfo(userId, model);



        return "user";
    }

    @GetMapping("/log")
    @ResponseBody
    public Result logToKafka(@RequestParam("id") String userId,
                             @RequestParam("prod") String productId,
                             @RequestParam("action") String action){

        String log = kafkaService.makeLog(userId, productId, action);
        kafkaService.send(null, log);
        return ResultUtils.success();
    }

}
