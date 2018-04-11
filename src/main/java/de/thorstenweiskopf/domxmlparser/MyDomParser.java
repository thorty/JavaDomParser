/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thorstenweiskopf.domxmlparser;

import de.thorstenweiskopf.domxmlparser.exception.MyDomParserException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author MVC6
 */
public class MyDomParser {
    
    protected String src;
    protected Document doc ;
    
    public MyDomParser(){        
        
    }
    
    /**
     * 
     * @return String presentation of XML Content
     */
    public String getSrc() {
        return src;
    }

    /***
     * 
     * @param src : XML as String
     */
    public void setSrc(String src) throws MyDomParserException {        
        try {
            this.src = src;
            //create DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = createInputSource(src);
            doc = dBuilder.parse(is);
                        
            doc.getDocumentElement().normalize();
        } catch (SAXException ex) {
            Logger.getLogger(MyDomParserForCompanyXML.class.getName()).log(Level.SEVERE, null, ex);
            throw new MyDomParserException("Error during parse the SRC String: " +ex.getMessage());
        } catch (IOException ex) {
            throw new MyDomParserException("Error during parse the SRC String: " +ex.getMessage());
        } catch (ParserConfigurationException ex) {
            throw new MyDomParserException("Error during parse the SRC String: " +ex.getMessage());
        }
                
        }              
    
    
    /***
     * 
     * @param path: path to File with XML Content 
     * under src/main/ressources in maven project
     */
    public void setFileSrc(String file) throws MyDomParserException{
        
        try {                 
            if (MyDomParserForCompanyXML.class.getResource(file) != null){
                
            
            File fXmlFile = new File( MyDomParserForCompanyXML.class.getResource( file ).toURI() );
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();                        
            
            if (fXmlFile.exists() && fXmlFile.canRead()){
                try {
                    doc = dBuilder.parse(fXmlFile);
                    doc.getDocumentElement().normalize();
                    src = domToString(doc);
                } catch (SAXException ex) {
                    throw new MyDomParserException("Error during parse File: "+ex.getMessage() );
                } catch (IOException ex) {
                    throw new MyDomParserException("Error during parse File: "+ex.getMessage() );
                } catch (Exception ex) {
                    throw new MyDomParserException("Error during parse File: "+ex.getMessage() );
                }
            } else {
                throw new MyDomParserException("File not found: "+fXmlFile.getAbsolutePath() );
            }
            }
            else {
                throw new MyDomParserException("File not found: "+file );
            }            
        } catch (URISyntaxException ex) {
            throw new MyDomParserException("File not found: "+ex.getMessage() );
        } catch (ParserConfigurationException ex) {
          throw new MyDomParserException("File not found: "+ex.getMessage() );
        }                
    }
    
    
  private String domToString(Document newDoc) throws Exception{
        DOMSource domSource = new DOMSource(newDoc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);    
        src = sw.toString();                
        return src;    
    }
    
    private InputSource createInputSource(String src) {
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(src));
        return is;
    }    
    
}
