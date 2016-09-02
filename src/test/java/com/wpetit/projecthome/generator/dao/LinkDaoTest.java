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

import com.wpetit.projecthome.generator.dao.LinkDao;
import com.wpetit.projecthome.generator.model.Link;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link LinkDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class LinkDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	LinkDao linkDao;

	/**
	 * Test method for {@link LinkDao#findByProjectId(Long)}
	 */
	@Test
	public void testFindByProjectId() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link link = new Link();
		link.setName("envName");
		link.setUrl("http://ci.wpetit.com/link");
		link.setProject(project);
		link.setImage("images/link.png");
		entityManager.persistAndFlush(link);

		final List<Link> links = linkDao.findByProjectId(project.getId());
		assertEquals(1, links.size());
		assertEquals(link, links.get(0));
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation name is null.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutName() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link linkWithoutName = new Link();
		linkWithoutName.setProject(project);
		linkWithoutName.setImage("test");
		linkWithoutName.setUrl("http://example.org");
		linkDao.saveAndFlush(linkWithoutName);
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation name too long.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushNameTooLong() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link linkWithNameTooLong = new Link();
		linkWithNameTooLong.setProject(project);
		linkWithNameTooLong.setImage("test");
		linkWithNameTooLong.setUrl("http://example.org");
		linkWithNameTooLong.setName(
				"string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars");
		linkDao.saveAndFlush(linkWithNameTooLong);
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation without image.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutImage() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link linkWithoutImage = new Link();
		linkWithoutImage.setProject(project);
		linkWithoutImage.setName("valid_name");
		linkWithoutImage.setUrl("http://example.org");
		linkDao.saveAndFlush(linkWithoutImage);
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation image too long.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushImageTooLong() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link linkWithImageTooLong = new Link();
		linkWithImageTooLong.setProject(project);
		linkWithImageTooLong.setName("valid_name");
		linkWithImageTooLong.setUrl("http://example.org");
		linkWithImageTooLong.setImage(
				"string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars");
		linkDao.saveAndFlush(linkWithImageTooLong);
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation with invalid url.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithInvalidUrl() {
		final Project project = new Project();
		project.setName("name");
		entityManager.persistAndFlush(project);

		final Link linkWithInvalidUrl = new Link();
		linkWithInvalidUrl.setProject(project);
		linkWithInvalidUrl.setName("valid_name");
		linkWithInvalidUrl.setImage("valid_image");
		linkWithInvalidUrl.setUrl("example.org");
		linkDao.saveAndFlush(linkWithInvalidUrl);
	}

	/**
	 * Test method for {@link LinkDao#saveAndFlush(Link)}. Check constraints
	 * validation project null.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutProject() {
		final Link linkWithoutProject = new Link();
		linkWithoutProject.setName("valid_name");
		linkWithoutProject.setImage("valid_image");
		linkWithoutProject.setUrl("http://example.org");
		linkDao.saveAndFlush(linkWithoutProject);
	}

}
