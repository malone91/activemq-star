package com.melo.message;

import javax.jms.Message;
import java.util.concurrent.Semaphore;

/**
 * 应答报文
 * Created by Ablert
 * on 2018/6/11.
 * @author Ablert
 */
public class ReplyMessage {

    private Semaphore semaphore = new Semaphore(0);

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}
