package com.example.administrator.rxjavaretrofitlambdademo.entity;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class MessageEntity
{
    public int what;//标识
    public Object obj;//消息
    public MessageEntity next;//指向下一个MessagePojo对象，类似于链表的指针
    public static MessageEntity mPool;//消息池
    public static int count;

    public static MessageEntity obtianMessage() {
        if (mPool != null) {
            MessageEntity msg = mPool;
            mPool = msg.next;
            count--;
            return msg;
        }
        return new MessageEntity();
    }
}
