package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReactorDatabase {

    public static ArrayList<Reactor> getReactors(String filePath) {
         String DB_URL = "jdbc:sqlite:"+filePath;
        ArrayList<Reactor> reactors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT rp.name, rp.country, rp.status, rp.type, rp.owner, rp.thermalCapacity, "
                    + "rp.firstGridConnection, rp.suspendedDate, rp.permanentShutdownDate, rt.burnup, c.region, "
                    + "lf.year, lf.loadfactor "
                    + "FROM Reactors rp "
                    + "JOIN ReactorsTypes rt ON rp.type = rt.type "
                    + "JOIN Countries c ON rp.country = c.country "
                    + "LEFT JOIN LoadFactor lf ON rp.name = lf.reactor";

            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            HashMap<String, Reactor> reactorMap = new HashMap<>();

            while (rs.next()) {
                String name = rs.getString("name");

                Reactor reactor = reactorMap.get(name);
                if (reactor == null) {
                    String country = rs.getString("country");
                    String status = rs.getString("status");
                    String type = rs.getString("type");
                    String owner = rs.getString("owner");
                    double thermalCapacity = rs.getDouble("thermalCapacity");
                    String firstGridConnection = rs.getString("firstGridConnection");
                    String suspendedDate = rs.getString("suspendedDate");
                    String permanentShutdownDate = rs.getString("permanentShutdownDate");
                    double burnup = rs.getDouble("burnup");
                    String region = rs.getString("region");
                    HashMap<String, Double> yearLoadFactor = new HashMap<>();

                    reactor = new Reactor(name, country, status, type, owner, thermalCapacity,
                            firstGridConnection, suspendedDate, permanentShutdownDate,
                            burnup, region, yearLoadFactor);
                    reactorMap.put(name, reactor);
                }

                String year = rs.getString("year");
                if (year != null) {
                    double loadFactor = rs.getDouble("loadfactor");
                    reactor.yearLoadFactor.put(year, loadFactor);
                }
            }

            reactors.addAll(reactorMap.values());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return reactors;
    }

}
