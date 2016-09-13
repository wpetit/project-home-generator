/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.ApacheConfiguration;

/**
 * The {@link ApacheConfigurationDao} class.
 *
 * @author wpetit
 *
 */
public interface ApacheConfigurationDao extends JpaRepository<ApacheConfiguration, Long> {
	/**
	 * Return the apache configuration of the given project.
	 * 
	 * @param projectId
	 *            the project id
	 * @return the apache configuration
	 */
	ApacheConfiguration findByProjectId(Long projectId);
}
