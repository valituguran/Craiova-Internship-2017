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
public class ParsePairs {
    public static ArrayList<String> pairs(String a) {

        int x;
        int y;
        a = "E:\\workspace\\Craiova-Internship-2017\\parse\\src\\parse.xml";
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
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
                    list.add(eElement.getAttribute("currency"));
                }
            }
            for( x=0;x<32;x++){
                for( y=0;y<32;y++){
                    list2.add(list.get(x)+list.get(y));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2;
    }
}