package com.zhang.myrocketmq.service.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

//发送单项消息
public class OneWayProducer {
        public static void main(String[] args) throws Exception {
            //1.创建消息生产者producer，并制定生产者组名
            DefaultMQProducer producer = new DefaultMQProducer("group1");
            //2.指定Nameserver地址
            producer.setNamesrvAddr("127.0.0.1:9876");
            //3.启动producer
            producer.start();

            for (int i = 0; i < 10; i++) {
                //4.创建消息对象，指定主题Topic、Tag和消息体
            /*
            参数一：消息主题Topic
            参数二：消息Tag
            参数三：消息内容
            */
                Message msg = new Message("test-topic", "Tag3", ("Hello World，单向消息"+i).getBytes());
                //5.发送单项消息
                producer.sendOneway(msg);

                TimeUnit.SECONDS.sleep(1);//休眠1秒
            }
            //6.关闭生产者producer
            producer.shutdown();
        }
    }
