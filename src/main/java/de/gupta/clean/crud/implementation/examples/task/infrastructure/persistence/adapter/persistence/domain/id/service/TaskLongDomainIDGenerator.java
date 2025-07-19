package de.gupta.clean.crud.implementation.examples.task.infrastructure.persistence.adapter.persistence.domain.id.service;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.AbstractLongDomainIDGenerator;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("taskLongDomainIDGenerator")
final class TaskLongDomainIDGenerator extends AbstractLongDomainIDGenerator implements DomainIDGenerator<Long>
{
}