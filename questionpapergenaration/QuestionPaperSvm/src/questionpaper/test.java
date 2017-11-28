/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionpaper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author comp
 */
public class test
{
    public void frameQuestion()
    {
        try{
            ArrayList< String> qword=new ArrayList<String>();
            FileInputStream fis=new FileInputStream("questions.txt");
            byte bb[]=new byte[fis.available()];
            fis.read(bb);
            fis.close();
            
            String qlist=new String(bb);
            qlist.trim();
            String EOL="\r\n";
            StringTokenizer qstr=new StringTokenizer(qlist ,EOL);
            while(qstr.hasMoreTokens())
            {
                qword.add(qstr.nextToken());
                
            }
            System.out.println("Question Words are:"+qword);
            
    }catch(Exception e)
    {
        System.out.println("Exception "+e);
    }
    }
    public static void main(String[] args) {
        
        test t=new test();
        t.frameQuestion();
    }
    
}
