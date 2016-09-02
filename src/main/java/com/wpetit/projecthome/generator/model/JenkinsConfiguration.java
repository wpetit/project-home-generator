/**
 *
 */
package com.wpetit.projecthome.generator.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link JenkinsConfiguration} class.
 *
 * @author wpetit
 *
 */
@Entity
public class JenkinsConfiguration {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The jobsName. **/
	@NotEmpty
	@ElementCollection
	private List<String> jobsName;
	/** The project. **/
	@OneToOne
	private Project project;

	/**
	 * JenkinsConfiguration constructor.
	 */
	public JenkinsConfiguration() {
		// default constructor
	}

	/**
	 * JenkinsConfiguration constructor.
	 *
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param jobsName
	 *            the jobsName
	 * @param project
	 *            the project
	 */
	public JenkinsConfiguration(final Long id, final String url, final List<String> jobsName, final Project project) {
		this.id = id;
		this.url = url;
		this.jobsName = jobsName;
		this.project = project;
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
	 * Return the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Set the project.
	 *
	 * @param project
	 *            the project to set
	 */
	public void setProject(final Project project) {
		this.project = project;
	}

}
