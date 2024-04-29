package org.example;

import org.example.FileHandler;
import org.example.Reactor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.io.File;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
public class LoadReactorsXml extends FileHandler {
    public boolean returnTypeStatus(String filePath) {
        File file = new File(filePath);
        if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("xml")) {
            return true;
        }
        else{
            return false;
        }
    }
    public  HashMap<String, Reactor> loadReactors(String filePath) {
        HashMap<String, Reactor> reactors = new HashMap<>();
        File file = new File(filePath);

            try {
                File inputFile = new File(filePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                NodeList reactorList = doc.getElementsByTagName("root").item(0).getChildNodes();
                for (int i = 0; i < reactorList.getLength(); i++) {
                    Node reactorNode = reactorList.item(i);
                    if (reactorNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element reactorElement = (Element) reactorNode;
                        double burnup = Double.parseDouble(reactorElement.getElementsByTagName("burnup").item(0).getTextContent());
                        String classe = reactorElement.getElementsByTagName("class").item(0).getTextContent();
                        double electricalCapacity = Double.parseDouble(reactorElement.getElementsByTagName("electrical_capacity").item(0).getTextContent());
                        double firstLoad = Double.parseDouble(reactorElement.getElementsByTagName("first_load").item(0).getTextContent());
                        double kpd = Double.parseDouble(reactorElement.getElementsByTagName("kpd").item(0).getTextContent());
                        double lifetime = Double.parseDouble(reactorElement.getElementsByTagName("life_time").item(0).getTextContent());
                        double terminalCapacity = Double.parseDouble(reactorElement.getElementsByTagName("termal_capacity").item(0).getTextContent());
                        Reactor reactor = new Reactor(burnup, classe, electricalCapacity, firstLoad, kpd, lifetime, terminalCapacity, "xml");
                        reactors.put(reactor.classe, reactor);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                reactors=nextFileHandler.loadReactors(filePath);
            }


        return reactors;
    }

}
