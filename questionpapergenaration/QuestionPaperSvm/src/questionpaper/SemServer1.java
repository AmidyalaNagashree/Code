
package questionpaper;
 
 
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
public class SemServer1 {
  public static void main(String []args) {
    try{
      ServerSocket server=new ServerSocket(8888);
      int counter=0;
      JOptionPane.showMessageDialog(null,"SEMANTEC SERVER1 STARTED..");
      while(true){
        counter++;
        Socket serverClient=server.accept();  //server accept the client connection request
        System.out.println(" >> " + "Client No:" + counter + " started!");
        ServerClientThreadFinal sct = new ServerClientThreadFinal(serverClient,counter,1); //send  the request to a separate thread
        sct.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}


