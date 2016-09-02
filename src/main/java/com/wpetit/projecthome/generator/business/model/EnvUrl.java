/**
 *
 */
package com.wpetit.projecthome.generator.business.model;

/**
 * The {@link EnvUrl} class.
 *
 * @author wpetit
 *
 */
public class EnvUrl {
	/** The urlName. **/
	private String urlName;
	/** The url. **/
	private String url;

	/**
	 * EnvUrl constructor.
	 */
	public EnvUrl() {
		// default constructor
	}

	/**
	 * EnvUrl constructor.
	 *
	 * @param urlName
	 * @param url
	 */
	public EnvUrl(final String urlName, final String url) {
		this.urlName = urlName;
		this.url = url;
	}

	/**
	 * Return the urlName.
	 *
	 * @return the urlName
	 */
	public String getUrlName() {
		return urlName;
	}

	/**
	 * Set the urlName.
	 *
	 * @param urlName
	 *            the urlName to set
	 */
	public void setUrlName(final String urlName) {
		this.urlName = urlName;
	}

	/**
	 * Return the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url.
	 *
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}
}
