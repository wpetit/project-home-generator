/**
 *
 */
package com.wpetit.projecthome.generator.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wpetit.projecthome.generator.dto.ApacheConfigurationDto;
import com.wpetit.projecthome.generator.dto.EnvironmentDto;
import com.wpetit.projecthome.generator.dto.EnvironmentLinkDto;
import com.wpetit.projecthome.generator.dto.JenkinsConfigurationDto;
import com.wpetit.projecthome.generator.dto.LinkDto;
import com.wpetit.projecthome.generator.dto.ProjectDto;
import com.wpetit.projecthome.generator.dto.SonarConfigurationDto;
import com.wpetit.projecthome.generator.dto.ToolDto;
import com.wpetit.projecthome.generator.model.ApacheConfiguration;
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;
import com.wpetit.projecthome.generator.model.JenkinsConfiguration;
import com.wpetit.projecthome.generator.model.Link;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.SonarConfiguration;
import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link ModelToDtoMapper} class.
 *
 * @author wpetit
 *
 */
@Mapper(componentModel = "spring")
public interface ModelToDtoMapper {
	/**
	 * Map {@link Project} to {@link ProjectDto}.
	 *
	 * @param project
	 *            the project
	 * @return the dto
	 */
	ProjectDto map(Project project);

	/**
	 * Map {@link Environment} to {@link EnvironmentDto}.
	 *
	 * @param environment
	 *            the environment
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	EnvironmentDto map(Environment environment);

	/**
	 * Map {@link EnvironmentLink} to {@link EnvironmentLinkDto}.
	 *
	 * @param environmentLink
	 *            the environment link
	 * @return the dto
	 */
	@Mapping(source = "environment.id", target = "environmentId")
	EnvironmentLinkDto map(EnvironmentLink environmentLink);

	/**
	 * Map {@link Tool} to {@link ToolDto}
	 *
	 * @param tool
	 *            the tool
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	ToolDto map(Tool tool);

	/**
	 * Map {@link Link} to {@link LinkDto}
	 *
	 * @param link
	 *            the link
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	LinkDto map(Link link);

	/**
	 * Map {@link JenkinsConfiguration} to {@link JenkinsConfigurationDto}.
	 *
	 * @param jenkinsConfiguration
	 *            the jenkinsConfiguration
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	JenkinsConfigurationDto map(JenkinsConfiguration jenkinsConfiguration);

	/**
	 * Map {@link ApacheConfiguration} to {@link ApacheConfigurationDto}.
	 *
	 * @param apacheConfiguration
	 *            the apacheConfiguration
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	ApacheConfigurationDto map(ApacheConfiguration apacheConfiguration);

	/**
	 * Map {@link SonarConfiguration} to {@link SonarConfigurationDto}.
	 *
	 * @param sonarConfiguration
	 *            the sonarConfiguration
	 * @return the dto
	 */
	@Mapping(source = "project.id", target = "projectId")
	SonarConfigurationDto map(SonarConfiguration sonarConfiguration);
}
