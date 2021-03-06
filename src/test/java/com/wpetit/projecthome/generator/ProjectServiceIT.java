/**
 *
 */
package com.wpetit.projecthome.generator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;
import com.wpetit.projecthome.generator.dto.ApacheConfigurationDto;
import com.wpetit.projecthome.generator.dto.EnvironmentDto;
import com.wpetit.projecthome.generator.dto.EnvironmentLinkDto;
import com.wpetit.projecthome.generator.dto.JenkinsConfigurationDto;
import com.wpetit.projecthome.generator.dto.LinkDto;
import com.wpetit.projecthome.generator.dto.ProjectDto;
import com.wpetit.projecthome.generator.dto.SonarConfigurationDto;
import com.wpetit.projecthome.generator.dto.ToolDto;
import com.wpetit.projecthome.generator.services.ProjectService;

/**
 * The {@link ProjectServiceIT} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProjectServiceIT {

	/** The restTemplate. **/
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Test method for {@link ProjectService#addProject(ProjectDto)}.
	 */
	@Test
	public void testAddProject() {
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> entity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project", HttpMethod.PUT,
				entity, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{name:\"my-project\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getProject(Long)}.
	 */
	@Test
	public void testGetProject() {
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> entity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, entity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}",
				String.class, projectId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		final String expected = "{name:\"my-project\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#updateProject(ProjectDto)}.
	 */
	@Test
	public void testUpdateProject() {
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> entity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, entity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		projectDto.setName("my-project-renamed");
		final HttpEntity<ProjectDto> postEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}",
				HttpMethod.POST, postEntity, String.class, projectId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		final String expected = "{name:\"my-project-renamed\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getProject(Long)} with unknown
	 * project.
	 */
	@Test
	public void testGetProjectUnknown() {
		final Integer projectId = 98000;

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}",
				String.class, projectId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * Test method for {@link ProjectService#deleteProject(Long)}.
	 */
	@Test
	public void testDeleteProject() {
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> entity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, entity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}",
				HttpMethod.DELETE, null, String.class, projectId);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		final ResponseEntity<String> responseGet = restTemplate.exchange("/project-home-generator/project/{id}",
				HttpMethod.GET, null, String.class, projectId);
		assertEquals(HttpStatus.NOT_FOUND, responseGet.getStatusCode());
	}

	/**
	 * Test method for
	 * {@link ProjectService#addJenkinsConfiguration(Long, JenkinsConfigurationDto)}.
	 */
	@Test
	public void testAddJenkinsConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add jenkins conf
		final JenkinsConfigurationDto jenkinsConfigurationDto = new JenkinsConfigurationDto();
		jenkinsConfigurationDto.setUrl("http://ci.wpetit.com/jenkins");
		jenkinsConfigurationDto.setJobsName(Arrays.asList("job1"));
		final HttpEntity<JenkinsConfigurationDto> entity = new HttpEntity<>(jenkinsConfigurationDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}/jenkins",
				HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"url\":\"http://ci.wpetit.com/jenkins\",\"jobsName\":[\"job1\"],\"projectId\":"
				+ projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getJenkinsConfiguration(Long)}.
	 */
	@Test
	public void testGetJenkinsConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add jenkins conf
		final JenkinsConfigurationDto jenkinsConfigurationDto = new JenkinsConfigurationDto();
		jenkinsConfigurationDto.setUrl("http://ci.wpetit.com/jenkins");
		jenkinsConfigurationDto.setJobsName(Arrays.asList("job1"));
		final HttpEntity<JenkinsConfigurationDto> entity = new HttpEntity<>(jenkinsConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/jenkins", HttpMethod.PUT, entity, String.class,
				projectId);

		final ResponseEntity<String> response = restTemplate
				.getForEntity("/project-home-generator/project/{id}/jenkins", String.class, projectId);

		final String expected = "{\"url\":\"http://ci.wpetit.com/jenkins\",\"jobsName\":[\"job1\"],\"projectId\":"
				+ projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	/**
	 * Test method for {@link ProjectService#getJenkinsConfiguration(Long)} with
	 * unknown project.
	 */
	@Test
	public void testGetJenkinsConfigurationUnknownProject() {
		final Integer projectId = 98000;

		final ResponseEntity<String> response = restTemplate
				.getForEntity("/project-home-generator/project/{id}/jenkins", String.class, projectId);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * Test method for
	 * {@link ProjectService#addApacheConfiguration(Long, ApacheConfigurationDto)}.
	 */
	@Test
	public void testAddApacheConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add apache conf
		final ApacheConfigurationDto apacheConfigurationDto = new ApacheConfigurationDto();
		apacheConfigurationDto.setUrl("http://ci.wpetit.com");
		final HttpEntity<ApacheConfigurationDto> entity = new HttpEntity<>(apacheConfigurationDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}/apache",
				HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"url\":\"http://ci.wpetit.com\",\"projectId\":" + projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getApacheConfiguration(Long)}.
	 */
	@Test
	public void testGetApacheConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add apache conf
		final ApacheConfigurationDto apacheConfigurationDto = new ApacheConfigurationDto();
		apacheConfigurationDto.setUrl("http://ci.wpetit.com");
		final HttpEntity<ApacheConfigurationDto> entity = new HttpEntity<>(apacheConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/apache", HttpMethod.PUT, entity, String.class,
				projectId);

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/apache",
				String.class, projectId);

		final String expected = "{\"url\":\"http://ci.wpetit.com\",\"projectId\":" + projectId + "}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getApacheConfiguration(Long)}.
	 */
	@Test
	public void testGetApacheConfigurationUnknownProject() {
		final Integer projectId = 99000;

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/apache",
				String.class, projectId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * Test method for
	 * {@link ProjectService#addSonarConfiguration(Long, SonarConfigurationDto)}.
	 */
	@Test
	public void testAddSonarConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add sonar conf
		final SonarConfigurationDto sonarConfigurationDto = new SonarConfigurationDto();
		sonarConfigurationDto.setUrl("http://ci.wpetit.com/sonar");
		sonarConfigurationDto.setResourceNames(Arrays.asList("res1"));
		final HttpEntity<SonarConfigurationDto> entity = new HttpEntity<>(sonarConfigurationDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}/sonar",
				HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"url\":\"http://ci.wpetit.com/sonar\",\"resourceNames\":[\"res1\"],\"projectId\":"
				+ projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getSonarConfiguration(Long)}.
	 */
	@Test
	public void testGetSonarConfiguration() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add sonar conf
		final SonarConfigurationDto sonarConfigurationDto = new SonarConfigurationDto();
		sonarConfigurationDto.setUrl("http://ci.wpetit.com/sonar");
		sonarConfigurationDto.setResourceNames(Arrays.asList("res1"));
		final HttpEntity<SonarConfigurationDto> entity = new HttpEntity<>(sonarConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/sonar", HttpMethod.PUT, entity, String.class,
				projectId);

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/sonar",
				String.class, projectId);

		final String expected = "{\"url\":\"http://ci.wpetit.com/sonar\",\"resourceNames\":[\"res1\"],\"projectId\":"
				+ projectId + "}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#getSonarConfiguration(Long)}.
	 */
	@Test
	public void testGetSonarConfigurationUnknownProject() {
		final Integer projectId = 99000;

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/sonar",
				String.class, projectId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * Test method for {@link ProjectService#addTool(Long, ToolDto)}.
	 */
	@Test
	public void testAddTool() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add tool
		final ToolDto toolDto = new ToolDto();
		toolDto.setUrl("http://ci.wpetit.com/nexus");
		toolDto.setName("Nexus");
		final HttpEntity<ToolDto> entity = new HttpEntity<>(toolDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}/tool",
				HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"url\":\"http://ci.wpetit.com/nexus\",\"name\":\"Nexus\",\"projectId\":" + projectId
				+ "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#addLink(Long, LinkDto)}.
	 */
	@Test
	public void testAddLink() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add link
		final LinkDto linkDto = new LinkDto();
		linkDto.setUrl("http://ci.wpetit.com/link");
		linkDto.setName("link1");
		linkDto.setImage("http://ci.wpetit.com/link.png");
		final HttpEntity<LinkDto> entity = new HttpEntity<>(linkDto);
		final ResponseEntity<String> response = restTemplate.exchange("/project-home-generator/project/{id}/link",
				HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"url\":\"http://ci.wpetit.com/link\",\"name\":\"link1\", \"image\":\"http://ci.wpetit.com/link.png\",\"projectId\":"
				+ projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for
	 * {@link ProjectService#addEnvironment(Long, EnvironmentDto)}.
	 */
	@Test
	public void testAddEnvironment() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add environment
		final EnvironmentDto environmentDto = new EnvironmentDto();
		environmentDto.setName("env1");
		final HttpEntity<EnvironmentDto> entity = new HttpEntity<>(environmentDto);
		final ResponseEntity<String> response = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.PUT, entity, String.class, projectId);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"name\":\"env1\",\"projectId\":" + projectId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#deleteEnvironment(Long)}.
	 */
	@Test
	public void testDeleteEnvironment() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add environment
		final EnvironmentDto environmentDto = new EnvironmentDto();
		environmentDto.setName("env1");
		final HttpEntity<EnvironmentDto> entity = new HttpEntity<>(environmentDto);
		final ResponseEntity<String> environmentResponse = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.PUT, entity, String.class, projectId);

		final Integer environmentId = JsonPath.read(environmentResponse.getBody(), "$.id");

		// delete environment
		final ResponseEntity<String> response = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment/{environmentId}", HttpMethod.DELETE, entity,
				String.class, projectId, environmentId);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		final ResponseEntity<String> responseGet = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.GET, null, String.class, projectId);
		assertEquals(HttpStatus.OK, responseGet.getStatusCode());
		JSONAssert.assertEquals("[]", responseGet.getBody(), false);
	}

	/**
	 * Test method for
	 * {@link ProjectService#addEnvironmentLink(Long, EnvironmentLinkDto)}.
	 */
	@Test
	public void testAddEnvironmentLink() {
		// create project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("my-project");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add environment
		final EnvironmentDto environmentDto = new EnvironmentDto();
		environmentDto.setName("env1");
		final HttpEntity<EnvironmentDto> environmentEntity = new HttpEntity<>(environmentDto);
		final ResponseEntity<String> environmentResponse = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.PUT, environmentEntity, String.class,
				projectId);
		final Integer environmentId = JsonPath.read(environmentResponse.getBody(), "$.id");

		// add environment link
		final EnvironmentLinkDto environmentLinkDto = new EnvironmentLinkDto();
		environmentLinkDto.setName("envLink1");
		environmentLinkDto.setUrl("http://ci.wpetit.com/envLink1");
		environmentLinkDto.setEnvironmentId(Long.valueOf(environmentId));
		final HttpEntity<EnvironmentLinkDto> entity = new HttpEntity<>(environmentLinkDto);
		final ResponseEntity<String> response = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment/{environmentId}/link", HttpMethod.PUT, entity,
				String.class, projectId, environmentId);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		final String expected = "{\"name\":\"envLink1\",\"url\":\"http://ci.wpetit.com/envLink1\",\"environmentId\":"
				+ environmentId + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for
	 * {@link ProjectService#generateProjectConfiguration(Long)}.
	 */
	@Test
	public void testGenerateProjectConfiguration() throws Exception {
		// add project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("project1");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add environment
		final EnvironmentDto environmentDto = new EnvironmentDto();
		environmentDto.setName("env1");
		final HttpEntity<EnvironmentDto> environmentEntity = new HttpEntity<>(environmentDto);
		final ResponseEntity<String> environmentResponse = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.PUT, environmentEntity, String.class,
				projectId);
		final Integer environmentId = JsonPath.read(environmentResponse.getBody(), "$.id");

		// add environment link
		final EnvironmentLinkDto environmentLinkDto = new EnvironmentLinkDto();
		environmentLinkDto.setName("Project-home");
		environmentLinkDto.setUrl("http://ci.wpetit.com");
		environmentLinkDto.setEnvironmentId(Long.valueOf(environmentId));
		final HttpEntity<EnvironmentLinkDto> envLinkEntity = new HttpEntity<>(environmentLinkDto);
		restTemplate.exchange("/project-home-generator/project/{id}/environment/{environmentId}/link", HttpMethod.PUT,
				envLinkEntity, String.class, projectId, environmentId);

		// add tool
		final ToolDto toolDto = new ToolDto();
		toolDto.setUrl("http://ci.wpetit.com/nexus");
		toolDto.setName("Nexus");
		final HttpEntity<ToolDto> toolEntity = new HttpEntity<>(toolDto);
		restTemplate.exchange("/project-home-generator/project/{id}/tool", HttpMethod.PUT, toolEntity, String.class,
				projectId);

		// add link
		final LinkDto linkDto = new LinkDto();
		linkDto.setUrl("http://ci.wpetit.com/nexus");
		linkDto.setName("Nexus");
		linkDto.setImage("images/nexus.png");
		final HttpEntity<LinkDto> linkEntity = new HttpEntity<>(linkDto);
		restTemplate.exchange("/project-home-generator/project/{id}/link", HttpMethod.PUT, linkEntity, String.class,
				projectId);

		// add apache configuration
		final ApacheConfigurationDto apacheConfigurationDto = new ApacheConfigurationDto();
		apacheConfigurationDto.setUrl("http://ci.wpetit.com");
		final HttpEntity<ApacheConfigurationDto> apacheEntity = new HttpEntity<>(apacheConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/apache", HttpMethod.PUT, apacheEntity, String.class,
				projectId);

		// add jenkins configuration
		final JenkinsConfigurationDto jenkinsConfigurationDto = new JenkinsConfigurationDto();
		jenkinsConfigurationDto.setUrl("http://ci.wpetit.com/jenkins");
		jenkinsConfigurationDto.setJobsName(Arrays.asList("project-home-generator"));
		final HttpEntity<JenkinsConfigurationDto> jenkinsEntity = new HttpEntity<>(jenkinsConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/jenkins", HttpMethod.PUT, jenkinsEntity,
				String.class, projectId);

		// add sonar conf
		final SonarConfigurationDto sonarConfigurationDto = new SonarConfigurationDto();
		sonarConfigurationDto.setUrl("http://ci.wpetit.com/sonar");
		sonarConfigurationDto.setResourceNames(Arrays.asList("com.wpetit:project-home-generator"));
		final HttpEntity<SonarConfigurationDto> sonarEntity = new HttpEntity<>(sonarConfigurationDto);
		restTemplate.exchange("/project-home-generator/project/{id}/sonar", HttpMethod.PUT, sonarEntity, String.class,
				projectId);

		final String expected = "{\"applicationName\":\"project1\",\"applicationLogo\":null,\"applicationLinks\":[{\"linkLogo\":\"images/nexus.png\",\"linkName\":\"Nexus\",\"linkUrl\":\"http://ci.wpetit.com/nexus\"}],\"applicationTools\":[{\"url\":\"http://ci.wpetit.com/nexus\",\"name\":\"Nexus\"}],"
				+ "\"env\":[{\"name\":\"env1\",\"urls\":[{\"urlName\":\"Project-home\",\"url\":\"http://ci.wpetit.com\"}]}],"
				+ "\"jenkinsUrl\":\"http://ci.wpetit.com/jenkins\",\"jenkinsBase64UsrPwd\":\"REPLACE_WITH BASE64(USER_JENKINS:PWD_JENKINS)\","
				+ "\"sonarBase64UsrPwd\":\"REPLACE_WITH BASE64(USER_SONAR:PWD_SONAR)\",\"sonarUrl\":\"http://ci.wpetit.com/sonar\","
				+ "\"jenkinsJobs\":[\"project-home-generator\"],\"sonarResources\":[\"com.wpetit:project-home-generator\"]}";

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/conf",
				String.class, projectId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	/**
	 * Test method for {@link ProjectService#generateProjectConfiguration(Long)}
	 * with unknown project.
	 */
	@Test
	public void testGenerateProjectConfigurationWithUnknownProject() throws Exception {
		final Integer projectId = 99000;

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/conf",
				String.class, projectId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/**
	 * Test method for {@link ProjectService#generateProjectConfiguration(Long)}
	 * without Jenkins and Sonar.
	 */
	@Test
	public void testGenerateProjectConfigurationWithoutJenkinsAndSonar() throws Exception {
		// add project
		final ProjectDto projectDto = new ProjectDto();
		projectDto.setName("project1");
		final HttpEntity<ProjectDto> projectEntity = new HttpEntity<>(projectDto);
		final ResponseEntity<String> projectResponse = restTemplate.exchange("/project-home-generator/project",
				HttpMethod.PUT, projectEntity, String.class);

		final Integer projectId = JsonPath.read(projectResponse.getBody(), "$.id");

		// add environment
		final EnvironmentDto environmentDto = new EnvironmentDto();
		environmentDto.setName("env1");
		final HttpEntity<EnvironmentDto> environmentEntity = new HttpEntity<>(environmentDto);
		final ResponseEntity<String> environmentResponse = restTemplate.exchange(
				"/project-home-generator/project/{id}/environment", HttpMethod.PUT, environmentEntity, String.class,
				projectId);
		final Integer environmentId = JsonPath.read(environmentResponse.getBody(), "$.id");

		// add environment link
		final EnvironmentLinkDto environmentLinkDto = new EnvironmentLinkDto();
		environmentLinkDto.setName("Project-home");
		environmentLinkDto.setUrl("http://ci.wpetit.com");
		environmentLinkDto.setEnvironmentId(Long.valueOf(environmentId));
		final HttpEntity<EnvironmentLinkDto> envLinkEntity = new HttpEntity<>(environmentLinkDto);
		restTemplate.exchange("/project-home-generator/project/{id}/environment/{environmentId}/link", HttpMethod.PUT,
				envLinkEntity, String.class, projectId, environmentId);

		// add tool
		final ToolDto toolDto = new ToolDto();
		toolDto.setUrl("http://ci.wpetit.com/nexus");
		toolDto.setName("Nexus");
		final HttpEntity<ToolDto> toolEntity = new HttpEntity<>(toolDto);
		restTemplate.exchange("/project-home-generator/project/{id}/tool", HttpMethod.PUT, toolEntity, String.class,
				projectId);

		// add link
		final LinkDto linkDto = new LinkDto();
		linkDto.setUrl("http://ci.wpetit.com/nexus");
		linkDto.setName("Nexus");
		linkDto.setImage("images/nexus.png");
		final HttpEntity<LinkDto> linkEntity = new HttpEntity<>(linkDto);
		restTemplate.exchange("/project-home-generator/project/{id}/link", HttpMethod.PUT, linkEntity, String.class,
				projectId);

		final String expected = "{\"applicationName\":\"project1\",\"applicationLogo\":null,\"applicationLinks\":[{\"linkLogo\":\"images/nexus.png\",\"linkName\":\"Nexus\",\"linkUrl\":\"http://ci.wpetit.com/nexus\"}],\"applicationTools\":[{\"url\":\"http://ci.wpetit.com/nexus\",\"name\":\"Nexus\"}],"
				+ "\"env\":[{\"name\":\"env1\",\"urls\":[{\"urlName\":\"Project-home\",\"url\":\"http://ci.wpetit.com\"}]}],"
				+ "\"jenkinsUrl\":null,\"jenkinsBase64UsrPwd\":null," + "\"sonarBase64UsrPwd\":null,\"sonarUrl\":null,"
				+ "\"jenkinsJobs\":null,\"sonarResources\":null}";

		final ResponseEntity<String> response = restTemplate.getForEntity("/project-home-generator/project/{id}/conf",
				String.class, projectId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

}
