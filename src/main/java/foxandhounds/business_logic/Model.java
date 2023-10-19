/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foxandhounds.business_logic;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author valyis
 *  Model only wraps a table. This is the model in the MVC style division.
 * 
 */
@XmlRootElement
@XmlType
public class Model {
    Table table;
    public Model(int N) {table = Table.getEmptyTable(N);}
    public Model(){}

    @XmlElement(name="tablaelem")    
    public Table getTable() {
        return table;
    }
   
    public void setTable(Table table) {
        this.table = table;
    }
    
    /** This method translates the table to an XML description automatically (by JAXB).
     * 
     * @return a string that describes the table
     */
    public String toXML() {
         StringWriter swriter = new StringWriter();
         try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.marshal(this,swriter);
         } catch (JAXBException jaxbex) {jaxbex.printStackTrace();}
   
         String s = swriter.toString();
         if (s==null) s = "";
           return s;
     }
    

/** This method gets a string and processes it as an xml describing a table.
 * The table is then loaded to the actual table of the actual model.
 * @param xmlString it is an xml text that describes a table
 * 
 */
     
public void loadFromXML(String xmlString) {
          StringReader sreader = new StringReader(xmlString);
          
          Model anotherModel = null;
         try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            anotherModel  = (Model) jaxbUnmarshaller.unmarshal(sreader);
         } catch (JAXBException jaxbex) {jaxbex.printStackTrace();}
           reBuildFromAnotherModel(anotherModel);
          
         
         
         
}

    private void reBuildFromAnotherModel(Model anotherModel) {
        setTable(anotherModel.getTable());
        int N = table.getTableSize();
        table.hounds = new ArrayList<>();
        for (int i=0;i<N;i++)for (int j=0;j<N;j++){
            if (table.matrix[i][j]=='f') table.addFox(new Fox(i,j));
            if (table.matrix[i][j]=='h') table.addHound(new Hound(i,j));          
        }
    }
  
}
