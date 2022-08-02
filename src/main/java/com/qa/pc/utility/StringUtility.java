package com.qa.pc.utility;

import org.apache.commons.exec.util.StringUtils;

public class StringUtility extends StringUtils {

	public static boolean isEmpty_Or_Blank(String name) {
		if(name.isBlank()||name.isEmpty()) {
			return true;
		}
		return false;
	}
}
