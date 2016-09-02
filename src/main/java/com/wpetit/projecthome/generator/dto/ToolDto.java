/**
 *
 */
package com.wpetit.projecthome.generator.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link ToolDto} class.
 *
 * @author wpetit
 *
 */
public class ToolDto {
	/** The id. **/
	private Long id;
	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;
	/** The url. **/
	@NotNull
	@URL
	private String url;
	/** The project. **/
	private Long projectId;

	/**
	 * ToolDto constructor.
	 */
	public ToolDto() {
	}

	/**
	 * ToolDto constructor.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param url
	 *            the url
	 * @param projectId
	 *            the projectId
	 */
	public ToolDto(final Long id, final String name, final String url, final Long projectId) {
		this.id = id;
		this.name = name;
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
