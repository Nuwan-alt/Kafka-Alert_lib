package com.dev.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AltertMessage {
    private String level = "";
    private String component = "";
    private String version = "";
    @Id
    private String ip = "";
    private String type = "";
    private String messageText = "";
    private String stacktrace = "";

    public AltertMessage() {
    }

    public  AltertMessage (String level, String component, String version, String ip, String type, String messageText, String stacktrace){
        this.setLevel(level);
        this.setComponent(component);
        this.setVersion(version);
        this.setIp(ip);
        this.setType(type);
        this.setMessageText(messageText);
        this.setStacktrace(stacktrace);

        System.out.println();



    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
