package de.gupta.clean.crud.implementation.examples.task.domain.service.query;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.query.AbstractPropertyExtractor;
import de.gupta.clean.crud.template.domain.service.query.PropertyExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
final class StringPropertyExtractor extends AbstractPropertyExtractor<TaskDomainModel, String>
		implements PropertyExtractor<TaskDomainModel, String>
{
	private static final Map<String, Function<TaskDomainModel, String>> PROPERTY_MAP =
			Map.of(
					"title", TaskDomainModel::title,
					"description", task -> task.description().orElse("")
			);

	StringPropertyExtractor()
	{
		super(PROPERTY_MAP);
	}
}