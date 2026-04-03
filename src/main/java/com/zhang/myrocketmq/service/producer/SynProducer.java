package com.zhang.myrocketmq.service.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import java.util.concurrent.TimeUnit;

public class SynProducer {
    //测试同步消息
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
            Message msg = new Message("test-topic", "Tag1", ("Hello World"+i).getBytes());
            //5.发送消息
            SendResult result = producer.send(msg);
            //发送状态
            SendStatus sendStatus = result.getSendStatus();
            //消息id
            String msgId = result.getMsgId();
            //消息接受队列id
            int queueId = result.getMessageQueue().getQueueId();
            System.out.println("发送状态:"+result+"，消息id:"+msgId+"，消息接受队列id:"+queueId);
            TimeUnit.SECONDS.sleep(1);//休眠1秒
        }
        //6.关闭生产者producer
        producer.shutdown();
    }
}
