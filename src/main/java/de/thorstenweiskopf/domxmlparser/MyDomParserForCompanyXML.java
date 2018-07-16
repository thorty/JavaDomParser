/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thorstenweiskopf.domxmlparser;


import de.thorstenweiskopf.domxmlparser.exception.MyDomParserException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author MVC6
 */
public class MyDomParserForCompanyXML extends MyDomParser{
    
    public MyDomParserForCompanyXML(){
        super();
    }
    
 
    /***
     * 
     * @return List<String> of all the nicknames of all companys
     * @throws MyDomParserException 
     */
    public List<String> getAllNickNames() throws MyDomParserException{
        ArrayList<String> list = new ArrayList<String>();
        if (doc != null){
            NodeList nList = doc.getElementsByTagName("nickname");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                String nick = nList.item(temp).getTextContent();
                System.out.println(nick);
                list.add(nick);
            }
        }else {
            throw new MyDomParserException("No XML doc available.");
        }
        return list;
        
    }
  
    /***
     * @param int number of the company you wan't to scan
     * @return List<String> of all the nicknames of the first company
     * @throws MyDomParserException 
     */
    public List<String> getAllNickNamesFromCompany(int elementNumber) throws MyDomParserException{
        ArrayList<String> list = new ArrayList<String>();
        Element rootElement = doc.getDocumentElement();
        
        NodeList companysNL = rootElement.getElementsByTagName("company");
        //System.out.println("companysNL.getLength(): "+companysNL.getLength());
        if(elementNumber<=companysNL.getLength()){
            Node firstCompany = companysNL.item(elementNumber);
            if (firstCompany instanceof Element) {
                NodeList nList_nickname = ((Element) firstCompany).getElementsByTagName("nickname");
                for (int temp = 0; temp < nList_nickname.getLength(); temp++) {
                    //String nick = nList_nickname.item(temp).getTextContent();
                    list.add(nList_nickname.item(temp).getTextContent());
                   //System.out.println(nick);
                }
            }
        }
        
        return list;
    }    
  
    
}
