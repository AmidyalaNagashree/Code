/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionpaper;
 import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
class ServerClientThreadFinal extends Thread {
  Socket serverClient;
  int clientNo;
  int squre;
  int serNumber;
  ServerClientThreadFinal(Socket inSocket,int counter,int serverNumber){
    serverClient = inSocket;
    clientNo=counter;
    serNumber = serverNumber;
  }
  public void run(){
    try{
        String EOL = "\r\n";
      DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
      DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
      
       String clientMessage="", serverMessage="";
        //serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage + " is " +squre;
       while(!clientMessage.equals("bye")){
        if(serNumber == 1){
           FileInputStream fis = new FileInputStream("keywordsnormalML.txt");
           byte bb[] = new byte[fis.available()];
           fis.read(bb);
           fis.close();
           
           String fileData = new String(bb);
           fileData = fileData.trim();
           
           StringTokenizer st = new StringTokenizer(fileData,EOL);
           while(st.hasMoreTokens()){
             serverMessage += st.nextToken()+" ";
           }
           
           serverMessage = serverMessage.trim();
       }
       
       else if(serNumber == 2){
           FileInputStream fis = new FileInputStream("dict2.txt");
           byte bb[] = new byte[fis.available()];
           fis.read(bb);
           fis.close();
           
           String fileData = new String(bb);
           fileData = fileData.trim();
           
           StringTokenizer st = new StringTokenizer(fileData,EOL);
           while(st.hasMoreTokens()){
             serverMessage += st.nextToken()+" ";
           }
           
           serverMessage = serverMessage.trim();
       }
       
        outStream.writeUTF(serverMessage);
        outStream.flush();
    }
      inStream.close();
      outStream.close();
      serverClient.close();
    }catch(Exception ex){
      System.out.println(ex);
    }finally{
      System.out.println("Client -" + clientNo + " exit!! ");
    }
  }
}

