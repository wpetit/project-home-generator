/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.dao.EnvironmentDao;
import com.wpetit.projecthome.generator.dao.EnvironmentLinkDao;
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.EnvironmentLink;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link EnvironmentLinkDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EnvironmentLinkDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	EnvironmentLinkDao environmentLinkDao;

	/**
	 * Test method for {@link EnvironmentDao#findByProjectId(Long)}
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Environment environment = new Environment();
		environment.setName("envName");
		environment.setProject(project);
		entityManager.persistAndFlush(environment);

		final EnvironmentLink environmentLink = new EnvironmentLink();
		environmentLink.setName("envName");
		environmentLink.setUrl("http://ci.wpetit.com/envlink");
		environmentLink.setEnvironment(environment);
		entityManager.persistAndFlush(environmentLink);

		final List<EnvironmentLink> environmentLinks = environmentLinkDao.findByEnvironmentId(environment.getId());
		assertEquals(1, environmentLinks.size());
		assertEquals(environmentLink, environmentLinks.get(0));
	}
}
