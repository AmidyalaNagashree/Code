
package questionpaper;
import java.io.*;
import java.util.*;
 class GetMaxLengthIndex {
    int index = 0; 
    String EOL = "\r\n";
    ArrayList<String> wholeDataContainer = new ArrayList<String>();
    ArrayList<String> keyContainers = new ArrayList<String>();
    ArrayList<Integer> keyContainersIndexes = new ArrayList<Integer>();    
    ArrayList<Integer> keyContainersLengths = new ArrayList<Integer>(); 
    public int getMaxIndexForKey(String key) {
        try{
        wholeDataContainer.clear();
        keyContainers.clear();
        keyContainersIndexes.clear();
        keyContainersLengths.clear();
        FileInputStream fis = new FileInputStream("keywordsnormalML.txt");
        byte bb[] = new byte[fis.available()];
        fis.read(bb);
        fis.close();        
        String wholeContent = new String(bb);
        wholeContent = wholeContent.trim();
        StringTokenizer st = new StringTokenizer(wholeContent,EOL);
        while(st.hasMoreTokens()){
           wholeDataContainer.add(st.nextToken());
        }        
        for(int i=0;i<wholeDataContainer.size();i++){
            String line = wholeDataContainer.get(i);
            if(line.contains(key)){
                System.out.println(line);
              keyContainers.add(line);
              keyContainersIndexes.add(i); 
              keyContainersLengths.add(line.length());
            }
        }
        int maxLen = Collections.max(keyContainersLengths);
        int tempIndex = keyContainersLengths.indexOf(maxLen);
        index = keyContainersIndexes.get(tempIndex);
        }catch(Exception e){
            System.out.println(e);
        }
        return index;
    }
    
}
