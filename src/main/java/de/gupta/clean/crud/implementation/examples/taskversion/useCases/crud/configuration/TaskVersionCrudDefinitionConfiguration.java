package de.gupta.clean.crud.implementation.examples.taskversion.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.TaskVersionDomainModel;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.taskversion.domain.model.dto.TaskVersionDomainModelUpdatePatch;
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
class TaskVersionCrudDefinitionConfiguration
{
	@Bean
	@Qualifier("taskVersionAggregateCrudDefinition")
	AggregateCrudDefinition<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch, TaskVersionDomainModelResponse> taskVersionAggregateCrudDefinition(
			@Qualifier("taskVersionAggregateMutationPort") final AggregateMutationPort<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch> mutationPort,
			@Qualifier("taskVersionAggregateFetchPort") final AggregateFetchPort<Long, TaskVersionDomainModel> fetchPort,
			@Qualifier("taskVersionDomainModelBuilder") final DomainModelBuilder<TaskVersionDomainModelCreate, TaskVersionDomainModel> createBuilder,
			@Qualifier("taskVersionDomainModelPatcher") final DomainModelPatcher<TaskVersionDomainModel, TaskVersionDomainModelUpdatePatch> patcher,
			@Qualifier("taskVersionDomainResponseBuilder") final DomainResponseBuilder<TaskVersionDomainModel, TaskVersionDomainModelResponse> responseBuilder,
			@Qualifier("taskVersionInsertionPolicy") final InsertionPolicy<TaskVersionDomainModel> insertionPolicy,
			@Qualifier("taskVersionPatchPolicy") final PatchPolicy<TaskVersionDomainModel> patchPolicy,
			@Qualifier("taskVersionDeletionPolicy") final DeletionPolicy<TaskVersionDomainModel> deletionPolicy,
			@Qualifier("taskVersionDomainSecurityPolicy") final DomainSecurityPolicy<TaskVersionDomainModel> securityPolicy,
			@Qualifier("taskVersionDuplicateDefinition") final DuplicateDefinition<TaskVersionDomainModel> duplicateDefinition)
	{
		return AggregateCrudDefinitions
				.<Long, TaskVersionDomainModel, TaskVersionDomainModelCreate, TaskVersionDomainModelUpdatePatch, TaskVersionDomainModelResponse>aggregateCrudDefinition()
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
