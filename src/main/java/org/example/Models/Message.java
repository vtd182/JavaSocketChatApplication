package org.example.Models;

import java.io.Serializable;

public class Message implements Serializable {
    private String type;
    private Object payload;
    private String from;
    private String to;

    public Message(String type, Object payload, String from, String to) {
        this.type = type;
        this.payload = payload;
        this.from = from;
        this.to = to;
    }

    public Message(String type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
