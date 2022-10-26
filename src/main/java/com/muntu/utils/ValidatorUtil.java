package com.muntu.utils;

import java.util.List;
import java.util.Objects;

public class ValidatorUtil {


	public static boolean isValidString(String input) {
		return !(input == null || input.isEmpty() || input.isBlank());
	}

	public static boolean isBlank(String input) {
		return input == null || input.isEmpty() || input.isBlank();
	}

	public static boolean isNull(Object user) {
		return Objects.isNull(user);
	}

	public static boolean isEmpty(List<String> initRoles) {
		return null == initRoles || initRoles.isEmpty();
	}

}
