package de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto;

public record TagAPIModelResponse(
		Long id,
		String name
)
{
	public static TagAPIModelResponse of(
			final long id,
			final String name)
	{
		return new TagAPIModelResponse(
				id, name);
	}
}