package org.example;

import java.util.HashMap;

public abstract class FileHandler {
    FileHandler nextFileHandler;


    public void setNextMessageSender(FileHandler nextFileHandler) {
        this.nextFileHandler = nextFileHandler;
    }
    public abstract HashMap<String, Reactor> loadReactors(String filePath);
}