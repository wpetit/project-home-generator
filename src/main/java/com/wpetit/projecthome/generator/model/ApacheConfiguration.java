/**
 *
 */
package com.wpetit.projecthome.generator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.URL;

/**
 * The {@link ApacheConfiguration} class.
 *
 * @author wpetit
 *
 */
@Entity
public class ApacheConfiguration {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The project. **/
	@OneToOne
	private Project project;

	/**
	 * ApacheConfiguration constructor.
	 */
	public ApacheConfiguration() {
		// default constructor
	}

	/**
	 * ApacheConfiguration constructor.
	 *
	 * @param url
	 *            the url
	 * @param project
	 *            the project
	 */
	public ApacheConfiguration(final String url, final Project project) {
		this.url = url;
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
