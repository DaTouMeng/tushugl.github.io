package com.cashier.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地属性加载工具，支持增量加载，默认加载 ClassPath路径下的config.properties文件。
 *
 * @author Bosco.Liao
 * @since 1.2.0
 */
public final class PropertiesUtils {

	private static final String DEFAULT_PROPERTIES_FILE_NAME = "config.properties";

	private static final ConcurrentHashMap<Object, Object> PROPERTIES = new ConcurrentHashMap<Object, Object>();

	static {
		try {
			load(DEFAULT_PROPERTIES_FILE_NAME);
		} catch (IOException e) {
			// ignore
		}
	}

	private PropertiesUtils() {
		throw new InstantiationError("Utility class must not be instantiated.");
	}

	/**
	 * 获取基于字符串的属性.
	 */
	public static final String getProperty(final String name) {
		final Object value = PROPERTIES.get(name);
		return (value instanceof String) ? (String) value : null;
	}

	/**
	 * 获取基于整型的属性值.
	 */
	public static final Integer getPropertyAsInt(final String name) {
		return Integer.valueOf(getProperty(name));
	}

	public static final Long getPropertyAsLong(final String name) {
		return Long.valueOf(getProperty(name));
	}

	/**
	 * 获取基于双精度类型的属性值
	 */
	public static final Double getPropertyAsDouble(final String name) {
		return Double.valueOf(getProperty(name));
	}

	/**
	 * 获取基于字符串的属性，可设置默认值.
	 */
	public static String getProperty(final String name, final String defaultValue) {
		final Object value = PROPERTIES.getOrDefault(name, defaultValue);
		return (value instanceof String) ? (String) value : null;
	}

	

	/**
	 * 根据属性文件路径加载属性，支持多次加载，例如：com.example.config.properties.
	 * 
	 * @param pathname
	 *            属性文件路径
	 * @throws IOException
	 */
	public static final void load(String pathname) throws IOException {
		InputStream ins = PropertiesUtils.class.getClassLoader().getResourceAsStream(pathname);
		if (ins != null && ins.read() != -1) {
			load(ins);
		}
	}

	private static void load(final InputStream input) {
		Properties properties = new Properties();
		synchronized (properties) {
			try {
				properties.load(input);
				if (!properties.isEmpty()) {
					Enumeration<Object> enums = properties.keys();
					while (enums.hasMoreElements()) {
						Object key = enums.nextElement();
						PROPERTIES.put(key, properties.get(key));
					}
				}
			} catch (Exception e) {
				throw new PropertyLoadingException("Properties loading failed.", e);
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
		}
	}

	public static class PropertyLoadingException extends RuntimeException {

		private static final long serialVersionUID = 1653894904548237538L;

		public PropertyLoadingException(String message) {
			super(message);
		}

		public PropertyLoadingException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
