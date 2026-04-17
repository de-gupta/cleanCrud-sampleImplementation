package de.gupta.clean.crud.implementation.examples.task.useCases.crud.configuration;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelUpdatePatch;
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
class TaskCrudDefinitionConfiguration
{
	@Bean
	@Qualifier("taskAggregateCrudDefinition")
	AggregateCrudDefinition<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse> taskAggregateCrudDefinition(
			@Qualifier("taskAggregateMutationPort") final AggregateMutationPort<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch> mutationPort,
			@Qualifier("taskAggregateFetchPort") final AggregateFetchPort<Long, TaskDomainModel> fetchPort,
			@Qualifier("taskDomainModelBuilder") final DomainModelBuilder<TaskDomainModelCreate, TaskDomainModel> createBuilder,
			@Qualifier("taskDomainModelPatcher") final DomainModelPatcher<TaskDomainModel, TaskDomainModelUpdatePatch> patcher,
			@Qualifier("taskDomainResponseBuilder") final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> responseBuilder,
			@Qualifier("taskInsertionPolicy") final InsertionPolicy<TaskDomainModel> insertionPolicy,
			@Qualifier("taskPatchPolicy") final PatchPolicy<TaskDomainModel> patchPolicy,
			@Qualifier("taskDeletionPolicy") final DeletionPolicy<TaskDomainModel> deletionPolicy,
			@Qualifier("taskDomainSecurityPolicy") final DomainSecurityPolicy<TaskDomainModel> securityPolicy,
			@Qualifier("taskDuplicateDefinition") final DuplicateDefinition<TaskDomainModel> duplicateDefinition)
	{
		return AggregateCrudDefinitions.<Long, TaskDomainModel, TaskDomainModelCreate, TaskDomainModelUpdatePatch, TaskDomainModelResponse>
											   aggregateCrudDefinition()
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