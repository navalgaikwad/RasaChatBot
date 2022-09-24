package com.rasa.rasa_test.model;

import java.util.ArrayList;

public class RasaResModel {
    private String recipient_id;
    private String text;
    private String image;
    private String flag;
    private String input;
    private ArrayList<Button> buttons;


    public String getInput() {
        return this.input;
    }


    public void setInput(String input) {
        this.input = input;
    }


    public String getImage() {
        return this.image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getRecipient_id() {
        return this.recipient_id;
    }


    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }


    public ArrayList<Button> getButtons() {
        return this.buttons;
    }


    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }


    public String getText() {
        return this.text;
    }


    public void setText(String text) {
        if (text.contains(";")) {
            String[] split = text.split(";");
            setFlag(split[1].trim());
            setInput(split[2].trim());
            this.text = split[0].trim();
        } else {
            this.text = text;
        }
    }


    public String getFlag() {
        return this.flag;
    }


    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String toString() {
        return "RasaResModel [recipient_id=" + this.recipient_id + ", text=" + this.text + ", image=" + this.image + ", flag=" + this.flag + ", buttons=" + this.buttons + ", input=" + this.input + "]";
    }
}

