package de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.security;

import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityPolicy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public final class VersionEndpointSecurityPolicy implements EndpointSecurityPolicy
{
	@Override
	public boolean isAccessAllowed(final Method method, final Object[] args, final HttpServletRequest request)
	{
		// TODO from Template: replace this permissive default with real endpoint authorization logic.
		// Example: return request.isUserInRole("ADMIN") || method.getName().startsWith("find");
		return true;
	}
}