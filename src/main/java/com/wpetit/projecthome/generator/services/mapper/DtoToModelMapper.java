/**
 *
 */
package com.wpetit.projecthome.generator.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wpetit.projecthome.generator.dto.EnvironmentDto;
import com.wpetit.projecthome.generator.dto.EnvironmentLinkDto;
import com.wpetit.projecthome.generator.dto.JenkinsConfigurationDto;
import com.wpetit.projecthome.generator.dto.LinkDto;
import com.wpetit.projecthome.generator.dto.ProjectDto;
import com.wpetit.projecthome.generator.dto.SonarConfigurationDto;
import com.wpetit.projecthome.generator.dto.ToolDto;
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;
import com.wpetit.projecthome.generator.model.JenkinsConfiguration;
import com.wpetit.projecthome.generator.model.Link;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.SonarConfiguration;
import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link DtoToModelMapper} class.
 *
 * @author wpetit
 *
 */
@Mapper(componentModel = "spring")
public interface DtoToModelMapper {
	/**
	 * Map {@link ProjectDto} to {@link Project}.
	 *
	 * @param projectDto
	 *            the dto
	 * @return the model
	 */
	Project map(ProjectDto projectDto);

	/**
	 * Map the {@link EnvironmentDto} to {@link Environment}.
	 *
	 * @param environmentDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "project", ignore = true)
	@Mapping(target = "environmentLinks", ignore = true)
	Environment map(EnvironmentDto environmentDto);

	/**
	 * Map the {@link EnvironmentLinkDto} to {@link EnvironmentLink}.
	 *
	 * @param environmentLinkDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "environment", ignore = true)
	EnvironmentLink map(EnvironmentLinkDto environmentLinkDto);

	/**
	 * Map the {@link ToolDto} to {@link Tool}.
	 *
	 * @param toolDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "project", ignore = true)
	Tool map(ToolDto toolDto);

	/**
	 * Map the {@link LinkDto} to {@link Link}.
	 *
	 * @param linkDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "project", ignore = true)
	Link map(LinkDto linkDto);

	/**
	 * Map the {@link JenkinsConfigurationDto} to {@link JenkinsConfiguration}.
	 *
	 * @param jenkinsConfigurationDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "project", ignore = true)
	JenkinsConfiguration map(JenkinsConfigurationDto jenkinsConfigurationDto);

	/**
	 * Map the {@link SonarConfigurationDto} to {@link SonarConfiguration}.
	 *
	 * @param sonarConfigurationDto
	 *            the dto
	 * @return the model
	 */
	@Mapping(target = "project", ignore = true)
	SonarConfiguration map(SonarConfigurationDto sonarConfigurationDto);
}
