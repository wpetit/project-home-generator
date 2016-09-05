package com.wpetit.projecthome.generator.services.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The ErrorService class.
 *
 * @author wpetit
 *
 */
@RestController
@RequestMapping(ErrorService.ERROR_PATH)
public class ErrorService implements ErrorController {

	/** The logger. **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorService.class);

	/** The ERROR_PATH. **/
	protected static final String ERROR_PATH = "/error";

	/** The TRACE_PARAM. **/
	public static final String TRACE_PARAM = "trace";

	/** The errorAttributes. **/
	@Autowired
	private ErrorAttributes errorAttributes;

	/**
	 * Return a map containing error traces.
	 *
	 * @param aRequest
	 *            the request
	 * @return the map
	 */
	@RequestMapping
	public Map<String, Object> error(final HttpServletRequest aRequest) {
		LOGGER.error(aRequest.getParameterNames().toString());
		final Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
		final String trace = (String) body.get(TRACE_PARAM);
		if (trace != null) {
			final String[] lines = trace.split("\n\t");
			body.put(TRACE_PARAM, lines);
		}
		LOGGER.error(body.toString());
		return body;
	}

	private boolean getTraceParameter(final HttpServletRequest request) {
		final String parameter = request.getParameter(TRACE_PARAM);
		if (parameter == null) {
			return false;
		}
		return BooleanUtils.toBoolean(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(final HttpServletRequest aRequest, final boolean includeStackTrace) {
		final RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

	/** {@inheritDoc} **/
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
