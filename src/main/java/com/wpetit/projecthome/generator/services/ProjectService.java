package com.wpetit.projecthome.generator.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wpetit.projecthome.generator.business.ProjectBusiness;
import com.wpetit.projecthome.generator.business.model.ApplicationConfiguration;
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
import com.wpetit.projecthome.generator.services.mapper.DtoToModelMapper;
import com.wpetit.projecthome.generator.services.mapper.ModelToDtoMapper;

/**
 * The {@link ProjectService} class.
 *
 * @author wpetit
 *
 */
@RestController
@RequestMapping("/project")
public class ProjectService {

	/** The modelToDtoMapper. **/
	@Autowired
	private ModelToDtoMapper modelToDtoMapper;

	/** The dtoToModelMapper. **/
	@Autowired
	private DtoToModelMapper dtoToModelMapper;

	/** The projectBusiness. **/
	@Autowired
	private ProjectBusiness projectBusiness;

	/**
	 * Find all projects.
	 *
	 * @return the projects
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ProjectDto> findAllProjects() {
		return projectBusiness.findAll().stream().map(modelToDtoMapper::map).collect(Collectors.toList());
	}

	/**
	 * Return the project with the given id.
	 *
	 * @param projectId
	 *            the project id
	 * @return the project
	 */
	@RequestMapping(value = "{projectId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ProjectDto> getProject(@PathVariable("projectId") final Long projectId) {
		final Project project = projectBusiness.getProject(projectId);
		if (project == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(modelToDtoMapper.map(project), HttpStatus.OK);
		}
	}

	/**
	 * Add a project.
	 *
	 * @param projectDto
	 *            the project
	 * @return the project saved
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ProjectDto addProject(@RequestBody @Valid final ProjectDto projectDto) {
		final Project project = projectBusiness.addOrUpdateProject(dtoToModelMapper.map(projectDto));
		return modelToDtoMapper.map(project);
	}

	/**
	 * Update a project.
	 *
	 * @param projectDto
	 *            the project
	 * @return the project updated
	 */
	@RequestMapping(value = "{projectId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ProjectDto updateProject(@RequestBody @Valid final ProjectDto projectDto) {
		final Project project = projectBusiness.addOrUpdateProject(dtoToModelMapper.map(projectDto));
		return modelToDtoMapper.map(project);
	}

	/**
	 * Delete a project.
	 *
	 * @param projectId
	 *            the project id
	 */
	@RequestMapping(value = "{projectId}", method = RequestMethod.DELETE)
	public void deleteProject(@PathVariable("projectId") final Long projectId) {
		projectBusiness.deleteProject(projectId);
	}

	/**
	 * Add an environment to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param environmentDto
	 *            the environment
	 * @return the environment saved
	 */
	@RequestMapping(value = "{projectId}/environment", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public EnvironmentDto addEnvironment(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final EnvironmentDto environmentDto) {
		final Environment environment = projectBusiness.addEnvironment(projectId, dtoToModelMapper.map(environmentDto));
		return modelToDtoMapper.map(environment);
	}

	/**
	 * Find environments of the given project.
	 *
	 * @param projectId
	 *            the project
	 * @return the environments
	 */
	@RequestMapping(value = "{projectId}/environment", method = RequestMethod.GET, produces = "application/json")
	public List<EnvironmentDto> findEnvironmentsByProject(@PathVariable("projectId") final Long projectId) {
		return projectBusiness.findAllEnvironments(projectId).stream().map(modelToDtoMapper::map)
				.collect(Collectors.toList());
	}

	/**
	 * Delete environment.
	 *
	 * @param envId
	 *            the environment id
	 */
	@RequestMapping(value = "{projectId}/environment/{envId}", method = RequestMethod.DELETE)
	public void deleteEnvironment(@PathVariable("envId") final Long envId) {
		projectBusiness.deleteEnvironment(envId);
	}

	/**
	 * Add an environmentLink to a given environment of a project.
	 *
	 * @param environmentId
	 *            the environment
	 * @param environmentLinkDto
	 *            the environmentLink
	 * @return the environmentLink saved
	 */
	@RequestMapping(value = "{projectId}/environment/{environmentId}/link", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public EnvironmentLinkDto addEnvironmentLink(@PathVariable("environmentId") final Long environmentId,
			@Valid @RequestBody final EnvironmentLinkDto environmentLinkDto) {
		final EnvironmentLink environmentLink = projectBusiness.addEnvironmentLink(environmentId,
				dtoToModelMapper.map(environmentLinkDto));
		return modelToDtoMapper.map(environmentLink);
	}

	/**
	 * Find environmentLinks of the given environment.
	 *
	 * @param environmentId
	 *            the environmentId
	 * @return the environment links
	 */
	@RequestMapping(value = "{projectId}/environment/{environmentId}/link", method = RequestMethod.GET, produces = "application/json")
	public List<EnvironmentLinkDto> findEnvironmentLinksByEnvironment(
			@PathVariable("environmentId") final Long environmentId) {
		return projectBusiness.findAllEnvironmentLinks(environmentId).stream().map(modelToDtoMapper::map)
				.collect(Collectors.toList());
	}

	/**
	 * Delete environment link.
	 *
	 * @param environmentLinkId
	 *            the environmentLinkId
	 */
	@RequestMapping(value = "{projectId}/environment/{environmentId}/link/{environmentLinkId}", method = RequestMethod.DELETE)
	public void deleteEnvironmentLink(@PathVariable("environmentLinkId") final Long environmentLinkId) {
		projectBusiness.deleteEnvironmentLink(environmentLinkId);
	}

	/**
	 * Add a tool to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param toolDto
	 *            the tool
	 * @return the tool saved
	 */
	@RequestMapping(value = "{projectId}/tool", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ToolDto addTool(@PathVariable("projectId") final Long projectId, @Valid @RequestBody final ToolDto toolDto) {
		final Tool tool = projectBusiness.addTool(projectId, dtoToModelMapper.map(toolDto));
		return modelToDtoMapper.map(tool);
	}

	/**
	 * Find project tools.
	 *
	 * @param projectId
	 *            the project
	 * @return the tools
	 */
	@RequestMapping(value = "{projectId}/tool", method = RequestMethod.GET, produces = "application/json")
	public List<ToolDto> findToolsByProject(@PathVariable("projectId") final Long projectId) {
		return projectBusiness.findAllTools(projectId).stream().map(modelToDtoMapper::map).collect(Collectors.toList());
	}

	/**
	 * Delete tool.
	 *
	 * @param toolId
	 *            the link id
	 */
	@RequestMapping(value = "{projectId}/tool/{toolId}", method = RequestMethod.DELETE)
	public void deleteTool(@PathVariable("toolId") final Long toolId) {
		projectBusiness.deleteTool(toolId);
	}

	/**
	 * Add a link to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param linkDto
	 *            the link
	 * @return the link saved
	 */
	@RequestMapping(value = "{projectId}/link", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public LinkDto addLink(@PathVariable("projectId") final Long projectId, @Valid @RequestBody final LinkDto linkDto) {
		final Link link = projectBusiness.addLink(projectId, dtoToModelMapper.map(linkDto));
		return modelToDtoMapper.map(link);
	}

	/**
	 * Find project links.
	 *
	 * @param projectId
	 *            the project
	 * @return the links
	 */
	@RequestMapping(value = "{projectId}/link", method = RequestMethod.GET, produces = "application/json")
	public List<LinkDto> findLinksByProject(@PathVariable("projectId") final Long projectId) {
		return projectBusiness.findAllLinks(projectId).stream().map(modelToDtoMapper::map).collect(Collectors.toList());
	}

	/**
	 * Delete link.
	 *
	 * @param linkId
	 *            the link id
	 */
	@RequestMapping(value = "{projectId}/link/{linkId}", method = RequestMethod.DELETE)
	public void deleteLink(@PathVariable("linkId") final Long linkId) {
		projectBusiness.deleteLink(linkId);
	}

	/**
	 * Add an Apache configuration to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param apacheConfigurationDto
	 *            the apacheConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/apache", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ApacheConfigurationDto addApacheConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final ApacheConfigurationDto apacheConfigurationDto) {
		final ApacheConfiguration apacheConfiguration = projectBusiness.addOrUpdateApacheConfiguration(projectId,
				dtoToModelMapper.map(apacheConfigurationDto));
		return modelToDtoMapper.map(apacheConfiguration);
	}

	/**
	 * Update an Apache configuration to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param apacheConfigurationDto
	 *            the apacheConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/apache/{apacheConfigurationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ApacheConfigurationDto updateApacheConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final ApacheConfigurationDto apacheConfigurationDto) {
		final ApacheConfiguration apacheConfiguration = projectBusiness.addOrUpdateApacheConfiguration(projectId,
				dtoToModelMapper.map(apacheConfigurationDto));
		return modelToDtoMapper.map(apacheConfiguration);
	}

	/**
	 * Get the apache configuration of the project.
	 *
	 * @param projectId
	 *            the project
	 * @return the apache configuration
	 */
	@RequestMapping(value = "{projectId}/apache", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ApacheConfigurationDto> getApacheConfiguration(
			@PathVariable("projectId") final Long projectId) {
		final ApacheConfiguration apacheConfiguration = projectBusiness.getApacheConfiguration(projectId);
		if (apacheConfiguration != null) {
			return new ResponseEntity<>(modelToDtoMapper.map(apacheConfiguration), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Add a jenkins configuration to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param jenkinsConfigurationDto
	 *            the jenkinsConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/jenkins", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public JenkinsConfigurationDto addJenkinsConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final JenkinsConfigurationDto jenkinsConfigurationDto) {
		final JenkinsConfiguration jenkinsConfiguration = projectBusiness.addOrUpdateJenkinsConfiguration(projectId,
				dtoToModelMapper.map(jenkinsConfigurationDto));
		return modelToDtoMapper.map(jenkinsConfiguration);
	}

	/**
	 * Update a jenkins configuration.
	 *
	 * @param projectId
	 *            the project id
	 * @param jenkinsConfigurationDto
	 *            the jenkinsConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/jenkins/{jenkinsConfigurationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public JenkinsConfigurationDto updateJenkinsConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final JenkinsConfigurationDto jenkinsConfigurationDto) {
		final JenkinsConfiguration jenkinsConfiguration = projectBusiness.addOrUpdateJenkinsConfiguration(projectId,
				dtoToModelMapper.map(jenkinsConfigurationDto));
		return modelToDtoMapper.map(jenkinsConfiguration);
	}

	/**
	 * Get the jenkins configuration of the project.
	 *
	 * @param projectId
	 *            the project
	 * @return the jenkins configuration
	 */
	@RequestMapping(value = "{projectId}/jenkins", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<JenkinsConfigurationDto> getJenkinsConfiguration(
			@PathVariable("projectId") final Long projectId) {
		final JenkinsConfiguration jenkinsConfiguration = projectBusiness.getJenkinsConfiguration(projectId);
		if (jenkinsConfiguration != null) {
			return new ResponseEntity<>(modelToDtoMapper.map(jenkinsConfiguration), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Add a sonar configuration to a given project.
	 *
	 * @param projectId
	 *            the project
	 * @param sonarConfigurationDto
	 *            the sonarConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/sonar", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public SonarConfigurationDto addSonarConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final SonarConfigurationDto sonarConfigurationDto) {
		final SonarConfiguration sonarConfiguration = projectBusiness.addOrUpdateSonarConfiguration(projectId,
				dtoToModelMapper.map(sonarConfigurationDto));
		return modelToDtoMapper.map(sonarConfiguration);
	}

	/**
	 * Update a sonar configuration.
	 *
	 * @param projectId
	 *            the project id
	 * @param sonarConfigurationDto
	 *            the sonarConfiguration
	 * @return the configuration saved
	 */
	@RequestMapping(value = "{projectId}/sonar/{sonarConfigurationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public SonarConfigurationDto updateSonarConfiguration(@PathVariable("projectId") final Long projectId,
			@Valid @RequestBody final SonarConfigurationDto sonarConfigurationDto) {
		final SonarConfiguration sonarConfiguration = projectBusiness.addOrUpdateSonarConfiguration(projectId,
				dtoToModelMapper.map(sonarConfigurationDto));
		return modelToDtoMapper.map(sonarConfiguration);
	}

	/**
	 * Get the sonar configuration of the project.
	 *
	 * @param projectId
	 *            the project
	 * @return the sonar configuration
	 */
	@RequestMapping(value = "{projectId}/sonar", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<SonarConfigurationDto> getSonarConfiguration(
			@PathVariable("projectId") final Long projectId) {
		final SonarConfiguration sonarConfiguration = projectBusiness.getSonarConfiguration(projectId);
		if (sonarConfiguration != null) {
			return new ResponseEntity<>(modelToDtoMapper.map(sonarConfiguration), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Generate the project configuration.
	 *
	 * @param projectId
	 *            the project id
	 * @param response
	 *            the response of the request to set the content disposition in
	 *            order to set the name and force download.
	 * @return the configuration
	 */
	@RequestMapping(value = "{projectId}/conf", method = RequestMethod.GET, produces = "application/json")
	public ApplicationConfiguration generateProjectConfiguration(@PathVariable("projectId") final Long projectId,
			final HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment; filename=environment.json");
		return projectBusiness.generateProjectConfiguration(projectId);
	}

}
