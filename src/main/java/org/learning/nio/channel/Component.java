package org.learning.nio.channel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Component extends Thread {
	public String getDateTime() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
	}

	public String getServerTitle() {
		return getDateTime() + ", Server say: ";
	}

	public String getClientTitle() {
		return getDateTime() + ", Client say: ";
	}

	public String getReverseTitle(Type type) {
		if (Type.SERVER == type) {
			return getClientTitle();
		} else {
			return getServerTitle();
		}
	}

	public String getFrontalTitle(Type type) {
		if (Type.SERVER == type) {
			return getServerTitle();
		} else {
			return getClientTitle();
		}
	}
}