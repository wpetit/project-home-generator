/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.JenkinsConfiguration;

/**
 * The {@link JenkinsConfigurationDao} class.
 *
 * @author wpetit
 *
 */
public interface JenkinsConfigurationDao extends JpaRepository<JenkinsConfiguration, Long> {
	/**
	 * Get the project jenkins configuration.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the jenkins configuration
	 */
	JenkinsConfiguration findByProjectId(Long projectId);
}
