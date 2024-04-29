package org.example;

import java.util.HashMap;

public class ReadCommonClass {
    public  HashMap<String, Reactor> readCommonClass(String filePath){
        LoadReatorsJson loadReatorsJson = new LoadReatorsJson();
        LoadReactorsXml loadReactorsXml = new LoadReactorsXml();
        LoadReactorsYaml loadReactorsYaml= new LoadReactorsYaml();
        loadReactorsYaml.setNextMessageSender(loadReactorsXml);
        loadReactorsXml.setNextMessageSender(loadReatorsJson);
        HashMap<String, Reactor> reactors = loadReactorsYaml.commonloadReactors(filePath);
        return reactors;
    }
}
