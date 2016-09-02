/**
 *
 */
package com.wpetit.projecthome.generator.business;

import java.util.List;

import com.wpetit.projecthome.generator.business.model.ApplicationConfiguration;
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;
import com.wpetit.projecthome.generator.model.JenkinsConfiguration;
import com.wpetit.projecthome.generator.model.Link;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.SonarConfiguration;
import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link ProjectBusiness} class.
 *
 * @author wpetit
 *
 */
public interface ProjectBusiness {
	/**
	 * Find all projects.
	 *
	 * @return projects found.
	 */
	List<Project> findAll();

	/**
	 * Add a project.
	 *
	 * @param project
	 *            the project
	 * @return the project
	 */
	Project addProject(Project project);

	/**
	 * Add an environment.
	 *
	 * @param projectId
	 *            the project
	 * @param environment
	 *            the environment
	 * @return the environment
	 */
	Environment addEnvironment(Long projectId, Environment environment);

	/**
	 * Find all environments of the given project.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the environments
	 */
	List<Environment> findAllEnvironments(Long projectId);

	/**
	 * Return the project with the given id.
	 *
	 * @param projectId
	 *            the project id
	 * @return the project
	 */
	Project getProject(Long projectId);

	/**
	 * Add an environmentLink.
	 *
	 * @param environmentId
	 *            the environment
	 * @param environmentLink
	 *            the environmentLink
	 * @return the environmentLink saved
	 */
	EnvironmentLink addEnvironmentLink(Long environmentId, EnvironmentLink environmentLink);

	/**
	 * Find all environment links of the given environment.
	 *
	 * @param environmentId
	 *            the environmentId Id
	 * @return the environment links
	 */
	List<EnvironmentLink> findAllEnvironmentLinks(Long environmentId);

	/**
	 * Add a tool to a project.
	 *
	 * @param projectId
	 *            the project
	 * @param tool
	 *            the tool
	 * @return the tool saved
	 */
	Tool addTool(Long projectId, Tool tool);

	/**
	 * Find project tools.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the tools found
	 */
	List<Tool> findAllTools(Long projectId);

	/**
	 * Add jenkinsConfiguration to project.
	 *
	 * @param projectId
	 *            the project
	 * @param jenkinsConfiguration
	 * @return the jenkinsConfiguration saved.
	 */
	JenkinsConfiguration addJenkinsConfiguration(Long projectId, JenkinsConfiguration jenkinsConfiguration);

	/**
	 * Get the jenkins configuration of the project.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the jenkins configuration
	 */
	JenkinsConfiguration getJenkinsConfiguration(Long projectId);

	/**
	 * Generate the project configuration.
	 *
	 * @param projectId
	 *            the project id
	 * @return the configuration
	 */
	ApplicationConfiguration generateProjectConfiguration(Long projectId);

	/**
	 * Add a link to a project.
	 *
	 * @param projectId
	 *            the project
	 * @param link
	 *            the link
	 * @return the link saved
	 */
	Link addLink(Long projectId, Link link);

	/**
	 * Find project links.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the links found
	 */
	List<Link> findAllLinks(Long projectId);

	/**
	 * Get the sonar configuration of the project.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the sonar configuration
	 */
	SonarConfiguration getSonarConfiguration(Long projectId);

	/**
	 * Add sonarConfiguration to project.
	 *
	 * @param projectId
	 *            the project
	 * @param sonarConfiguration
	 * @return the sonarConfiguration saved.
	 */
	SonarConfiguration addSonarConfiguration(Long projectId, SonarConfiguration sonarConfiguration);
}
