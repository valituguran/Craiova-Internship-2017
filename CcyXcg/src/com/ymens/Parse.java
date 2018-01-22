package com.ymens;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Parse {
    public static HashMap<String,Double> currencypairs=new HashMap<String,Double> ();
    public void setCurrencypairs(HashMap<String,Double> currencypairs){this.currencypairs=currencypairs;}
    public static void pairs(String a) {
        HashMap<String,Double> currencypairs=new HashMap<String,Double> ();
        int x;
        int y;
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<String> list2 = new ArrayList<>();
        try {
            File fXmlFile = new File(a);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Rate");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    list.add(Double.valueOf(eElement.getTextContent()));
                    list2.add(eElement.getAttribute("currency"));
                }
            }
            for( x=0;x< nList.getLength();x++){
                for( y=0;y< nList.getLength();y++){
                    currencypairs.put(list2.get(x)+list2.get(y),list.get(x)/list.get(y));
                }
            }
            Parse object = new Parse();
            object.setCurrencypairs(currencypairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}