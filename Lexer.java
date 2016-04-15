package ru.mirea.spo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import ru.mirea.spo.lab1.Token;



public class Lexer {
	List<Token> tokens = new ArrayList<Token>();
	String accum="";
	String currentLucky = null;
	
	int i;

	Pattern sm= Pattern.compile("^;$");
	Pattern varKeyWordPattern = Pattern.compile("^var$");
	Pattern assingOperationPattern = Pattern.compile("^=$");
	Pattern op_MUL = Pattern.compile("^\\*$");
	Pattern op_DIV = Pattern.compile("^\\/$");
	Pattern op_ADD = Pattern.compile("^\\+$");
	Pattern op_SUB = Pattern.compile("^\\-$");
	Pattern digitPattern = Pattern.compile("^0|[1-9]{1}[0-9]*$");
	Pattern varPattern = Pattern.compile("^[a-zA-Z]+$*");
	Pattern ws = Pattern.compile("^\\s*$");
	Pattern bracket_l = Pattern.compile("^\\($");
	Pattern bracket_r = Pattern.compile("^\\)$");
	//Pattern err = Pattern.compile("^!\\;|\\S$");

	    public static final String TRUE = "true";
	    public static final String FALSE = "false";
	    public static final String NUM = "num";
	    public static final String ADDRESS = "address";
	    public static final String LABEL = "label";
	    public static final String ID = "id";
	    public static final String NOT = "not";
	    public static final String OR = "or";
	    public static final String AND = "and";
	    public static final String GO = "go";
	    public static final String FGO = "fgo";
	    public static final String WRITE = "write";
	Map<String, Pattern> keyWord = new HashMap<String, Pattern>();
	Map<String, Pattern> termimal = new HashMap<String, Pattern>();
	

	
	public Lexer(){
		keyWord.put("VAR_KW", varKeyWordPattern);
		termimal.put("SM", sm);
		termimal.put("ASSING_OP", assingOperationPattern);
		termimal.put("OP_MULTI", op_MUL);
		termimal.put("OP_DIVISION", op_DIV);
		termimal.put("OP_ADDITION", op_ADD);
		termimal.put("OP_SUBTRACTION", op_SUB);
		termimal.put("DIGIT", digitPattern);
		termimal.put("VAR", varPattern);
		termimal.put("WS", ws);
		termimal.put("BRACKET LEFT", bracket_l);
		termimal.put("BRACKET RIGHT", bracket_r);
		//termimal.put("ERROR", err);
		
		
	}
	
	public void processInput(String filename) throws IOException {
		File file = new File(filename);
		Reader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		
		while((line = bufferedReader.readLine()) != null){
			
			processLine(line);
			
			
		}
		System.out.println("TOKEN("+currentLucky+") recognized with value:"+ accum);
		tokens.add(new Token(currentLucky, accum));
		for(Token token: tokens){
			System.out.println(token);
		}
	}
	
	private void processLine(String line) {
		for(i=0; i<line.length(); i++){
			accum = accum + line.charAt(i);
			//System.out.println(accum);
			processAccum();
		}
		
	}

	private void processAccum() {
		boolean found = false;
		for(String regExpName:termimal.keySet()){
			Pattern currentPattern = termimal.get(regExpName);
			Matcher m = currentPattern.matcher(accum);
			if(m.matches()){
				currentLucky = regExpName;
				
				found=true;
			}else{
			
				
			}
		}
		if(currentLucky!=null&&!found){
			System.out.println("TOKEN("+currentLucky+") recognized with value:"+ accum.substring(0, accum.length()-1));
			tokens.add(new Token(currentLucky, accum.substring(0, accum.length()-1)));
			i--;
			accum="";
			currentLucky = null;
		}else if (currentLucky==null&&!found){
		//currentLucky= "ERROR";
		
		//System.out.println("TOKEN("+currentLucky+") recognized with value:"+ accum.substring(0, accum.length()-1));
		//tokens.add(new Token(currentLucky, accum.substring(0, accum.length()-1)));
		
		accum="";}
	}
	public List<Token> getTokens(){
		return tokens;
	}


}
