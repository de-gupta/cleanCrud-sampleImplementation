open module de.gupta.clean.crud.implementation.examples
{
	requires de.gupta.clean.crud;
	requires aegis;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.beans;
	requires spring.core;
	requires spring.web;
	requires spring.webmvc;
	requires spring.aop;
	requires spring.tx;
	requires spring.data.jpa;
	requires spring.data.commons;
	requires jakarta.persistence;
	requires jakarta.validation;
	requires org.apache.tomcat.embed.core;
	requires org.aspectj.weaver;
	requires io.swagger.v3.oas.annotations;
	requires io.swagger.v3.oas.models;
	requires org.springdoc.openapi.common;
	requires org.springdoc.openapi.ui;
}