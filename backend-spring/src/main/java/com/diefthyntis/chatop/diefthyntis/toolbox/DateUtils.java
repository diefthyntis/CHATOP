package com.diefthyntis.chatop.diefthyntis.toolbox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static String convertLocalDateToString(LocalDateTime localDateTime) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return localDateTime.format(formatter);
	}
}
