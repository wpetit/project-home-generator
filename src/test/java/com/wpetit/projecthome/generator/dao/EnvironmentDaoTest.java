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
import com.wpetit.projecthome.generator.model.Environment;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link EnvironmentDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EnvironmentDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	EnvironmentDao environmentDao;

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

		final List<Environment> environments = environmentDao.findByProjectId(project.getId());
		assertEquals(1, environments.size());
		assertEquals(environment, environments.get(0));
	}
}
