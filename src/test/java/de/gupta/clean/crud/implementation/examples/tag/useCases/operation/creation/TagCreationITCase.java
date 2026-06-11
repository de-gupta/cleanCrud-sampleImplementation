package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation.register.domain.RegisterTagCreation;
import de.gupta.clean.crud.template.domain.model.exceptions.security.AccessDeniedException;
import de.gupta.clean.crud.template.useCases.operation.creation.api.application.CreationApplicationController;
import de.gupta.clean.crud.template.useCases.operation.quarantine.application.service.QuarantineService;
import de.gupta.clean.crud.template.useCases.operation.quarantine.domain.model.CreationReplayInputs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tag Creation Tests")
class TagCreationITCase extends AbstractTagCreationITCase
{
	@Autowired
	@Qualifier("tagCreationApplicationController")
	private CreationApplicationController<Long, TagDomainModel> tagCreationApplicationController;

	@Autowired
	private QuarantineService<CreationReplayInputs> creationQuarantineService;

	@Test
	@Tag(FAST)
	@DisplayName("Should create a tag through an internal creation")
	void shouldCreateTagThroughInternalCreation() throws Exception
	{
		var tagName = uniqueTagName("tag-creation");

		var created = tagCreationApplicationController.createInternalCommand(
				new RegisterTagCreation(tagName)).createdOrThrow();
		var fetched = fetchTag(created.domainId());

		assertThat(created.model().name()).isEqualTo(tagName);
		assertThat(fetched.name()).isEqualTo(tagName);
		assertThat(tagJpaRepository.existsByName(tagName)).isTrue();
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should reserve managed tag names for authoritative external events")
	void shouldReserveManagedTagNamesForAuthoritativeExternalEvents() throws Exception
	{
		var managedName = "managed:" + uniqueTagName("registry");

		assertThatThrownBy(() -> tagCreationApplicationController.createUserIntent(
				new RegisterTagCreation(managedName)))
				.isInstanceOf(AccessDeniedException.class)
				.hasMessageContaining("managed tag names");

		var created = tagCreationApplicationController.createAuthoritativeExternalEvent(
				new RegisterTagCreation(managedName)).createdOrThrow();
		var fetched = fetchTag(created.domainId());

		assertThat(created.model().name()).isEqualTo(managedName);
		assertThat(fetched.name()).isEqualTo(managedName);
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should quarantine authoritative events outside the managed namespace")
	void shouldQuarantineAuthoritativeEventsOutsideManagedNamespace() throws Exception
	{
		var plainName = uniqueTagName("tag-plain");

		var result = tagCreationApplicationController.createAuthoritativeExternalEventWithResult(
				new RegisterTagCreation(plainName));

		assertThat(result.quarantined()).isTrue();
		assertThat(result.created()).isEmpty();
		assertThat(result.quarantineRequest()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().quarantineId()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().violations()).hasSize(1);
		assertThat(result.quarantineRequest().orElseThrow().violations().getFirst().message())
				.isEqualTo("Authoritative tag registration must target the managed namespace");
		var quarantineId = result.quarantineRequest().orElseThrow().quarantineId().orElseThrow();
		assertThat(creationQuarantineService.findById(quarantineId)).isPresent();
		assertThat(fetchCreationQuarantine(quarantineId.value())).contains(quarantineId.value())
		                                                         .contains("OPEN");
		assertThat(tagJpaRepository.existsByName(plainName)).isFalse();
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should replay a quarantined tag creation through the durable creation quarantine lane")
	void shouldReplayQuarantinedTagCreation()
	{
		var plainName = uniqueTagName("tag-replay");

		var result = tagCreationApplicationController.createAuthoritativeExternalEventWithResult(
				new RegisterTagCreation(plainName));
		var quarantineId = result.quarantineRequest().orElseThrow().quarantineId().orElseThrow();

		var replayed = creationQuarantineService.replay(quarantineId);

		assertThat(replayed.status()).hasToString("REPLAYED");
		assertThat(tagJpaRepository.existsByName(plainName)).isTrue();
	}
}
