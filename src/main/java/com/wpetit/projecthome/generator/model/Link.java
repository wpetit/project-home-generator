/**
 *
 */
package com.wpetit.projecthome.generator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link Link} class.
 *
 * @author wpetit
 *
 */
@Entity
public class Link {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;
	/** The url. **/
	@NotBlank
	@URL
	private String url;
	/** The image. **/
	@NotBlank
	@Length(max = 1024)
	private String image;
	/** The project. **/
	@NotNull
	@ManyToOne
	private Project project;

	/**
	 * Link constructor.
	 */
	public Link() {
		// default constructor
	}

	/**
	 * Link constructor.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param url
	 *            the url
	 * @param image
	 *            the image
	 * @param project
	 *            the project
	 */
	public Link(final Long id, final String name, final String url, final String image, final Project project) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.image = image;
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
	 * Return the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Set the image.
	 *
	 * @param image
	 *            the image to set
	 */
	public void setImage(final String image) {
		this.image = image;
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
