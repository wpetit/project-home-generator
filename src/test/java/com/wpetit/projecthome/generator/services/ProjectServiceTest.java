/**
 *
 */
package com.wpetit.projecthome.generator.services;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wpetit.projecthome.generator.business.ProjectBusiness;
import com.wpetit.projecthome.generator.business.model.ApplicationConfiguration;
import com.wpetit.projecthome.generator.business.model.ApplicationLink;
import com.wpetit.projecthome.generator.business.model.ApplicationTool;
import com.wpetit.projecthome.generator.business.model.Env;
import com.wpetit.projecthome.generator.business.model.EnvUrl;
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
 * The {@link ProjectServiceTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProjectService.class)
public class ProjectServiceTest {
	/** The mvc. **/
	@Autowired
	private MockMvc mvc;

	/** The projectBusiness. **/
	@MockBean
	private ProjectBusiness projectBusiness;

	/** The modelToDtoMapper. **/
	@MockBean
	private ModelToDtoMapper modelToDtoMapper;

	/** The dtoToModelMapper. **/
	@MockBean
	private DtoToModelMapper dtoToModelMapper;

	/**
	 * Test method for {@link ProjectService#findAllProjects()}.
	 */
	@Test
	public void testFindAllProjects() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		given(projectBusiness.findAll()).willReturn(Arrays.asList(project1));

		final ProjectDto projectDto1 = new ProjectDto(1L, "name1", "image1");
		given(modelToDtoMapper.map(project1)).willReturn(projectDto1);

		mvc.perform(get("/project").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[{\"name\":\"name1\", \"image\":\"image1\", \"id\":1}]"));
	}

	/**
	 * Test method for {@link ProjectService#findEnvironmentsByProject(Long)}.
	 */
	@Test
	public void testFindAllEnvironments() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final Environment environment1 = new Environment(1L, "name1", Arrays.asList(), project1);
		final EnvironmentLink environmentLink = new EnvironmentLink(1L, "link1", "http://example.org", environment1);
		environment1.setEnvironmentLinks(Arrays.asList(environmentLink));

		final EnvironmentDto environmentDto = new EnvironmentDto(1L, "name1", 1L);

		given(projectBusiness.findAllEnvironments(1L)).willReturn(Arrays.asList(environment1));

		given(modelToDtoMapper.map(environment1)).willReturn(environmentDto);

		mvc.perform(get("/project/{id}/environment", 1)).andExpect(status().isOk())
				.andExpect(content().json("[{\"name\":\"name1\", \"id\":1, \"projectId\":1}]"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#findEnvironmentLinksByEnvironment(Long)}.
	 */
	@Test
	public void testFindAllEnvironmentLinksByEnvironment() throws Exception {
		final Environment environment1 = new Environment(1L, "name1", Arrays.asList(), null);
		final EnvironmentLink environmentLink = new EnvironmentLink(1L, "link1", "http://example.org", environment1);

		final EnvironmentLinkDto environmentLinkDto = new EnvironmentLinkDto(1L, "link1", "http://example.org", 1L);

		given(projectBusiness.findAllEnvironmentLinks(1L)).willReturn(Arrays.asList(environmentLink));

		given(modelToDtoMapper.map(environmentLink)).willReturn(environmentLinkDto);

		mvc.perform(get("/project/{id}/environment/{id}/link", 1, 1)).andExpect(status().isOk()).andExpect(content()
				.json("[{\"name\":\"link1\", \"id\":1, \"url\":\"http://example.org\", \"environmentId\":1}]"));
	}

	/**
	 * Test method for {@link ProjectService#findLinksByProject(Long)}.
	 */
	@Test
	public void testFindLinks() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final Link link = new Link(1L, "name1", "http://example.org/link", "image1", project1);

		given(projectBusiness.findAllLinks(1L)).willReturn(Arrays.asList(link));

		final LinkDto linkDto = new LinkDto(1L, "name1", "image1", "http://example.org/link", 1L);
		given(modelToDtoMapper.map(link)).willReturn(linkDto);

		mvc.perform(get("/project/{id}/link", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(
						"[{\"name\":\"name1\", \"url\":\"http://example.org/link\", \"image\":\"image1\", \"id\":1, \"projectId\":1}]"));
	}

	/**
	 * Test method for {@link ProjectService#findToolsByProject(Long)}.
	 */
	@Test
	public void testFindTools() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final Tool tool = new Tool(1L, "name1", "http://example.org/tool", project1);

		given(projectBusiness.findAllTools(1L)).willReturn(Arrays.asList(tool));

		final ToolDto toolDto = new ToolDto(1L, "name1", "http://example.org/tool", 1L);
		given(modelToDtoMapper.map(tool)).willReturn(toolDto);

		mvc.perform(get("/project/{id}/tool", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(
						"[{\"name\":\"name1\", \"url\":\"http://example.org/tool\", \"id\":1, \"projectId\":1}]"));
	}

	/**
	 * Test method for {@link ProjectService#getProject(Long)}.
	 */
	@Test
	public void testGetProject() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		given(projectBusiness.getProject(1L)).willReturn(project1);

		final ProjectDto projectDto1 = new ProjectDto(1L, "name1", "image1");
		given(modelToDtoMapper.map(project1)).willReturn(projectDto1);

		mvc.perform(get("/project/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("{\"name\":\"name1\", \"image\":\"image1\", \"id\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#getProject(Long) with unknown
	 * project}.
	 */
	@Test
	public void testGetProjectUnknown() throws Exception {
		given(projectBusiness.getProject(1L)).willReturn(null);
		mvc.perform(get("/project/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	/**
	 * Test method for {@link ProjectService#addProject(ProjectDto)}.
	 */
	@Test
	public void testAddProject() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final ProjectDto projectDto1 = new ProjectDto(1L, "name1", "image1");

		given(dtoToModelMapper.map((ProjectDto) notNull())).willReturn(project1);
		given(projectBusiness.addOrUpdateProject((Project) notNull())).willReturn(project1);
		given(modelToDtoMapper.map(project1)).willReturn(projectDto1);

		mvc.perform(put("/project").content("{\"name\":\"name1\", \"image\":\"image1\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\"name\":\"name1\", \"image\":\"image1\", \"id\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#addJenkinsConfiguration(Long, JenkinsConfigurationDto)}.
	 */
	@Test
	public void testAddJenkinsConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration(1L, "http://example.org/jenkins",
				Arrays.asList("job1"), project1);

		final JenkinsConfigurationDto jenkinsConfigurationDto = new JenkinsConfigurationDto(1L,
				"http://example.org/jenkins", Arrays.asList("job1"), 1L);

		given(dtoToModelMapper.map((JenkinsConfigurationDto) notNull())).willReturn(jenkinsConfiguration);
		given(projectBusiness.addOrUpdateJenkinsConfiguration(eq(1L), (JenkinsConfiguration) notNull()))
				.willReturn(jenkinsConfiguration);
		given(modelToDtoMapper.map(jenkinsConfiguration)).willReturn(jenkinsConfigurationDto);

		mvc.perform(put("/project/{id}/jenkins", 1)
				.content("{\"jobsName\":[\"job1\"], \"url\":\"http://example.org/jenkins\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(
						content().json("{\"jobsName\":[\"job1\"], \"url\":\"http://example.org/jenkins\", \"id\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#addSonarConfiguration(Long, SonarConfigurationDto)}.
	 */
	@Test
	public void testAddSonarConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");

		final SonarConfiguration sonarConfiguration = new SonarConfiguration(1L, "http://example.org/sonar",
				Arrays.asList("job1"), project1);

		final SonarConfigurationDto sonarConfigurationDto = new SonarConfigurationDto(1L, "http://example.org/sonar",
				Arrays.asList("resource1"), 1L);

		given(dtoToModelMapper.map((SonarConfigurationDto) notNull())).willReturn(sonarConfiguration);
		given(projectBusiness.addOrUpdateSonarConfiguration(eq(1L), (SonarConfiguration) notNull()))
				.willReturn(sonarConfiguration);
		given(modelToDtoMapper.map(sonarConfiguration)).willReturn(sonarConfigurationDto);

		mvc.perform(put("/project/{id}/sonar", 1)
				.content("{\"resourceNames\":[\"resource1\"], \"url\":\"http://example.org/sonar\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content()
						.json("{\"resourceNames\":[\"resource1\"], \"url\":\"http://example.org/sonar\", \"id\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#addApacheConfiguration(Long, SonarConfigurationDto)}.
	 */
	@Test
	public void testAddApacheConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");

		final ApacheConfiguration apacheConfiguration = new ApacheConfiguration("http://example.org", project1);
		apacheConfiguration.setId(1L);

		final ApacheConfigurationDto apacheConfigurationDto = new ApacheConfigurationDto(1L, "http://example.org", 1L);

		given(dtoToModelMapper.map((ApacheConfigurationDto) notNull())).willReturn(apacheConfiguration);
		given(projectBusiness.addOrUpdateApacheConfiguration(eq(1L), (ApacheConfiguration) notNull()))
				.willReturn(apacheConfiguration);
		given(modelToDtoMapper.map(apacheConfiguration)).willReturn(apacheConfigurationDto);

		mvc.perform(put("/project/{id}/apache", 1).content("{\"url\":\"http://example.org\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\"url\":\"http://example.org\", \"id\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#addLink(Long, LinkDto)}.
	 */
	@Test
	public void testAddLink() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");

		final Link link = new Link(1L, "name1", "http://example.org/link", "image1", project1);
		final LinkDto linkDto = new LinkDto(1L, "name1", "image1", "http://example.org/link", 1L);

		given(dtoToModelMapper.map((LinkDto) notNull())).willReturn(link);
		given(projectBusiness.addLink(eq(1L), (Link) notNull())).willReturn(link);
		given(modelToDtoMapper.map(link)).willReturn(linkDto);

		mvc.perform(put("/project/{id}/link", 1)
				.content(
						"{\"name\":\"name1\", \"url\":\"http://example.org/link\", \"image\":\"image1\", \"projectId\":1}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().json(
						"{\"name\":\"name1\", \"url\":\"http://example.org/link\", \"image\":\"image1\", \"id\":1, \"projectId\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#addLink(Long, LinkDto)} with
	 * invalid name.
	 */
	@Test
	public void testAddLinkNameTooLong() throws Exception {
		mvc.perform(put("/project/{id}/link", 1)
				.content(
						"{\"name\":\"string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars\", \"url\":\"http://example.org/link\", \"image\":\"image1\", \"projectId\":1}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	/**
	 * Test method for {@link ProjectService#addTool(Long, ToolDto)}.
	 */
	@Test
	public void testAddTool() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final Tool tool = new Tool(1L, "name1", "http://example.org/tool", project1);
		final ToolDto toolDto = new ToolDto(1L, "name1", "http://example.org/tool", 1L);

		given(dtoToModelMapper.map((ToolDto) notNull())).willReturn(tool);
		given(projectBusiness.addTool(eq(1L), (Tool) notNull())).willReturn(tool);
		given(modelToDtoMapper.map(tool)).willReturn(toolDto);

		mvc.perform(put("/project/{id}/tool", 1)
				.content("{\"name\":\"name1\", \"url\":\"http://example.org/tool\", \"projectId\":1}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content()
						.json("{\"name\":\"name1\", \"url\":\"http://example.org/tool\", \"id\":1, \"projectId\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#addEnvironment(Long, EnvironmentDto)}.
	 */
	@Test
	public void testAddEnvironment() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final Environment environment1 = new Environment(1L, "name1", Arrays.asList(), project1);
		final EnvironmentLink environmentLink = new EnvironmentLink(1L, "link1", "http://example.org", environment1);
		environment1.setEnvironmentLinks(Arrays.asList(environmentLink));

		final EnvironmentDto environmentDto = new EnvironmentDto(1L, "name1", 1L);

		given(dtoToModelMapper.map((EnvironmentDto) notNull())).willReturn(environment1);
		given(projectBusiness.addEnvironment(eq(1L), (Environment) notNull())).willReturn(environment1);
		given(modelToDtoMapper.map(environment1)).willReturn(environmentDto);

		mvc.perform(put("/project/{id}/environment", 1).content("{\"name\":\"name1\", \"projectId\":1}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json("{\"name\":\"name1\", \"id\":1, \"projectId\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#addEnvironmentLink(Long, EnvironmentLinkDto)}.
	 */
	@Test
	public void testAddEnvironmentLink() throws Exception {
		final Environment environment1 = new Environment(1L, "name1", Arrays.asList(), null);
		final EnvironmentLink environmentLink = new EnvironmentLink(1L, "link1", "http://example.org", environment1);

		final EnvironmentLinkDto environmentLinkDto = new EnvironmentLinkDto(1L, "link1", "http://example.org", 1L);

		given(dtoToModelMapper.map((EnvironmentLinkDto) notNull())).willReturn(environmentLink);
		given(projectBusiness.addEnvironmentLink(eq(1L), (EnvironmentLink) notNull())).willReturn(environmentLink);
		given(modelToDtoMapper.map(environmentLink)).willReturn(environmentLinkDto);

		mvc.perform(put("/project/{id}/environment/1/link", 1)
				.content("{\"name\":\"link1\",\"url\":\"http://example.org\", \"environmentId\":1}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content()
						.json("{\"name\":\"link1\", \"id\":1, \"url\":\"http://example.org\", \"environmentId\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#getJenkinsConfiguration(Long)}.
	 */
	@Test
	public void testGetJenkinsConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration(1L, "http://example.org/jenkins",
				Arrays.asList("job1"), project1);

		given(projectBusiness.getJenkinsConfiguration(1L)).willReturn(jenkinsConfiguration);

		final JenkinsConfigurationDto jenkinsConfigurationDto = new JenkinsConfigurationDto(1L,
				"http://example.org/jenkins", Arrays.asList("job1"), 1L);
		given(modelToDtoMapper.map(jenkinsConfiguration)).willReturn(jenkinsConfigurationDto);

		mvc.perform(get("/project/{id}/jenkins", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(
						content().json("{\"jobsName\":[\"job1\"], \"url\":\"http://example.org/jenkins\", \"id\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#getSonarConfiguration(Long)}.
	 */
	@Test
	public void testGetSonarConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final SonarConfiguration sonarConfiguration = new SonarConfiguration(1L, "http://example.org/sonar",
				Arrays.asList("job1"), project1);

		given(projectBusiness.getSonarConfiguration(1L)).willReturn(sonarConfiguration);

		final SonarConfigurationDto sonarConfigurationDto = new SonarConfigurationDto(1L, "http://example.org/sonar",
				Arrays.asList("resource1"), 1L);
		given(modelToDtoMapper.map(sonarConfiguration)).willReturn(sonarConfigurationDto);

		mvc.perform(get("/project/{id}/sonar", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content()
						.json("{\"resourceNames\":[\"resource1\"], \"url\":\"http://example.org/sonar\", \"id\":1}"));
	}

	/**
	 * Test method for {@link ProjectService#getSonarConfiguration(Long)}.
	 */
	@Test
	public void testGetApacheConfiguration() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final ApacheConfiguration apacheConfiguration = new ApacheConfiguration("http://example.org", project1);
		apacheConfiguration.setId(1L);

		given(projectBusiness.getApacheConfiguration(1L)).willReturn(apacheConfiguration);

		final ApacheConfigurationDto apacheConfigurationDto = new ApacheConfigurationDto(1L, "http://example.org", 1L);
		given(modelToDtoMapper.map(apacheConfiguration)).willReturn(apacheConfigurationDto);

		mvc.perform(get("/project/{id}/apache", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("{\"url\":\"http://example.org\", \"id\":1}"));
	}

	/**
	 * Test method for
	 * {@link ProjectService#generateProjectConfiguration(Long)}.
	 */
	@Test
	public void testGenerateProjectConfiguration() throws Exception {
		final EnvUrl envUrl = new EnvUrl("Project-home", "http://ci.wpetit.com");
		final Env env = new Env("env1", Arrays.asList(envUrl));
		final ApplicationTool applicationTool = new ApplicationTool("http://ci.wpetit.com/nexus", "Nexus");
		final ApplicationLink applicationLink = new ApplicationLink("images/nexus.png", "Nexus",
				"http://ci.wpetit.com/nexus");
		final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration("project1", null,
				"http://ci.wpetit.com/jenkins", "REPLACE_WITH BASE64(USER_JENKINS:PWD_JENKINS)",
				"REPLACE_WITH BASE64(USER_SONAR:PWD_SONAR)", "http://ci.wpetit.com/sonar");
		applicationConfiguration.setApplicationLinks(Arrays.asList(applicationLink));
		applicationConfiguration.setApplicationTools(Arrays.asList(applicationTool));
		applicationConfiguration.setEnv(Arrays.asList(env));
		applicationConfiguration.setJenkinsJobs(Arrays.asList("project-home-generator"));
		applicationConfiguration.setSonarResources(Arrays.asList("com.wpetit:project-home-generator"));

		given(projectBusiness.generateProjectConfiguration(1L)).willReturn(applicationConfiguration);

		mvc.perform(get("/project/{id}/conf", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content()
						.json("{\"applicationName\":\"project1\",\"applicationLogo\":null,\"applicationLinks\":[{\"linkLogo\":\"images/nexus.png\",\"linkName\":\"Nexus\",\"linkUrl\":\"http://ci.wpetit.com/nexus\"}],\"applicationTools\":[{\"url\":\"http://ci.wpetit.com/nexus\",\"name\":\"Nexus\"}],"
								+ "\"env\":[{\"name\":\"env1\",\"urls\":[{\"urlName\":\"Project-home\",\"url\":\"http://ci.wpetit.com\"}]}],"
								+ "\"jenkinsUrl\":\"http://ci.wpetit.com/jenkins\",\"jenkinsBase64UsrPwd\":\"REPLACE_WITH BASE64(USER_JENKINS:PWD_JENKINS)\","
								+ "\"sonarBase64UsrPwd\":\"REPLACE_WITH BASE64(USER_SONAR:PWD_SONAR)\",\"sonarUrl\":\"http://ci.wpetit.com/sonar\","
								+ "\"jenkinsJobs\":[\"project-home-generator\"],\"sonarResources\":[\"com.wpetit:project-home-generator\"]}"));
	}

	/**
	 * Test method for {@link ProjectService#updateProject(ProjectDto)}.
	 */
	@Test
	public void testUpdateProject() throws Exception {
		final Project project1 = new Project(1L, "name1", "image1");
		final ProjectDto projectDto1 = new ProjectDto(1L, "name2", "image1");

		given(dtoToModelMapper.map((ProjectDto) notNull())).willReturn(project1);
		given(projectBusiness.addOrUpdateProject((Project) notNull())).willReturn(project1);
		given(modelToDtoMapper.map(project1)).willReturn(projectDto1);

		mvc.perform(post("/project/1").content("{\"id\":1,\"name\":\"name2\", \"image\":\"image1\"}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("{\"id\":1,\"name\":\"name2\", \"image\":\"image1\"}"));
	}

	/**
	 * Test method for {@link ProjectService#deleteProject(Long)}.
	 */
	@Test
	public void testDeleteProject() throws Exception {
		BDDMockito.willDoNothing().given(projectBusiness).deleteProject(1L);
		mvc.perform(delete("/project/1")).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link ProjectService#deleteEnvironment(Long)}.
	 */
	@Test
	public void testDeleteEnvironment() throws Exception {
		BDDMockito.willDoNothing().given(projectBusiness).deleteEnvironment(1L);
		mvc.perform(delete("/project/1/environment/1")).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link ProjectService#deleteEnvironmentLink(Long)}.
	 */
	@Test
	public void testDeleteEnvironmentLink() throws Exception {
		BDDMockito.willDoNothing().given(projectBusiness).deleteEnvironmentLink(1L);
		mvc.perform(delete("/project/1/environment/1/link/1")).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link ProjectService#deleteTool(Long)}.
	 */
	@Test
	public void testDeleteTool() throws Exception {
		BDDMockito.willDoNothing().given(projectBusiness).deleteTool(1L);
		mvc.perform(delete("/project/1/tool/1")).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link ProjectService#deleteLink(Long)}.
	 */
	@Test
	public void testDeleteLink() throws Exception {
		BDDMockito.willDoNothing().given(projectBusiness).deleteLink(1L);
		mvc.perform(delete("/project/1/link/1")).andExpect(status().isOk());
	}

}
