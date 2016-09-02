/**
 *
 */
package com.wpetit.projecthome.generator.business.model;

/**
 * The {@link ApplicationTool} class.
 *
 * @author wpetit
 *
 */
public class ApplicationTool {
	/** The url. **/
	private String url;
	/** The name. **/
	private String name;

	/**
	 * ApplicationTool constructor.
	 */
	public ApplicationTool() {
		// default constructor
	}

	/**
	 * ApplicationTool constructor.
	 *
	 * @param url
	 * @param name
	 */
	public ApplicationTool(final String url, final String name) {
		this.url = url;
		this.name = name;
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

	/**
	 * Return the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
