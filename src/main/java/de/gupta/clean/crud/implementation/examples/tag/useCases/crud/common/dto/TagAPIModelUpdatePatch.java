package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto;

import java.util.Optional;

public record TagAPIModelUpdatePatch(
		Optional<String> name
)
{
	public static TagAPIModelUpdatePatch of(
			final Optional<String> name
	)
	{
		return new TagAPIModelUpdatePatch(
				name);
	}

	public TagAPIModelUpdatePatch
	{
		name = Optional.ofNullable(name).orElse(Optional.empty());
	}
}