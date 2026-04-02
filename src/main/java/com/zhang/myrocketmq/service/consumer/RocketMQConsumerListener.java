package com.zhang.myrocketmq.service.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "test-topic",
        consumerGroup = "my-test-consumer-group"
)

public class RocketMQConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("📥 收到消息！内容：" + message);
    }


}
