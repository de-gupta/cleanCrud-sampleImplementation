package de.gupta.clean.crud.implementation.examples.task.domain.mapping.save;

import de.gupta.clean.crud.implementation.examples.task.domain.model.TaskDomainModel;
import de.gupta.clean.crud.implementation.examples.task.domain.model.dto.TaskDomainModelCreate;
import de.gupta.clean.crud.template.domain.mapping.save.DomainModelBuilder;
import de.gupta.clean.crud.template.domain.model.builder.ModelBuilderFactory;
import org.springframework.stereotype.Component;

@Component
final class TaskDomainModelBuilder implements DomainModelBuilder<TaskDomainModelCreate, TaskDomainModel>
{
    private final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> modelBuilderFactory;

    @Override
    public TaskDomainModel toModel(final TaskDomainModelCreate domainModelCreate)
    {
        return modelBuilderFactory.builder()
                                  .withTitle(domainModelCreate.title())
                                  .withDescription(domainModelCreate.description())
                                  .withVersions(java.util.List.of())
                                  .build();
    }

    TaskDomainModelBuilder(
            final ModelBuilderFactory<TaskDomainModel, TaskDomainModel.TaskDomainModelBuilder> modelBuilderFactory)
    {
        this.modelBuilderFactory = modelBuilderFactory;
    }
}
