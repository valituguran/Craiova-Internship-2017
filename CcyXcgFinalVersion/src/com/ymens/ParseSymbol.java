package com.ymens;
import dao.ParseDao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParseSymbol {
    public static LinkedList<String> symbol = new LinkedList<>();
    public void setSymbol(LinkedList<String> symbol){ this.symbol = symbol; }
    public static void parsesymb(String a) {
        LinkedList<String> symb = new LinkedList<>();
        LinkedList<String> list = new LinkedList<>();
        int x;
        a = "E:\\workspace\\Craiova-Internship-2017\\CcyXcg\\web\\WEB-INF\\bnr.xml";
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
                symb.add(list.get(x));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ParseSymbol ps = new ParseSymbol();
        ps.setSymbol(symb);
    }
}