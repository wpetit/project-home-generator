/**
 *
 */
package com.wpetit.projecthome.generator.business.model;

import java.util.List;

/**
 * The {@link ApplicationConfiguration} class.
 *
 * @author wpetit
 *
 */
public class ApplicationConfiguration {
	/** The applicationName. **/
	private String applicationName;
	/** The applicationLogo. **/
	private String applicationLogo;
	/** The applicationLinks. **/
	private List<ApplicationLink> applicationLinks;
	/** The applicationTools. **/
	private List<ApplicationTool> applicationTools;
	/** The env. **/
	private List<Env> env;
	/** The jenkinsUrl. **/
	private String jenkinsUrl;
	/** The jenkinsBase64UsrPwd. **/
	private String jenkinsBase64UsrPwd;
	/** The sonarBase64UsrPwd. **/
	private String sonarBase64UsrPwd;
	/** The sonarUrl. **/
	private String sonarUrl;
	/** The jenkinsJobs. **/
	private List<String> jenkinsJobs;
	/** The sonarResources. **/
	private List<String> sonarResources;

	/**
	 * ApplicationConfiguration constructor.
	 */
	public ApplicationConfiguration() {
		// default constructor
	}

	/**
	 * ApplicationConfiguration constructor.
	 *
	 * @param applicationName
	 * @param applicationLogo
	 * @param applicationLinks
	 * @param applicationTools
	 * @param env
	 * @param jenkinsUrl
	 * @param jenkinsBase64UsrPwd
	 * @param sonarBase64UsrPwd
	 * @param sonarUrl
	 * @param jenkinsJobs
	 * @param sonarResources
	 */
	public ApplicationConfiguration(final String applicationName, final String applicationLogo,
			final List<ApplicationLink> applicationLinks, final List<ApplicationTool> applicationTools,
			final List<Env> env, final String jenkinsUrl, final String jenkinsBase64UsrPwd,
			final String sonarBase64UsrPwd, final String sonarUrl, final List<String> jenkinsJobs,
			final List<String> sonarResources) {
		this.applicationName = applicationName;
		this.applicationLogo = applicationLogo;
		this.applicationLinks = applicationLinks;
		this.applicationTools = applicationTools;
		this.env = env;
		this.jenkinsUrl = jenkinsUrl;
		this.jenkinsBase64UsrPwd = jenkinsBase64UsrPwd;
		this.sonarBase64UsrPwd = sonarBase64UsrPwd;
		this.sonarUrl = sonarUrl;
		this.jenkinsJobs = jenkinsJobs;
		this.sonarResources = sonarResources;
	}

	/**
	 * Return the applicationName.
	 *
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * Set the applicationName.
	 *
	 * @param applicationName
	 *            the applicationName to set
	 */
	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Return the applicationLogo.
	 *
	 * @return the applicationLogo
	 */
	public String getApplicationLogo() {
		return applicationLogo;
	}

	/**
	 * Set the applicationLogo.
	 *
	 * @param applicationLogo
	 *            the applicationLogo to set
	 */
	public void setApplicationLogo(final String applicationLogo) {
		this.applicationLogo = applicationLogo;
	}

	/**
	 * Return the applicationLinks.
	 *
	 * @return the applicationLinks
	 */
	public List<ApplicationLink> getApplicationLinks() {
		return applicationLinks;
	}

	/**
	 * Set the applicationLinks.
	 *
	 * @param applicationLinks
	 *            the applicationLinks to set
	 */
	public void setApplicationLinks(final List<ApplicationLink> applicationLinks) {
		this.applicationLinks = applicationLinks;
	}

	/**
	 * Return the applicationTools.
	 *
	 * @return the applicationTools
	 */
	public List<ApplicationTool> getApplicationTools() {
		return applicationTools;
	}

	/**
	 * Set the applicationTools.
	 *
	 * @param applicationTools
	 *            the applicationTools to set
	 */
	public void setApplicationTools(final List<ApplicationTool> applicationTools) {
		this.applicationTools = applicationTools;
	}

	/**
	 * Return the env.
	 *
	 * @return the env
	 */
	public List<Env> getEnv() {
		return env;
	}

	/**
	 * Set the env.
	 *
	 * @param env
	 *            the env to set
	 */
	public void setEnv(final List<Env> env) {
		this.env = env;
	}

	/**
	 * Return the jenkinsUrl.
	 *
	 * @return the jenkinsUrl
	 */
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}

	/**
	 * Set the jenkinsUrl.
	 *
	 * @param jenkinsUrl
	 *            the jenkinsUrl to set
	 */
	public void setJenkinsUrl(final String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}

	/**
	 * Return the sonarUrl.
	 *
	 * @return the sonarUrl
	 */
	public String getSonarUrl() {
		return sonarUrl;
	}

	/**
	 * Set the sonarUrl.
	 *
	 * @param sonarUrl
	 *            the sonarUrl to set
	 */
	public void setSonarUrl(final String sonarUrl) {
		this.sonarUrl = sonarUrl;
	}

	/**
	 * Return the jenkinsJobs.
	 *
	 * @return the jenkinsJobs
	 */
	public List<String> getJenkinsJobs() {
		return jenkinsJobs;
	}

	/**
	 * Set the jenkinsJobs.
	 *
	 * @param jenkinsJobs
	 *            the jenkinsJobs to set
	 */
	public void setJenkinsJobs(final List<String> jenkinsJobs) {
		this.jenkinsJobs = jenkinsJobs;
	}

	/**
	 * Return the sonarResources.
	 *
	 * @return the sonarResources
	 */
	public List<String> getSonarResources() {
		return sonarResources;
	}

	/**
	 * Set the sonarResources.
	 *
	 * @param sonarResources
	 *            the sonarResources to set
	 */
	public void setSonarResources(final List<String> sonarResources) {
		this.sonarResources = sonarResources;
	}

	/**
	 * Return the jenkinsBase64UsrPwd.
	 *
	 * @return the jenkinsBase64UsrPwd
	 */
	public String getJenkinsBase64UsrPwd() {
		return jenkinsBase64UsrPwd;
	}

	/**
	 * Set the jenkinsBase64UsrPwd.
	 *
	 * @param jenkinsBase64UsrPwd
	 *            the jenkinsBase64UsrPwd to set
	 */
	public void setJenkinsBase64UsrPwd(final String jenkinsBase64UsrPwd) {
		this.jenkinsBase64UsrPwd = jenkinsBase64UsrPwd;
	}

	/**
	 * Return the sonarBase64UsrPwd.
	 *
	 * @return the sonarBase64UsrPwd
	 */
	public String getSonarBase64UsrPwd() {
		return sonarBase64UsrPwd;
	}

	/**
	 * Set the sonarBase64UsrPwd.
	 *
	 * @param sonarBase64UsrPwd
	 *            the sonarBase64UsrPwd to set
	 */
	public void setSonarBase64UsrPwd(final String sonarBase64UsrPwd) {
		this.sonarBase64UsrPwd = sonarBase64UsrPwd;
	}
}
