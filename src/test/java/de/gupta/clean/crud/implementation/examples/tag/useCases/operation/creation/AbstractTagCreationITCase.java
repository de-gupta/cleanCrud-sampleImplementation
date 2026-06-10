package de.gupta.clean.crud.implementation.examples.tag.useCases.operation.creation;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.gupta.clean.crud.implementation.examples.setup.IntegrationTest;
import de.gupta.clean.crud.implementation.examples.tag.infrastructure.persistence.repository.TagJpaRepository;
import de.gupta.clean.crud.implementation.examples.tag.useCases.crud.common.dto.TagAPIModelResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@Transactional
@Rollback
abstract class AbstractTagCreationITCase
{
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected TagJpaRepository tagJpaRepository;

	protected TagAPIModelResponse fetchTag(final Long tagId) throws Exception
	{
		MvcResult result = mockMvc.perform(get("/tag/fetch/{id}", tagId))
		                          .andExpect(status().isOk())
		                          .andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), TagAPIModelResponse.class);
	}

	protected String fetchCreationQuarantine(final String quarantineId) throws Exception
	{
		MvcResult result = mockMvc.perform(get("/internal/creation-quarantines/{id}", quarantineId))
		                          .andExpect(status().isOk())
		                          .andReturn();

		return result.getResponse().getContentAsString();
	}

	protected String uniqueTagName(final String prefix)
	{
		return prefix + "-" + System.nanoTime();
	}
}
