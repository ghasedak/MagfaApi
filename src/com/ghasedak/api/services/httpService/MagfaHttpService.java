package com.ghasedak.api.services.httpService;

import com.ghasedak.api.configuration.Config;
import com.ghasedak.api.entity.Credit;
import com.ghasedak.api.entity.SmsMessage;
import com.ghasedak.api.assets.ErrorCodes;
import com.ghasedak.api.exceptions.MagfaHttpServiceException;
import com.ghasedak.api.services.httpService.common.HttpRequestHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by gladiator on 1/24/16.
 */
public class MagfaHttpService {
    private final static String SEND_MESSAGE_METHOD_CALL = "enqueue";
    private final static String GET_CREDIT_METHOD_CALL = "getCredit";
    private final static String GET_MESSAGE_STATUS_METHOD_CALL = "getMessageStatus";
    private final static String GET_MESSAGE_ID_METHOD_CALL = "getMessageId";
    private final static String GET_REAL_MESSAGE_STATUS_METHOD_CALL = "getRealMessageStatus";

    private SmsMessage smsMessage;
    private Credit credit;

    public MagfaHttpService() {}

    public MagfaHttpService(SmsMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    public void sendMessage() throws IOException {

        Long response = Long.parseLong(HttpRequestHandler.sendUrl(generateSendMessageUrl()));

        if (response <= ErrorCodes.MAX_VALUE) {
            System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response.intValue()));
            throw new MagfaHttpServiceException(response.intValue());
        } else {
            System.out.println("Submitted successfully, messageId: " + response);
            this.smsMessage.setMessageId(response);
        }
    }

    private String generateSendMessageUrl() throws UnsupportedEncodingException {
        StringBuilder stringBuilderUrl = new StringBuilder(Config.getHttpEndPointUrl());
        stringBuilderUrl.append("domain=").append(Config.getDomain()).append("&");
        stringBuilderUrl.append("service=").append(SEND_MESSAGE_METHOD_CALL).append("&");
        stringBuilderUrl.append("username=").append(Config.getUsername()).append("&");
        stringBuilderUrl.append("password=").append(Config.getPassword()).append("&");

        stringBuilderUrl.append("from=").append(this.smsMessage.getSenderNumber()).append("&");
        stringBuilderUrl.append("to=").append(this.smsMessage.getRecipientNumber()).append("&");
        stringBuilderUrl.append("message=").append(URLEncoder.encode(this.smsMessage.getMessage(), "ISO-8859-1")).append("&");
        if (this.smsMessage.getUdh() != null) {
            stringBuilderUrl.append("udh=").append(this.smsMessage.getUdh()).append("&");
        }
        stringBuilderUrl.append("coding=").append(this.smsMessage.getEncoding()).append("&");
        stringBuilderUrl.append("chkmessageid=").append(this.smsMessage.getChkMessageId());

        return stringBuilderUrl.toString();
    }

    public void sendGetCreditRequest() throws IOException {
        Double response = Double.valueOf(HttpRequestHandler.sendUrl(generateGetCreditUrl()));

        if (response <= ErrorCodes.MAX_VALUE) {
            System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response.intValue()));
            throw new MagfaHttpServiceException(response.intValue());
        } else {
            this.credit.setCreditValue(Double.valueOf(response));
        }
    }

    private String generateGetCreditUrl() {
        StringBuilder sb = new StringBuilder(Config.getHttpEndPointUrl());
        sb.append("domain=").append(Config.getDomain()).append("&");
        sb.append("service=").append(GET_CREDIT_METHOD_CALL).append("&");
        sb.append("username=").append(Config.getUsername()).append("&");
        sb.append("password=").append(Config.getPassword());

        return sb.toString();
    }

    @Deprecated
    public void sendGetMessageStatus() throws IOException {
        int response = Integer.parseInt(HttpRequestHandler.sendUrl(generateGetMessageStatusUrl()));

        if (response > 3) {
            System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response));
            throw new MagfaHttpServiceException(response);
        } else {
            this.smsMessage.setMessageStatus(response);
        }
    }

    private String generateGetMessageStatusUrl(){
        StringBuilder sb = new StringBuilder(Config.getHttpEndPointUrl());
        sb.append("service=").append(GET_MESSAGE_STATUS_METHOD_CALL).append("&");

        if (this.smsMessage.getMessageId() != 0){
            sb.append("messageId=").append(this.smsMessage.getMessageId());
        }else {
            sb.append("messageId=").append(this.smsMessage.getChkMessageId());
        }
        return sb.toString();
    }

    public void sendGetMessageIdRequest() throws IOException {
        int response = Integer.parseInt(HttpRequestHandler.sendUrl(generateGetMessageIdUrl()));

        if (response <= ErrorCodes.MAX_VALUE ) {
            System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response));
            throw new MagfaHttpServiceException(response);
        } else {
            this.smsMessage.setMessageId((long) response);
        }
    }

    private String generateGetMessageIdUrl(){
        StringBuilder sb = new StringBuilder(Config.getHttpEndPointUrl());
        sb.append("service=").append(GET_MESSAGE_ID_METHOD_CALL).append("&");
        sb.append("domain=").append(Config.getDomain()).append("&");
        sb.append("service=").append(GET_CREDIT_METHOD_CALL).append("&");
        sb.append("username=").append(Config.getUsername()).append("&");
        sb.append("password=").append(Config.getPassword()).append("&");
        sb.append("chkMessageId=").append(this.smsMessage.getChkMessageId());
        return sb.toString();
    }

    public void sendGetRealMessageStatus() throws IOException {
        int response = Integer.parseInt(HttpRequestHandler.sendUrl(generateGetRealMessageStatusUrl()));

        //TODO: set error handler
        /*if (response > 3) {
            System.out.println("error occurred, code: " + response + ", " + ErrorCodes.getDescriptionForCode(response));
            throw new MagfaHttpServiceException(response);
        } else {*/
            this.smsMessage.setMessageStatus(response);
//        }
    }

    private String generateGetRealMessageStatusUrl(){
        StringBuilder sb = new StringBuilder(Config.getHttpEndPointUrl());
        sb.append("service=").append(GET_REAL_MESSAGE_STATUS_METHOD_CALL).append("&");

        if (this.smsMessage.getMessageId() != 0){
            sb.append("messageId=").append(this.smsMessage.getMessageId());
        }else {
            sb.append("messageId=").append(this.smsMessage.getChkMessageId());
        }
        return sb.toString();
    }

    public SmsMessage getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(SmsMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
}
