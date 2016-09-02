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
 * The {@link EnvironmentLink} class.
 *
 * @author wpetit
 *
 */
@Entity
public class EnvironmentLink {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@ManyToOne
	private Environment environment;

	/**
	 * EnvironmentLink constructor.
	 */
	public EnvironmentLink() {
		// default constructor
	}

	/**
	 * EnvironmentLink constructor.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param url
	 *            the url
	 * @param environment
	 *            the environment
	 */
	public EnvironmentLink(final Long id, final String name, final String url, final Environment environment) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.environment = environment;
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
	 * Return the environment.
	 *
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * Set the environment.
	 *
	 * @param environment
	 *            the environment to set
	 */
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}
}
