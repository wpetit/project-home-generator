package com.wpetit.projecthome.generator.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * The {@link EnvironmentLinkDto} class.
 *
 * @author wpetit
 *
 */
public class EnvironmentLinkDto {
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
	/** The environment. **/
	@NotNull
	private Long environmentId;

	/**
	 * EnvironmentLinkDto constructor.
	 */
	public EnvironmentLinkDto() {
	}

	/**
	 * EnvironmentLinkDto constructor.
	 *
	 * @param id
	 * @param name
	 * @param url
	 * @param environmentId
	 */
	public EnvironmentLinkDto(final Long id, final String name, final String url, final Long environmentId) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.environmentId = environmentId;
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
	 * Return the environmentId.
	 *
	 * @return the environmentId
	 */
	public Long getEnvironmentId() {
		return environmentId;
	}

	/**
	 * Set the environmentId.
	 *
	 * @param environmentId
	 *            the environmentId to set
	 */
	public void setEnvironmentId(final Long environmentId) {
		this.environmentId = environmentId;
	}
}
