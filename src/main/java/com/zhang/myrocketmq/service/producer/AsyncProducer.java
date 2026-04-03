package com.zhang.myrocketmq.service.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

//测试同步消息
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
    //1.创建消息生产者producer，并制定生产者组名
    DefaultMQProducer producer = new DefaultMQProducer("group1");
    //2.指定Nameserver地址
    producer.setNamesrvAddr("127.0.0.1:9876");
    //3.启动producer
    producer.start();

    for (int i = 0; i <= 10; i++) {
        //4.创建消息对象，指定主题Topic、Tag和消息/参数一：消息主题Topi参数二：消息Ta参数三：消息内*/
        Message msg = new Message("test-topic", "Tag2", ("Hello World"+i).getBytes());
        //5.发送异步消息
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送结果:"+sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送异常:"+throwable);
            }
        });

        TimeUnit.SECONDS.sleep(1);//休眠1秒
    }
    //6.关闭生产者producer
    producer.shutdown();
}
    }



