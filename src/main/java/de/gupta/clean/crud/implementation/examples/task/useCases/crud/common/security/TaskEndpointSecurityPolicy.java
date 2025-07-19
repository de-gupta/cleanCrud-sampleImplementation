package de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.security;

import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityPolicy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public final class TaskEndpointSecurityPolicy implements EndpointSecurityPolicy
{
	@Override
	public boolean isAccessAllowed(final Method method, final Object[] args, final HttpServletRequest request)
	{
//		return !method.getName().equals("findAll");
		return true;
	}
}