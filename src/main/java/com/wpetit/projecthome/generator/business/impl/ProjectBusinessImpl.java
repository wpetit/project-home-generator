/**
 *
 */
package com.wpetit.projecthome.generator.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wpetit.projecthome.generator.business.ProjectBusiness;
import com.wpetit.projecthome.generator.business.model.ApplicationConfiguration;
import com.wpetit.projecthome.generator.business.model.ApplicationLink;
import com.wpetit.projecthome.generator.business.model.ApplicationTool;
import com.wpetit.projecthome.generator.business.model.Env;
import com.wpetit.projecthome.generator.business.model.EnvUrl;
import com.wpetit.projecthome.generator.dao.ApacheConfigurationDao;
import com.wpetit.projecthome.generator.dao.EnvironmentDao;
import com.wpetit.projecthome.generator.dao.EnvironmentLinkDao;
import com.wpetit.projecthome.generator.dao.JenkinsConfigurationDao;
import com.wpetit.projecthome.generator.dao.LinkDao;
import com.wpetit.projecthome.generator.dao.ProjectDao;
import com.wpetit.projecthome.generator.dao.SonarConfigurationDao;
import com.wpetit.projecthome.generator.dao.ToolDao;
import com.wpetit.projecthome.generator.model.ApacheConfiguration;
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;
import com.wpetit.projecthome.generator.model.JenkinsConfiguration;
import com.wpetit.projecthome.generator.model.Link;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.SonarConfiguration;
import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link ProjectBusinessImpl} class.
 *
 * @author wpetit
 *
 */
@Transactional
@Component
public class ProjectBusinessImpl implements ProjectBusiness {

	/** The projectDao. **/
	@Autowired
	private ProjectDao projectDao;

	/** The environmentDao. **/
	@Autowired
	private EnvironmentDao environmentDao;

	/** The environmentLinkDao. **/
	@Autowired
	private EnvironmentLinkDao environmentLinkDao;

	/** The toolDao. **/
	@Autowired
	private ToolDao toolDao;

	/** The linkDao. **/
	@Autowired
	private LinkDao linkDao;

	/** The jenkinsConfigurationDao. **/
	@Autowired
	private JenkinsConfigurationDao jenkinsConfigurationDao;

	/** The sonarConfigurationDao. **/
	@Autowired
	private SonarConfigurationDao sonarConfigurationDao;

	/** The apacheConfigurationDao. **/
	@Autowired
	private ApacheConfigurationDao apacheConfigurationDao;

	/** {@inheritDoc} **/
	@Override
	public List<Project> findAll() {
		return projectDao.findAll();
	}

	/** {@inheritDoc} **/
	@Override
	public Project addOrUpdateProject(final Project project) {
		return projectDao.saveAndFlush(project);
	}

	/** {@inheritDoc} **/
	@Override
	public Environment addEnvironment(final Long projectId, final Environment environment) {
		final Project project = projectDao.findOne(projectId);
		environment.setProject(project);
		return environmentDao.saveAndFlush(environment);
	}

	/** {@inheritDoc} **/
	@Override
	public List<Environment> findAllEnvironments(final Long projectId) {
		return environmentDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public Project getProject(final Long projectId) {
		return projectDao.findOne(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public EnvironmentLink addEnvironmentLink(final Long environmentId, final EnvironmentLink environmentLink) {
		final Environment environment = environmentDao.findOne(environmentId);
		environmentLink.setEnvironment(environment);
		return environmentLinkDao.saveAndFlush(environmentLink);
	}

	/** {@inheritDoc} **/
	@Override
	public List<EnvironmentLink> findAllEnvironmentLinks(final Long environmentId) {
		return environmentLinkDao.findByEnvironmentId(environmentId);
	}

	/** {@inheritDoc} **/
	@Override
	public Tool addTool(final Long projectId, final Tool tool) {
		final Project project = projectDao.findOne(projectId);
		tool.setProject(project);
		return toolDao.saveAndFlush(tool);
	}

	/** {@inheritDoc} **/
	@Override
	public List<Tool> findAllTools(final Long projectId) {
		return toolDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public JenkinsConfiguration addOrUpdateJenkinsConfiguration(final Long projectId,
			final JenkinsConfiguration jenkinsConfiguration) {
		final Project project = projectDao.findOne(projectId);
		jenkinsConfiguration.setProject(project);
		return jenkinsConfigurationDao.saveAndFlush(jenkinsConfiguration);
	}

	/** {@inheritDoc} **/
	@Override
	public JenkinsConfiguration getJenkinsConfiguration(final Long projectId) {
		return jenkinsConfigurationDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public ApplicationConfiguration generateProjectConfiguration(final Long projectId) {
		final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
		final Project project = projectDao.findOne(projectId);
		if (project != null) {
			applicationConfiguration.setApplicationName(project.getName());
			applicationConfiguration.setApplicationLogo(project.getImage());

			final List<ApplicationLink> applicationLinks = new ArrayList<>();
			linkDao.findByProjectId(projectId).stream().forEach(link -> {
				final ApplicationLink applicationLink = new ApplicationLink();
				applicationLink.setLinkLogo(link.getImage());
				applicationLink.setLinkName(link.getName());
				applicationLink.setLinkUrl(link.getUrl());
				applicationLinks.add(applicationLink);
			});
			applicationConfiguration.setApplicationLinks(applicationLinks);

			final List<ApplicationTool> applicationTools = new ArrayList<>();
			toolDao.findByProjectId(projectId).stream().forEach(tool -> {
				final ApplicationTool applicationTool = new ApplicationTool();
				applicationTool.setName(tool.getName());
				applicationTool.setUrl(tool.getUrl());
				applicationTools.add(applicationTool);
			});
			applicationConfiguration.setApplicationTools(applicationTools);

			final List<Env> envList = new ArrayList<>();
			environmentDao.findByProjectId(projectId).stream().forEach(environment -> {
				final Env env = new Env();
				env.setName(environment.getName());
				final List<EnvUrl> envUrlList = new ArrayList<>();
				environmentLinkDao.findByEnvironmentId(environment.getId()).stream().forEach(envLink -> {
					final EnvUrl envUrl = new EnvUrl();
					envUrl.setUrl(envLink.getUrl());
					envUrl.setUrlName(envLink.getName());
					envUrlList.add(envUrl);
				});
				env.setUrls(envUrlList);
				envList.add(env);
			});
			applicationConfiguration.setEnv(envList);

			final ApacheConfiguration apacheConfiguration = apacheConfigurationDao.findByProjectId(projectId);

			final JenkinsConfiguration jenkinsConfiguration = jenkinsConfigurationDao.findByProjectId(projectId);
			if (jenkinsConfiguration != null) {
				if (apacheConfiguration != null) {
					applicationConfiguration
							.setJenkinsUrl(apacheConfiguration.getUrl().replaceAll("/$", "") + "/jenkins");
				}
				applicationConfiguration.setJenkinsJobs(jenkinsConfiguration.getJobsName());
				applicationConfiguration.setJenkinsBase64UsrPwd("REPLACE_WITH BASE64(USER_JENKINS:PWD_JENKINS)");
			}

			final SonarConfiguration sonarConfiguration = sonarConfigurationDao.findByProjectId(projectId);
			if (sonarConfiguration != null) {
				if (apacheConfiguration != null) {
					applicationConfiguration.setSonarUrl(apacheConfiguration.getUrl().replaceAll("/$", "") + "/sonar");
				}
				applicationConfiguration.setSonarResources(sonarConfiguration.getResourceNames());
				applicationConfiguration.setSonarBase64UsrPwd("REPLACE_WITH BASE64(USER_SONAR:PWD_SONAR)");
			}
		}

		return applicationConfiguration;
	}

	/** {@inheritDoc} **/
	@Override
	public Link addLink(final Long projectId, final Link link) {
		final Project project = projectDao.findOne(projectId);
		link.setProject(project);
		return linkDao.saveAndFlush(link);
	}

	/** {@inheritDoc} **/
	@Override
	public List<Link> findAllLinks(final Long projectId) {
		return linkDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public SonarConfiguration getSonarConfiguration(final Long projectId) {
		return sonarConfigurationDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public SonarConfiguration addOrUpdateSonarConfiguration(final Long projectId,
			final SonarConfiguration sonarConfiguration) {
		final Project project = projectDao.findOne(projectId);
		sonarConfiguration.setProject(project);
		return sonarConfigurationDao.saveAndFlush(sonarConfiguration);
	}

	/** {@inheritDoc} **/
	@Override
	public void deleteProject(final Long projectId) {
		linkDao.deleteByProjectId(projectId);
		toolDao.deleteByProjectId(projectId);
		environmentLinkDao.deleteByEnvironmentProjectId(projectId);
		environmentDao.deleteByProjectId(projectId);
		jenkinsConfigurationDao.deleteByProjectId(projectId);
		sonarConfigurationDao.deleteByProjectId(projectId);
		projectDao.delete(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public ApacheConfiguration addOrUpdateApacheConfiguration(final Long projectId,
			final ApacheConfiguration apacheConfiguration) {
		final Project project = projectDao.findOne(projectId);
		apacheConfiguration.setProject(project);
		return apacheConfigurationDao.saveAndFlush(apacheConfiguration);
	}

	/** {@inheritDoc} **/
	@Override
	public ApacheConfiguration getApacheConfiguration(final Long projectId) {
		return apacheConfigurationDao.findByProjectId(projectId);
	}

	/** {@inheritDoc} **/
	@Override
	public void deleteLink(final Long linkId) {
		linkDao.delete(linkId);
	}

	/** {@inheritDoc} **/
	@Override
	public void deleteTool(final Long toolId) {
		toolDao.delete(toolId);
	}

	/** {@inheritDoc} **/
	@Override
	public void deleteEnvironment(final Long environmentId) {
		environmentLinkDao.deleteByEnvironmentId(environmentId);
		environmentDao.delete(environmentId);
	}

	/** {@inheritDoc} **/
	@Override
	public void deleteEnvironmentLink(final Long environmentLinkId) {
		environmentLinkDao.delete(environmentLinkId);
	}

}
