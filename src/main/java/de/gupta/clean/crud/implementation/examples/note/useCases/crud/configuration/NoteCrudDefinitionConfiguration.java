package de.gupta.clean.crud.implementation.examples.note.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.note.domain.model.NoteDomainModel;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.note.domain.model.dto.NoteDomainModelUpdatePatch;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.mapping.update.DomainModelPatcher;
import de.gupta.clean.crud.template.domain.service.crud.policy.DeletionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.InsertionPolicy;
import de.gupta.clean.crud.template.domain.service.crud.policy.PatchPolicy;
import de.gupta.clean.crud.template.domain.service.equality.DuplicateDefinition;
import de.gupta.clean.crud.template.domain.service.security.DomainSecurityPolicy;
import de.gupta.clean.crud.template.useCases.crud.aggregate.builder.AggregateCrudDefinitions;
import de.gupta.clean.crud.template.useCases.crud.aggregate.definition.AggregateCrudDefinition;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateFetchPort;
import de.gupta.clean.crud.template.useCases.crud.aggregate.port.AggregateMutationPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NoteCrudDefinitionConfiguration
{
	@Bean
	@Qualifier("noteAggregateCrudDefinition")
	AggregateCrudDefinition<Long, NoteDomainModel, NoteDomainModelCreate,
			NoteDomainModelUpdatePatch, NoteDomainModelResponse> noteAggregateCrudDefinition(
			@Qualifier("noteAggregateMutationPort") final AggregateMutationPort<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch> mutationPort,
			@Qualifier("noteAggregateFetchPort") final AggregateFetchPort<Long, NoteDomainModel> fetchPort,
			@Qualifier("noteDomainModelBuilder") final DomainModelBuilder<NoteDomainModelCreate, NoteDomainModel> createBuilder,
			@Qualifier("noteDomainModelPatcher") final DomainModelPatcher<NoteDomainModel, NoteDomainModelUpdatePatch> patcher,
			@Qualifier("noteDomainResponseBuilder") final DomainResponseBuilder<NoteDomainModel, NoteDomainModelResponse> responseBuilder,
			@Qualifier("noteInsertionPolicy") final InsertionPolicy<NoteDomainModel> insertionPolicy,
			@Qualifier("notePatchPolicy") final PatchPolicy<NoteDomainModel> patchPolicy,
			@Qualifier("noteDeletionPolicy") final DeletionPolicy<NoteDomainModel> deletionPolicy,
			@Qualifier("noteDomainSecurityPolicy") final DomainSecurityPolicy<NoteDomainModel> securityPolicy,
			@Qualifier("noteDuplicateDefinition") final DuplicateDefinition<NoteDomainModel> duplicateDefinition)
	{
		return AggregateCrudDefinitions
				.<Long, NoteDomainModel, NoteDomainModelCreate, NoteDomainModelUpdatePatch, NoteDomainModelResponse>aggregateCrudDefinition()
				.mutationPort(mutationPort)
				.fetchPort(fetchPort)
				.createBuilder(createBuilder)
				.patcher(patcher)
				.responseBuilder(responseBuilder)
				.insertionPolicy(insertionPolicy)
				.patchPolicy(patchPolicy)
				.deletionPolicy(deletionPolicy)
				.securityPolicy(securityPolicy)
				.duplicateDefinition(duplicateDefinition)
				.build();
	}
}
