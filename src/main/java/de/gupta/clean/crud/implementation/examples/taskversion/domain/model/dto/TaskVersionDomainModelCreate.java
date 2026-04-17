package de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto;

public record TaskVersionDomainModelCreate(
		long version
)
{
	public static TaskVersionDomainModelCreate of(
			final long version
	)
	{
		return new TaskVersionDomainModelCreate(version);
	}
}