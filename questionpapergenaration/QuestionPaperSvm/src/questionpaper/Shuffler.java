
package questionpaper;
import java.io.*;
import java.util.*;
public class Shuffler {
    
    String toMain = "";
    public String getSuffledWord(String strFromMain)   {
       try{
       String toToken = strFromMain;
       ArrayList<String> tokens = new ArrayList<String>();
       tokens.clear();
       StringTokenizer st = new StringTokenizer(strFromMain," ");
       while(st.hasMoreTokens()){
          tokens.add(st.nextToken());
       }
       
       Collections.shuffle(tokens);
       for(int i=0;i<tokens.size();i++){
           toMain +=tokens.get(i)+" ";
       }
       toMain = toMain.trim();
       }catch(Exception e){
           System.out.println(e);
       } 
        return toMain;
    }    
}
