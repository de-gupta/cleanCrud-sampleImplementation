package de.gupta.clean.crud.implementation.examples.tag.domain.model.dto;

public record TagDomainModelCreate(
		String name
)
{
	public static TagDomainModelCreate of(
			final String name
	)
	{
		return new TagDomainModelCreate(
				name);
	}

	public static TagDomainModelCreate fromUpdatePatch(final TagDomainModelUpdatePatch updatePatch)
	{
		return new TagDomainModelCreate(
				updatePatch.name().orElseThrow());
	}

	public TagDomainModelCreate
	{
	}
}