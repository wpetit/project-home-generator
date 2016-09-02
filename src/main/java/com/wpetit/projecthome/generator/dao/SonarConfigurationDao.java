/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.SonarConfiguration;

/**
 * The {@link SonarConfigurationDao} class.
 *
 * @author wpetit
 *
 */
public interface SonarConfigurationDao extends JpaRepository<SonarConfiguration, Long> {
	/**
	 * Get the project sonar configuration.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the sonar configuration
	 */
	SonarConfiguration findByProjectId(Long projectId);
}
