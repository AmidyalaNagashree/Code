/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionpaper;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.ui.RefineryUtilities;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class MainUIFinal extends javax.swing.JFrame {

    ArrayList<String> allQuestionsList = new ArrayList<String>();
    
    ArrayList<String> preProcessingWords = new ArrayList<String>();  
    ArrayList<String> allCleanedQuestions = new ArrayList<String>();
   
    ArrayList<ArrayList<String>> allTokensInds = new ArrayList<ArrayList<String>>(); 
    
    ArrayList<ArrayList<String>> allTokensIndsNLP = new ArrayList<ArrayList<String>>();
    ArrayList<Integer> allQuestionIndexes = new ArrayList<Integer>();
    
    ArrayList<String> allPreQuetionTags = new ArrayList<String>();
    
    String EOL = "\r\n";
    
    String allQuetsionFrameString = "";
    public MainUIFinal() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        gta = new javax.swing.JButton();
        gq = new javax.swing.JButton();
        gq1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("LOAD DATASET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(90, 140, 529, 35);

        gta.setText("GENERATE TOKENS AND CLEAN THE DATA(PRE PROCESSING)");
        gta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gtaActionPerformed(evt);
            }
        });
        getContentPane().add(gta);
        gta.setBounds(130, 230, 427, 23);

        gq.setText("GENERATE QUESTIONS");
        gq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gqActionPerformed(evt);
            }
        });
        getContentPane().add(gq);
        gq.setBounds(200, 320, 311, 23);

        gq1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gq1.setText("GENERATE QUESTIONS");
        gq1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gq1ActionPerformed(evt);
            }
        });
        getContentPane().add(gq1);
        gq1.setBounds(220, 400, 248, 34);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Question paper Analytical Process");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 20, 488, 56);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/questionpaper/at1.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 700, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      try{
          
          long startTime=System.currentTimeMillis();
        allQuestionsList.clear();  
        FileReader fr = new FileReader("dataset60.txt"); 
        BufferedReader br = new BufferedReader(fr); 
        String str = "";
        while((str = br.readLine()) != null){
           allQuestionsList.add(str);
           //System.out.println(str);
        }
        
        //System.out.println(allQuestionsList.size());
        //cleaning code starts...
        preProcessingWords.clear();
        allCleanedQuestions.clear();
        FileInputStream fis = new FileInputStream("preprocess.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);   
        fis.close();
        String allPreProKeywords = new String(bb);
        allPreProKeywords = allPreProKeywords.trim();
        StringTokenizer st = new StringTokenizer(allPreProKeywords,EOL);
        while(st.hasMoreTokens()){
           preProcessingWords.add(st.nextToken()); 
        }
        
        String cleanedText = "";
	String tempText = "";
        for(int i=0;i<allQuestionsList.size();i++){
            tempText = allQuestionsList.get(i);
            
            for(int j=0;j<preProcessingWords.size();j++){
             cleanedText = tempText.replace(preProcessingWords.get(j), "");	
	     tempText  = cleanedText;
            }
            allCleanedQuestions.add(cleanedText);
        }
        
        //cleaning code ends;.
        for(int i=0;i<allCleanedQuestions.size();i++){
            System.out.println(allCleanedQuestions.get(i));
        }
        
        allQuestionsList.clear();
        allQuestionsList = allCleanedQuestions;
         long endTime = System.currentTimeMillis();
            long proceCloudTime = endTime - startTime;
            String processCloudTime = proceCloudTime+"";
            FileOutputStream fos1 = new FileOutputStream("ftime.txt");
            fos1.write(processCloudTime.getBytes());
            fos1.close();
        JOptionPane.showMessageDialog(null, "Dataset loaded into Memory");
      Thread.sleep(1000);
      }catch(Exception e){
        System.out.println(e);
    }
      
     // JOptionPane.showMessageDialog(null, "NLP And SVN Activated");
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void gtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gtaActionPerformed
       try{  
           
           allTokensInds.clear();           
           for(int i=0;i<allQuestionsList.size();i++){
               
               String question = allQuestionsList.get(i);
               ArrayList<String> allTokens = new ArrayList<String>();
               allTokens.clear();
               StringTokenizer st = new StringTokenizer(question," ");
               while(st.hasMoreTokens()){
               allTokens.add(st.nextToken());
               }
               //System.out.println(allTokens);
               allTokensInds.add(allTokens);             
           }     
           for(int i=0;i<allTokensInds.size();i++){
               //System.out.println(allTokensInds.get(i));
           }
           
           
           
           JOptionPane.showMessageDialog(null, "Preprocessing and tokens generation is done.!");
       }catch(Exception e){
           System.out.println(e);
       }
    }//GEN-LAST:event_gtaActionPerformed

    private void gqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gqActionPerformed
       try{
       String questionPaperFinal = "";  
       String strNumber = JOptionPane.showInputDialog(null,"Enter number of questions to be generated?");
       int questionCount = Integer.parseInt(strNumber);
       if(questionCount>50){
       JOptionPane.showMessageDialog(null, "sorry count should be below");
       }
       else{
           System.out.println(questionCount);
           allQuestionIndexes.clear();
           for(int i=1;i<=questionCount;i++){
               Random random = new Random();
               int temIndex = random.nextInt(questionCount-1) + 1;
               allQuestionIndexes.add(temIndex);
           }
       }
       for(int i=0;i<allQuestionIndexes.size();i++){
           System.out.print(allQuestionIndexes.get(i));
       }
       
       for(int i=0;i<allQuestionIndexes.size();i++){
          ArrayList<String> questionsTokens = new ArrayList<String>(); 
          questionsTokens.clear();
          int index = allQuestionIndexes.get(i);
          String question = allQuestionsList.get(index-1);
          questionsTokens = allTokensInds.get(index-1);
          questionPaperFinal += question+EOL;   
          for(int j=0;j<5;j++){
             Collections.shuffle(questionsTokens); 
             questionPaperFinal += (j+1)+") ";
             for(int k=0;k<questionsTokens.size();k++){                
                questionPaperFinal += questionsTokens.get(k)+" ";
             }
             
             questionPaperFinal += " ?";
             questionPaperFinal += EOL;
          }
          
          questionPaperFinal +=EOL+"-----------------------------"+EOL;
          
       }
       
       FileOutputStream fos = new FileOutputStream("finalpaper.txt");
       fos.write(questionPaperFinal.getBytes());
       fos.close();
       
       Runtime r= Runtime.getRuntime();
       r.exec("notepad finalpaper.txt");
       }catch(Exception e){
           System.out.println(e);
       }
    }//GEN-LAST:event_gqActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
    try{
        
        gq.setVisible(false);
        gta.setVisible(false);
    }catch(Exception e){
           System.out.println(e);
       }
    }//GEN-LAST:event_formComponentShown

    private void gq1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gq1ActionPerformed
        try{
            long startTime=System.currentTimeMillis();
        allTokensIndsNLP.clear();
        allPreQuetionTags.clear();
        InputStream is = new FileInputStream("en-sent.bin");
        SentenceModel model = new SentenceModel(is);        
        SentenceDetectorME sdetector = new SentenceDetectorME(model);        
        for(int i=0;i<allQuestionsList.size();i++){
            String sentences[] = sdetector.sentDetect(allQuestionsList.get(i));
            ArrayList<String> allTokens = new ArrayList<String>();
            allTokens.clear();            
            for(int j=0;j<sentences.length;j++){
                String subToken = sentences[j];
                String toAdd = subToken.replace(".", "");
                allTokens.add(toAdd);
            }
            allTokensIndsNLP.add(allTokens);                        
        }
        FileInputStream fis = new FileInputStream("questions.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();
        
        String questionData = new String(bb);
        StringTokenizer st = new StringTokenizer(questionData,EOL);
        while(st.hasMoreTokens()){
            allPreQuetionTags.add(st.nextToken());
        }
        
        allQuetsionFrameString="";
        
        for(int i=0;i<allTokensIndsNLP.size();i++){
            ArrayList<String> allTokensTemp = new ArrayList<String>();
            allTokensTemp.clear();            
            allTokensTemp = allTokensIndsNLP.get(i);
            allQuetsionFrameString += allQuestionsList.get(i)+EOL;
            for(int j=0;j<5;j++){
            Collections.shuffle(allTokensTemp);
            Collections.shuffle(allPreQuetionTags);
            allQuetsionFrameString += allPreQuetionTags.get(0)+" ";
            for(int k=0;k<allTokensTemp.size();k++){
                allQuetsionFrameString += allTokensTemp.get(k)+" ";
            }
             allQuetsionFrameString += "?"+EOL;
             }
            allQuetsionFrameString += EOL+"----------------------------------"+EOL;
        }
        
        
        FileOutputStream fos = new FileOutputStream("allquestionbank.txt");
        fos.write(allQuetsionFrameString.getBytes());
        fos.close();
        
        Runtime r = Runtime.getRuntime();
        r.exec("notepad allquestionbank.txt");
        
        
        long endTime = System.currentTimeMillis();
            long proceCloudTime = endTime - startTime;
            String processCloudTime = proceCloudTime+"";
            FileOutputStream fos1 = new FileOutputStream("ltime.txt");
            fos1.write(processCloudTime.getBytes());
            fos1.close();
            
            FileInputStream fis11 = new FileInputStream("ftime.txt");
            byte bb11[] = new byte[fis11.available()];
            fis11.read(bb11);
            fis11.close();							
            String ffTime = new String(bb11);
            Long ProcessTime1 = Long.parseLong(ffTime);
           
            FileInputStream fis22 = new FileInputStream("ltime.txt");
            byte bb22[] = new byte[fis22.available()];
            fis22.read(bb22);
            fis22.close();							
            String llTime = new String(bb22);
            Long ProcessTime2 = Long.parseLong(llTime);
            
            
            Long TotalProcessTime=ProcessTime1+ProcessTime2;
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null, "Total Time for processing Your Data is :"+TotalProcessTime+"ms");
           
            PieChartAnalisys demo1 = new PieChartAnalisys( "Question Paper Genaration " ,ProcessTime1,ProcessTime2);  
            demo1.setSize( 560 , 367 );    
            RefineryUtilities.centerFrameOnScreen( demo1 );    
            demo1.setVisible( true ); 
        
    }catch(Exception e){
           System.out.println(e);
       }
    }//GEN-LAST:event_gq1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainUIFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUIFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUIFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUIFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              MainUIFinal mm =  new MainUIFinal();
              mm.setVisible(true);
              mm.setSize(700,530);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gq;
    private javax.swing.JButton gq1;
    private javax.swing.JButton gta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
