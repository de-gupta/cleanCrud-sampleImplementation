package de.gupta.clean.crud.implementation.benchmark;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = {"de.gupta.clean.crud"})
@EnableAutoConfiguration
public class BenchmarkApplication
{
}