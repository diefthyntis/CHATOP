package com.diefthyntis.chatop.diefthyntis.utils;

import java.util.Optional;

public class NumberUtils {
	public static Float convertToNumeric(String data){
	      return Optional.ofNullable(data).map(Float::parseFloat).orElse(0f);
	    }
}
