package com.wpetit.projecthome.generator.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The {@link ProjectDto} class.
 *
 * @author wpetit
 *
 */
public class ProjectDto {
	/** The id. **/
	private Long id;

	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;

	/** The image. **/
	@Length(max = 256)
	private String image;

	/**
	 * ProjectDto constructor.
	 */
	public ProjectDto() {
		// NO-OP
	}

	/**
	 * ProjectDto constructor.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param image
	 *            the image
	 */
	public ProjectDto(final Long id, final String name, final String image) {
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
