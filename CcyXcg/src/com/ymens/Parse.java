package com.ymens;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Parse {
    private static int x;
    private static int y;
    public static ArrayList<Double> values(String a) {

        a="E:\\workspace\\Craiova-Internship-2017\\parse\\src\\parse.xml";
        ArrayList<Double > value = new ArrayList<Double>();
        try {
            File fXmlFile = new File(a);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Cube");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    for( x=0;x<32;x++){
                        for( y=0;y<32;y++){
                            value.add(DatatypeConverter.parseDouble(eElement.getElementsByTagName("Rate").item(x).getTextContent())/(DatatypeConverter.parseDouble(eElement.getElementsByTagName("Rate").item(y).getTextContent())));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
           return value;
    }
}