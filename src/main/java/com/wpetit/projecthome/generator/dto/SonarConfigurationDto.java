/**
 *
 */
package com.wpetit.projecthome.generator.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link SonarConfigurationDto} class.
 *
 * @author wpetit
 *
 */
public class SonarConfigurationDto {
	/** The id. **/
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The resourceNames. **/
	@NotEmpty
	private List<String> resourceNames;
	/** The project. **/
	private Long projectId;

	/**
	 * SonarConfigurationDto constructor.
	 */
	public SonarConfigurationDto() {
	}

	/**
	 * SonarConfigurationDto constructor.
	 * 
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param resourceNames
	 *            the resourceNames
	 * @param projectId
	 *            the projectId
	 */
	public SonarConfigurationDto(final Long id, final String url, final List<String> resourceNames,
			final Long projectId) {
		this.id = id;
		this.url = url;
		this.resourceNames = resourceNames;
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

	/**
	 * Return the resourceNames.
	 *
	 * @return the resourceNames
	 */
	public List<String> getResourceNames() {
		return resourceNames;
	}

	/**
	 * Set the resourceNames.
	 *
	 * @param resourceNames
	 *            the resourceNames to set
	 */
	public void setResourceNames(final List<String> resourceNames) {
		this.resourceNames = resourceNames;
	}

}
