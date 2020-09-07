package org.learning.nio.selector;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Component {
	public static String getDateTime() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
	}

	public static String getServerTitle() {
		return getDateTime() + ", Server say: ";
	}

	public static String getClientTitle() {
		return getDateTime() + ", Client say: ";
	}

	public static String getReverseTitle(Type type) {
		if (Type.SERVER == type) {
			return getClientTitle();
		} else {
			return getServerTitle();
		}
	}

	public static String getFrontalTitle(Type type) {
		if (Type.SERVER == type) {
			return getServerTitle();
		} else {
			return getClientTitle();
		}
	}
}