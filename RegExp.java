package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
public static void main(String []args) throws IOException{
		
		
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/oleg/Desktop/1.txt"));
        String line;
        int f = 0;
        
        while ((line = reader.readLine()) != null) {
         Pattern p=Pattern.compile("[a-z]{1,}[a-z0-9]+[a-z]{1,}\\s{3}[a-z]{1,}[a-z0-9]+[a-z]{1,}");
        	Matcher m=p.matcher(line);
        	 while(m.find()){
        		 f++; }
         
        } System.out.println("found : " + f);
		
	
	}

}
