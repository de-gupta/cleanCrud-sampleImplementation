package de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;

public record TaskVersionDomainModelResponse(
		long version
)
{
	public static TaskVersionDomainModelResponse fromDomainModel(final TaskVersionDomainModel taskVersionDomainModel)
	{
		return new TaskVersionDomainModelResponse(
				taskVersionDomainModel.version());
	}
}