package com.scaler.myspringproject.models;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mail{
    String fromMail;
    String toMail;
    String mailSubject;
    String header;
    String mailBody;

    public Mail () {
        System.out.println("emptyy");
    }
    public Mail (String s) {
        System.out.println("Single" + s);
    }
    public Mail(String toMail, String mailSubject, String mailBody){
        this.toMail = toMail;
        this.mailSubject = mailSubject;
        this.mailBody = mailBody;
        System.out.println("Multiple" + toMail);
    };

    public void setFromMail(String fromMail){
        this.fromMail = fromMail;
    }

    public String getFromMail(){
        return this.fromMail;
    }

    public void setToMail(String toMail){
        this.toMail = toMail;
    }

    public String getToMail(){
        return this.toMail;
    }

    public void setMailSubject(String mailSubject){
        this.mailSubject = mailSubject;
    }

    public String getMailSubject(){
        return this.mailSubject;
    }

    public void setHeader(String header){
        this.header = header;
    }

    public String getHeader(){
        return this.header;
    }

    public void setMailBody(String mailBody){
        this.mailBody = mailBody;
    }

    public String getMailBody(){
        return this.mailBody;
    }
}