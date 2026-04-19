package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.service;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.AbstractLongDomainIDGenerator;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("noteLongDomainIDGenerator")
final class NoteLongDomainIDGenerator extends AbstractLongDomainIDGenerator implements DomainIDGenerator<Long>
{
}