package com.mie.util;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static String listToString(List<String> lst) {
		String str = "(";
		for (String i:lst) {
			str=str+"'"+i+"', ";
		}
		str = str.substring(0, str.length()-2);
		str+=")";
		return str;
	}
	
	
	public static void main(String[] args) {
		
		String newColQuery = "ALTER TABLE TaggedEvents ADD "+ "test"+ " VARCHAR(20)";
		System.out.println(newColQuery);
	}
	
	
}
