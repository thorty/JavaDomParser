/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thorstenweiskopf.domxmlparser.test;

import de.thorstenweiskopf.domxmlparser.MyDomParserForCompanyXML;
import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author MVC6
 */
public class PrintTest2 {
    
     public static void main(String[] args) {
        try {        
                                   
        File fXmlFile = new File( PrintTest2.class.getResource( "/toParse.xml" ).toURI() );                
        
        System.out.println(fXmlFile.toString());
        System.out.println(fXmlFile.exists());        
        
        
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	NodeList firstChildNodes = doc.getChildNodes();                
        System.out.println("########### firstChildNodes ###########" + firstChildNodes.item(0).getNodeName());
        System.out.println(firstChildNodes.getLength());
        System.out.println(nodeToString(firstChildNodes.item(0)));
        NodeList secondChildNodes = firstChildNodes.item(0).getChildNodes();        
        System.out.println("########### secondChildNodes ###########" +secondChildNodes.item(0).getNodeName());
        System.out.println(secondChildNodes.getLength());
        System.out.println(nodeToString(secondChildNodes.item(1)));        
        NodeList thirdChildNodes = secondChildNodes.item(1).getChildNodes();                
        System.out.println("########### thirdChildNodes ###########");
        System.out.println(thirdChildNodes.getLength());        
        System.out.println(nodeToString(thirdChildNodes.item(1)));        
        
//        for (int i =0; i < secondChildNodes.getLength(); i++){
//            System.out.println(secondChildNodes.item(i).getNodeName());
//            if (secondChildNodes.item(i).getNodeName().equals("nickname")){
//                System.out.println(secondChildNodes.item(i).getTextContent());
//            }
//        }
        
    } catch (Exception e) {
	e.printStackTrace();
    }
        
    }
     
     
  public static String nodeToString(Node node) throws Exception{
   StringWriter sw = new StringWriter();
    try {
      Transformer t = TransformerFactory.newInstance().newTransformer();
      t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      t.setOutputProperty(OutputKeys.INDENT, "yes");
      t.transform(new DOMSource(node), new StreamResult(sw));
    } catch (TransformerException te) {
      System.out.println("nodeToString Transformer Exception");
    }
    return sw.toString();  
    }     
     
    
}
