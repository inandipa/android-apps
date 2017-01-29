package com.example.indra.messageme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.firebase.client.core.ServerValues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by indra on 6/27/16.
 */
public class MessageDetails implements Serializable {

    String sender,message,key,senderid;
    private Long creationDate;

    boolean isRead;

    public MessageDetails() {

    }

    @Override
    public String toString() {
        return "MessageDetails{" +
                "isRead=" + isRead +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", key='" + key + '\'' +
                '}';
    }



    public java.util.Map<String, String> getCreationDate() {
        return ServerValue.TIMESTAMP;
    }

    @JsonIgnore
    public Long getCreationDateLong() {
        return creationDate;
    }
    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }


    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
