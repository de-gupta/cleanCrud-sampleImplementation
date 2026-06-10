package de.gupta.clean.crud.implementation.examples.tag.useCases.incantation;

import de.gupta.clean.crud.implementation.examples.tag.domain.model.TagDomainModel;
import de.gupta.clean.crud.implementation.examples.tag.useCases.incantation.register.domain.RegisterTagIncantation;
import de.gupta.clean.crud.template.domain.model.exceptions.security.AccessDeniedException;
import de.gupta.clean.crud.template.useCases.incantation.api.application.IncantationApplicationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static de.gupta.clean.crud.implementation.examples.setup.TestTags.FAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Tag Incantation Tests")
class TagIncantationITCase extends AbstractTagIncantationITCase
{
	@Autowired
	@Qualifier("tagIncantationApplicationController")
	private IncantationApplicationController<Long, TagDomainModel> tagIncantationApplicationController;

	@Test
	@Tag(FAST)
	@DisplayName("Should create a tag through an internal incantation")
	void shouldCreateTagThroughInternalIncantation() throws Exception
	{
		var tagName = uniqueTagName("tag-incantation");

		var created = tagIncantationApplicationController.invokeInternalCommand(
				new RegisterTagIncantation(tagName));
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

		assertThatThrownBy(() -> tagIncantationApplicationController.invokeUserIntent(
				new RegisterTagIncantation(managedName)))
				.isInstanceOf(AccessDeniedException.class)
				.hasMessageContaining("managed tag names");

		var created = tagIncantationApplicationController.invokeAuthoritativeExternalEvent(
				new RegisterTagIncantation(managedName));
		var fetched = fetchTag(created.domainId());

		assertThat(created.model().name()).isEqualTo(managedName);
		assertThat(fetched.name()).isEqualTo(managedName);
	}

	@Test
	@Tag(FAST)
	@DisplayName("Should quarantine authoritative events outside the managed namespace")
	void shouldQuarantineAuthoritativeEventsOutsideManagedNamespace()
	{
		var plainName = uniqueTagName("tag-plain");

		var result = tagIncantationApplicationController.invokeAuthoritativeExternalEventWithResult(
				new RegisterTagIncantation(plainName));

		assertThat(result.quarantined()).isTrue();
		assertThat(result.created()).isEmpty();
		assertThat(result.quarantineRequest()).isPresent();
		assertThat(result.quarantineRequest().orElseThrow().violations()).hasSize(1);
		assertThat(result.quarantineRequest().orElseThrow().violations().getFirst().message())
				.isEqualTo("Authoritative tag registration must target the managed namespace");
		assertThat(tagJpaRepository.existsByName(plainName)).isFalse();
	}
}
