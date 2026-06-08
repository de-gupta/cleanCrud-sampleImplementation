package de.gupta.clean.crud.implementation.examples.task.useCases.process.print.application;

import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelCreate;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelResponse;
import de.gupta.clean.crud.implementation.examples.task.useCases.crud.common.dto.TaskAPIModelUpdatePatch;
import de.gupta.clean.crud.implementation.examples.task.useCases.process.print.domain.AppendPrintedSuffixToTaskTitleCommand;
import de.gupta.clean.crud.template.useCases.crud.update.api.application.UpdateApplicationController;
import de.gupta.clean.crud.template.useCases.process.application.dispatch.ApplicationActionDispatcher;
import de.gupta.clean.crud.template.useCases.process.domain.action.ApplicationAction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
final class TaskPrintApplicationActionDispatcher implements ApplicationActionDispatcher
{
	private final UpdateApplicationController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
			taskUpdateApplicationController;

	@Override
	public void dispatch(final Collection<? extends ApplicationAction> applicationActions)
	{
		applicationActions.forEach(this::dispatchAction);
	}

	private void dispatchAction(final ApplicationAction applicationAction)
	{
		if (applicationAction instanceof AppendPrintedSuffixToTaskTitleCommand(Long taskId, String updatedTitle))
		{
			taskUpdateApplicationController.updateById(
					taskId,
					TaskAPIModelUpdatePatch.of(Optional.of(updatedTitle), Optional.empty()));
			return;
		}
		throw new IllegalArgumentException("Unsupported application action: " + applicationAction.getClass().getName());
	}

	TaskPrintApplicationActionDispatcher(
			@Lazy
			@Qualifier("taskUpdateApplicationController") final UpdateApplicationController<TaskAPIModelCreate, TaskAPIModelUpdatePatch, TaskAPIModelResponse, Long>
					taskUpdateApplicationController)
	{
		this.taskUpdateApplicationController = taskUpdateApplicationController;
	}
}