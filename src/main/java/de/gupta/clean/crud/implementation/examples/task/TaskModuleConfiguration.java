package de.gupta.clean.crud.implementation.examples.task;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class TaskModuleConfiguration
{
}