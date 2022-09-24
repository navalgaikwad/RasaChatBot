package com.rasa.rasa_test.model;

public class RasaReqModel {
    private String message;
    private String sender;
    private String flag;
    private String input;


    public String getInput() {
        return this.input;
    }


    public void setInput(String input) {
        this.input = input;
    }


    public String getMessage() {
        return this.message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getSender() {
        return this.sender;
    }


    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getFlag() {
        return this.flag;
    }


    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String toString() {
        return "RasaReqModel [message=" + this.message + ", sender=" + this.sender + ", flag=" + this.flag + ", input=" + this.input + "]";
    }
}
