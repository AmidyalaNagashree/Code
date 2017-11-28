package questionpaper;
import java.io.*;
import java.util.*;
public class GetTokens {
    ArrayList<String> allTokens = new ArrayList<String>();
    String fromMain = "";
    public ArrayList<String> getAllTokens(String strData){
      allTokens.clear();
      fromMain = strData;
      StringTokenizer st = new StringTokenizer(fromMain," ");
      while(st.hasMoreTokens()){
        allTokens.add(st.nextToken());  
      }
      return allTokens;
    }
    
}
