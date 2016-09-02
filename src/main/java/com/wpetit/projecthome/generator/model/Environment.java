package com.wpetit.projecthome.generator.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The {@link Environment} class.
 *
 * @author wpetit
 *
 */
@Entity
public class Environment {
	/** The id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The name. **/
	@NotBlank
	@Length(max = 256)
	private String name;
	/** The environmentLinks. **/
	@OneToMany(mappedBy = "environment")
	private List<EnvironmentLink> environmentLinks;
	/** The project. **/
	@NotNull
	@ManyToOne
	private Project project;

	/**
	 * Environment constructor.
	 */
	public Environment() {
		// NO-OP
	}

	/**
	 * Environment constructor.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param environmentLinks
	 *            the links
	 * @param project
	 *            the project
	 */
	public Environment(final Long id, final String name, final List<EnvironmentLink> environmentLinks,
			final Project project) {
		this.id = id;
		this.name = name;
		this.environmentLinks = environmentLinks;
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
	 * Return the environmentLinks.
	 *
	 * @return the environmentLinks
	 */
	public List<EnvironmentLink> getEnvironmentLinks() {
		return environmentLinks;
	}

	/**
	 * Set the environmentLinks.
	 *
	 * @param environmentLinks
	 *            the environmentLinks to set
	 */
	public void setEnvironmentLinks(final List<EnvironmentLink> environmentLinks) {
		this.environmentLinks = environmentLinks;
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
