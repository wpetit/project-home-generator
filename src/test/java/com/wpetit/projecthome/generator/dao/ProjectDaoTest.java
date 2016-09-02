/**
 *
 */
package com.wpetit.projecthome.generator.dao;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wpetit.projecthome.generator.dao.ProjectDao;
import com.wpetit.projecthome.generator.model.Project;

/**
 * The {@link ProjectDaoTest} class.
 *
 * @author wpetit
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectDaoTest {

	@Autowired
	ProjectDao projectDao;

	/**
	 * Test method for
	 * {@link ProjectDao#saveAndFlush(com.wpetit.projecthome.generator.model.Project)}. Check
	 * constraints validation name is null.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushWithoutName() {
		final Project projectWithoutName = new Project();
		projectDao.saveAndFlush(projectWithoutName);
	}

	/**
	 * Test method for
	 * {@link ProjectDao#saveAndFlush(com.wpetit.projecthome.generator.model.Project)}. Check
	 * constraints validation name too long.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushNameTooLong() {
		final Project projectWithNameTooLong = new Project();
		projectWithNameTooLong.setName(
				"string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars_string_with_more_than_256_chars");
		projectDao.saveAndFlush(projectWithNameTooLong);
	}

	/**
	 * Test method for
	 * {@link ProjectDao#saveAndFlush(com.wpetit.projecthome.generator.model.Project)}. Check
	 * constraints validation image too long.
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveAndFlushImageTooLong() {
		final Project projectWithImageTooLong = new Project();
		projectWithImageTooLong.setName("valid_name");
		projectWithImageTooLong.setImage(
				"string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars_string_with_more_than_1024_chars");
		projectDao.saveAndFlush(projectWithImageTooLong);
	}
}
