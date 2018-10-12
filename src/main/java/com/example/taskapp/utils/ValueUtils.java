package com.example.taskapp.utils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.taskapp.exception.ApiException;

public class ValueUtils {

	/**
	 * Not null and value must be greater than zero
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean empty(Integer value) {
		return value == null || value <= 0;
	}

	/**
	 * Not null non-zero list
	 * 
	 * @param list
	 * @return
	 */
	public static boolean empty(List<Integer> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * Not null not empty {@link Collection}
	 * 
	 * @param coll
	 * @return
	 */
	public static boolean empty(Collection<?> coll) {
		return (coll == null || coll.isEmpty());
	}

	/**
	 * Not null and value must be greater than zero
	 * 
	 * @param value
	 * @return
	 */
	public static boolean empty(Long value) {
		return value == null || value.longValue() <= 0;
	}

	/**
	 * Not null and value must not be empty
	 * 
	 * @param value
	 * @return
	 */
	public static boolean empty(String value) {
		return value == null || value.length() < 1;
	}

	/**
	 * Not null and value must not be empty
	 * 
	 * @param value
	 * @return
	 */
	private static boolean empty(Date dueDate) {
		return dueDate == null;
	}

	private static boolean empty(Short status) {
		return status == null || status.shortValue() <= 0;
	}

	/**
	 * Check if array is null or empty
	 * 
	 * @param array
	 * @return
	 */
	public static boolean emptyArray(Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * Transform a list of object in boolean hashmap
	 * 
	 * @param existing
	 * @return
	 */
	public static <T> Map<T, Boolean> listToBooleanMap(List<T> existing) {
		if (existing == null) {
			return null;
		}
		Map<T, Boolean> map = new HashMap<T, Boolean>();
		for (T o : existing) {
			map.put(o, Boolean.TRUE);
		}
		return map;
	}

	public static void checkRequired(Integer... values) {
		for (Integer value : values) {
			checkRequired(value, "Missing required field");
		}
	}

	public static void checkRequired(Integer value, String message) {
		if (ValueUtils.empty(value)) {
			throw new ApiException(ApiException.ERROR_MISSING_REQUREID_FIELDS, message);// bad
																						// request
		}
	}

	public static void checkRequired(Long... values) {
		for (Long value : values) {
			checkRequired(value, "Missing required field");
		}
	}

	public static void checkRequired(Long value, String message) {
		if (ValueUtils.empty(value)) {
			throw new ApiException(ApiException.ERROR_MISSING_REQUREID_FIELDS, message);// bad
																						// request
		}
	}

	public static void checkRequired(String value) {
		checkRequired(value, "Missing required field");
	}

	public static void checkRequired(String... values) {
		for (String value : values) {
			checkRequiredString(value, "Missing required field");
		}
	}

	public static void checkRequired(String[] names, String... values) {
		for (int i = 0; i < values.length; i++) {
			checkRequiredString(values[i], "Missing required field " + names[i]);
		}
	}

	public static void checkRequiredString(String value, String message) {
		if (ValueUtils.empty(value)) {
			throw new ApiException(ApiException.ERROR_MISSING_REQUREID_FIELDS, message);// bad
																						// request
		}
	}

	public static void checkRequired(Date dueDate, String message) {
		if (ValueUtils.empty(dueDate)) {
			throw new ApiException(ApiException.ERROR_MISSING_REQUREID_FIELDS, message);// bad
		}

	}

	public static void checkRequired(Short status, String message) {
		if (ValueUtils.empty(status)) {
			throw new ApiException(ApiException.ERROR_MISSING_REQUREID_FIELDS, message);// bad
		}
	}

}
