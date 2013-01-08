/**
 * 
 */
package com.gffny.leaderboard.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class ApplicationConfiguration {

	protected final static Logger logger = Logger
			.getLogger(ApplicationConfiguration.class);

	private static PropertiesConfiguration config;

	private static String fileName = "leaderboard.properties";

	static {
		String systemRoot = System.getProperty("application.root");
		String path = FilenameUtils.concat(
				FilenameUtils.concat(systemRoot, "config"), fileName);

		try {
			if (StringUtils.isEmpty(path)) {
				config = new PropertiesConfiguration(fileName);
			} else {
				File externalFile = new File(path);
				if (externalFile.exists()) {
					config = new PropertiesConfiguration(externalFile);
				} else {
					config = new PropertiesConfiguration(fileName);
				}
			}

			if (config != null)
				config.setReloadingStrategy(new FileChangedReloadingStrategy());

		} catch (Throwable ex) {
			logger.error("Unable to load configuration from: " + path, ex);
		}

		try {
			Properties buildProperties = new Properties();
			buildProperties.load(ApplicationConfiguration.class
					.getResourceAsStream("/build.properties"));
			addProperties(buildProperties);
		} catch (Throwable ex) {
			logger.error("Unable to load build info.");
		}

	}

	/*
	 * Application specific lookups to reduce property keys from being scattered
	 * through the application.
	 */
	public static boolean useMockEnvironment() {
		return true;
		// return getBoolean("local.environment");
	}

	public static String getLogDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"),
				"logs");
	}

	public static String getFileCacheDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"),
				"cache");
	}

	public static String getCodebaseDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"),
				"src");
	}

	public static String getConfigDirectory() {
		return FilenameUtils.concat(System.getProperty("application.root"),
				"config");
	}

	public static String getUserCookieName() {
		return getString("ldrbrd.cookie", "ldrbrd.cookie");
	}

	public static String getGolferIdHeaderName() {
		return getString("ldrbrd.golfer.id.header");
	}

	public static String getGolferNameHeaderName() {
		return getString("ldrbrd.golfer.name.header");
	}

	public static String getTestGolferId() {
		return getString("test.ldrbrd.golfer.id", "123");
	}

	public static String getTestGolferName() {
		return getString("test.ldrbrd.golfer.name", "Gaffney");
	}

	public static String getBuildNumber() {
		return getString("ldrbrd.build.number", "0");
	}

	public static String getRevisionNumber() {
		return getString("ldrbrd.revision.number", "0");
	}

	public static String getCompetitionRoundIdHeaderName() {
		return getString("ldrbrd.competitionroundid.header.name");
	}

	public static String getIPhoneAppDownloadLink() {
		return config.getString("mobile.iphone.app.download.link");
	}

	public static boolean isProductionEnvironment() {
		return getBoolean("production.environment", false);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getCompetitionVisibilityList() {
		return config.getList("ldrbrd.competition.visibility.list",
				CollectionUtils.asList("society"));
	}

	private static String getStringFromKeys(String... keys) {
		if (CollectionUtils.isEmpty(keys)) {
			return null;
		}

		for (String key : keys) {
			String value = getString(key, null);

			if (StringUtils.isNotBlank(value)) {
				return value;
			}
		}

		return null;
	}

	public static String getBuildDescription() {
		List<String> parts = new ArrayList<String>();

		String buildNumber = getBuildNumber();

		if (StringUtils.isNotBlank(buildNumber)) {
			parts.add("b" + buildNumber);
		} else {
			String revisionNumber = getRevisionNumber();

			if (StringUtils.isNotBlank(revisionNumber)) {
				parts.add("r" + revisionNumber);
			}
		}

		if (useMockEnvironment()) {
			parts.add("local");
		}

		String buildDescription = StringUtils.join(parts, "-");

		return StringUtils.isNotBlank(buildDescription) ? buildDescription
				: "unknown";
	}

	/*
	 * Delegate methods which pass the request down to the underlying
	 * implementation
	 */

	public static void addProperty(String key, Object value) {
		if (StringUtils.isNotBlank(key)) {
			config.addProperty(key, value);
		}
	}

	public static void addProperties(Map<?, Object> properties) {
		if (MapUtils.isEmpty(properties)) {
			return;
		}

		for (Entry<?, Object> entry : properties.entrySet()) {
			addProperty(StringUtils.valueOf(entry.getKey()), entry.getValue());
		}
	}

	public static void clear() {
		config.clear();
	}

	public static void clearProperty(String key) {
		config.clearProperty(key);
	}

	public static boolean containsKey(String key) {
		return config.containsKey(key);
	}

	public static boolean containsValue(String key, String value) {
		List<?> ids = getList(key);
		for (Iterator<?> i = ids.iterator(); i.hasNext();) {
			String s = (String) i.next();
			if (StringUtils.isEquivalent(s, value)) {
				return true;
			}
		}

		return false;
	}

	public static BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
		return config.getBigDecimal(key, defaultValue);
	}

	public static BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
		return config.getBigInteger(key, defaultValue);
	}

	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	public static byte getByte(String key) {
		return config.getByte(key);
	}

	public static byte getByte(String key, byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	public static Byte getByte(String key, Byte defaultValue) {
		return config.getByte(key, defaultValue);
	}

	public static double getDouble(String key) {
		return config.getDouble(key);
	}

	public static double getDouble(String key, double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	public static Double getDouble(String key, Double defaultValue) {
		return config.getDouble(key, defaultValue);
	}

	public static float getFloat(String key) {
		return config.getFloat(key);
	}

	public static float getFloat(String key, float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	public static Float getFloat(String key, Float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	public static int getInt(String key) {
		return config.getInt(key);
	}

	public static int getInt(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	public static Integer getInteger(String key, Integer defaultValue) {
		return config.getInteger(key, defaultValue);
	}

	public static Iterator<?> getKeys() {
		return config.getKeys();
	}

	public static Iterator<?> getKeys(String prefix) {
		return config.getKeys(prefix);
	}

	public static List<?> getList(String key) {
		return config.getList(key);
	}

	public static List<?> getList(String key, List<?> defaultValue) {
		return config.getList(key, defaultValue);
	}

	public static long getLong(String key) {
		return config.getLong(key);
	}

	public static long getLong(String key, long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public static Long getLong(String key, Long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public static Properties getProperties(String key) {
		return config.getProperties(key);
	}

	public static Object getProperty(String key) {
		return config.getProperty(key);
	}

	public static short getShort(String key) {
		return config.getShort(key);
	}

	public static short getShort(String key, short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	public static Short getShort(String key, Short defaultValue) {
		return config.getShort(key, defaultValue);
	}

	public static String getString(String key) {
		return config.getString(key);
	}

	public static String getString(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public static String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public static String getDecryptedString(String key) {
		String encrypted = getString(key);
		return Security.decrypt(encrypted);
	}

	public static String getDecryptedString(String key,
			String defaultEncryptedValue) {
		String encrypted = getString(key, defaultEncryptedValue);
		return Security.decrypt(encrypted);
	}

	public static boolean isEmpty() {
		return config.isEmpty();
	}

	public static void setProperty(String key, Object value) {
		config.setProperty(key, value);
	}

	public static Configuration subset(String prefix) {
		return config.subset(prefix);
	}

	public static Boolean getBoolean(String key, Boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}
}
