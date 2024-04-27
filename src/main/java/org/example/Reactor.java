package org.example;

import java.util.HashMap;

public class Reactor {
      double burnup;
      String classe;
      double electricalCapacity;
      double firstLoad;
      double kpd;
      double lifeTime;
      double terminalCapacity;
    String  fileType;


    public Reactor(double burnup, String classe, double electricalCapacity, double firstLoad, double kpd, double lifeTime, double terminalCapacity, String fileType) {
        this.burnup = burnup;
        this.classe = classe;
        this.electricalCapacity = electricalCapacity;
        this.firstLoad = firstLoad;
        this.kpd = kpd;
        this.lifeTime = lifeTime;
        this.terminalCapacity = terminalCapacity;
        this.fileType=fileType;
    }
}
