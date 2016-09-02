package com.wpetit.projecthome.generator.services.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/error")
public class ErrorService implements ErrorController {

	/** The logger. **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorService.class);

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping
	public Map<String, Object> error(final HttpServletRequest aRequest) {
		LOGGER.error(aRequest.getParameterNames().toString());
		final Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
		final String trace = (String) body.get("trace");
		if (trace != null) {
			final String[] lines = trace.split("\n\t");
			body.put("trace", lines);
		}
		LOGGER.error(body.toString());
		return body;
	}

	private boolean getTraceParameter(final HttpServletRequest request) {
		final String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equalsIgnoreCase(parameter);
	}

	private Map<String, Object> getErrorAttributes(final HttpServletRequest aRequest, final boolean includeStackTrace) {
		final RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
