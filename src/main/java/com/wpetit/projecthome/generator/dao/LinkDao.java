/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.Link;

/**
 * The {@link LinkDao} class.
 *
 * @author wpetit
 *
 */
public interface LinkDao extends JpaRepository<Link, Long> {

	/**
	 * Find project links.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the links found
	 */
	List<Link> findByProjectId(Long projectId);

}
