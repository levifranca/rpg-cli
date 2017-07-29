package org.rpgcli.utils;

public class StringUtils {

	public static boolean isBlank(String str) {
		return str == null
			|| str.trim().isEmpty();
	}

	public static Integer getIntValue(String str) {
		Integer intValue = null;
		try {
			intValue = Integer.valueOf(str);
		} catch (NumberFormatException e) {
			// do nothing
		}
		return intValue;
	}
}
