package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.common.dto;

public record TaskVersionAPIModelResponse(
		Long id,
		long version
)
{
	public static TaskVersionAPIModelResponse of(
			final long id,
			final long version)
	{
		return new TaskVersionAPIModelResponse(
				id, version);
	}
}