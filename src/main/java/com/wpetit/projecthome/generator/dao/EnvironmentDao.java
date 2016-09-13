/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.Environment;

/**
 * The {@link EnvironmentDao} class.
 *
 * @author wpetit
 *
 */
public interface EnvironmentDao extends JpaRepository<Environment, Long> {

	/**
	 * Find environments of the given project.
	 *
	 * @param projecId
	 *            the project Id
	 * @return the environments
	 */
	List<Environment> findByProjectId(Long projecId);

	/**
	 * Delete environments related to the given project.
	 *
	 * @param projectId
	 *            the project id
	 */
	void deleteByProjectId(Long projectId);
}
