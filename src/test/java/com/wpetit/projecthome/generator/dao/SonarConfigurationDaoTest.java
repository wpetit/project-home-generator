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

import com.wpetit.projecthome.generator.dao.SonarConfigurationDao;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.SonarConfiguration;

/**
 * The {@link SonarConfigurationDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SonarConfigurationDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	SonarConfigurationDao sonarConfigurationDao;

	/**
	 * Test method for {@link SonarConfigurationDao#findByProjectId(Long)}
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final SonarConfiguration sonarConfiguration = new SonarConfiguration();
		sonarConfiguration.setUrl("http://ci.wpetit.com/sonar");
		sonarConfiguration.setProject(project);
		sonarConfiguration.setResourceNames(Arrays.asList("job"));
		entityManager.persistAndFlush(sonarConfiguration);

		final SonarConfiguration actualSonarConfiguration = sonarConfigurationDao.findByProjectId(project.getId());
		assertEquals(sonarConfiguration, actualSonarConfiguration);
	}
}
