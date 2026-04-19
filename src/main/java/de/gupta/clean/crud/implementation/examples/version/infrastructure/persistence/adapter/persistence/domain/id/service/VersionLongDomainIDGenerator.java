package de.gupta.clean.crud.implementation.examples.version.infrastructure.persistence.adapter.persistence.domain.id.service;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.AbstractLongDomainIDGenerator;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.service.DomainIDGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("versionLongDomainIDGenerator")
final class VersionLongDomainIDGenerator extends AbstractLongDomainIDGenerator implements DomainIDGenerator<Long>
{
}