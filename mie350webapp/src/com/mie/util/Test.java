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
		
		String[] a = {"hello","hi","bye"}; 
		List<String> b = Arrays.asList(a);
		String c = b.toString();
		String d = c.substring(1, c.length()-1);
		System.out.println(d);
		String e = listToString(b);
		System.out.println(e);
	}
	
	
}
