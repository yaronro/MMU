package com.hit.model.messages;

public class ShowInfoDialogModelMessage implements ModelMessage {

    private final String infoMessage;

    public ShowInfoDialogModelMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public String getInfoMessage() {
        return infoMessage;
    }
}
