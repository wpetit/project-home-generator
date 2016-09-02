package com.wpetit.projecthome.generator.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The {@link EnvironmentDto} class.
 *
 * @author wpetit
 *
 */
public class EnvironmentDto {
	/** The id. **/
	private Long id;
	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;
	/** The projectId. **/
	@NotNull
	private Long projectId;

	/**
	 * EnvironmentDto constructor.
	 */
	public EnvironmentDto() {
		// default constructor
	}

	/**
	 * EnvironmentDto constructor.
	 *
	 * @param id
	 * @param name
	 * @param projectId
	 */
	public EnvironmentDto(final Long id, final String name, final Long projectId) {
		this.id = id;
		this.name = name;
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
