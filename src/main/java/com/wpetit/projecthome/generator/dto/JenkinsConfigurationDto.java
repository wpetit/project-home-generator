/**
 *
 */
package com.wpetit.projecthome.generator.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link JenkinsConfigurationDto} class.
 *
 * @author wpetit
 *
 */
public class JenkinsConfigurationDto {
	/** The id. **/
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The jobsName. **/
	@NotEmpty
	private List<String> jobsName;
	/** The project. **/
	private Long projectId;

	/**
	 * JenkinsConfigurationDto constructor.
	 */
	public JenkinsConfigurationDto() {
		// default constructor
	}

	/**
	 * JenkinsConfigurationDto constructor.
	 *
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param jobsName
	 *            the jobsName
	 * @param projectId
	 *            the projectId
	 */
	public JenkinsConfigurationDto(final Long id, final String url, final List<String> jobsName, final Long projectId) {
		this.id = id;
		this.url = url;
		this.jobsName = jobsName;
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
	 * Return the jobsName.
	 *
	 * @return the jobsName
	 */
	public List<String> getJobsName() {
		return jobsName;
	}

	/**
	 * Set the jobsName.
	 *
	 * @param jobsName
	 *            the jobsName to set
	 */
	public void setJobsName(final List<String> jobsName) {
		this.jobsName = jobsName;
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
