/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionpaper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 * Sentence Detection Example in openNLP using Java
 * @author tutorialkart
 */
public class SentenceDetect {
    
    
	public static void main(String[] args) {
		try {
			new SentenceDetect().sentenceDetect();
                        
                   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to detect sentences in a paragraph/string
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void sentenceDetect() throws InvalidFormatException,	IOException {
		
            
            //String paragraph = "computer systems allow multiple programs. to be loaded into memory. and executed concurrently. ";

            
            String paragraph = "Early computer systems. allow only one program.  be execut at a time.";
            
		// refer to model file "en-sent,bin", available at link http://opennlp.sourceforge.net/models-1.5/
		InputStream is = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(is);
		
		// feed the model to SentenceDetectorME class 
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		
		// detect sentences in the paragraph
		String sentences[] = sdetector.sentDetect(paragraph);

		// print the sentences detected, to console
		for(int i=0;i<sentences.length;i++){
			System.out.println(sentences[i]);
		}
		is.close();
	}
}