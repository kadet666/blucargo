package com.blusoft.blucargo.gwt.server;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringHelper {

	public static Object getBean(String name, ServletContext servletContext) {

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (ctx == null) {
			throw new IllegalStateException("No Spring web application context found");
		}

		if (!ctx.containsBean(name)) {
			throw new IllegalArgumentException("Spring bean not found: " + name);
		}

		return ctx.getBean(name);
	}

}
