package de.gupta.clean.crud.implementation.examples.common.adapter.id;

import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.APIDomainIDAdapter;
import de.gupta.clean.crud.template.useCases.crud.common.adapter.id.AbstractIdentityAPIDomainIDAdapter;
import org.springframework.stereotype.Component;

@Component
final class IdentityAPIDomainIDAdapter<ID> extends AbstractIdentityAPIDomainIDAdapter<ID>
		implements APIDomainIDAdapter<ID, ID>
{
}