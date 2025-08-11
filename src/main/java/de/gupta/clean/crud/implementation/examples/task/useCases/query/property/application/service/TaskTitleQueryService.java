package de.gupta.clean.crud.implementation.examples.task.useCases.query.property.application.service;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelResponse;
import de.gupta.clean.crud.template.domain.mapping.fetch.DomainResponseBuilder;
import de.gupta.clean.crud.template.domain.service.query.PropertyExtractor;
import de.gupta.clean.crud.template.useCases.crud.fetch.application.service.FetchService;
import de.gupta.clean.crud.template.useCases.query.property.application.service.AbstractPropertyQueryService;
import de.gupta.clean.crud.template.useCases.query.property.application.service.PropertyQueryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
final class TaskTitleQueryService
		extends AbstractPropertyQueryService<String, Long, TaskDomainModel, TaskDomainModelResponse>
		implements PropertyQueryService<String, Long, TaskDomainModelResponse>
{
	TaskTitleQueryService(
			final FetchService<TaskDomainModel, Long> fetchService,
			final PropertyExtractor<TaskDomainModel, String> propertyExtractor,
			final DomainResponseBuilder<TaskDomainModel, TaskDomainModelResponse> domainResponseBuilder)
	{
		super(fetchService, propertyExtractor, Comparator.naturalOrder(), domainResponseBuilder);
	}
}