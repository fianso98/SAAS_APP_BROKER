/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sofia
 */
public class UpdateWsdlFile extends javax.swing.JPanel {

    /**
     * Creates new form UdateWsdlFile
     */
     String UserName;
    String Domain;
    public UpdateWsdlFile() {
        initComponents();
    }
    public UpdateWsdlFile(String User,String Domain) {
          try {
              this.UserName=User;
              this.Domain=Domain;
              
              initComponents();
               String path="C:\\Users\\sofia\\Documents\\NetBeansProjects\\JavaApplication6\\tmpWSDL\\";
              getFiles(path, Domain, UserName);
              File fXmlFile=new File(path+UserName+".xml");
              jTextField1.setText(xmlReaderGetReputation(fXmlFile));
              jTextField3.setText(xmlReaderGetExperience(fXmlFile));
              jTextField4.setText(xmlReaderGetDisponibilite(fXmlFile));
              jTextField5.setText(xmlReaderGetCout(fXmlFile));
              jTextField6.setText(xmlReaderGetTDRepence(fXmlFile));
              jTextField7.setText(xmlReaderGetRisques(fXmlFile));
              jTextField8.setText(xmlReaderGetTMDRepresentation(fXmlFile));
          } catch (SQLException ex) {
              Logger.getLogger(UpdateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IOException ex) {
              Logger.getLogger(UpdateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(UpdateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     public String xmlReaderGetReputation(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("réputation").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetExperience(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("expérience").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetDisponibilite(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("disponibilité").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetCout(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("coût").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetTDRepence(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("temps_de_réponse").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetRisques(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("risques").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
    public String xmlReaderGetTMDRepresentation(File fXmlFile){
        
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	//System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		//System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line=eElement.getElementsByTagName("temps_moyen_de_représentation").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
     public Connection conecter()throws SQLException, ClassNotFoundException {
            
            String url="jdbc:mysql://localhost:3306/saass" ;
         String user="root";
         String pass="";
          Connection con= DriverManager.getConnection(url,user,pass);
         return con;
        }
    public void getFiles(String path,String Domain,String UserF) throws FileNotFoundException, SQLException, IOException, ClassNotFoundException{
        // DELETE DERECTORY
            Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
       
            
        File file=new File(path+UserF+".xml");
        FileOutputStream fos=new FileOutputStream(file);
        byte b[];
        Blob blob;

        PreparedStatement ps=conecter().prepareStatement("SELECT fichier FROM fournisseur where fournisseur_nom='"+UserF+"' and domain_nom='"+Domain+"'"); 
        ResultSet rs=ps.executeQuery();

        if(rs.next()){
            blob=rs.getBlob("fichier");
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);
        }

        ps.close();
        fos.close();
        conecter().close();
    }
       public static void AddElemnt(String filename,String Réputation,String Expérience,String Disponibilité,String Coût,String TempsDeRéponse,String Risques,String Tmr) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, TransformerConfigurationException, TransformerException{
    
    //transformer lage en String
    
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //dbf.setValidating(false);
        DocumentBuilder db = dbf.newDocumentBuilder();
         File file=new File(filename);
         if(!file.exists()){
             file.createNewFile();
         }
        //Document doc = db.parse(file);
        Document doc = db.newDocument();
        /**
        Element root = doc.createElement("company");
            doc.appendChild(root);
 
            // employee element
            Element employee = doc.createElement("employee");
 
            root.appendChild(employee);
 
            // set an attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("10");
            employee.setAttributeNode(attr);
 
            //you can also use staff.setAttribute("id", "1") for this
 
            // firstname element
            Element firstName = doc.createElement("firstname");
            firstName.appendChild(doc.createTextNode("James"));
            employee.appendChild(firstName);
 
            // lastname element
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("Harley"));
            employee.appendChild(lastname);
 
            // email element
            Element email = doc.createElement("email");
            email.appendChild(doc.createTextNode("james@example.org"));
            employee.appendChild(email);
 
            // department elements
            Element department = doc.createElement("department");
            department.appendChild(doc.createTextNode("Human Resources"));
            employee.appendChild(department);
        **/
        
        Element definitions= doc.createElement("definitions");
        doc.appendChild(definitions);
        
        
        Element types= doc.createElement("types");
        definitions.appendChild(types);
        
        Element propriétés= doc.createElement("propriétés");
        types.appendChild(propriétés);
       //addfirstname
         
        Element réputation = doc.createElement("réputation");
        réputation.appendChild(doc.createTextNode(Réputation));
        propriétés.appendChild(réputation);
        //add secondname
        Element confiance = doc.createElement("confiance");
        confiance.appendChild(doc.createTextNode("0"));
        propriétés.appendChild(confiance);
        //add sexe
        Element expérience = doc.createElement("expérience");
        expérience.appendChild(doc.createTextNode(Expérience));
        propriétés.appendChild(expérience);
        //add age
        Element disponibilité = doc.createElement("disponibilité");
        disponibilité.appendChild(doc.createTextNode(Disponibilité));
        propriétés.appendChild(disponibilité);
        //add adress
        Element coût = doc.createElement("coût");
        coût.appendChild(doc.createTextNode(Coût));
        propriétés.appendChild(coût);
        //add email
        Element tempsDeRéponse = doc.createElement("temps_de_réponse");
        tempsDeRéponse.appendChild(doc.createTextNode(TempsDeRéponse));
        propriétés.appendChild(tempsDeRéponse);
        //add numero
        Element risques = doc.createElement("risques");
        risques.appendChild(doc.createTextNode(Risques));
        propriétés.appendChild(risques);

        Element tmr = doc.createElement("temps_moyen_de_représentation");
        tmr.appendChild(doc.createTextNode(Tmr));
        propriétés.appendChild(tmr);
           
        Element message = doc.createElement("message");
        message.appendChild(doc.createTextNode("definition of the data being communicated...."));
        definitions.appendChild(message);
        Element portType = doc.createElement("portType");
        portType.appendChild(doc.createTextNode("set of operations...... "));
        definitions.appendChild(portType);
        Element binding = doc.createElement("binding");
        binding.appendChild(doc.createTextNode("protocol and data format specification...."));
        definitions.appendChild(binding);
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
       
          
    
    }
       public String currenttime(){
       java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String currentTime = sdf.format(dt);
       return currentTime;
       }
   public void insertIntoDb(String filename,String User,String Domain) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException{
     File file= new File(filename);


//PreparedStatement statement = conecter().prepareStatement("INSERT INTO Fournisser (file) VALUES (?) where UserName='"+UserName+"'and PassWord='"+PassWord+"'");
//statement.setBlob(1, inputStream);
     
        
        //DataInputStream dataIs = new DataInputStream(new FileInputStream(f1));
        PreparedStatement pst = conecter().prepareStatement("UPDATE fournisseur SET fichier = ? ,fournisseur_date=? where (fournisseur_nom= '"+User+"') and (domain_nom= '"+Domain+"')");
        
                              FileInputStream input=new FileInputStream(file);
                               
                               pst.setBinaryStream(1,input,(int) file.length());
                               pst.setString(2, currenttime());
                             
                                pst.executeUpdate();
                                pst.close();
    //pst.setInt(1,67);
      
       System.out.println("Reading file " + file.getAbsolutePath());
            System.out.println("Store file in the database.");
        
        System.out.println(" database.");
        
    
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("reputation");

        jLabel3.setText("experience");

        jLabel4.setText("desponibilite");

        jLabel5.setText("cout");

        jLabel6.setText("temp de repence");

        jLabel7.setText("risques");

        jLabel8.setText("temps moyen de représentation");

        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addComponent(jTextField3)
                        .addComponent(jTextField7)
                        .addComponent(jTextField6)
                        .addComponent(jTextField5)
                        .addComponent(jTextField4))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String pn="[1-9]|10";

        //control reputation
        Pattern pat = Pattern.compile(pn);
        Matcher matchreputation= pat.matcher(jTextField1.getText());

        //control confience

        //control experience

        Matcher matchex= pat.matcher(jTextField3.getText());

        //control desponibilité

        Matcher matchdes= pat.matcher(jTextField4.getText());

        //control reputation

        Matcher matchcout= pat.matcher(jTextField5.getText());

        //control temp de responce

        Matcher matchtemp= pat.matcher(jTextField6.getText());

        //control risque

        Matcher matchrisque= pat.matcher(jTextField7.getText());

        //control temp moyen

        Matcher matchtempmoyen= pat.matcher(jTextField8.getText());
        if((!matchtempmoyen.matches())||(!matchrisque.matches())||(!matchtemp.matches())||(!matchreputation.matches())||(!matchdes.matches())||(!matchex.matches())){
            JOptionPane.showMessageDialog(null,"fill the with numbers only between 1 and 10");
        }else{

            try {

                String path="C:\\Users\\sofia\\Documents\\NetBeansProjects\\JavaApplication6\\tmpWSDL\\";
                AddElemnt(path+UserName+".xml", jTextField1.getText(),jTextField3.getText(), jTextField4.getText(), jTextField5.getText(), jTextField6.getText(), jTextField7.getText(), jTextField8.getText());
                insertIntoDb(path+UserName+".xml",UserName,Domain);
                JOptionPane.showMessageDialog(null,"your file has been Updated");
            } catch (IOException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(CreateWsdlFile.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
