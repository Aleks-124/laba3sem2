package org.example;

import java.util.HashMap;

public abstract class FileHandler {
    FileHandler nextFileHandler;


    public void setNextMessageSender(FileHandler nextFileHandler) {
        this.nextFileHandler = nextFileHandler;
    }
    public  HashMap<String, Reactor> commonloadReactors(String filePath ){
        HashMap<String, Reactor> reactors = new HashMap<>();
        if (returnTypeStatus(filePath) == true){
            reactors=loadReactors(filePath);
        }
        else{
            reactors = nextFileHandler.commonloadReactors(filePath);
        }
        return reactors;
    };
    public abstract boolean returnTypeStatus(String filePath);
    public abstract HashMap<String, Reactor> loadReactors(String filePath);
}