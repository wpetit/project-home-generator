/**
 *
 */
package com.wpetit.projecthome.generator.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.business.impl.ProjectBusinessImpl;
import com.wpetit.projecthome.generator.business.model.ApplicationConfiguration;
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
 * The {@link ProjectBusinessTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectBusinessImplTest {

	// specify name because of a bug in spring boot test 1.4. If name is not
	// provided then mock is not injected for repository.
	@MockBean(name = "projectDao")
	private ProjectDao projectDao;

	@MockBean(name = "environmentDao")
	private EnvironmentDao environmentDao;

	@MockBean(name = "environmentLinkDao")
	private EnvironmentLinkDao environmentLinkDao;

	@MockBean(name = "linkDao")
	private LinkDao linkDao;

	@MockBean(name = "toolDao")
	private ToolDao toolDao;

	@MockBean(name = "jenkinsConfigurationDao")
	private JenkinsConfigurationDao jenkinsConfigurationDao;

	@MockBean(name = "sonarConfigurationDao")
	private SonarConfigurationDao sonarConfigurationDao;

	@MockBean(name = "apacheConfigurationDao")
	private ApacheConfigurationDao apacheConfigurationDao;

	@Autowired
	private ProjectBusinessImpl projectBusinessImpl;

	/**
	 * Test method for {@link ProjectBusinessImpl#addProject(Project)}.
	 */
	@Test
	public void testAddProject() {
		final Project project = new Project();
		project.setName("test");
		when(projectDao.saveAndFlush(project)).thenReturn(project);
		assertEquals(project, projectBusinessImpl.addProject(project));
		verify(projectDao).saveAndFlush(project);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addEnvironment(Long, com.wpetit.projecthome.generator.model.Environment)}.
	 */
	@Test
	public void testAddEnvironment() {
		final Project project = new Project();
		final Environment environment = new Environment();
		environment.setName("test");
		environment.setProject(project);

		when(projectDao.findOne(1L)).thenReturn(project);
		when(environmentDao.saveAndFlush(environment)).thenReturn(environment);

		assertEquals(environment, projectBusinessImpl.addEnvironment(1L, environment));
		verify(projectDao).findOne(1L);
		verify(environmentDao).saveAndFlush(environment);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addEnvironmentLink(Long, com.wpetit.projecthome.generator.model.EnvironmentLink)}.
	 */
	@Test
	public void testAddEnvironmentLink() {
		final Environment environment = new Environment();
		environment.setName("test");

		final EnvironmentLink environmentLink = new EnvironmentLink();
		environmentLink.setName("link");
		environmentLink.setEnvironment(environment);

		when(environmentDao.findOne(1L)).thenReturn(environment);
		when(environmentLinkDao.saveAndFlush(environmentLink)).thenReturn(environmentLink);

		assertEquals(environmentLink, projectBusinessImpl.addEnvironmentLink(1L, environmentLink));
		verify(environmentDao).findOne(1L);
		verify(environmentLinkDao).saveAndFlush(environmentLink);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addLink(Long, com.wpetit.projecthome.generator.model.Link)}.
	 */
	@Test
	public void testAddLink() {
		final Project project = new Project();
		final Link link = new Link();
		link.setName("name");
		link.setUrl("http://ci.wpetit.com/link");

		when(projectDao.findOne(1L)).thenReturn(project);
		when(linkDao.saveAndFlush(link)).thenReturn(link);

		assertEquals(link, projectBusinessImpl.addLink(1L, link));
		verify(projectDao).findOne(1L);
		verify(linkDao).saveAndFlush(link);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addTool(Long, com.wpetit.projecthome.generator.model.Tool)}.
	 */
	@Test
	public void testAddTool() {
		final Project project = new Project();
		final Tool tool = new Tool();
		tool.setName("name");
		tool.setUrl("http://ci.wpetit.com/link");

		when(projectDao.findOne(1L)).thenReturn(project);
		when(toolDao.saveAndFlush(tool)).thenReturn(tool);

		assertEquals(tool, projectBusinessImpl.addTool(1L, tool));
		verify(projectDao).findOne(1L);
		verify(toolDao).saveAndFlush(tool);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addOrUpdateJenkinsConfiguration(Long, com.wpetit.projecthome.generator.model.JenkinsConfiguration)}.
	 */
	@Test
	public void testAddJenkinsConfiguration() {
		final Project project = new Project();
		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration();
		jenkinsConfiguration.setUrl("http://ci.wpetit.com/jenkins");
		jenkinsConfiguration.setProject(project);
		jenkinsConfiguration.setJobsName(Arrays.asList("job1"));

		when(projectDao.findOne(1L)).thenReturn(project);
		when(jenkinsConfigurationDao.saveAndFlush(jenkinsConfiguration)).thenReturn(jenkinsConfiguration);

		assertEquals(jenkinsConfiguration,
				projectBusinessImpl.addOrUpdateJenkinsConfiguration(1L, jenkinsConfiguration));
		verify(projectDao).findOne(1L);
		verify(jenkinsConfigurationDao).saveAndFlush(jenkinsConfiguration);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#addOrUpdateSonarConfiguration(Long, com.wpetit.projecthome.generator.model.SonarConfiguration)}.
	 */
	@Test
	public void testAddSonarConfiguration() {
		final Project project = new Project();
		final SonarConfiguration sonarConfiguration = new SonarConfiguration();
		sonarConfiguration.setUrl("http://ci.wpetit.com/jenkins");
		sonarConfiguration.setProject(project);
		sonarConfiguration.setResourceNames(Arrays.asList("resource1"));

		when(projectDao.findOne(1L)).thenReturn(project);
		when(sonarConfigurationDao.saveAndFlush(sonarConfiguration)).thenReturn(sonarConfiguration);

		assertEquals(sonarConfiguration, projectBusinessImpl.addOrUpdateSonarConfiguration(1L, sonarConfiguration));
		verify(projectDao).findOne(1L);
		verify(sonarConfigurationDao).saveAndFlush(sonarConfiguration);
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#findAll()}.
	 */
	@Test
	public void testFindAll() {
		final Project project = new Project();
		final List<Project> projects = Arrays.asList(project);

		when(projectDao.findAll()).thenReturn(projects);

		assertEquals(projects, projectBusinessImpl.findAll());
		verify(projectDao).findAll();
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#findAllEnvironments(Long)}.
	 */
	@Test
	public void testFindAllEnvironments() {
		final Environment environment = new Environment();
		final List<Environment> environments = Arrays.asList(environment);

		when(environmentDao.findByProjectId(1L)).thenReturn(environments);

		assertEquals(environments, projectBusinessImpl.findAllEnvironments(1L));
		verify(environmentDao).findByProjectId(1L);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#findAllEnvironmentLinks(Long)}.
	 */
	@Test
	public void testFindAllEnvironmentLinks() {
		final EnvironmentLink environmentLink = new EnvironmentLink();
		final List<EnvironmentLink> environmentLinks = Arrays.asList(environmentLink);

		when(environmentLinkDao.findByEnvironmentId(1L)).thenReturn(environmentLinks);

		assertEquals(environmentLinks, projectBusinessImpl.findAllEnvironmentLinks(1L));
		verify(environmentLinkDao).findByEnvironmentId(1L);
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#findAllLinks(Long)}.
	 */
	@Test
	public void testFindAllLinks() {
		final Link link = new Link();
		final List<Link> links = Arrays.asList(link);

		when(linkDao.findByProjectId(1L)).thenReturn(links);

		assertEquals(links, projectBusinessImpl.findAllLinks(1L));
		verify(linkDao).findByProjectId(1L);
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#findAllTools(Long)}.
	 */
	@Test
	public void testFindAllTools() {
		final Tool tool = new Tool();
		final List<Tool> tools = Arrays.asList(tool);

		when(toolDao.findByProjectId(1L)).thenReturn(tools);

		assertEquals(tools, projectBusinessImpl.findAllTools(1L));
		verify(toolDao).findByProjectId(1L);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#getJenkinsConfiguration(Long)}.
	 */
	@Test
	public void testGetJenkinsConfiguration() {
		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration();

		when(jenkinsConfigurationDao.findByProjectId(1L)).thenReturn(jenkinsConfiguration);

		assertEquals(jenkinsConfiguration, projectBusinessImpl.getJenkinsConfiguration(1L));
		verify(jenkinsConfigurationDao).findByProjectId(1L);
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#getSonarConfiguration(Long)}.
	 */
	@Test
	public void testGetSonarConfiguration() {
		final SonarConfiguration sonarConfiguration = new SonarConfiguration();

		when(sonarConfigurationDao.findByProjectId(1L)).thenReturn(sonarConfiguration);

		assertEquals(sonarConfiguration, projectBusinessImpl.getSonarConfiguration(1L));
		verify(sonarConfigurationDao).findByProjectId(1L);
	}

	/**
	 * Test method for {@link ProjectBusinessImpl#getProject(Long)}.
	 */
	@Test
	public void testGetProject() {
		final Project project = new Project();

		when(projectDao.findOne(1L)).thenReturn(project);

		assertEquals(project, projectBusinessImpl.getProject(1L));
		verify(projectDao).findOne(1L);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#generateProjectConfiguration(Long)}.
	 */
	@Test
	public void testGenerateProjectConfiguration() {
		final Project project = new Project(1L, "project", "images/project.png");
		final EnvironmentLink environmentLink = new EnvironmentLink();
		environmentLink.setName("envLink1");
		environmentLink.setUrl("http://ci.wpetit.com/project-home-generator");
		final List<EnvironmentLink> environmentLinks = Arrays.asList(environmentLink);
		final Environment environment = new Environment(1L, "env", environmentLinks, project);
		final List<Environment> environments = Arrays.asList(environment);
		final Tool tool = new Tool(1L, "tool", "http://ci.wpetit.com/tool", project);
		final List<Tool> tools = Arrays.asList(tool);
		final Link link = new Link(1L, "link", "http://ci.wpetit.com/link", "images/link.png", project);
		final List<Link> links = Arrays.asList(link);
		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration(1L, "http://ci.wpetit.com/jenkins",
				Arrays.asList("job"), project);
		final SonarConfiguration sonarConfiguration = new SonarConfiguration(1L, "http://ci.wpetit.com/sonar",
				Arrays.asList("res"), project);
		final ApacheConfiguration apacheConfiguration = new ApacheConfiguration("http://ci.wpetit.com", project);

		when(apacheConfigurationDao.findByProjectId(1L)).thenReturn(apacheConfiguration);
		when(jenkinsConfigurationDao.findByProjectId(1L)).thenReturn(jenkinsConfiguration);
		when(sonarConfigurationDao.findByProjectId(1L)).thenReturn(sonarConfiguration);
		when(linkDao.findByProjectId(1L)).thenReturn(links);
		when(toolDao.findByProjectId(1L)).thenReturn(tools);
		when(environmentDao.findByProjectId(1L)).thenReturn(environments);
		when(environmentLinkDao.findByEnvironmentId(1L)).thenReturn(environmentLinks);
		when(projectDao.findOne(1L)).thenReturn(project);

		final ApplicationConfiguration applicationConfiguration = projectBusinessImpl.generateProjectConfiguration(1L);

		assertEquals("project", applicationConfiguration.getApplicationName());
		assertEquals("http://ci.wpetit.com/jenkins", applicationConfiguration.getJenkinsUrl());
		assertEquals(Arrays.asList("job"), applicationConfiguration.getJenkinsJobs());
		assertEquals("http://ci.wpetit.com/sonar", applicationConfiguration.getSonarUrl());
		assertEquals(Arrays.asList("res"), applicationConfiguration.getSonarResources());
		assertEquals(1, applicationConfiguration.getApplicationLinks().size());
		assertEquals("link", applicationConfiguration.getApplicationLinks().get(0).getLinkName());
		assertEquals("http://ci.wpetit.com/link", applicationConfiguration.getApplicationLinks().get(0).getLinkUrl());
		assertEquals("images/link.png", applicationConfiguration.getApplicationLinks().get(0).getLinkLogo());
		assertEquals(1, applicationConfiguration.getApplicationTools().size());
		assertEquals("tool", applicationConfiguration.getApplicationTools().get(0).getName());
		assertEquals("http://ci.wpetit.com/tool", applicationConfiguration.getApplicationTools().get(0).getUrl());
		assertEquals(1, applicationConfiguration.getEnv().size());
		assertEquals("env", applicationConfiguration.getEnv().get(0).getName());
		assertEquals(1, applicationConfiguration.getEnv().get(0).getUrls().size());
		assertEquals("envLink1", applicationConfiguration.getEnv().get(0).getUrls().get(0).getUrlName());
		assertEquals("http://ci.wpetit.com/project-home-generator",
				applicationConfiguration.getEnv().get(0).getUrls().get(0).getUrl());

		verify(projectDao).findOne(1L);
		verify(environmentDao).findByProjectId(1L);
		verify(environmentLinkDao).findByEnvironmentId(1L);
		verify(toolDao).findByProjectId(1L);
		verify(linkDao).findByProjectId(1L);
		verify(jenkinsConfigurationDao).findByProjectId(1L);
		verify(sonarConfigurationDao).findByProjectId(1L);
	}

	/**
	 * Test method for
	 * {@link ProjectBusinessImpl#generateProjectConfiguration(Long)}.
	 */
	@Test
	public void testGenerateProjectConfigurationWithoutJenkinsAndSonar() {
		final Project project = new Project(1L, "project", "images/project.png");
		final EnvironmentLink environmentLink = new EnvironmentLink();
		environmentLink.setName("envLink1");
		environmentLink.setUrl("http://ci.wpetit.com/project-home-generator");
		final List<EnvironmentLink> environmentLinks = Arrays.asList(environmentLink);
		final Environment environment = new Environment(1L, "env", environmentLinks, project);
		final List<Environment> environments = Arrays.asList(environment);
		final Tool tool = new Tool(1L, "tool", "http://ci.wpetit.com/tool", project);
		final List<Tool> tools = Arrays.asList(tool);
		final Link link = new Link(1L, "link", "http://ci.wpetit.com/link", "images/link.png", project);
		final List<Link> links = Arrays.asList(link);
		final JenkinsConfiguration jenkinsConfiguration = null;
		final SonarConfiguration sonarConfiguration = null;

		when(jenkinsConfigurationDao.findByProjectId(1L)).thenReturn(jenkinsConfiguration);
		when(sonarConfigurationDao.findByProjectId(1L)).thenReturn(sonarConfiguration);
		when(linkDao.findByProjectId(1L)).thenReturn(links);
		when(toolDao.findByProjectId(1L)).thenReturn(tools);
		when(environmentDao.findByProjectId(1L)).thenReturn(environments);
		when(environmentLinkDao.findByEnvironmentId(1L)).thenReturn(environmentLinks);
		when(projectDao.findOne(1L)).thenReturn(project);

		final ApplicationConfiguration applicationConfiguration = projectBusinessImpl.generateProjectConfiguration(1L);

		assertEquals("project", applicationConfiguration.getApplicationName());
		assertNull(applicationConfiguration.getJenkinsUrl());
		assertNull(applicationConfiguration.getJenkinsJobs());
		assertNull(applicationConfiguration.getSonarUrl());
		assertNull(applicationConfiguration.getSonarResources());
		assertEquals(1, applicationConfiguration.getApplicationLinks().size());
		assertEquals("link", applicationConfiguration.getApplicationLinks().get(0).getLinkName());
		assertEquals("http://ci.wpetit.com/link", applicationConfiguration.getApplicationLinks().get(0).getLinkUrl());
		assertEquals("images/link.png", applicationConfiguration.getApplicationLinks().get(0).getLinkLogo());
		assertEquals(1, applicationConfiguration.getApplicationTools().size());
		assertEquals("tool", applicationConfiguration.getApplicationTools().get(0).getName());
		assertEquals("http://ci.wpetit.com/tool", applicationConfiguration.getApplicationTools().get(0).getUrl());
		assertEquals(1, applicationConfiguration.getEnv().size());
		assertEquals("env", applicationConfiguration.getEnv().get(0).getName());
		assertEquals(1, applicationConfiguration.getEnv().get(0).getUrls().size());
		assertEquals("envLink1", applicationConfiguration.getEnv().get(0).getUrls().get(0).getUrlName());
		assertEquals("http://ci.wpetit.com/project-home-generator",
				applicationConfiguration.getEnv().get(0).getUrls().get(0).getUrl());

		verify(projectDao).findOne(1L);
		verify(environmentDao).findByProjectId(1L);
		verify(environmentLinkDao).findByEnvironmentId(1L);
		verify(toolDao).findByProjectId(1L);
		verify(linkDao).findByProjectId(1L);
		verify(jenkinsConfigurationDao).findByProjectId(1L);
		verify(sonarConfigurationDao).findByProjectId(1L);
	}
}
