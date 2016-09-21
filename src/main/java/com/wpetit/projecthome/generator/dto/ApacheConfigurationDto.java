/**
 *
 */
package com.wpetit.projecthome.generator.dto;

import org.hibernate.validator.constraints.URL;

/**
 * The {@link ApacheConfigurationDto} class.
 *
 * @author wpetit
 *
 */
public class ApacheConfigurationDto {
	/** The id. **/
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The project. **/
	private Long projectId;

	/**
	 * ApacheConfigurationDto constructor.
	 */
	public ApacheConfigurationDto() {
		// default constructor
	}

	/**
	 * ApacheConfigurationDto constructor.
	 *
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param projectId
	 *            the projectId
	 */
	public ApacheConfigurationDto(final Long id, final String url, final Long projectId) {
		this.id = id;
		this.url = url;
		this.projectId = projectId;
	}

	/**
	 * Return the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * Return the projectId.
	 *
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * Set the projectId.
	 *
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(final Long projectId) {
		this.projectId = projectId;
	}
}
