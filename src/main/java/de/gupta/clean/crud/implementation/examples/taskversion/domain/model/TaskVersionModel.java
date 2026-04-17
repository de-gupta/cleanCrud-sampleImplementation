package de.gupta.clean.crud.implementation.examples.taskversion.domain.model;

import de.gupta.clean.crud.template.domain.model.validation.Validatable;

public interface TaskVersionModel extends Validatable
{
	long version();
}