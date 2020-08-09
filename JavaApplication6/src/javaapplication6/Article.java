/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Component;
import java.awt.List;
import java.awt.MenuComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.math.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author sofia
 */
public class Article extends javax.swing.JFrame {
    String UserC,Password;
 public String getDate(String User,String Domain) throws SQLException{
        
        String date=" ";
        try {
            ResultSet rs ;
            
            String q="select DAY(fournisseur_date),MONTH(fournisseur_date),YEAR(fournisseur_date) from Fournisseur WHERE fournisseur_nom='"+User+"' and domain_nom='"+Domain +"'";
            Statement stmt = conecter().createStatement();
            rs = stmt.executeQuery(q);
            rs.next();
            int month=Integer.parseInt(rs.getString("MONTH(fournisseur_date)"));
            if(month<10){
             date=rs.getString("YEAR(fournisseur_date)")+"-0"+month+"-"+rs.getString("DAY(fournisseur_date)");
            rs.close();
            stmt.close();
            }else{
                date=rs.getString("YEAR(fournisseur_date)")+"-"+month+"-"+rs.getString("DAY(fournisseur_date)");
            rs.close();
            stmt.close();
            }
            
                    } catch (SQLException ex) {
            Logger.getLogger(ControlPanelClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("javaapplication6.FilterChoise.getDate()"+date);
        return date;
    }
    public void asember(String Domain,String UserF,String UserC) throws SQLException{
        
        try {
            /*  button te3 valider
            segem sql 3la 7sab database te3k
            ida l9ah chrah 9bal 90 days donc ywidlo points f database te3 client li dayerha ana integer asemha vintime
            sema ida chtah <90 days wkanet la valeur te3 vintime+1==5 ymdlo service batel + yrod vintime = 0
            ida kanet lavaleur vintime+1<5  ydir update l vintime f database ydirha vintime+1 */
            
            
            // jibli time li f fichier li khayro
            
            
            
            Statement st = conecter().createStatement();
            Long i = Diff(UserF,Domain);
            System.out.println("javaapplication6.FilterChoise.asember()"+i);
            //************
            String query="insert into  validation (client_nom,client_password,fournisseur_nom,fournisseur_domain) values(?,?,?,?)  ";
            PreparedStatement pst = conecter().prepareStatement(query);
            
            
            pst.setString(1,UserC );
            
            pst.setString(2, Password);
            pst.setString(3, UserF);
            pst.setString(4, Domain);
            
            
            
            pst.executeUpdate();
            pst.close();
            //*******
            String query2="UPDATE client SET client_valider=? , client_fournisseur_nom=? where (client_nom=?) ";
            PreparedStatement psta = conecter().prepareStatement(query2);
            
            
            psta.setBoolean(1,true );
            psta.setString(2,UserF );
            psta.setString(3,UserC );
            
            psta.executeUpdate();
            psta.close();
            //90= 3mois ida chrah w kan 3ando 9al ma 3 mois meli 7atouh lel bi3
            if(i<90){
                String qu = "SELECT client_points FROM client WHERE client_nom='"+UserC+"'";//  where username = asemclient li logina
                ResultSet rs = st.executeQuery(qu);
                rs.next();
                String j =rs.getString("client_points");
                System.out.println("k="+j+" userC= "+UserC+" f "+UserF+" d "+Domain);
                rs.close();
                st.close();
                conecter().close();
                
                int k= Integer.parseInt(j);
                
                
               
                
                
                
                if(k+1==5){
                    
                   
                    PreparedStatement Stmt =(PreparedStatement)conecter().prepareStatement("UPDATE client SET client_points=? where (client_nom=?)");
                    JOptionPane.showMessageDialog(null,"congrats you have a service for free");
                    Stmt.setInt(1,0 );
                
                Stmt.setString(2, UserC);
                Stmt.executeUpdate();
                
                Stmt.close();
                conecter().close();
               
                }else{
                   
                     PreparedStatement Stmt =(PreparedStatement)conecter().prepareStatement("UPDATE client SET client_points=? where (client_nom=?)");
                       Stmt.setInt(1,k+1 );
                                Stmt.setString(2, UserC);
                                Stmt.executeUpdate();
                                
                                Stmt.close();
                                conecter().close();
                               
                                JOptionPane.showMessageDialog(null,"validated");
                      }
                
                
            }
        } catch(MySQLIntegrityConstraintViolationException ex){
            System.out.println( "QUERY IS ALREADY THERE: ");
        }catch (SQLException ex) {
            int errCode = ex.getErrorCode();
     if(errCode == 1062){ //MySQLIntegrityConstraintViolationException 
     JOptionPane.showMessageDialog(null, "Duplicate validation \n wait for a broker to \n validate your offer");}
      
           
        }
                           
       

       
    
    }

public String currenttime(){
       java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd");

    String currentTime = sdf.format(dt);
    System.out.println("javaapplication6.FilterChoise.currenttime()"+currentTime);
       return currentTime;
       }
public Long Diff(String UserF,String Domain) throws SQLException{
long dif;
 String crnttime=currenttime();
DateTimeFormatter smpl= DateTimeFormatter.ofPattern("yyyy-MM-dd");
//SimpleDateFormat smpl = new SimpleDateFormat("dd MM yyyy");

LocalDate curnt = LocalDate.parse(crnttime,smpl);
LocalDate old = LocalDate.parse(getDate(UserF, Domain),smpl);
dif=ChronoUnit.DAYS.between(curnt,old);
return dif;
}


    private void printValues(ArrayList<ListFonction> maList) {
         //To change body of generated methods, choose Tools | Templates.
         int n=maList.size();
         System.out.println("list A");
         for (int i = 0; i < n; i++) 
        { 
         ListFonction data = maList.get(i); 
            System.out.println(data.s1+" "+data.s2+" "+data.s3+" "+data.s4+" "+data.s5+" "+data.s6+" "+data.s7+" "+data.s8+" "+data.filenameR);
        }
    }
    private ArrayList<ListWD> ALGO(ArrayList<ListFonction> A,ArrayList<String> R,ArrayList<String> W){
        
       int ALength=A.size();
       int RLength=R.size();
       int WLength=W.size();
       ArrayList<ListWD> WD=new ArrayList<ListWD>();
       int num=1;
       for (int i = 0; i < ALength; i++) 
        { 
            ListFonction data = A.get(i); 
            
        Double moy=Math.exp(-((Double.parseDouble(data.s1)-Double.parseDouble(R.get(0)))*Double.parseDouble(W.get(0))))+Math.exp(-((Double.parseDouble(data.s2)-Double.parseDouble(R.get(1)))*Double.parseDouble(W.get(1))))+Math.exp(-((Double.parseDouble(data.s3)-Double.parseDouble(R.get(2)))*Double.parseDouble(W.get(2))))+Math.exp(-((Double.parseDouble(data.s4)-Double.parseDouble(R.get(3)))*Double.parseDouble(W.get(3))))+Math.exp(-((Double.parseDouble(data.s5)-Double.parseDouble(R.get(4)))*Double.parseDouble(W.get(4))))+Math.exp(-((Double.parseDouble(data.s6)-Double.parseDouble(R.get(5)))*Double.parseDouble(W.get(5))))+Math.exp(-((Double.parseDouble(data.s7)-Double.parseDouble(R.get(6)))*Double.parseDouble(W.get(6))))+Math.exp(-((Double.parseDouble(data.s8)-Double.parseDouble(R.get(7)))*Double.parseDouble(W.get(7))));
            System.err.println("moy= "+moy);
        WD.add(new ListWD(moy,data.filenameR));   
        
        }
       
        return WD;
        
    }

    /**
     * Creates new form Article
     */
    public class ListFonction {
    String s1,s2,s3,s4,s5,s6,s7,s8,filenameR;

    public ListFonction(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String f) {
   this.s1=s1;
   this.s2=s2;
   this.s3=s3;
   this.s4=s4;
   this.s5=s5;
   this.s6=s6;
   this.s7=s7;
   this.s8=s8;
   this.filenameR=f;

   
    
    }
   
}
    public Article() {
        try {
            initComponents();
            
            
            int i;
            
            //combien de service
            //
            getFiles();
            File dir = new File(path);
            A=  new ArrayList<ListFonction>();
            //réputation
            for (File fXmlFile : dir.listFiles()) {
           
               String réputation= xmlReder(fXmlFile,path,"réputation");
               String confiance= xmlReder(fXmlFile,path,"confiance");
               String expérience= xmlReder(fXmlFile,path,"expérience");
               String disponibilité=xmlReder(fXmlFile,path,"disponibilité");
               String coût=xmlReder(fXmlFile,path,"coût");
               String temps_de_réponse=xmlReder(fXmlFile,path,"temps_de_réponse");
               String risques=xmlReder(fXmlFile,path,"risques");
               String temps_moyen_de_représentation=xmlReder(fXmlFile,path,"temps_moyen_de_représentation");
               String filename=fXmlFile.getName();
               
               
                A.add(new ListFonction(réputation,confiance,expérience,disponibilité,coût,temps_de_réponse,risques,temps_moyen_de_représentation,filename));
                
            }
            
            printValues(A);
        } catch (SQLException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public Article(String UserC,String Password) {
        this.UserC=UserC;
        this.Password=Password;
        try{
        initComponents();
        
         int i;
            
            //combien de service
            //
            getFiles();
            File dir = new File(path);
            A=  new ArrayList<ListFonction>();
            //réputation
            for (File fXmlFile : dir.listFiles()) {
           
               String réputation= xmlReder(fXmlFile,path,"réputation");
               String confiance= xmlReder(fXmlFile,path,"confiance");
               String expérience= xmlReder(fXmlFile,path,"expérience");
               String disponibilité=xmlReder(fXmlFile,path,"disponibilité");
               String coût=xmlReder(fXmlFile,path,"coût");
               String temps_de_réponse=xmlReder(fXmlFile,path,"temps_de_réponse");
               String risques=xmlReder(fXmlFile,path,"risques");
               String temps_moyen_de_représentation=xmlReder(fXmlFile,path,"temps_moyen_de_représentation");
               String filename=fXmlFile.getName();
               
               
                A.add(new ListFonction(réputation,confiance,expérience,disponibilité,coût,temps_de_réponse,risques,temps_moyen_de_représentation,filename));
                
            }
            
            printValues(A);
        } catch (SQLException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public class ListWD{
        double moy;
            String filename;
        public ListWD(double m,String f){
            moy=m;
            filename=f;
                
            
        }
    }
    public Connection conecter() throws SQLException{
            String url="jdbc:mysql://localhost:3306/saass" ;
         String user="root";
         String pass="";
          Connection con= DriverManager.getConnection(url,user,pass);
         return con;
        }
    
     public String xmlReder(File fXmlFile,String path,String element){
         
String line = null;

    
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

			line=eElement.getElementsByTagName(element).item(0).getTextContent();
                        

		}
	}
        
    } catch (Exception e) {
	e.printStackTrace();
    }
    

        
       
       return line;
   }
    public void getFiles() throws FileNotFoundException, SQLException, IOException{
          Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
        PreparedStatement ps1=conecter().prepareStatement("SELECT fournisseur_nom FROM Fournisseur  WHERE   (fichier  IS NOT NULL) "); 
        ResultSet rs1=ps1.executeQuery();
        while(rs1.next()){
            
        File file=new File(path+rs1.getString(1)+".wsdl");
        FileOutputStream fos=new FileOutputStream(file);
        byte b[];
        Blob blob;

        PreparedStatement ps=conecter().prepareStatement("SELECT fichier FROM Fournisseur where fournisseur_nom='"+rs1.getString(1)+"'"); 
        ResultSet rs=ps.executeQuery();

        if(rs.next()){
            blob=rs.getBlob("fichier");
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);
        }

        ps.close();
        fos.close();
        conecter().close();}
    }
    private ArrayList<String> CreateUserList(){
        
        
        ArrayList<String> UserList = new ArrayList<String>();
        UserList.add(jt1.getText());
        UserList.add(jt2.getText());
        UserList.add(jt3.getText());
        UserList.add(jt4.getText());
        UserList.add(jt5.getText());
        UserList.add(jt6.getText());
        UserList.add(jt7.getText());
        UserList.add(jt8.getText());
        
        
        
        
        return UserList ;
        
    }
    private ArrayList<String> CreateWeightsList(){
        
        
        ArrayList<String> UserList = new ArrayList<String>();
        UserList.add(jComboBox1.getSelectedItem().toString());
        UserList.add(jComboBox2.getSelectedItem().toString());
        UserList.add(jComboBox3.getSelectedItem().toString());
        UserList.add(jComboBox9.getSelectedItem().toString());
        UserList.add(jComboBox10.getSelectedItem().toString());
        UserList.add(jComboBox11.getSelectedItem().toString());
        UserList.add(jComboBox12.getSelectedItem().toString());
        UserList.add(jComboBox13.getSelectedItem().toString());
        
        
        
        
        return UserList ;
        
    }
    String path="C:\\Users\\sofia\\Documents\\NetBeansProjects\\JavaApplication6\\filesWsdl\\";
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jt1 = new javax.swing.JTextField();
        jt2 = new javax.swing.JTextField();
        jt3 = new javax.swing.JTextField();
        jt4 = new javax.swing.JTextField();
        jt5 = new javax.swing.JTextField();
        jt6 = new javax.swing.JTextField();
        jt7 = new javax.swing.JTextField();
        jt8 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        Filter = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        domain = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        back = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel25.setText("reputation");

        jLabel26.setText("confience");

        jLabel27.setText("experience");

        jLabel28.setText("desponibilite");

        jLabel29.setText("cout");

        jLabel30.setText("temp de repence");

        jLabel31.setText("risques");

        jLabel32.setText("temps moyen de représentation");

        Filter.setText("Filter");
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        domain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "transport", "restaurant", "web" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel25)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jt1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jt8)
                    .addComponent(jt2)
                    .addComponent(jt3)
                    .addComponent(jt7)
                    .addComponent(jt6)
                    .addComponent(jt5)
                    .addComponent(jt4))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(Filter))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(domain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(domain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Filter)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jTextField1.setText("Enter the name of Fournisseur");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("valider");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
      
       String pn="[1-9]|10";
         
        //control reputation
        Pattern pat = Pattern.compile(pn);
      Matcher matchreputation= pat.matcher(jt1.getText());
      
       //control confience
        
      Matcher matchconf= pat.matcher(jt2.getText());
      
       //control experience
       
      Matcher matchex= pat.matcher(jt3.getText());
      
       //control desponibilité
        
      Matcher matchdes= pat.matcher(jt4.getText());
      
       //control reputation
        
      Matcher matchcout= pat.matcher(jt5.getText());
      
       //control temp de responce
       
      Matcher matchtemp= pat.matcher(jt6.getText());
      
       //control risque
       
      Matcher matchrisque= pat.matcher(jt7.getText());
      
       //control temp moyen
      
      Matcher matchtempmoyen= pat.matcher(jt8.getText());
      if((!matchtempmoyen.matches())||(!matchrisque.matches())||(!matchtemp.matches())||(!matchreputation.matches())||(!matchconf.matches())||(!matchdes.matches())||(!matchex.matches())){
          JOptionPane.showMessageDialog(null,"fill the with numbers only between 1 and 10");
      }else{
           try {
               ArrayList<String> UserList= CreateUserList();
               ArrayList<String> WeightsList= CreateWeightsList();
               System.out.println("R list: "+UserList);
               System.out.println("W list: "+WeightsList);
               ArrayList<ListWD> WD= ALGO(A, UserList, WeightsList);
               ListWD Result=findNearestNumber(WD);
               System.out.println("la moyenne == "+Result.moy+" nom de fichier =="+Result.filename);
               File file=new File(path+Result.filename);
               TextView(xmlReder(file));
           } catch (IOException ex) {
               Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
           }
      
      }
    }//GEN-LAST:event_FilterActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        try {
            conecter().close();
            dispose();
            new ControlPanelClient().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_backActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jTextField1.setText(" ");
    }//GEN-LAST:event_jTextField1ActionPerformed
public void confiance(String domain,String userF) throws IOException, FileNotFoundException, ClassNotFoundException, SAXException, XPathExpressionException, TransformerException, ParserConfigurationException{
        try {
            Connection conn = null;
            
            
            Statement st = conecter().createStatement();
            
            //jib nombre de vente
            String query = "SELECT fournisseur_nb_vente FROM fournisseur WHERE fournisseur_nom='"+userF+"' and domain_nom='"+domain+"'";
             ResultSet rs = st.executeQuery(query);
             rs.next();
            int nbV =rs.getInt("fournisseur_nb_vente");
            //jib la date li 7at fiha service tsema old time
            
            //conf= jib la valeur te3 confiance ;
            String path2="C:\\Users\\sofia\\Documents\\NetBeansProjects\\JavaApplication6\\tmpWSDL\\";
            getFiles(path2,domain,userF);
            File file=new File(path2+"/"+userF+".xml");
            
            int conf=Integer.parseInt(xmlReaderGetConfiance(file));  // confiance nta hna ki tjib la valeur t3ha affectiha fih
            
            if(nbV<5 && Diff(userF, domain)>20 && conf>0){conf=conf-1;}else if(nbV == 5 && conf<10){conf+=1;}else
            if(nbV>6 && Diff(userF, domain)<20 && Diff(userF, domain)>10 && conf>0){conf=conf+1;}else if(nbV == 6 && conf<10){conf=conf+1;}
            if(nbV>20){conf=(conf+1)*3/2;
            if(conf>10){
                conf=10;
            }
            }
             System.out.println("javaapplication6.FilterChoise.confiance() ="+conf+" getconf= "+xmlReaderGetConfiance(file));
            // dok confiance rahi wajda ab3at din babaha l fichier wsdl 
            AddElemnt(path2+userF+".xml", xmlReaderGetReputation(file), Integer.toString(conf), xmlReaderGetExperience(file), xmlReaderGetDisponibilite(file), xmlReaderGetCout(file), xmlReaderGetTDRepence(file), xmlReaderGetRisques(file),xmlReaderGetTMDRepresentation(file));
            
            insertIntoDb(path2+userF+".xml", userF, domain);
            System.out.println("javaapplication6.FilterChoise.confiance() ="+conf+" getconf= "+xmlReaderGetConfiance(file));
        } catch (SQLException ex) {
            Logger.getLogger(FilterChoise.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
       
    
    }
    public String xmlReaderGetConfiance(File fXmlFile){
        
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

			line=eElement.getElementsByTagName("confiance").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   
    }
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
    
    public void xmlReaderSetConfiance(String path,String line) throws SAXException, IOException, XPathExpressionException, TransformerException{
        
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(new File(path));
            
//Now, there are a few ways to do this, but simply, you can use the xpath API to find the nodes you want and update their content

XPath xPath = XPathFactory.newInstance().newXPath();
Node startDateNode = (Node) xPath.compile("/definitions/types/propriétés").evaluate(doc, XPathConstants.NODE);
startDateNode.setTextContent("29/07/2015");
Transformer tf = TransformerFactory.newInstance().newTransformer();
tf.setOutputProperty(OutputKeys.INDENT, "yes");
tf.setOutputProperty(OutputKeys.METHOD, "xml");
tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

DOMSource domSource = new DOMSource(doc);
StreamResult sr = new StreamResult(new File("Data.xml"));
tf.transform(domSource, sr);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FilterChoise.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(FilterChoise.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
    }
        public void insertIntoDb(String filename,String User,String Domain) throws FileNotFoundException, SQLException, ClassNotFoundException, IOException{
     File file= new File(filename);


//PreparedStatement statement = conecter().prepareStatement("INSERT INTO Fournisser (file) VALUES (?) where UserName='"+UserName+"'and PassWord='"+PassWord+"'");
//statement.setBlob(1, inputStream);
     
        
        //DataInputStream dataIs = new DataInputStream(new FileInputStream(f1));
        PreparedStatement pst = conecter().prepareStatement("UPDATE fournisseur SET fichier = ? where (fournisseur_nom= '"+User+"') and (domain_nom= '"+Domain+"')");
        
                              FileInputStream input=new FileInputStream(file);
                               
                               pst.setBinaryStream(1,input,(int) file.length());
                               
                             
                                pst.executeUpdate();
                                pst.close();
    //pst.setInt(1,67);
      
       System.out.println("Reading file " + file.getAbsolutePath());
            System.out.println("Store file in the database.");
        
        System.out.println(" database.");
        
    
    
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
       public static void AddElemnt(String filename,String Réputation,String conf,String Expérience,String Disponibilité,String Coût,String TempsDeRéponse,String Risques,String Tmr) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException, TransformerConfigurationException, TransformerException{
    
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
        confiance.appendChild(doc.createTextNode(conf));
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
       
 
    public void getFiles(String Domain) throws FileNotFoundException, SQLException, IOException{
        // DELETE DERECTORY
            Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
        PreparedStatement ps1=conecter().prepareStatement("SELECT fournisseur_nom FROM fournisseur where domain_nom='"+Domain+"'"); 
        ResultSet rs1=ps1.executeQuery();
        while(rs1.next()){
            
        File file=new File(path+rs1.getString(1)+".xml");
        FileOutputStream fos=new FileOutputStream(file);
        byte b[];
        Blob blob;

        PreparedStatement ps=conecter().prepareStatement("SELECT fichier FROM fournisseur where fournisseur_nom='"+rs1.getString(1)+"'"); 
        ResultSet rs=ps.executeQuery();

        if(rs.next()){
            blob=rs.getBlob("fichier");
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);
        }

        ps.close();
        fos.close();
        conecter().close();}
    }
    /**
     * @param args the command line arguments
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
            String Domain=domain.getSelectedItem().toString();
            String UserF=jTextField1.getText();
            
            asember(Domain,UserF,UserC);
            
        } catch (SQLException ex) {
            Logger.getLogger(FilterChoise.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public String xmlReder(File fXmlFile){
       String line=null;
       try {

	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nL = doc.getElementsByTagName("types");	
	NodeList nList = doc.getElementsByTagName("propriétés");
			
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
				
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
				
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;

			line="Fournisseur name :"+fXmlFile.getName() +"\n réputation : " + eElement.getElementsByTagName("réputation").item(0).getTextContent()+
                                "\n confiance : " + eElement.getElementsByTagName("confiance").item(0).getTextContent()+
			"\n expérience : " + eElement.getElementsByTagName("expérience").item(0).getTextContent()+
			"\n disponibilité : " + eElement.getElementsByTagName("disponibilité").item(0).getTextContent()+
                        "\n coût : " + eElement.getElementsByTagName("coût").item(0).getTextContent()+
                        "\n temps de réponse : " + eElement.getElementsByTagName("temps_de_réponse").item(0).getTextContent()+
                        "\n risques : " + eElement.getElementsByTagName("risques").item(0).getTextContent()+
                        "\n temps moyen de représentation : " + eElement.getElementsByTagName("temps_moyen_de_représentation").item(0).getTextContent();
                        

		}
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
       return line;
   }
     public void TextView(String line) throws FileNotFoundException, IOException{
   
                    jTextArea1.setText(null);
                    jTextArea1.append(line);
                    jTextArea1.append("\n");
          
   }

    public  ListWD findNearestNumber(ArrayList<ListWD> array){

    ListWD min ,max ,nearestNumber;
    int myNumber=8;
    min=new ListWD(0, "");
    min.moy=array.get(0).moy;
    min.filename=array.get(0).filename;
    for(int i=1;i<array.size();i++)
    {
        ListWD data=array.get(i);
        if(data.moy<min.moy)
        {
            
            
                min.moy=data.moy;
                min.filename=data.filename;
            
           
        }
        
        }
    return min;
    }

    
    /**
     * @param args the command line arguments
     */
    
ArrayList<ListFonction>  A;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Filter;
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> domain;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jt1;
    private javax.swing.JTextField jt2;
    private javax.swing.JTextField jt3;
    private javax.swing.JTextField jt4;
    private javax.swing.JTextField jt5;
    private javax.swing.JTextField jt6;
    private javax.swing.JTextField jt7;
    private javax.swing.JTextField jt8;
    // End of variables declaration//GEN-END:variables
}
