/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.File;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sofia
 */
public class AHP extends javax.swing.JFrame {

    /**
     * Creates new form AHP
     */
    String UserC,Password;
    public AHP() {
        initComponents();
           
    }
     public AHP(String UserC,String Password) {
         this.UserC=UserC;
         this.Password=Password;
        initComponents();
           
    }
    public Double[][] getTableData (JTable table) {
    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
    Double[][] tableData = new Double[nRow][nCol];
    for (int i = 0 ; i < nRow ; i++){
        for (int j = 0 ; j < nCol ; j++){
                tableData[i][j] = Double.parseDouble(dtm.getValueAt(i,j).toString());
            
        }}
    return tableData;
}
    public void printvalue(Double[][] table,String name){
        System.out.println("Table Contenu : "+name);
        for (int i = 0 ; i < table.length ; i++){
            System.out.println("[");
            String s=" ";
        for (int j = 0 ; j < table[i].length ; j++){
                s=s+"t= "+table[i][j];
        }
        System.out.println(s+"]");
        }
        System.out.println("-------------------");
        System.out.println("END Table Contenu");
    }
    // bdit mena
    public Double[][] Weights(Double[][] table) {
         
    
    Double[][] tableData = new Double[3][1];
    
    for (int i = 0 ; i < table.length ; i++){
        Double mult=1.0;
        for (int j = 0 ; j < table[i].length ; j++){
            mult=mult*table[i][j];
        }
        tableData[i][0]=Math.pow(mult,0.333);
    }
    
    
      
    return tableData;
    }
    public Double WeightsSum(Double[][] table){
    Double sumWeight=0.0;
    for (int i = 0 ; i < table.length ; i++){
        sumWeight=sumWeight+table[i][0];
    }
    return sumWeight;
    }
    public Double[][] PriorityVector(Double[][] table){
        Double WS=WeightsSum(table);
        Double[][] tableData=new Double[3][1];
          for (int i = 0 ; i < table.length ; i++){
                tableData[i][0]=table[i][0]/WS;
                
                }
          return tableData;
    } 
    public Double PriorityVectorSum(Double[][] table){
        Double sumPriority=0.0;
    for (int i = 0 ; i < table.length ; i++){
        sumPriority=sumPriority+table[i][0];
    }
    return sumPriority;
    }
    public Double[][]SumParColumn(Double[][] table){
        int ligne=table.length;
        int cloumn=table.length;
        Double[][]tableData=new Double[1][3];

        for (int i = 0 ; i < cloumn ; i++){
                    double sum=0.0;
            for (int j = 0 ; j <ligne  ; j++){
                sum=sum+table[j][i];
            }
            tableData[0][i]=sum;
        }
        return tableData;
    }
    public Double[][]SumParColumn_Mult_PriorityVector(Double[][] table,Double[][] table2){
        Double[][]tableData=new Double[1][3];
        for (int i = 0 ; i <3  ; i++){
           tableData[0][i]=table[0][i]*table2[i][0];
            }
        return tableData;
    } 
    public Double LandaMax(Double[][] table){
        Double sum=0.0;
        for (int j = 0 ; j <table.length  ; j++){
    for (int i = 0 ; i <table[j].length  ; i++){
        sum=sum+table[0][i];
    }}
    return sum;
    }
    public Double CI(Double[][] table,Double LandaMax){
        int n=table.length;
        Double CI=(LandaMax-n)/(n-1);
        return CI;
    }
    public Double CR(Double[][]table,Double CI){
        int n=table.length;
        Double RI=0.0;
        switch(n){
            case 1:RI=0.00;
                break;
            case 2: RI=0.00;
                break;
            case 3: RI=0.58;
                break;
            case 4: RI=0.90;
                break;
            case 5: RI=1.12;
                break;
            case 6: RI=1.24;
                break;
            case 7: RI=1.32;
                break;
            case 8: RI=1.41;
                break;
            case 9: RI=1.45;
                break;    
        }
        Double CR=CI/RI;
      return CR;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "0.125", "0.333"},
                {"8", "1", "3"},
                {"3", "0.333", "1"}
            },
            new String [] {
                "reputation", "disponiblité", "experience"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("value equal to 1 stands for the equally important;\n3 stands for moderately important;\n5 stands for strongly important;\n7 stands for the demonstrably\nimportant, and 9 stands for extremely important.\nAt the same time, an intermediary number is feasible \n(such as 2, 4, 6, 8)");
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("validate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addContainerGap(901, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public Double[][] Work(Double[][] tabledata){
         
        Double [][]tableWeights=Weights(tabledata);
        printvalue(tableWeights,"Weight");
        Double WS=WeightsSum(tableWeights);
        System.out.println(WS+"WeightsSum");
        Double [][]tablePriorityVector=PriorityVector(tableWeights);
        printvalue(tablePriorityVector,"PriorityVector");
        Double PVS=PriorityVectorSum(tablePriorityVector);
        System.out.println(PVS+"PriorityVectorSum");
        Double [][]tableSumParColumn=SumParColumn(tabledata);
        Double [][]tableSPC=SumParColumn_Mult_PriorityVector(tableSumParColumn,tablePriorityVector);
        printvalue(tableSumParColumn,"SumParColumn");
        printvalue(tableSPC,"SumParColumn_Mult_PriorityVector");
        Double landamax=LandaMax(tableSPC);
        System.out.println(landamax+"landa");
        Double CI=CI(tabledata,landamax);
        System.out.println(CI+"CI");
        Double CR=CR(tabledata, CI);
        System.out.println(CR+"CR");
        
        return tablePriorityVector;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            Double [][]tabledata=getTableData(jTable1);
        printvalue(tabledata,"Normal");
           Double [][]priorityCritére= Work(tabledata);
            Double[][]ta=CreateCS();
            printvalue(ta, "cs");
            Double[][]rep=CreateCS_reputation(ta);
            printvalue(rep, "reputation");
            Double[][]exp=CreateCS_experience(ta);
            printvalue(exp, "experience");
            Double[][]dis=CreateCS_disponibilité(ta);
            printvalue(dis, "disponibilité");
            Double [][]priorityRep= Work(rep);
            Double [][]priorityExp= Work(exp);
            Double [][]priorityDis=  Work(dis);
            Double[][]score=Score(priorityCritére, priorityRep, priorityExp, priorityDis);
            printvalue(score, "score");
            System.out.println("la somme du score"+(somScore(score)));
            System.out.println(BestScore(score));
            jTextArea1.setText("Le meilleur score appartient a : "+BestScore(score));
        } catch (SQLException ex) {
            Logger.getLogger(AHP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        new ControlPanelClient(UserC, Password).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    public Double[][]Score(Double [][]table,Double [][]table2,Double [][]table3,Double [][]table4){
       Double[][] tabledata=new Double[3][1];
       for(int i=0;i<3;i++){
           
           if(i==0)
           tabledata[i][0]=(table[i][0]*table2[i][0])+(table[i+1][0]*table3[i][0])+(table[i+2][0]*table4[i][0]);
           if(i==1)
           tabledata[i][0]=(table[i-1][0]*table2[i][0])+(table[i][0]*table3[i][0])+(table[i+1][0]*table4[i][0]);
           if(i==2)
           tabledata[i][0]=(table[i-2][0]*table2[i][0])+(table[i-1][0]*table3[i][0])+(table[i][0]*table4[i][0]);    
       }
       return tabledata;
    }
    public Double somScore(Double[][]table ){
        Double somme=0.0;
        for(int i=0;i<3;i++){
            somme=somme+table[i][0];
        }
        return somme;
    }
    public String BestScore(Double[][]table){
        Double max=0.0;
        for(int i=0;i<3;i++){
            if(table[i][0]>max){
                max=table[i][0];
            }
        }
        for(int i=0;i<3;i++){
            if(i==0&&max==table[i][0]){return "isam";}
            if(i==1&&max==table[i][0]){return "sofiane";}
            if(i==2&&max==table[i][0]) {return "younes";}  
        }
        
        return null;
    }
    public Double [][]CreateCS_reputation(Double [][]table){
        {
Double[][] tab=new Double[3][3] ;
Double valeur=0.0;

for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
        
        
          
       if(i==j){tab[i][j]=1.0;}
       else if(i<j){
         valeur=table[0][i] /table[0][j];
        if(valeur<1){valeur=limen_9riba(valeur);}else{
           valeur= Math.round(valeur*1.0)/1.0;
        }
        tab[i][j]=valeur;

       }else{
        valeur= table[0][i] /table[0][j];
       
       if(valeur>1){
           
       tab[i][j]=Math.round(valeur*1.0)/1.0;
       }
       else{
       tab[i][j]=limen_9riba(valeur);
       }
       
       
       }
}   
    
}  
return tab;
 }
    } 
    public Double [][]CreateCS_experience(Double [][]table){
        {
Double[][] tab=new Double[3][3] ;
Double valeur=0.0;

for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
        
        
          
       if(i==j){tab[i][j]=1.0;}
       else if(i<j){
         valeur=table[1][i] /table[1][j];
        if(valeur<1){valeur=limen_9riba(valeur);}else{
           valeur= Math.round(valeur*1.0)/1.0;
        }
        tab[i][j]=valeur;

       }else{
        valeur= table[1][i] /table[1][j];
       
       if(valeur>1){
           
       tab[i][j]=Math.round(valeur*1.0)/1.0;
       }
       else{
       tab[i][j]=limen_9riba(valeur);
       }
       
       
       }
}   
    
}  
return tab;
 }
    } 
    public Double [][]CreateCS_disponibilité(Double [][]table){
        {
Double[][] tab=new Double[3][3] ;
Double valeur=0.0;

for(int i=0;i<3;i++){
    for(int j=0;j<3;j++){
        
        
          
       if(i==j){tab[i][j]=1.0;}
       else if(i>j){
         valeur=table[2][i] /table[2][j];
        if(valeur<0.7){valeur=limen_9riba(valeur);}else{
           valeur= Math.round(valeur*1.0)/1.0;
        }
        tab[i][j]=valeur;

       }else{
        valeur= table[2][i] /table[2][j];
       
       if(valeur>1){
           
       tab[i][j]=Math.round(valeur*1.0)/1.0;
       }
       else{
       tab[i][j]=limen_9riba(valeur);
       }
       
       
       }
}   
    
}  
return tab;
 }
    } 
    public Double [][]CreateCS() throws SQLException, IOException{
            getFiles();
            File dir = new File(path);
            Double [][] table=new Double[3][3];
            //réputation
            int i = 0,j=0;
            for (File fXmlFile : dir.listFiles()) {
                
                table[i][j]=Double.parseDouble(xmlReaderGetReputation(fXmlFile));
               table[i+1][j]= Double.parseDouble(xmlReaderGetExperience(fXmlFile));
                table[i+2][j]=Double.parseDouble(xmlReaderGetDisponibilite(fXmlFile));
                j=j+1;
                
            }
            return table;
    }
    public Double limen_9riba(Double v){
    Double[] valeur=new Double[9];
    Double v1,v2,v3,v4,v5,v6,v7,v8;
    valeur[1]=Math.abs(v-0.5);
   valeur[2]=Math.abs(v-0.333);
   valeur[3]=Math.abs(v-0.25);
   valeur[4]=Math.abs(v-0.2);
   valeur[5]=Math.abs(v-0.166);
   valeur[6]=Math.abs(v-0.142);
   valeur[7]=Math.abs(v-0.125);
   valeur[8]=Math.abs(v-0.111);
 Double min= valeur[1];
 for(int i=2;i<8;i++){
 if(valeur[i]<min)min=valeur[i];

 }
    return v-min;
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

        PreparedStatement ps=conecter().prepareStatement("SELECT fichier FROM fournisseur where fournisseur_nom='"+UserF+"' and domain_nom='"+Domain+"' and (fichier  IS NOT NULL) LIMIT 3"); 
        ResultSet rs=ps.executeQuery();
           int i=0;
        if(rs.next()){
            if(i<3){
            blob=rs.getBlob("fichier");
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);}
        }

        ps.close();
        fos.close();
        conecter().close();
    }
     public Connection conecter() throws SQLException{
            String url="jdbc:mysql://localhost:3306/saass" ;
         String user="root";
         String pass="";
          Connection con= DriverManager.getConnection(url,user,pass);
         return con;
        }
//    
     
     public void getFiles() throws FileNotFoundException, SQLException, IOException{
          Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .forEach(File::delete);
        PreparedStatement ps1=conecter().prepareStatement("SELECT fournisseur_nom FROM Fournisseur where (fichier  IS NOT NULL) LIMIT 3"); 
        ResultSet rs1=ps1.executeQuery();
        while(rs1.next()){
            
        File file=new File(path+rs1.getString(1)+".wsdl");
        FileOutputStream fos=new FileOutputStream(file);
        byte b[];
        Blob blob;

        PreparedStatement ps=conecter().prepareStatement("SELECT fichier FROM Fournisseur where fournisseur_nom='"+rs1.getString(1)+"'"); 
        ResultSet rs=ps.executeQuery();
        int i=0;
        if(rs.next()){
            if(i<3){
            blob=rs.getBlob("fichier");
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);}
        }

        ps.close();
        fos.close();
        conecter().close();}
    }
     String path="C:\\Users\\sofia\\Documents\\NetBeansProjects\\JavaApplication6\\filesWsdl\\";

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AHP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AHP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

 
        
    
}
