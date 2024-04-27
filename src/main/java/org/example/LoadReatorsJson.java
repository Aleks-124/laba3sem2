package org.example;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class LoadReatorsJson extends FileHandler {
    private FileHandler nextFileHandler;
    public  HashMap<String, Reactor> loadReactors(String filePath) {
        HashMap<String, Reactor> reactors = new HashMap<>();

        JSONParser parser = new JSONParser();
        File file = new File(filePath);
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("json")){

            try {
                JSONObject reactorsObj = (JSONObject) parser.parse(new FileReader(filePath));

                for (Object key : reactorsObj.keySet()) {
                    String reactorClass = (String) key;
                    JSONObject reactorData = (JSONObject) reactorsObj.get(reactorClass);

                    Reactor reactor = new Reactor(
                            ((Number) reactorData.get("burnup")).doubleValue(),
                            reactorData.get("class").toString(),
                            ((Number) reactorData.get("electrical_capacity")).doubleValue(),
                            ((Number) reactorData.get("first_load")).doubleValue(),
                            ((Number) reactorData.get("kpd")).doubleValue(),
                            ((Number) reactorData.get("life_time")).doubleValue(),
                            ((Number) reactorData.get("termal_capacity")).doubleValue(),
                            "json"
                    );

                    reactors.put(reactorClass, reactor);
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
                nextFileHandler.loadReactors(filePath);
            }

        }
        else{
            reactors=nextFileHandler.loadReactors(filePath);
        }

        return reactors;
    }

}
