/**
 *
 */
package com.wpetit.projecthome.generator.business.model;

/**
 * The {@link ApplicationLink} class.
 *
 * @author wpetit
 *
 */
public class ApplicationLink {
	/** The linkLogo. **/
	private String linkLogo;
	/** The linkName. **/
	private String linkName;
	/** The linkUrl. **/
	private String linkUrl;

	/**
	 * ApplicationLink constructor.
	 */
	public ApplicationLink() {
		// default constructor
	}

	/**
	 * ApplicationLink constructor.
	 *
	 * @param linkLogo
	 * @param linkName
	 * @param linkUrl
	 */
	public ApplicationLink(final String linkLogo, final String linkName, final String linkUrl) {
		this.linkLogo = linkLogo;
		this.linkName = linkName;
		this.linkUrl = linkUrl;
	}

	/**
	 * Return the linkLogo.
	 *
	 * @return the linkLogo
	 */
	public String getLinkLogo() {
		return linkLogo;
	}

	/**
	 * Set the linkLogo.
	 *
	 * @param linkLogo
	 *            the linkLogo to set
	 */
	public void setLinkLogo(final String linkLogo) {
		this.linkLogo = linkLogo;
	}

	/**
	 * Return the linkName.
	 *
	 * @return the linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * Set the linkName.
	 *
	 * @param linkName
	 *            the linkName to set
	 */
	public void setLinkName(final String linkName) {
		this.linkName = linkName;
	}

	/**
	 * Return the linkUrl.
	 *
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * Set the linkUrl.
	 *
	 * @param linkUrl
	 *            the linkUrl to set
	 */
	public void setLinkUrl(final String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
