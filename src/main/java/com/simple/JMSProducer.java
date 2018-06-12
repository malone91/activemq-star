package com.simple;

import org.apache.activemq.ActiveMQConnection;

import javax.jms.*;

/**
 * 简易的activemq demo
 * Created by Ablert
 * on 2018/6/12.
 * @author Ablert
 */
public class JMSProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    /** 发送的次数 **/
    private static final int sendCount = 5;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        //消息目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
    }
}
