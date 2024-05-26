package org.example;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LoadReactorsYaml extends FileHandler{
    @Override
    public boolean returnTypeStatus(String filePath) {
        File file = new File(filePath);
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("yaml")) {
            return true;
        }
        else{
            return false;
        }
    }

    public HashMap<String, Reactor> loadReactors(String filePath) {
        Yaml yaml = new Yaml();
        HashMap<String, Reactor> reactors = new HashMap<>();
        File file = new File(filePath);
            try {
                FileInputStream fis = new FileInputStream(filePath);
                // Parse YAML as Map<String, Map<String, Object>>
                Map<String, Map<String, Object>> data = yaml.load(fis);

                // Create Reactor objects
                /* for (String reactorName : data.keySet()) {
                    Map<String, Object> reactorData = data.get(reactorName);
                    Reactor reactor = new Reactor(((Number) reactorData.get("burnup")).doubleValue(), (String) reactorData.get("class"), ((Number) reactorData.get("electrical_capacity")).doubleValue(), ((Number) reactorData.get("first_load")).doubleValue(), ((Number) reactorData.get("kpd")).doubleValue(), ((Number) reactorData.get("life_time")).doubleValue(), ((Number) reactorData.get("termal_capacity")).doubleValue(), "yaml");
                    reactors.put(reactor.classe, reactor);
                } */

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                reactors = nextFileHandler.loadReactors(filePath);
            }


        return reactors;
    }
}


