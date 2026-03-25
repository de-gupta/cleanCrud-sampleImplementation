package de.gupta.clean.crud.implementation.examples;

import de.gupta.clean.crud.implementation.examples.task.TaskModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import(
		{
				TaskModuleConfiguration.class,
//				PersonModuleConfiguration.class,
		}
)
public class ExampleApplication
{
	static void main(final String[] args)
	{
		SpringApplication.run(ExampleApplication.class, args);
	}
}