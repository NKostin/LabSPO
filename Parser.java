package ru.mirea.spo.lab1;

import java.util.Iterator;
import java.util.List;

public class Parser {
	private List<Token> tokens;
	private Token currentToken;
	private Iterator<Token> tokenIterator;
	
	
	
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
		tokenIterator=this.tokens.iterator(); 
		
	}
	
	
	public void lang() throws Exception {
		boolean exist =false;
		
		while(match()){
			if(expr()){
				if (!sm()){
					throw new Exception(currentToken + "found, but SM expected");
				}
			}else {
				throw new Exception(currentToken + "found, but expr expected");
			}
			
			exist = true;
		}
		if(!exist){
			throw new Exception("error in lang");
		}
		
	}
	
	
	public boolean expr() throws Exception {
		if(declare()||assign()){
			return true;
		}else {
			throw new Exception("declare or assign expected, but "
					+currentToken +"found.");
		}
		
		
	}
	
	
	public boolean declare() throws Exception{
		if(varKw()){
			if(!var()){
				throw new Exception("VAR expected, but "
						+currentToken +"found.");
			}
		} else {
			throw new Exception("VAR_KW  expected, but "
					+currentToken +"found.");
		}
			return true;
	}
	
	
	public boolean assign() throws Exception{
		if(var()){
			if(assignOp()){
				if(!stmt()){
					throw new Exception("stmt  expected, but "
							+currentToken +"found.");
				}
			} else{
				throw new Exception("assignOp  expected, but "
						+currentToken +"found.");
			}
			return true;
		}else {
			throw new Exception("assignOp  expected, but "
					+currentToken +"found.");
		}
		
	}
	
	
	public boolean stmt() throws Exception{
		if(stmtUnit()){
			while(plus()||minus()||umn()||del()){
				if(!stmtUnit()){
					throw new Exception("stmt_unit  expected, but "
							+ currentToken +"found.");
				}
			} 
			return true;
		}else {
			throw new Exception("stmt_unit  expected, but "
					+ currentToken +"found.");
		}
		
	}
	public boolean stmtUnit() throws Exception{
		if(digit()||var()){
			return true;
		} else{
			throw new Exception("DIGIT or VAR expected, but "+currentToken +"found.");
		}
		
	}
	
	public boolean sm()  {
		match();
		return currentToken.getName().equals("SM");
	}
	public boolean varKw(){
		match();
		return currentToken.getName().equals("VAR_KW");
	}
	public boolean assignOp() {
		match();
		return currentToken.getName().equals("ASSIGN_OP");
	}
/*	public boolean op() {
		match();
		return currentToken.getName().equals("OP");
	}
	*/
	
	public boolean plus() {
		match();
		return currentToken.getName().equals("PL");
	}
	
	public boolean minus() {
		match();
		return currentToken.getName().equals("MN");
	}
	public boolean umn() {
		match();
		return currentToken.getName().equals("UMN");
	}
	public boolean del() {
		match();
		return currentToken.getName().equals("DL");
	}
	
	public boolean digit() {
		match();
		return currentToken.getName().equals("DIGIT");
	}
	public boolean var() {
		match();
		return currentToken.getName().equals("VAR");
	}
	
	public boolean match(){
		if(tokenIterator.hasNext()){
			currentToken = tokenIterator.next();
			return true;
		}
		return false;
	}
	


}
