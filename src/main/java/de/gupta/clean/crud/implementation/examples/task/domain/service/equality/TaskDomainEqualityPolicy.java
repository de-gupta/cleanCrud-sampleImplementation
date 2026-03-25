package de.gupta.clean.crud.implementation.examples.task.domain.service.equality;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.template.domain.service.equality.DomainEqualityPolicy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
final class TaskDomainEqualityPolicy implements DomainEqualityPolicy<TaskDomainModel>
{
	@Override
	public boolean areEqual(final TaskDomainModel left, final TaskDomainModel right)
	{
		return Objects.equals(left.title(), right.title());
	}
}