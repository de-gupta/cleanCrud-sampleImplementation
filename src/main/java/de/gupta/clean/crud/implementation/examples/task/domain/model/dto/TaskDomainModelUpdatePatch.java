package de.gupta.clean.crud.implementation.examples.task.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.version.useCases.crud.common.dto.VersionAPIModelUpdatePatch;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public record TaskDomainModelUpdatePatch(
		Optional<String> title,
		Optional<String> description,
		Optional<Collection<VersionAPIModelUpdatePatch>> versions,
		Collection<Long> removeVersionIds
)
{
	public static TaskDomainModelUpdatePatch of(
			final Optional<String> title,
			final Optional<String> description,
			final Optional<Collection<VersionAPIModelUpdatePatch>> versions,
			final Collection<Long> removeVersionIds)
	{
		return new TaskDomainModelUpdatePatch(
				title,
				description,
				versions.map(List::copyOf),
				List.copyOf(removeVersionIds));
	}

	public static TaskDomainModelUpdatePatch of(final Optional<String> title, final Optional<String> description)
	{
		return of(
				title,
				description,
				Optional.empty(),
				List.of());
	}
}