/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.dao.ToolDao;
import com.wpetit.projecthome.generator.model.Project;
import com.wpetit.projecthome.generator.model.Tool;

/**
 * The {@link ToolDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ToolDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ToolDao toolDao;

	/**
	 * Test method for {@link ToolDao#findByProjectId(Long)}
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Tool tool = new Tool();
		tool.setName("envName");
		tool.setUrl("http://ci.wpetit.com/tool");
		tool.setProject(project);
		entityManager.persistAndFlush(tool);

		final List<Tool> tools = toolDao.findByProjectId(project.getId());
		assertEquals(1, tools.size());
		assertEquals(tool, tools.get(0));
	}

	/**
	 * Test method for {@link ToolDao#saveAndFlush(Tool)}. Check constraints
	 * validation name is null.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutName() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Tool toolWithoutName = new Tool();
		toolWithoutName.setProject(project);
		toolWithoutName.setUrl("http://example.org");
		toolDao.saveAndFlush(toolWithoutName);
	}

	/**
	 * Test method for {@link ToolDao#saveAndFlush(Tool)}. Check constraints
	 * validation name too long.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushNameTooLong() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Tool toolWithNameTooLong = new Tool();
		toolWithNameTooLong.setProject(project);
		toolWithNameTooLong.setUrl("http://example.org");
		toolWithNameTooLong.setName(
				"string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars");
		toolDao.saveAndFlush(toolWithNameTooLong);
	}

	/**
	 * Test method for {@link ToolDao#saveAndFlush(Tool)}. Check constraints
	 * validation with invalid url.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithInvalidUrl() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Tool toolWithInvalidUrl = new Tool();
		toolWithInvalidUrl.setProject(project);
		toolWithInvalidUrl.setName("valid_name");
		toolWithInvalidUrl.setUrl("example.org");
		toolDao.saveAndFlush(toolWithInvalidUrl);
	}

	/**
	 * Test method for {@link ToolDao#saveAndFlush(Tool)}. Check constraints
	 * validation project null.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutProject() {
		final Tool toolWithoutProject = new Tool();
		toolWithoutProject.setName("valid_name");
		toolWithoutProject.setUrl("http://example.org");
		toolDao.saveAndFlush(toolWithoutProject);
	}
}
