/**
 * 
 */
package com.gffny.leaderboard.portal.utils;

import com.gffny.leaderboard.portal.model.ui.RequestContext;
import com.gffny.leaderboard.util.ApplicationConfiguration;
import com.gffny.leaderboard.util.Locale;
import com.gffny.leaderboard.util.StringUtils;

/**
 * @author John Gaffney (john@gffny.com) Feb 11, 2013
 * 
 */
public class StaticAssetTool {

	private static final String prefix = ApplicationConfiguration
			.getStaticAssetPrefix();
	private static final String amazonContentPrefix = ApplicationConfiguration
			.getAmazonContentUrlPrefix();
	private static final String amazonContentPrefixBuildEnv = ApplicationConfiguration
			.getAmazonContentUrlPrefixBuildEnv();
	private static final String buildDescription = ApplicationConfiguration
			.getBuildDescription();

	/**
	 * 
	 * @param relativePath
	 * @return
	 */
	public static String staticUrl(String relativePath) {

		if (StringUtils.isNotEmpty(relativePath)
				&& relativePath.contains("build=")) {
			return relativePath;
		}

		String url = StringUtils.joinPath(prefix, relativePath);

		url += url.contains("?") ? "&" : "?";

		url += "build=" + buildDescription;

		return url;
	}

	/**
	 * 
	 * @param relativePath
	 * @param useLocale
	 * @return
	 */
	public static String staticUrlLocalized(String relativePath,
			boolean useLocale) {

		if (!useLocale) {
			Locale locale = getLocale();
			if (locale != Locale.en_US) {
				relativePath = relativePath + "_"
						+ getLocale().getLocale_symbol();
			}
		}

		return staticUrl(relativePath);
	}

	/**
	 * 
	 * @param relativePath
	 * @return
	 */
	public static String amazonContentUrl(String relativePath) {
		if (StringUtils.isNotEmpty(relativePath)
				&& (relativePath.contains("build=") || relativePath
						.contains("s3.amazonaws.com")))
			return relativePath;
		return StringUtils.joinPath(amazonContentPrefix, relativePath);
	}

	/**
	 * 
	 * @param relativePath
	 * @return
	 */
	public static String css(String relativePath) {
		return new StringBuilder().append("<link href=\"")
				.append(staticUrl(relativePath))
				.append("\" rel=\"stylesheet\" type=\"text/css\"/>").toString();
	}

	/**
	 * 
	 * @param relativePath
	 * @return
	 */
	public static String js(String relativePath) {
		return new StringBuilder()
				.append("<script type=\"text/javascript\" src=\"")
				.append(staticUrl(relativePath)).append("\"></script>")
				.toString();
	}

	/**
	 * 
	 * @param relativePath
	 * @return
	 */
	public static String amazonContentUrlForBuildEnv(String relativePath) {
		if (StringUtils.isNotEmpty(relativePath)
				&& (relativePath.contains("build=") || relativePath
						.contains("s3.amazonaws.com")))
			return relativePath;
		return StringUtils.joinPath(amazonContentPrefixBuildEnv, relativePath);
	}

	/**
	 * 
	 * @return
	 */
	public static Locale getLocale() {
		return RequestContext.get().getServletData()
				.getLocale(RequestContext.get().userLocaleFromDb());
	}
}
