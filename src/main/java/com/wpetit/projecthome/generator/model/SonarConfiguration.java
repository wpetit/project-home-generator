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
 * The {@link SonarConfiguration} class.
 *
 * @author wpetit
 *
 */
@Entity
public class SonarConfiguration {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The url. **/
	@URL
	private String url;
	/** The resourceNames. **/
	@NotEmpty
	@ElementCollection
	private List<String> resourceNames;
	/** The project. **/
	@OneToOne
	private Project project;

	/**
	 * SonarConfiguration constructor.
	 */
	public SonarConfiguration() {
		// default constructor
	}

	/**
	 * SonarConfiguration constructor.
	 *
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param resourceNames
	 *            the resourceNames
	 * @param project
	 *            the project
	 */
	public SonarConfiguration(final Long id, final String url, final List<String> resourceNames,
			final Project project) {
		this.id = id;
		this.url = url;
		this.resourceNames = resourceNames;
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
