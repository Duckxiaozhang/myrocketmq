package com.zhang.myrocketmq.controller;

import com.zhang.myrocketmq.service.producer.RocketMQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RocketMQProducerService producerService;

    @GetMapping("/send")
    public String sendNormalMessage() {
        producerService.sendMessage("test-topic", "这是一条普通消息");
        return "普通消息发送成功";
    }

    // 新增延迟消息测试接口（延迟10秒，等级3）
    @GetMapping("/send/delay")
    public String sendDelayMessage() {
        // 等级3 = 10秒后投递，可根据需求修改等级
        producerService.sendDelayMessage("test-topic", "这是一条延迟10秒的消息", 3);
        return "延迟消息发送成功，10秒后消费者会收到";
    }
}