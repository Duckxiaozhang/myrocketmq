package com.zhang.myrocketmq.service.producer;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RocketMQProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送普通同步消息
     * @param topic 消息主题
     * @param message 消息内容
     */
    public void sendMessage(String topic, String message) {
        rocketMQTemplate.syncSend(topic, message);
        System.out.println("✅ 消息发送成功！内容：" + message);
    }
    /**
     * 发送延迟消息（新增方法）
     * @param topic 消息主题
     * @param message 消息内容
     * @param delayLevel 延迟等级（1~18，开源版固定等级）
     *                   示例：3=10秒后投递，5=1分钟后投递
     */
    public void sendDelayMessage(String topic, String message, int delayLevel) {
        // 1. 构建 RocketMQ 原生 Message 对象
        Message msg = new Message(
                topic,          // Topic
                "*",            // Tag（* 表示无Tag，可自定义）
                message.getBytes() // 消息内容（转字节数组）
        );

        // 2. 核心：设置延迟等级（必须是1~18）
        msg.setDelayTimeLevel(delayLevel);

        // 3. 发送延迟消息
        rocketMQTemplate.syncSend(topic, msg);
        System.out.println("⏰ 延迟消息发送成功！内容：" + message + "，延迟等级：" + delayLevel);
    }
}