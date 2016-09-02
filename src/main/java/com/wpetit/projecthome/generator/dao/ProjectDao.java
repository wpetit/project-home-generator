/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link ProjectDao} class.
 *
 * @author wpetit
 *
 */
public interface ProjectDao extends JpaRepository<Project, Long> {

}
