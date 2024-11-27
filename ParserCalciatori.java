package parsexml;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;  //LM Classi mese a disposizione per i documenti di tipo DOM
import org.xml.sax.SAXException;


/**
 *
 * @author Luciano
 */
public class ParserCalciatori {
    private List<Calciatore> calciatori;

    public ParserCalciatori(){
        calciatori = new ArrayList<Calciatore>();
    }
    
    public List ParserDocument(String filename) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        Calciatore calciatore;
        //Creaiamo il documento DOM dal file XML
        factory=DocumentBuilderFactory.newInstance();
        builder=factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        // generazione della lista degli elementi Libro
        nodelist =root.getElementsByTagName("Calciatore");
        if (nodelist!=null && nodelist.getLength()>0){
            for (int i=0; i<nodelist.getLength(); i++){
                element =( Element)nodelist.item(i);
                calciatore=getCalciatore(element );  //LM ???
                calciatori.add(calciatore);
            }
        }
        return calciatori;
    
    }
    
    private Calciatore getCalciatore(Element el){
        Calciatore c;
        String ruolo      = getTextValue(el,"ruolo");
        String nome     = getTextValue(el, "nome");
        String cognome  = getTextValue(el, "cognome");
        String squadra  = getTextValue(el, "squadra");   
        int annoNascita = getIntValue(el,"annoNascita");
        int valoreIniziali = getIntValue(el,"valoreIniziali");
        int valoreattuale  = getIntValue(el,"valoreattuale");
        int golFatti       = getIntValue(el,"golFatti");
        int golSubiti      = getIntValue(el,"golSubiti");
        int golrigori      = getIntValue(el,"golrigori");
        int golrigorisbagliati       = getIntValue(el,"golrigorisbagliati");
        int ammonizioni       =  getIntValue(el,"ammonizioni");
        int  esplulsioni      = getIntValue(el,"esplulsioni");
        float media           = getFloatValue(el,"media");
        //istanzio il Calciatore recuperato dal nodo.
        c = new Calciatore( ruolo,
                            nome,
                            cognome,
                            squadra,
                            annoNascita,
                            valoreIniziali,
                            valoreattuale,
                            golFatti,
                            golSubiti,
                            golrigori,
                            golrigorisbagliati,
                            ammonizioni,
                            esplulsioni,
                            media
                            );
        return c;
       }
    
   // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
    
        // restituisce il valore intero dell’elemento figlio specificato
    private float  getFloatValue(Element element, String tag) {
        return Float.parseFloat(getTextValue(element, tag));
    }
    
        // restituisce il valore intero dell’elemento figlio specificato
    private int  getIntValue(Element element, String tag) {
        return Integer.parseInt(getTextValue(element, tag));
    }
    public static void main ( String args[]){
        List<Calciatore> calciatori=null;
        ParserCalciatori parser = new ParserCalciatori();
        try{
           
            String nomefile="E:\\Documenti_LucianoMerola\\NetBeansProjects\\ParseXML_Zanichelli_pg123\\src\\main\\java\\parsexml\\CalciatoreXSD.xml";
//            calciatori = parser.ParserDocument(args[0]);
            calciatori = parser.ParserDocument(nomefile);
            
        }
         catch (ParserConfigurationException | SAXException | IOException exception) {
                    System.out.println("dettaglio Errore "+  exception.getMessage()  );
                    System.out.println("dettaglio Errore "+  exception.getLocalizedMessage()  );  
                    System.out.println("Errore!");
        }
        // iterazione della lista e visualizzazione degli oggetti
        System.out.println("Numero di Calciatori: " + calciatori.size());
        Iterator iterator = calciatori.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}