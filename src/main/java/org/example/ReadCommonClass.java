package org.example;

import java.util.ArrayList;

public class ReadCommonClass {
    public ArrayList<Reactor> readCommonClass(String filePath) {
        ArrayList<Reactor> reactors = ReactorDatabase.getReactors(filePath);

        return reactors;
    }
}
