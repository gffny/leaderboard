/**
 * 
 */
package com.gffny.leaderboard.portal.model.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.gffny.leaderboard.util.ApplicationConfiguration;
import com.gffny.leaderboard.util.CollectionUtils;
import com.gffny.leaderboard.util.DateUtils;
import com.gffny.leaderboard.util.JsonUtils;
import com.gffny.leaderboard.util.MapUtils;
import com.gffny.leaderboard.util.StringUtils;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class ServletData {

	protected final static String HEADER_AJAX_REQUEST = "X-Requested-With";
	protected final static String HEADER_AJAX_REQUEST_VALUE = "XMLHttpRequest";

	protected final static String HEADER_SESSION_INVALID = "MWP-SESSION-INVALID";

	protected final static String OBLIX_SSO_COOKIE_NAME = "ObSSOCookie";
	protected final static String[] OBLIX_COOKIE_NAMES = new String[] {
			OBLIX_SSO_COOKIE_NAME, "ObTEMC" };

	protected final static String LOCALE_COOKIE_TMPL = "locale-%s";

	protected final Logger logger = Logger.getLogger(ServletData.class);

	private HttpServletRequest _request = null;

	private HttpServletResponse _response = null;

	private Map<String, Object> _params = new HashMap<String, Object>();

	private URLCodec encoder = new URLCodec();

	public ServletData(HttpServletRequest request, HttpServletResponse response) {
		_request = request;
		_response = response;
	}

	public HttpServletResponse getResponse() {
		return _response;
	}

	public String[] getStringParameters(String param) {
		return getStringParameters(param, null);
	}

	public String[] getStringParameters(String param, String def) {
		String[] value = getParameterValues(param);

		if ((value == null) && (def != null)) {
			value = new String[] { def };
		}

		return value;
	}

	public String getStringParameter(String param) {
		return getStringParameter(param, null);
	}

	public String getStringParameter(String param, String def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return value;
	}

	public String getQueryString() {
		return getRequest().getQueryString();
	}

	public String getRequestURI() {
		return getRequest().getRequestURI();
	}

	public String getServletPath() {
		return getRequest().getServletPath();
	}

	public String getPathInfo() {
		return getRequest().getPathInfo();
	}

	public String getContextFreeRequestURI() {
		return StringUtils.removeStart(getRequestURI(), getContextPath());
	}

	public String getFullRequestURI() {
		StringBuilder sb = new StringBuilder(getRequestURI());
		if (StringUtils.isNotEmpty(getQueryString()))
			sb.append("?").append(getQueryString());

		return sb.toString();
	}

	public int getIntegerParameter(String param) {
		return getIntegerParameter(param, 0);
	}

	public int getIntegerParameter(String param, int def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseInteger(value, def);
	}

	public long getLongParameter(String param) {
		return getLongParameter(param, 0);
	}

	public long getLongParameter(String param, long def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseLong(value, def);
	}

	public Long getNullableLongParameter(String param) {
		return getNullableLongParameter(param, null);
	}

	public Long getNullableLongParameter(String param, Long def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		try {
			return Long.parseLong(value);
		} catch (Throwable ex) {
			return def;
		}
	}

	public double getDoubleParameter(String param) {
		return getDoubleParameter(param, 0);
	}

	public double getDoubleParameter(String param, double def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseDouble(value, def);
	}

	public boolean getBooleanParameter(String name) {
		return getBooleanParameter(name, false);
	}

	public boolean getBooleanParameter(String param, boolean def) {
		String value = getParameter(param);

		if (value == null)
			return def;

		return StringUtils.parseBoolean(value, def);
	}

	public Date getDateParameter(String name) {
		Date returnDate = null;

		try {
			String value = getStringParameter(name);
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			returnDate = df.parse(value);
		} catch (ParseException ex) {
			logger.info("ReturnDate ex: " + returnDate);
		}

		return returnDate;
	}

	public Date getDateParameter(String name, String parsePattern) {
		String value = getStringParameter(name);

		return DateUtils.parseDateOrNull(value, parsePattern);
	}

	public Date getTimestampParameter(String name) {
		String value = getStringParameter(name);

		if (StringUtils.isBlank(value))
			return null;

		long timestamp = StringUtils.parseLong(value, -1);

		if (timestamp < 0)
			return null;

		Date returnDate = new Date(timestamp);

		return returnDate;
	}

	public String getParameter(String param) {
		String value = getRequest().getParameter(param);

		if (value != null)
			return value;

		String[] mValue = (String[]) _params.get(param);
		if ((mValue != null) && (mValue.length > 0))
			value = mValue[0];

		return value;
	}

	public String getCapitalizedParamter(String param) {
		String p = getParameter(param);
		if (p != null)
			p = p.toUpperCase();

		return p;
	}

	public String[] getParameterValues(String param) {
		String[] value = getRequest().getParameterValues(param);
		if (value != null)
			return value;

		value = (String[]) _params.get(param);

		return value;
	}

	@SuppressWarnings("unchecked")
	public Enumeration<String> getParameterNames() {
		Enumeration<String> values = getRequest().getParameterNames();
		if (values != null)
			return values;

		return Collections.enumeration(_params.keySet());
	}

	public String getParameterNameStartingWith(String prefix) {
		Enumeration<String> paramNames = getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (paramName.startsWith(prefix)) {
				return paramName;
			}
		}

		return null;
	}

	public List<String> getListParameterValues(String param) {
		List<String> result = new ArrayList<String>();

		String[] values = getParameterValues(param);
		if (values == null)
			return result;

		for (int i = 0; i < values.length; i++)
			result.add(values[i]);

		return result;
	}

	/**
	 * @param key
	 * @return true when the parameter is not null and parameter's value isn't
	 *         empty false otherwise
	 */
	public boolean hasParameter(String key) {
		if (StringUtils.isNotBlank(getParameter(key))) {
			return true;
		}

		if (MapUtils.containsKey(_params, key)) {
			return true;
		}

		return false;
	}

	public void addParameter(String key, String value) {
		String[] mValue = (String[]) _params.get(key);
		if (mValue == null)
			mValue = new String[0];

		String[] newValue = new String[mValue.length + 1];
		System.arraycopy(mValue, 0, newValue, 0, mValue.length);
		newValue[mValue.length] = value;

		_params.put(key, newValue);
	}

	public void setParameter(String key, String value) {
		setParameter(key, new String[] { value });
	}

	public void setParameter(String key, String[] values) {
		_params.put(key, values);
	}

	public boolean hasAttribute(String key) {
		return getAttribute(key) != null;
	}

	public Object getAttribute(String param) {
		return getRequest().getAttribute(param);
	}

	public void removeAttribute(String param) {
		getRequest().removeAttribute(param);
	}

	public void setAttribute(String param, Object e) {
		getRequest().setAttribute(param, e);
	}

	public void setAttribute(String param, int i) {
		Integer value = new Integer(i);
		setAttribute(param, value);
	}

	public void setAttribute(String param, long l) {
		Long value = new Long(l);
		setAttribute(param, value);
	}

	public void setAttribute(String param, double d) {
		Double value = new Double(d);
		setAttribute(param, value);
	}

	public void setAttribute(String param, boolean b) {
		Boolean value = new Boolean(b);
		setAttribute(param, value);
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		if (attributes == null)
			return;

		Iterator<String> keySet = attributes.keySet().iterator();
		while (keySet.hasNext()) {
			try {
				String key = keySet.next();
				Object value = attributes.get(key);
				// kludge - mimics the functionality of setAttribute(String,
				// boolean)
				value = (value instanceof Boolean) ? value.toString() : value;
				setAttribute(key, value);
			} catch (Throwable ex) {
				logger.error("setAttributes(HashMap) - ex: " + ex);
			}
		}
	}

	public int getIntegerAttribute(String param) {
		return getIntegerAttribute(param, 0);
	}

	public int getIntegerAttribute(String param, int def) {
		try {
			return ((Integer) getAttribute(param)).intValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	public long getLongAttribute(String param) {
		return getLongAttribute(param, 0);
	}

	public long getLongAttribute(String param, long def) {
		try {
			return ((Long) getAttribute(param)).longValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	public boolean getBooleanAttribute(String name) {
		return getBooleanAttribute(name, false);
	}

	public boolean getBooleanAttribute(String param, boolean def) {
		try {
			return ((Boolean) getAttribute(param)).booleanValue();
		} catch (Throwable ex) {
			return def;
		}
	}

	public String getStringAttribute(String param) {
		return getStringAttribute(param, null);
	}

	public String getStringAttribute(String param, String def) {
		String value = (String) getAttribute(param);

		if (value == null)
			return def;

		return value;
	}

	public Date getDateAttribute(String param) {
		return getDateAttribute(param, null);
	}

	public Date getDateAttribute(String key, Date def) {
		if (!hasAttribute(key))
			return def;

		return (Date) getAttribute(key);
	}

	public String getHeader(String param) {
		return getRequest().getHeader(param);
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public void setRequest(HttpServletRequest request) {
		_request = request;
	}

	public String getContextPath() {
		return getRequest().getContextPath();
	}

	public String getRelativeUrl(String uri) {
		return StringUtils.joinPath(getContextPath(), uri);
	}

	public boolean isRelativeUrl(String url) {
		if (StringUtils.isEmpty(url))
			return false;

		return (url.startsWith(getContextPath()));
	}

	public String getAbsoluteUrl(String uri) {
		return StringUtils.joinPath(getRequestPrefix(true), uri);
	}

	public String getRequestPrefix() {
		return getRequestPrefix(false);
	}

	public String getRequestPrefix(boolean includeContext) {
		String protocol = getRequest().getScheme();
		String serverName = getRequest().getServerName();
		int port = getRequest().getServerPort();

		if (port < 0) {
			port = 80;
		}

		StringBuilder prefix = new StringBuilder();

		prefix.append(protocol).append("://").append(serverName);

		if (port != 80 && port != 443) {
			prefix.append(":").append(port);
		}

		if (includeContext) {
			prefix.append(getRequest().getContextPath());
		}

		return prefix.toString();
	}

	public boolean isRequestSecure() {
		return StringUtils.endsWith(getRequest().getScheme(), "s");
	}

	public String getContentType() {
		return getRequest().getContentType();
	}

	public String getMethod() {
		return getRequest().getMethod();
	}

	public boolean isPost() {
		if (StringUtils.equalsIgnoreCase("POST", getMethod()))
			return true;

		return false;
	}

	public boolean isMultipartRequest() {
		String contentType = getContentType();
		if (contentType != null
				&& contentType.startsWith("multipart/form-data"))
			return true;

		return false;
	}

	public void setContentType(String s) {
		getResponse().setContentType(s);
	}

	public void setContentLength(int i) {
		getResponse().setContentLength(i);
	}

	public void setHtmlContentType() {
		setContentType("text/html");
	}

	public void setExcelContentType() {
		setContentType("application/vnd.ms-excel");
	}

	public void setPdfContentType() {
		setContentType("application/pdf");
	}

	public void setRtfContentType() {
		setContentType("application/rtf");
	}

	public void setXmlContentType() {
		setContentType("application/xml");
	}

	public void setJsonContentType() {
		setContentType("application/json");
	}

	public void setBrowserNoCacheHeader() {
		addHeader("Cache-Control", "no-store");
		addHeader("Pragma", "no-cache"); // for legacy browsers
	}

	public List<Cookie> getCookies() {
		Cookie[] cookies = getRequest().getCookies();

		if (cookies == null)
			return new ArrayList<Cookie>();

		return Arrays.asList(cookies);
	}

	public Cookie getCookie(String name) {
		Iterator<Cookie> i = getCookies().iterator();

		while (i.hasNext()) {
			Cookie c;
			c = i.next();

			if (c.getName().equals(name))
				return c;
		}

		return null;
	}

	public String getCookieValue(String name, String def) {
		Iterator<Cookie> i = getCookies().iterator();

		while (i.hasNext()) {
			Cookie c;
			c = i.next();

			if (c.getName().equals(name))
				return c.getValue();
		}

		return def;
	}

	public int getIntegerCookieValue(String name, int def) {
		return StringUtils.parseInteger(getCookieValue(name, null), def);
	}

	public boolean getBooleanCookieValue(String name, boolean def) {
		return StringUtils.parseBoolean(getCookieValue(name, null), def);
	}

	public boolean hasCookie(String name) {
		return getCookie(name) != null;
	}

	public void removeCookie(String cookieName) {
		Cookie c = getCookie(cookieName);
		if (c != null) {
			c.setMaxAge(0);
			c.setPath("/");
			addCookie(c);
		}
	}

	public void removeCookie(Cookie c) {
		c.setMaxAge(0);
		c.setPath("/");
		addCookie(c);
	}

	public void addCookie(Cookie c) {
		getResponse().addCookie(c);
	}

	public void addCookie(String name, String value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, value, oneYear);
	}

	public void addCookie(String name, long value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, Long.toString(value), oneYear);
	}

	public void addCookie(String name, boolean value) {
		int oneYear = 365 * 24 * 60 * 60;
		addCookie(name, Boolean.toString(value), oneYear);
	}

	public void addSessionCookie(String name, String value) {
		addCookie(name, value, -1);
	}

	public void addCookie(String name, String value, int age) {
		Cookie c = new Cookie(name, value);
		c.setMaxAge(age);
		c.setPath("/");
		addCookie(c);
	}

	public String getReferer() {
		return getHeader("Referer");
	}

	public void refreshReferer() {
		redirectRequest(getReferer());
	}

	public void redirectRequest(String url) {
		redirectRequest(url, false);
	}

	public void redirectRequest(String url, boolean addContext) {
		try {
			if (addContext) {
				url = StringUtils.joinPath(getContextPath(), url);
			}

			getResponse().sendRedirect(url);
			getResponse().flushBuffer();
		} catch (IOException ex) {
			logger.error("redirectRequest() - ex: " + ex);
		}
	}

	public void forwardRequest(String url) {
		try {
			RequestDispatcher rd = getRequest().getRequestDispatcher(url);
			rd.forward(getRequest(), getResponse());
		} catch (Throwable ex) {
			logger.error("forwardRequest() - ex: " + ex);
		}
	}

	public boolean containsHeader(String header) {
		return getResponse().containsHeader(header);
	}

	public void addHeader(String header, String value) {
		getResponse().addHeader(header, value);
	}

	public void setHeader(String header, String value) {
		getResponse().setHeader(header, value);
	}

	public void writeData(String data) {
		try {
			PrintWriter out = getResponse().getWriter();
			out.println(data);

		} catch (Throwable ex) {
			logger.error("Unable to write data to the response.");
		}
	}

	public Map<String, String[]> getParameterMap() {
		HashMap<String, String[]> properties = new HashMap<String, String[]>();
		Enumeration<String> names = null;
		names = getParameterNames();

		while (names.hasMoreElements()) {
			String name = names.nextElement();
			properties.put(name, getParameterValues(name));
		}

		return properties;
	}

	public void setResponseInvalidRequestHeader() {
		addHeader(HEADER_SESSION_INVALID, "true");
	}

	public boolean isAjaxRequest() {
		if (getHeader(HEADER_AJAX_REQUEST) != null) {
			if (getHeader(HEADER_AJAX_REQUEST)
					.equals(HEADER_AJAX_REQUEST_VALUE)) {
				return true;
			}
		}

		return false;
	}

	public void storeLocaleCookie(Locale locale) {
		// addSessionCookie(String.format(LOCALE_COOKIE_TMPL,
		// RequestContext.get()
		// .getOrgUnitId()), locale.getLocale_symbol());
	}

	public void removeLocaleCookie() {
		// removeCookie(String.format(LOCALE_COOKIE_TMPL, RequestContext.get()
		// .getOrgUnitId()));
	}

	public Cookie getPortalContextCookie() {
		return getCookie(ApplicationConfiguration.getPortalContextCookieName());
	}

	public void removePortalContextCookie() {
		removeCookie(ApplicationConfiguration.getPortalContextCookieName());
	}

	public PortalContext getPortalContextFromCookie() {
		Cookie cookie = getPortalContextCookie();
		if (cookie == null)
			return null;

		try {
			String cookieValue = encoder.decode(cookie.getValue());
			logger.info("cookie value:" + cookieValue);
			String json = new String(Base64.decodeBase64(cookieValue));
			logger.info("json:" + json);

			return JsonUtils.fromJsonNullable(json, PortalContext.class);
		} catch (Throwable ex) {
			logger.error("Unable to get user cookie: " + ex);
			return null;
		}
	}

	public void storePortalContextCookie(PortalContext context) {
		storeContextCookie(
				ApplicationConfiguration.getPortalContextCookieName(), context);
	}

	public PageContext getPageContextFromCookie() {
		return getContextFromCookie(
				ApplicationConfiguration.getPageContextCookieName(),
				PageContext.class);
	}

	public void storePageContextCookie(PageContext context) {
		storeContextCookie(ApplicationConfiguration.getPageContextCookieName(),
				context);
	}

	public void removePageContextCookie() {
		removeCookie(ApplicationConfiguration.getPageContextCookieName());
	}

	// public GroupContext getGroupContextFromCookie() {
	// return getContextFromCookie(
	// ApplicationConfiguration.getGroupContextCookieName(),
	// GroupContext.class);
	// }
	//
	// public void storeGroupContextCookie(GroupContext context) {
	// storeContextCookie(
	// ApplicationConfiguration.getGroupContextCookieName(), context);
	// }

	public void removeGroupContextCookie() {
		removeCookie(ApplicationConfiguration.getGroupContextCookieName());
	}

	public boolean hasAssessmentContextFromCookie() {
		return getCookie(ApplicationConfiguration
				.getAssessmentContextCookieName()) != null;
	}

	// public AssessmentContext getAssessmentContextFromCookie() {
	// return getContextFromCookie(
	// ApplicationConfiguration.getAssessmentContextCookieName(),
	// AssessmentContext.class);
	// }
	//
	// public void storeAssessmentContextCookie(AssessmentContext context) {
	// storeContextCookie(
	// ApplicationConfiguration.getAssessmentContextCookieName(),
	// context);
	// }

	public void removeAssessmentContextCookie() {
		removeCookie(ApplicationConfiguration.getAssessmentContextCookieName());
	}

	// public RewardsContext getRewardsContextFromCookie() {
	// return getContextFromCookie(
	// ApplicationConfiguration.getRewardsContextCookieName(),
	// RewardsContext.class);
	// }
	//
	// public IncentivesContext getIncentivesContextFromCookie() {
	// return getContextFromCookie(
	// ApplicationConfiguration.getIncentivesContextCookieName(),
	// IncentivesContext.class);
	// }
	//
	// public void storeIncentivesContextCookie(IncentivesContext context) {
	// storeContextCookie(
	// ApplicationConfiguration.getIncentivesContextCookieName(),
	// context);
	// storeRewardsUpdateFlag(context != null ? context.isUpdate() : false);
	// }
	//
	// public void removeIncentivesContextCookie() {
	// removeCookie(ApplicationConfiguration.getIncentivesContextCookieName());
	// removeCookie(ApplicationConfiguration.getRewardsUpdateFlagCookieName());
	// }
	//
	// public void storeRewardsContextCookie(RewardsContext context) {
	// storeContextCookie(
	// ApplicationConfiguration.getRewardsContextCookieName(), context);
	// storeRewardsUpdateFlag(context != null ? context.isUpdate() : false);
	// }
	//
	public void removeRewardsContextCookie() {
		removeCookie(ApplicationConfiguration.getRewardsContextCookieName());
		removeCookie(ApplicationConfiguration.getRewardsUpdateFlagCookieName());
	}

	public void storeRewardsUpdateFlag(boolean update) {
		addCookie(ApplicationConfiguration.getRewardsUpdateFlagCookieName(),
				update ? "1" : "0", -1);
	}

	public void removeMessageCenterUnviewedCountCookie() {
		removeCookie(ApplicationConfiguration
				.getMessageCenterUnviewedCountCookieName());
	}

	public boolean hasRegistered() {
		return getBooleanCookieValue(
				ApplicationConfiguration.getRegisteredCookieName(), false);
	}

	public void setRegistered(boolean registered) {
		if (registered) {
			addCookie(ApplicationConfiguration.getRegisteredCookieName(),
					registered);
		} else {
			removeCookie(ApplicationConfiguration.getRegisteredCookieName());
		}
	}

	/*
	 * Associate Poll with the Session to be consistent
	 */
	// public void setSessionPoll(String pollName) {
	// addSessionCookie(ApplicationConfiguration.getSessionPollCookie(),
	// pollName);
	// }
	//
	// public String getSessionPoll() {
	// return getCookieValue(ApplicationConfiguration.getSessionPollCookie(),
	// "NotSet");
	// }

	public <T> T getContextFromCookie(String cookieName, Class<T> c) {
		try {
			Cookie cookie = getCookie(cookieName);
			String cookieValue = encoder.decode(cookie.getValue());
			String json = new String(Base64.decodeBase64(cookieValue));
			logger.info("context json:" + json);
			return JsonUtils.fromJson(json, c);
		} catch (Throwable ex) {
			try {
				return c.newInstance();
			} catch (Throwable e) {
				return null;
			}
		}
	}

	public <T> T getContextFromCookieNoDefault(String cookieName, Class<T> c) {
		try {
			Cookie cookie = getCookie(cookieName);
			String cookieValue = encoder.decode(cookie.getValue());
			String json = new String(Base64.decodeBase64(cookieValue));
			logger.info("context json:" + json);
			return JsonUtils.fromJson(json, c);
		} catch (Throwable ex) {
			return null;
		}
	}

	public <T> void storeContextCookie(String name, T context) {
		try {
			String value = encoder.encode(Base64.encodeBase64String(JsonUtils
					.toJson(context, false).getBytes()));
			logger.info("writing value:" + value);
			addSessionCookie(name, value);
		} catch (Throwable ex) {
			logger.error("Unable to write user cookie: " + ex);
		}
	}

	public <T> void storeLimitedTTLContextCookie(String name, T context, int ttl) {
		try {
			String value = encoder.encode(Base64.encodeBase64String(JsonUtils
					.toJson(context, false).getBytes()));
			logger.info("writing value:" + value);
			addCookie(name, value, ttl);
		} catch (Throwable ex) {
			logger.error("Unable to write user cookie: " + ex);
		}
	}

	public boolean hasValidOblixCookies(boolean isLoggedIn) {
		logger.info(" checking for valid OblixCookies");
		for (String cookieName : OBLIX_COOKIE_NAMES) {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null) {
				logger.info(" has cookie: " + cookieName);
				if (isLoggedIn) {
					// ObSSO Cookie still exists after logout, so if we want to
					// check to
					// see if a user may still be logged in the cookie value
					// cannot be 'loggedout'
					logger.info(" cookie value [" + cookie.getValue() + "]");
					if (cookieName.equals(OBLIX_SSO_COOKIE_NAME)
							&& !cookie.getValue().equals("loggedout")) {
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public void removeOamCookies() {
		for (String cookieName : OBLIX_COOKIE_NAMES) {
			Cookie cookie = getCookie(cookieName);
			if (cookie != null)
				removeCookie(cookie);
		}
	}

	// public void storeSSOContextCookie(SSOContext context) {
	// storeLimitedTTLContextCookie(
	// ApplicationConfiguration.getSSOContextCookieName(), context,
	// ApplicationConfiguration.getSSOContextCookieTTL());
	// }
	//
	// public boolean hasSSOContextCookie() {
	// return getCookie(ApplicationConfiguration.getSSOContextCookieName()) !=
	// null;
	// }
	//
	// public SSOContext getSSOContextCookie() {
	// return getContextFromCookieNoDefault(
	// ApplicationConfiguration.getSSOContextCookieName(),
	// SSOContext.class);
	// }
	//
	public void removeSSOContextCookie() {
		removeCookie(ApplicationConfiguration.getSSOContextCookieName());
	}

	public String getTestUser() {
		String cookieName = "local-testuser";
		String username = getParameter("test-user");

		if (StringUtils.isNotEmpty(username)) {
			addSessionCookie(cookieName, username);
			return username;
		}

		Cookie cookie = getCookie(cookieName);
		if (cookie != null)
			return cookie.getValue();

		return null;
	}

	public String getTestGolferId() {
		String cookieName = "local-testid";
		String individualId = getParameter("test-id");

		if (StringUtils.isNotEmpty(individualId)) {
			addSessionCookie(cookieName, individualId);
			return individualId;
		}

		Cookie cookie = getCookie(cookieName);
		if (cookie != null)
			return cookie.getValue();

		return null;
	}

	public void removeLocalCookies() {
		Cookie cookie = getCookie("local-testid");
		if (cookie != null)
			removeCookie(cookie);

		cookie = getCookie("local-testuser");
		if (cookie != null)
			removeCookie(cookie);
	}

	private ApplicationContext _context;

	public ApplicationContext getWebApplicationContext() {
		if (_context == null)
			_context = (ApplicationContext) getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		return _context;
	}

	public void setWebApplicationContext(ApplicationContext context) {
		_context = context;
	}

	public String getLink(String uri) {
		return StringUtils.joinPath(getContextPath(), uri);
	}

	public void setReturningContext() {
		setAttribute("returning-context", true);
	}

	public boolean isReturningContext() {
		return hasAttribute("returning-context");
	}

	public String getGolferId() {
		if (ApplicationConfiguration.isLocalEnvironment())
			return getTestGolferId();

		String golferId = getWebgateGolferId();

		if (golferId == null) {
			logger.info("Assuming that this is a request from mobile device so checking different header.");
			golferId = getMobileWebgateGolferId();
		}
		return golferId;
	}

	public String getWebgateGolferId() {
		String key = ApplicationConfiguration.getWebGateIndividualHeaderName();
		return getHeader(key);
	}

	public String getMobileWebgateGolferId() {
		String key = ApplicationConfiguration
				.getMobileWebGateIndividualHeaderName();
		return getHeader(key);
	}

	public String getUsername() {
		if (ApplicationConfiguration.isLocalEnvironment()) {
			return getTestUser();
		}
		return getWebgateUsername();
	}

	public String getWebgateUsername() {
		// Following are the header-names which provide us user-name. Different
		// login-type (SSO, Duallogin, Direct Login, Mobile device etc) might
		// have different header which provides user-name value.

		String[] keys = ApplicationConfiguration.getWebGateHeaderName();
		if (CollectionUtils.isNotEmpty(keys)) {
			for (String key : keys) {
				String userName = getHeader(key);
				if (StringUtils.isNotEmpty(userName)) {
					return userName;
				}
			}
		}
		return null;
	}

}
