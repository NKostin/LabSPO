# LabSPO
package lab1;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tray {
	public static void main(String []args) throws IOException{
		
		
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/oleg/Desktop/1.txt"));
        String line;
        int f = 0;
        
        while ((line = reader.readLine()) != null) {
         Pattern p=Pattern.compile("[a-zA-Z]{1,}\\d+[a-zA-Z]{1,}+\\s{3}+[a-zA-Z]{1,}\\d+[a-zA-Z]{1,}");
        	Matcher m=p.matcher(line);
        	 while(m.find()){
        		 f++; }
         
        } System.out.println("found : " + f);
		
	
	}

}
