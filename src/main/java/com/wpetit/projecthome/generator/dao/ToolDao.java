/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link ToolDao} class.
 *
 * @author wpetit
 *
 */
public interface ToolDao extends JpaRepository<Tool, Long> {

	/**
	 * Find project tools.
	 *
	 * @param projectId
	 *            the project Id
	 * @return the tools found
	 */
	List<Tool> findByProjectId(Long projectId);

	/**
	 * Delete tools related to the given project.
	 *
	 * @param projectId
	 *            the project id
	 */
	void deleteByProjectId(Long projectId);
}
