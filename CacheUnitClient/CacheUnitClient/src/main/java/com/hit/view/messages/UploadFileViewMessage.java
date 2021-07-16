package com.hit.view.messages;

import java.io.File;

public class UploadFileViewMessage implements ViewMessage {
    private File file;
    public UploadFileViewMessage(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
