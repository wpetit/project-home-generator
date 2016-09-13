/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;

/**
 * The {@link EnvironmentLinkDao} class.
 *
 * @author wpetit
 *
 */
public interface EnvironmentLinkDao extends JpaRepository<EnvironmentLink, Long> {
	/**
	 * Find {@link EnvironmentLink} by {@link Environment}.
	 *
	 * @param environmentId
	 *            the environment id
	 * @return environmentLink found
	 */
	List<EnvironmentLink> findByEnvironmentId(Long environmentId);

	/**
	 * Delete environment links related to the given project.
	 *
	 * @param projectId
	 *            the project id
	 */
	void deleteByEnvironmentProjectId(Long projectId);

	/**
	 * Delete environment links related to the given environment.
	 * 
	 * @param environmentId
	 *            the environment id
	 */
	void deleteByEnvironmentId(Long environmentId);
}
