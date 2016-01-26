package com.ghasedak.api.entity;

/**
 * Created by gladiator on 1/24/16.
 */
public class SmsMessage {
    private Long messageId;
    private String senderNumber;
    private String recipientNumber;
    private String message;
    private int messageStatus;  // 1 = waiting, 2 = delivered, 3 = NOT delivered, etc = error codes;
    private int realMessageStatus;  // 1 = delivered to client, 2 = NOT delivered to client, 8 = delivered to carrier,
                                    //16 = NOT delivered to carrier, 0 = message is in Queue, -1 = message id is wrong;
    private int mClass;
    private long chkMessageId;
    private int encoding;
    private String udh;

    public SmsMessage(){}

    public SmsMessage(String senderNumber, String recipientNumber, String message){
        this.senderNumber = senderNumber;
        this.recipientNumber = recipientNumber;
        this.message = message;
    }

    public String getUdh() {
        return udh;
    }

    public void setUdh(String udh) {
        this.udh = udh;
    }

    public int getEncoding() {
        return encoding;
    }

    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }

    public long getChkMessageId() {
        return chkMessageId;
    }

    public void setChkMessageId(long chkMessageId) {
        this.chkMessageId = chkMessageId;
    }

    public int getmClass() {
        return mClass;
    }

    public void setmClass(int mClass) {
        this.mClass = mClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public int getRealMessageStatus() {
        return realMessageStatus;
    }

    public void setRealMessageStatus(int realMessageStatus) {
        this.realMessageStatus = realMessageStatus;
    }
}
