package com.hit.model.messages;

public class SetTextInfoModelMessage implements ModelMessage {

    private final String textInfo;

    public SetTextInfoModelMessage(String textInfo) {
        this.textInfo = textInfo;
    }

    public String getTextInfo() {
        return textInfo;
    }

}
