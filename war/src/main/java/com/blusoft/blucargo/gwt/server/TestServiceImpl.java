package com.blusoft.blucargo.gwt.server;

import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.blusoft.blucargo.gwt.client.TestService;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.service.CargoOfferService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TestServiceImpl extends RemoteServiceServlet implements TestService {

	public String test() {

		CargoOfferService cargoOfferService = (CargoOfferService) getBean("CargoOfferService");

		List<CargoOffer> offers = cargoOfferService.findAll();

		return "test";
	}

	protected Object getBean(String name) {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		if (ctx == null) {
			throw new IllegalStateException("No Spring web application context found");
		}

		if (!ctx.containsBean(name)) {
			throw new IllegalArgumentException("Spring bean not found: " + name);
		}

		return ctx.getBean(name);
	}

}
