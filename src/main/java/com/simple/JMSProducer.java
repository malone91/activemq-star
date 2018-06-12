package com.simple;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 简易的activemq demo
 * Created by Ablert
 * on 2018/6/12.
 * @author Ablert
 */
public class JmsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsProducer.class);

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    /** 发送的次数 **/
    private static final int sendCount = 5;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        //消息目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            //创建连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //提交事务，自动确认
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建消息队列
            destination = session.createQueue("FirstQueue");
            //创建消息发送者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);
            //提交事务
            session.commit();
        } catch (JMSException e) {
            LOGGER.error("send message error", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOGGER.error("close connection error", e);
                }
            }
        }
    }

    /**
     * 发送消息
     * @param session 会话
     * @param messageProducer 消息生产者
     */
    private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException {
        for (int i=0; i<sendCount; i++) {
            TextMessage textMessage = session.createTextMessage("发送消息: " + i);
            System.out.println("发送消息: " + i);
            messageProducer.send(textMessage);
        }
    }
}
