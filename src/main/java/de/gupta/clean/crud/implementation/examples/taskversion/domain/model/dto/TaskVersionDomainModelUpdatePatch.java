package de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto;

import java.util.Optional;

public record TaskVersionDomainModelUpdatePatch(
		Optional<Long> version
)
{
	public static TaskVersionDomainModelUpdatePatch of(
			final Optional<Long> version
	)
	{
		return new TaskVersionDomainModelUpdatePatch(version);
	}
}