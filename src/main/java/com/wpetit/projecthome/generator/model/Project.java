package com.wpetit.projecthome.generator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The {@link Project} class.
 *
 * @author wpetit
 *
 */
@Entity
public class Project {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;

	/** The image. **/
	@Length(max = 1024)
	private String image;

	/**
	 * Project constructor.
	 */
	public Project() {
		// NO-OP
	}

	/**
	 * Project constructor.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param image
	 *            the image
	 */
	public Project(final Long id, final String name, final String image) {
		this.id = id;
		this.name = name;
		this.image = image;
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

}
