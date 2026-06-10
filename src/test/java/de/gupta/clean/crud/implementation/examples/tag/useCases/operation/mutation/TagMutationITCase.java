package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.rename.domain.RenameTagMutation;
import de.gupta.clean.crud.template.domain.model.exceptions.security.AccessDeniedException;
import de.gupta.clean.crud.template.useCases.operation.mutation.api.application.MutationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.mutation.quarantine.application.MutationQuarantineService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tag Mutation Tests")
class TagMutationITCase
		extends de.gupta.clean.crud.implementation.examples.tag.useCases.operation.mutation.AbstractTagITCase
{
	@Autowired
	@Qualifier("tagMutationApplicationController")
	private MutationApplicationController<Long, TagDomainModel> tagMutationApplicationController;

	@Autowired
	private MutationQuarantineService mutationQuarantineService;

	@Test
	@Tag(FAST)
	@DisplayName("Should apply an internal rename mutation without going through the REST update path")
	void shouldApplyInternalRenameMutation() throws Exception
	{
		var createdTag = createTag(uniqueTagName("tag-internal"));
		var renamed = tagMutationApplicationController.mutateInternalCommand(
				createdTag.id(),
				new RenameTagMutation(uniqueTagName("tag-renamed"))).updatedOrThrow();
		var fetched = fetchTag(createdTag.id());

		assertThat(renamed.id()).isEqualTo(createdTag.id());
		assertThat(renamed.model().name()).isEqualTo(fetched.name());
		assertThat(fetched.name()).startsWith("tag-renamed");
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should reserve managed tag names for authoritative external events")
	void shouldReserveManagedNamesForAuthoritativeExternalEvents() throws Exception
	{
		var createdTag = createTag(uniqueTagName("tag-managed"));
		var managedName = "managed:" + uniqueTagName("registry");

		assertThatThrownBy(() -> tagMutationApplicationController.mutateUserIntent(
				createdTag.id(),
				new RenameTagMutation(managedName)))
				.isInstanceOf(AccessDeniedException.class)
				.hasMessageContaining("managed tag names");

		var updated = tagMutationApplicationController.mutateAuthoritativeExternalEvent(
				createdTag.id(),
				new RenameTagMutation(managedName)).updatedOrThrow();
		var fetched = fetchTag(createdTag.id());

		assertThat(updated.model().name()).isEqualTo(managedName);
		assertThat(fetched.name()).isEqualTo(managedName);
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should quarantine authoritative events that try to demote managed tags")
	void shouldQuarantineManagedNamespaceDemotion() throws Exception
	{
		var createdTag = createTag(uniqueTagName("tag-quarantine"));
		var managedName = "managed:" + uniqueTagName("registry");

		tagMutationApplicationController.mutateAuthoritativeExternalEvent(
				createdTag.id(),
				new RenameTagMutation(managedName));

		var result = tagMutationApplicationController.mutateAuthoritativeExternalEventWithResult(
				createdTag.id(),
				new RenameTagMutation(uniqueTagName("plain")));

		assertThat(result.quarantined()).isTrue();
		assertThat(result.quarantineRequest()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().quarantineId()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().violations()).hasSize(1);
		var quarantineId = result.quarantineRequest().orElseThrow().quarantineId().orElseThrow();
		assertThat(mutationQuarantineService.findById(quarantineId)).isPresent();
		assertThat(fetchMutationQuarantine(quarantineId.value())).contains(quarantineId.value())
		                                                         .contains("OPEN")
		                                                         .contains(
																		 "Managed namespace demotion requires operator review");

		var fetched = fetchTag(createdTag.id());
		assertThat(fetched.name()).isEqualTo(managedName);
	}
}
