package de.gupta.clean.crud.implementation.examples.note.domain.model.dto;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;

public record NoteDomainModelResponse(
		String note
)
{
	public static NoteDomainModelResponse fromDomainModel(final NoteDomainModel noteDomainModel)
	{
		return new NoteDomainModelResponse(
				noteDomainModel.note());
	}
}