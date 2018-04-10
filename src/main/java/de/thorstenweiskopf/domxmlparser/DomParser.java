/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thorstenweiskopf.domxmlparser;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 *
 * @author MVC6
 */
public class DomParser {
    
    private String src;
    
    public DomParser(String xmlSrc){        
        this.src = xmlSrc;                
    }
    


    public String getSrc() {
        return src;
    }
    
    
    
    
   
    
}
