/**
 *
 */
package com.wpetit.projecthome.generator.business.model;

import java.util.List;

/**
 * The {@link Env} class.
 *
 * @author wpetit
 *
 */
public class Env {
	/** The name. **/
	private String name;
	/** The urls. **/
	private List<EnvUrl> urls;

	/**
	 * Env constructor.
	 */
	public Env() {
		// default constructor
	}

	/**
	 * Env constructor.
	 *
	 * @param name
	 * @param urls
	 */
	public Env(final String name, final List<EnvUrl> urls) {
		this.name = name;
		this.urls = urls;
	}

	/**
	 * Return the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Return the urls.
	 *
	 * @return the urls
	 */
	public List<EnvUrl> getUrls() {
		return urls;
	}

	/**
	 * Set the urls.
	 *
	 * @param urls
	 *            the urls to set
	 */
	public void setUrls(final List<EnvUrl> urls) {
		this.urls = urls;
	}
}
