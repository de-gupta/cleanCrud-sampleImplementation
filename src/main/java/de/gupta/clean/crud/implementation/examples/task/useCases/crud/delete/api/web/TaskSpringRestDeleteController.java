package de.gupta.clean.crud.implementation.examples.task.useCases.crud.delete.api.web;

import de.gupta.clean.crud.template.useCases.crud.delete.api.web.AbstractSpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.api.web.SpringRestDeleteController;
import de.gupta.clean.crud.template.useCases.crud.delete.facade.DeleteServiceFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Task Delete", description = "Delete Operations pertaining to Task")
@RestController
@RequestMapping("/task/delete")
class TaskSpringRestDeleteController extends AbstractSpringRestDeleteController<Long>
		implements SpringRestDeleteController<Long>
{
	TaskSpringRestDeleteController(
			@Qualifier("taskDeleteServiceFacade") final DeleteServiceFacade<Long> service)
	{
		super(service);
	}
}