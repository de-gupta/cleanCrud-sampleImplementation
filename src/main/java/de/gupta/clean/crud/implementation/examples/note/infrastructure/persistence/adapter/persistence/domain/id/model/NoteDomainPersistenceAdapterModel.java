package de.gupta.clean.crud.implementation.examples.note.infrastructure.persistence.adapter.persistence.domain.id.model;

import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.AbstractDomainPersistenceAdapterModel;
import de.gupta.clean.crud.template.infrastructure.persistence.adapter.persistence.domain.id.model.DomainPersistenceAdapterModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(name = "note_domain_persistence_adapter_model",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "domain_id"),
				@UniqueConstraint(columnNames = "persistence_id")
		},
		indexes = {
				@Index(name = "note_idx_domain_id", columnList = "domain_id"),
				@Index(name = "note_idx_persistence_id", columnList = "persistence_id")
		})
public class NoteDomainPersistenceAdapterModel extends AbstractDomainPersistenceAdapterModel<Long, UUID>
		implements DomainPersistenceAdapterModel<Long, UUID>
{
	public static DomainPersistenceAdapterModel.Builder<Long, UUID, NoteDomainPersistenceAdapterModel> builder()
	{
		return new Builder();
	}

	protected NoteDomainPersistenceAdapterModel()
	{
		super();
	}

	private static final class Builder extends AbstractBuilder<Long, UUID, NoteDomainPersistenceAdapterModel>
			implements DomainPersistenceAdapterModel.Builder<Long, UUID, NoteDomainPersistenceAdapterModel>,
			de.gupta.clean.crud.template.domain.model.builder.ModelBuilder<NoteDomainPersistenceAdapterModel>
	{
		@Override
		protected NoteDomainPersistenceAdapterModel doBuild()
		{
			return (NoteDomainPersistenceAdapterModel) model;
		}

		private Builder()
		{
			super(new NoteDomainPersistenceAdapterModel());
		}
	}
}