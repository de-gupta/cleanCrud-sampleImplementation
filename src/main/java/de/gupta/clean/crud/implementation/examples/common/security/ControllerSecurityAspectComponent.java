package de.gupta.clean.crud.implementation.examples.common.security;

import de.gupta.clean.crud.template.useCases.crud.common.security.AbstractControllerSecurityAspect;
import de.gupta.clean.crud.template.useCases.crud.common.security.ControllerSecurityAspect;
import de.gupta.clean.crud.template.useCases.crud.common.security.EndpointSecurityConfiguration;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
final class ControllerSecurityAspectComponent extends AbstractControllerSecurityAspect
		implements ControllerSecurityAspect
{
	private static final String POINTCUT =
			"@target(endpointSecurityConfiguration) && execution(* *..api.web..*AbstractSpringRest*Controller.*(..))";

	@Override
	@Around(POINTCUT)
	public Object enforcePolicy(final ProceedingJoinPoint joinPoint,
								final EndpointSecurityConfiguration endpointSecurityConfiguration)
	{
		return super.enforcePolicy(joinPoint, endpointSecurityConfiguration);
	}

	ControllerSecurityAspectComponent(final ApplicationContext context)
	{
		super(context);
	}
}