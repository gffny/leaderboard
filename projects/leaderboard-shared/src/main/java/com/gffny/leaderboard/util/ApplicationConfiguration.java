/**
 * 
 */
package com.gffny.leaderboard.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
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

	public static boolean isLocalEnvironment() {
		return true;
		// return getBoolean("local.environment");
	}

	public static boolean isDbStubsEnabled() {
		return getBoolean("local.db.stubs.enabled");
	}

	public static boolean isSoapStubsEnabled() {
		return getBoolean("local.soap.stubs.enabled");
	}

	public static boolean isGsaStubsEnabled() {
		return getBoolean("local.gsa.stubs.enabled");
	}

	public static boolean isExternalBlocked() {
		return getBoolean("local.external.blocked");
	}

	public static boolean isMasterDataLoadFromDatabase() {
		return getBoolean("masterdata.db-load.enabled", false);
	}

	public static boolean isSimpleTypesFromDatabase() {
		return getBoolean("masterdata-simple.db-load.enabled", true);
	}

	public static boolean isComplexTypesFromDatabase() {
		return getBoolean("masterdata-complex.db-load.enabled", true);
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

	public static String getStaticAssetPrefix() {
		return getString("static.asset.prefix", "/healthways");
	}

	public static String getSurveyServiceUrl() {
		return getString("survey.service.url");
	}

	public static String[] getWebGateHeaderName() {
		return config.getStringArray("webgate.header");
	}

	public static String getWebGateIndividualHeaderName() {
		return getString("webgate.individual.header");
	}

	public static String getMobileWebGateIndividualHeaderName() {
		return getString("mobile.webgate.individual.header");
	}

	public static String getUserCookieName() {
		return getString("mwp.cookie", "mwp.cookie");
	}

	public static String getPortalContextCookieName() {
		return getString("mwp.context.cookie", "mwp.context.cookie");
	}

	public static String getPageContextCookieName() {
		return getString("page.context.cookie", "page.context.cookie");
	}

	public static String getGroupContextCookieName() {
		return getString("group.context.cookie", "group.context.cookie");
	}

	public static String getAssessmentContextCookieName() {
		return getString("assessment.context.cookie",
				"assessment.context.cookie");
	}

	public static String getRewardsContextCookieName() {
		return getString("rewards.context.cookie", "rewards.context.cookie");
	}

	public static String getRewardsUpdateFlagCookieName() {
		return getString("rewards.update.flag.cookie",
				"rewards.update.flag.cookie");
	}

	public static String getRegisteredCookieName() {
		return getString("registered.cookie", "registered.cookie");
	}

	public static String getMessageCenterUnviewedCountCookieName() {
		return getString("msg_center_unviewed_count_cookie",
				"mwp.msg-center-unviewed-count");
	}

	public static String getHostnameForGSA() {
		return getString("gsa.hostname");
	}

	public static String getFrontEndForGSA() {
		return getString("gsa.default.frontend");
	}

	public static String getCollectionForGSA() {
		return getString("gsa.default.collection");
	}

	public static String getGsaCollectionNameRecipeCenter() {
		return getString("gsa.recipe.collection");
	}

	public static String getLifestyleAssessmentId() {
		return getString("lifestyle.assessment.id");
	}

	public static String getDefaultLifestyleAssessmentId() {
		try {
			return (String) CollectionUtils
					.getFirstElement(getList("lifestyle.assessment.id"));
		} catch (Throwable ex) {
			return null;
		}
	}

	public static boolean isLifestyleAssessment(String assessmentId) {
		return containsValue("lifestyle.assessment.id", assessmentId);
	}

	public static boolean isHraAssessment(String assessmentId) {
		return containsValue("hra.assessment.id", assessmentId);
	}

	public static String getHraAssessmentId() {
		return getString("hra.assessment.id");
	}

	public static boolean isSoapCachingEnabled() {
		return getBoolean("soap.caching.enable", true);
	}

	public static boolean isEatingWellCachingEnabled() {
		return isSoapCachingEnabled()
				&& getBoolean("eating.well.caching.enable", true);
	}

	public static String getWebRoot() {
		return getString("web.root", System.getProperty("java.io.tmpdir"));
	}

	public static String getUploadRelativePath() {
		return getString("upload.root", "/uploaded/images");
	}

	public static boolean isStaticContentEditingEnabled() {
		return getBoolean("static.content.editing.enabled", true);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getBehaviorsWithStagingSurveys() {
		String key = "behaviors.with.surveys";
		if (containsKey(key)) {
			return (List<String>) getList(key);
		}

		return Arrays.asList("BRT-MEDAD", "BRT-APPTAD", "BRT-HE", "BRT-HEWM");
	}

	public static int getReassessWbaNumberOfDays() {
		return getInt("reassess.wba.days", 365);
	}

	public static int getStagingSurveyReminderNumberOfDays() {
		return getInt("staging.reminder.days", 30);
	}

	public static int getStagingSurveyMandatoryNumberOfDays() {
		return getInt("staging.mandatory.days", 90);
	}

	public static String getReportEnvironmentName() {
		return getString("report.environment.name", "devtest");
	}

	public static String getAmazonUrlPrefix() {
		return getString("amazon.url.prefix");
	}

	public static String getAmazonContentBucket() {
		return getString("amazon.content.bucket");
	}

	public static String getAmazonContentUrlPrefix() {
		return StringUtils.joinPath(getAmazonUrlPrefix(),
				getAmazonContentBucket());
	}

	public static String getAmazonUsername() {
		return getString("amazon.user");
	}

	public static String getAmazonPassword() {
		return getDecryptedString("amazon.pass");
	}

	public static String getAmazonAccessKey() {
		return getDecryptedString("amazon.access.key");
	}

	public static String getAmazonSecretKey() {
		return getDecryptedString("amazon.secret.key");
	}

	public static String getEatingWellHostUrl() {
		return getString("eatingwell.host.url", "http://hwapi01.eatingwell.com");
	}

	@SuppressWarnings("unchecked")
	public static List<String> getFeaturedRecipes() {
		return (List<String>) getList("recipecenter.featured.recipes");
	}

	@SuppressWarnings("unchecked")
	public static List<String> getFeaturedRecipeRelatedCategories() {
		return (List<String>) getList("recipecenter.featured.recipe.related.categories");
	}

	public static boolean loadImagePathFromSource() {
		return getBoolean("eatingwell.loading.image.path.from.source", true);
	}

	public static String getEshaServiceUrl() {
		return getString("esha.service.url", "http://api.esha.com");
	}

	public static String getEshaServiceApiKey() {
		return getDecryptedString("esha.service.api.key");
	}

	public static boolean getEshaServiceSearchCacheEnabled() {
		return getBoolean("esha.service.search.cache.enabled", true);
	}

	public static double getRecommendedFruitServings() {
		return getDouble("tracker.servings.fruit-recommendation", 2.0D);
	}

	public static double getRecommendedVegetableServings() {
		return getDouble("tracker.servings.vegetable-recommendation", 2.5D);
	}

	public static double getRecommendedWaterServings() {
		return getDouble("tracker.servings.water-recommendation", 6.0D);
	}

	public static double getRecommendedFiberMale() {
		return getDouble("tracker.food-log.fiber.recommendation.male", 30D);
	}

	public static double getRecommendedFiberFemale() {
		return getDouble("tracker.food-log.fiber.recommendation.female", 25D);
	}

	public static boolean isQuerySurveyStatusByDb() {
		return getBoolean("survey.query.status", true);
	}

	public static boolean isUserStatusByDb() {
		return getBoolean("user.query.status", true);
	}

	public static String getDefaultQuitnetUrl() {
		return getString("default.quitnet.url");
	}

	public static String getDownloadPrefix() {
		return getString("downloadable.resource.internal.url",
				"https://content.embrace.healthways.com/MWPPEMS/EmbraceMaterials");
	}

	public static String getOamUsername() {
		return getString("oam.username");
	}

	public static String getOamPassword() {
		return getDecryptedString("oam.password");
	}

	public static String getDefaultTermsKey() {
		return getString("default.terms.url", "DEFAULT_TERMS");
	}

	public static String getDefaultPrivacyKey() {
		return getString("default.privacy.url", "DEFAULT_PRIVACY");
	}

	public static String getWebwatchPassword() {
		return getDecryptedString("webwatch.password",
				"88401007b4e8c12fdc7fcf6ac258d0a3");
	}

	public static int getWebwatchTimeoutMinutes() {
		return getInteger("webwatch.session.timeout.minutes", 20);
	}

	public static String getNodeCommuncationKey() {
		return getString("node.communication.key", "NODE_COMM_KEY");
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

		if (isLocalEnvironment()) {
			parts.add("local");
		}

		String buildDescription = StringUtils.join(parts, "-");

		return StringUtils.isNotBlank(buildDescription) ? buildDescription
				: "unknown";
	}

	public static String getBuildNumber() {
		return getStringFromKeys("Implementation-Build", "build.number");
	}

	public static String getBuildTime() {
		return getStringFromKeys("Implementation-Build-Time", "build.timestamp");
	}

	public static String getRevisionNumber() {
		return getStringFromKeys("Implementation-Revision", "revision.number");
	}

	public static String getDefaultHeaderImage() {
		return getString("default.footer.image.path",
				"logos/healthways-white.png");
	}

	public static String getDefaultFooterImage() {
		return getString("default.footer.image.path",
				"logos/healthways-white.png");
	}

	public static int getMaxImageFileSize() {
		return getInt("max.image.upload.size", 5000000);
	}

	public static boolean isRetrieveSurveyWithJdbc() {
		return ApplicationConfiguration.getBoolean("survey.retrieval.use.db",
				true);
	}

	public static int getContractByIdCacheTimeout() {
		return getInteger("cache.organization-unit.ttl", 4);
	}

	public static int getGroupByIdCacheTimeout() {
		return getInteger("cache.group.ttl", 8);
	}

	public static int getAssessmentCacheTimeout() {
		return getInteger("cache.assessment.ttl", 24);
	}

	public static long getWbpRevisitId() {
		return getLong("wbp.revisit.id", 6844200070L);
	}

	public static boolean isSendOamCloseHeader() {
		return getBoolean("oam.send.close.header", false);
	}

	public static boolean isPromotionEnabled() {
		return getBoolean("promotions.enabled", true);
	}

	public static String getGlobalPromotionContentKey() {
		return getString("global.promotion.key", "HDL_GLOBAL_PROMOTION");
	}

	public static String getOrganizationPromotionContentKey() {
		return getString("organization.promotion.key",
				"HDL_ORGANIZATION_PROMOTION");
	}

	public static String getWba3ReportPrefix() {
		return getString("wba.report.prefix",
				"http://corpngdevsvc01:8180/survey-center");
	}

	public static String getOfflineEmailTemplate() {
		return getString("offline.email.template.name",
				"offline_survey_processed");
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

	public static String getMobileRegistrationGenerateTokenUrl() {
		return config.getString("mobile.registration.generate.token.url");
	}

	public static String getMobileRegistrationGenerateCertUrl() {
		return config.getString("mobile.registration.generate.cert.url");
	}

	public static String getMobileDeRegistrationUrl() {
		return config.getString("mobile.deregistration.url");
	}

	public static int getMobileRegistrationUrlPort() {
		return config.getInt("mobile.registration.url.port", 8443);
	}

	public static String getMobileRegistrationUrlScheme() {
		return config.getString("mobile.registration.url.scheme", "https");
	}

	public static String getIPhoneAppDownloadLink() {
		return config.getString("mobile.iphone.app.download.link");
	}

	public static int getTermsRepresentFrequency() {
		return config.getInt("terms.represent.frequency");
	}

	public static boolean isProductionEnvironment() {
		return getBoolean("production.environment", false);
	}

	public static String getAutonomyDatabaseName() {
		return config.getString("autonomy.db.name", "PREVIEW");
	}

	public static String getFitnessCenterRestServicesUrl() {
		return getString("fitnesscenter.rest.services.url");
	}

	public static String getFitnessCentersCacheNamespace() {
		return getString("fitnesscenter.cache.namespace", "FitnessCenters");
	}

	public static int getFitnessCenterCacheExpirationInterval() {
		return getInt("fitnesscenter.cache.expiration", 864000); // 864000
																	// seconds =
																	// 10 days
	}

	public static boolean isFilterSurveysByCampaign() {
		return getBoolean("filter.surveys.by.campaign", false);
	}

	public static boolean isFilterSurveysByCampaignAll() {
		return getBoolean("filter.surveys.by.campaign.all", false);
	}

	// filter.surveys.org.units
	@SuppressWarnings("unchecked")
	public static List<String> getSurveyFilterOrgIds() {
		String key = "filter.surveys.org.units";
		if (containsKey(key)) {
			return (List<String>) getList(key);
		}

		return null;
	}

	public static boolean isOrgUnitFiltered(String orgId) {
		try {
			return containsValue("filter.surveys.org.units", orgId);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean useLogoutRelay() {
		return getBoolean("logout.relay", false);
	}

	public static String getLogoutFederationUrl() {
		return getString("logout.federation.url");
	}

	public static String getLogoutNSpireUrlAppend() {
		return getString("logout.nspire.url.append",
				"/Default.aspx?ctl=logoff&returnurl=");
	}

	public static boolean isCgoEnabled() {
		return getBoolean("cgo.offering.enabled", false);
	}

	public static boolean isTeamChallengeEnabled() {
		return getBoolean("team.challenge.enabled", false);
	}

	public static String getFitnessCenterOfferingId() {
		return getString("fitness.offering.master.id", "8652194590");
	}

	// Get Properties used by EncryptionDecryptionService
	public static String getPrivateKeyFilePath() {
		return getString("webmd.pgp.privateKeyFilePath", "");
	}

	public static String getPrivateKeyPassphrase() {
		return getDecryptedString("webmd.pgp.privateKey.passphrase.encrypted",
				"");
	}

	public static String getWebMdContractIds() {
		return getString("webmd.contractIds", "");
	}

	public static String getWebMdUrl() {
		return getString("webmd.sso.url", "");
	}

	public static String getWebMdHraName() {
		return getString("webmd.hra.name", "");
	}

	public static int getFitnessMembershipCacheTimeout() {
		return getInteger("fitness.membership.ttl", 3600); // 3600 seconds = 1
															// hour
	}

	public static int getFitnessReplacementCardCacheTimeout() {
		return getInteger("fitness.replacement.card.ttl", 3600); // 3600 seconds
																	// = 1 hour
	}

	public static int getMobileRegistrationRetries() {
		return getInteger("mobile.registration.retries", 7);
	}

	public static boolean isI18nEnabled() {
		return getBoolean("i18n.enabled", false);
	}

	public static boolean isI18nDisabled() {
		return !isI18nEnabled();
	}

	public static boolean isWBA3ReportAvailable() {
		return getBoolean("wba3report.support.enabled", false);
	}

	public static boolean isAlwaysCall3rdPartyHRA() {
		return getBoolean("3rdpartyhra.service.alwaysCall.enabled", false);
	}

	public static String getOamDomain() {
		String oamDomain = getString("oam.domain-name");
		return oamDomain;
	}

	public static boolean isStaticContentFromIdolEnabled() {
		return getBoolean("static.content.idol.enabled", false);
	}

	public static int getRightRailTeamCount() {
		return getInt("team.challenges.right.rail.team.count", 10);
	}

	public static int getRightRailTeamMemberCount() {
		return getInt("team.challenges.right.rail.team.member.count", 10);
	}

	public static int getRightRailIndividualCount() {
		return getInt("team.challenges.right.rail.individual.count", 10);
	}

	public static int getChartCount() {
		return getInt("team.challenges.chart.count", 10);
	}

	public static int getIncentivesMasterCacheTimeout() {
		return getInteger("incentives.master.ttl", 3600); // 3600 seconds = 1
															// hour
	}

	public static long getInnergyOfferingId() {
		return getLong("innergy.offering.id", 8652137520L);
	}

	public static String getInnergyApplicationUrl() {
		return getString("innergy.app.url",
				"http://www.healthways.com/solution/default.aspx?id=969");
	}

	public static boolean isDualLoginCheckEnabled() {
		return getBoolean("dual.login.check.enabled", false);
	}

	public static String getSSOContextCookieName() {
		return getString("sso.context.cookie.name");
	}

	public static int getSSOContextCookieTTL() {
		return getInt("sso.context.cookie.ttl");
	}

	public static String getCmsIntegrtionServiceBaseUrl() {
		return getString("cms.integration.service.base.url");
	}

	public static String getStaticContentEditUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.edit.form.url"));
	}

	public static boolean isIncentivesEventServiceEnabled() {
		return getBoolean("incentives.event.service.enabled", false);
	}

	public static String getEventMasterIdUpdatePersonalTracker() {
		return getString("incentives.update.personal.tracker.id");
	}

	public static String getEventMasterIdUpdateWeightTracker() {
		return getString("incentives.update.weight.tracker.id");
	}

	public static String getEventMasterIdUpdateStepsTracker() {
		return getString("incentives.update.steps.tracker.id");
	}

	public static String getEventMasterIdUpdateExerciseTracker() {
		return getString("incentives.update.exercise.tracker.id");
	}

	public static String getEventMasterIdUpdateFoodLogTracker() {
		return getString("incentives.update.food.log.tracker.id");
	}

	public static String getEventMasterIdUpdateServingsTracker() {
		return getString("incentives.update.servings.tracker.id");
	}

	public static String getEventMasterIdBadgeActivelyParticipating() {
		return getString("incentives.badge.actively.participating.id");
	}

	public static String getEventMasterIdBadgeGoalMet() {
		return getString("incentives.badge.goal.met.id");
	}

	public static String getEventMasterIdBadgeParticipation() {
		return getString("incentives.badge.participation.id");
	}

	public static String getEventMasterIdBadgeHalfwayThere() {
		return getString("incentives.badge.halfway.there.id");
	}

	public static String getEventMasterIdJoinChallenge() {
		return getString("incentives.join.challenge.id");
	}

	public static String getEventMasterIdChatter() {
		return getString("incentives.chatter.id");
	}

	public static String getEventMasterIdShareProfile() {
		return getString("incentives.share.profile.id");
	}

	public static String getEventMasterIdSuccessStory() {
		return getString("incentives.success.story.id");
	}

	public static String getStaticContentSaveUrl() {
		return StringUtils
				.joinPath(
						getCmsIntegrtionServiceBaseUrl(),
						getString("cms.integration.service.content.edit.form.submit.url"));
	}

	public static String getStaticContentDeleteUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.delete.form.url"));
	}

	public static String getStaticContentSearchUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.search.content.url"));
	}

	public static String getStaticContentMigratePostUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.migrate.post.url"));
	}

	public static String getWbaBypassValue() {
		return getString("wba.assessment.bypass");
	}

	public static String getAppName() {
		return getString("application.name", "wbc");
	}

	public static String getStaticContentDeployUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.deploy.fetch.url"));
	}

	public static String getAllStaticContentFetchUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.all.content.url"));
	}

	public static boolean isEnforceWbaRetake() {
		return getBoolean("wba.assessment.enforce.retake.enabled");
	}

	public static String getSingleStaticContentFetchUrl() {
		return StringUtils
				.joinPath(
						getCmsIntegrtionServiceBaseUrl(),
						getString("cms.integration.service.content.single.content.url"));
	}

	public static String getStaticContentCountLocalUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.count.local.url"));
	}

	public static String getStaticContentCountRemoteUrl() {
		return StringUtils.joinPath(getCmsIntegrtionServiceBaseUrl(),
				getString("cms.integration.service.content.count.remote.url"));
	}

	public static boolean isDeviceIntegrationEnabled() {
		return getBoolean("device.integration.enabled", false);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getWBA3NamePrefix() {
		List<String> defaultList = new ArrayList<String>();
		defaultList.add("WBA 3.0");
		return (List<String>) getList("wba3.assessment.name.prefix",
				defaultList);
	}

	public static String getWBANamePrefix() {
		return getString("wba.assessment.name.prefix", "WBA");
	}
}
