/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.model.ApacheConfiguration;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link ApacheDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ApacheDaoTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ApacheConfigurationDao apacheConfigurationDao;

	/**
	 * Test method for {@link ApacheConfigurationDao#findByProjectId(Long)}.
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final ApacheConfiguration apacheConfiguration = new ApacheConfiguration();
		apacheConfiguration.setUrl("http://ci.wpetit.com");
		apacheConfiguration.setProject(project);
		entityManager.persistAndFlush(apacheConfiguration);

		final ApacheConfiguration apacheConfigurationResult = apacheConfigurationDao.findByProjectId(project.getId());
		assertEquals(apacheConfiguration, apacheConfigurationResult);
	}
}
