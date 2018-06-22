/**
 * 
 */
package com.techwizard.netflixzuulapigetwayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author s727953
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		HttpServletRequest req = RequestContext.getCurrentContext().getRequest();

		LOGGER.info("Request --> {}", req);
		LOGGER.info("RequestURI --> {}", req.getRequestURI());

		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
