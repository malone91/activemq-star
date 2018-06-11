package com.melo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * 消息消费者
 * Created by Ablert
 * on 2018/6/11.
 * @author Ablert
 */
public class Consumer implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private JmsTemplate jmsTemplate;

    /**
     * 实现接口方法
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String request = textMessage.getText();
                LOGGER.info("消费者获取到信息是：" + request);
                Destination destination = textMessage.getJMSReplyTo();
                String jmsCorrelationID = textMessage.getJMSCorrelationID();
                jmsTemplate.send(destination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        Message msg = session.createTextMessage(request + "的应答!");
                        msg.setJMSCorrelationID(jmsCorrelationID);
                        return msg;
                    }
                });
            } catch (JMSException e) {
                LOGGER.error("接收信息出错", e);
            }
        }
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
