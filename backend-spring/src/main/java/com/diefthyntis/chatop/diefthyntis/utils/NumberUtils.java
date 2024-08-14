package com.diefthyntis.chatop.diefthyntis.utils;

import java.util.Optional;

public class NumberUtils {
	public static Float convertToFloat(String data){
	      return Optional.ofNullable(data).map(Float::parseFloat).orElse(0f);
	    }
	
	public static Integer convertToInteger(String data){
	      return Optional.ofNullable(data).map(Integer::parseInt).orElse(0f);
	    }
}
