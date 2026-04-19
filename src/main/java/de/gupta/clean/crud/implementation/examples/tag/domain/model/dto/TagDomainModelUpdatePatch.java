package de.gupta.clean.crud.implementation.examples.tag.domain.model.dto;

import java.util.Optional;

public record TagDomainModelUpdatePatch(
		Optional<String> name
)
{
	public static TagDomainModelUpdatePatch of(
			final Optional<String> name
	)
	{
		return new TagDomainModelUpdatePatch(
				name);
	}

	public TagDomainModelUpdatePatch
	{
		name = Optional.ofNullable(name).orElse(Optional.empty());
	}
}