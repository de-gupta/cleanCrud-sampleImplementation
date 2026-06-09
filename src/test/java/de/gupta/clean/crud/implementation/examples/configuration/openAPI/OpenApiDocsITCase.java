package de.gupta.clean.crud.implementation.examples.configuration.openAPI;

import de.gupta.clean.crud.implementation.examples.ExampleApplication;
import de.gupta.clean.crud.implementation.examples.setup.PostgresTestContainerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
@ExtendWith(PostgresTestContainerExtension.class)
@ActiveProfiles({"it", "swagger"})
class OpenApiDocsITCase
{
	@Autowired
	private MockMvc mockMvc;

	@Test
	void exposesStablePathBasedOperationIdsInGeneratedApiDocs() throws Exception
	{
		mockMvc.perform(get("/v3/api-docs"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.paths['/note/delete/{id}'].delete.operationId").value("note.delete.id"))
		       .andExpect(jsonPath("$.paths['/note/delete/batch'].delete.operationId").value("note.delete.batch"))
		       .andExpect(jsonPath("$.paths['/task/fetch/{id}'].get.operationId").value("task.fetch.id"))
		       .andExpect(jsonPath("$.paths['/task/fetch/ids'].get.operationId").value("task.fetch.ids"))
		       .andExpect(jsonPath("$.paths['/task/update/{id}'].patch.operationId").value("task.update.id"))
		       .andExpect(jsonPath("$.paths['/task/update/{id}'].put.operationId").value("task.replace.id"));
	}
}
