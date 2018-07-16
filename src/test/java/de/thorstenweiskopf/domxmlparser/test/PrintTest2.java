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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author MVC6
 */
public class PrintTest2 {

    public static void main(String[] args) {
        try {

            File fXmlFile = new File(PrintTest2.class.getResource("/toParse.xml").toURI());

            System.out.println(fXmlFile.toString());
            System.out.println(fXmlFile.exists());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            Element rootElement = doc.getDocumentElement();
            System.out.println("Root element: " + rootElement.getNodeName());

            /* Node roots_firstChild = rootElement.getFirstChild();
            soutAttributes("soutAttributes) root.firstChild", roots_firstChild);
            Node nextNode = roots_firstChild.getNextSibling();
            NodeList childNodes = nextNode.getChildNodes();
            for(int i = 0 ; i < childNodes.getLength();i++){
                if(childNodes.item(i).getNextSibling() != null && (childNodes.item(i).getNextSibling() instanceof Element)){
                    NodeList nl = ((Element)childNodes.item(i).getNextSibling()).getElementsByTagName("nickname");
                    for(int j = 0 ; j < nl.getLength() ; j++){
                        System.out.println(nl.item(j).getTextContent());
                    }
                }
            }*/
            soutNicknamesFromXtCompanyInXML(doc,0);
            //dynamischSubTreeElementholen(doc);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void soutAttributes(String prefix, Node firstChild) {
        Node nextNode = firstChild.getNextSibling();
        System.out.print(prefix + " (" + nextNode.getNodeName() + "): ");
        if (nextNode.hasAttributes()) {
            for (int i = 0; i < nextNode.getAttributes().getLength(); i++) {
                System.out.print(nextNode.getAttributes().item(i) + "\n");
            }
        } else {
            System.out.print("Keine Attribute" + "\n");
        }
    }

    public static void soutNicknamesFromXtCompanyInXML(Document doc, int wievieltesElement) {
        Element rootElement = doc.getDocumentElement();
        
        //soutAttributes("soutAttributes) root.firstChild", roots_firstChild);
        
        //NodeList companysNL = nextNode.getChildNodes();
        NodeList companysNL = rootElement.getElementsByTagName("company");
        System.out.println("companysNL.getLength(): "+companysNL.getLength());
        if(wievieltesElement<=companysNL.getLength()){
            Node firstCompany = companysNL.item(wievieltesElement);
            if (firstCompany instanceof Element) {
                NodeList nList_nickname = ((Element) firstCompany).getElementsByTagName("nickname");
                for (int temp = 0; temp < nList_nickname.getLength(); temp++) {
                    String nick = nList_nickname.item(temp).getTextContent();
                    System.out.println(nick);
                }
            }
        }
    }

    public static void dynamischSubTreeElementholen(Document doc) throws DOMException {
        Element rootElement = doc.getDocumentElement();
        NodeList companysNL = rootElement.getElementsByTagName("company");
        for (int i = 0; i < companysNL.getLength(); i++) {
            // jede einzelnen SubTree einzeln betrachten als Node-Obj
            Node companySubTree = companysNL.item(i);
            // Zum selektieren werden nur company tags mit Attributen untersucht
            if (companySubTree.hasAttributes()) {
                Node attributeID = companySubTree.getAttributes().getNamedItem("id");
                // das zu untersuchende SubTree braucht die Attribute id mit dem wert 1
                if (attributeID.getNodeName().equals("id") && attributeID.getNodeValue().equals("1")) {
                    if (companySubTree instanceof Element) {

                        NodeList nList = ((Element) companySubTree).getElementsByTagName("nickname");
                        for (int temp = 0; temp < nList.getLength(); temp++) {
                            String nick = nList.item(temp).getTextContent();
                            System.out.println(nick);
                        }
                    }

                }
            }
        }
    }

    public static String nodeToString(Node node) throws Exception {
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
