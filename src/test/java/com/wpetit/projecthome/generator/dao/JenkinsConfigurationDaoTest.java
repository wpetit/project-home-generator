/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.dao.JenkinsConfigurationDao;
import com.wpetit.projecthome.generator.model.JenkinsConfiguration;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link JenkinsConfigurationDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class JenkinsConfigurationDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	JenkinsConfigurationDao jenkinsConfigurationDao;

	/**
	 * Test method for {@link JenkinsConfigurationDao#findByProjectId(Long)}
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final JenkinsConfiguration jenkinsConfiguration = new JenkinsConfiguration();
		jenkinsConfiguration.setUrl("http://ci.wpetit.com/jenkins");
		jenkinsConfiguration.setProject(project);
		jenkinsConfiguration.setJobsName(Arrays.asList("job"));
		entityManager.persistAndFlush(jenkinsConfiguration);

		final JenkinsConfiguration actualJenkinsConfiguration = jenkinsConfigurationDao
				.findByProjectId(project.getId());
		assertEquals(jenkinsConfiguration, actualJenkinsConfiguration);
	}
}
