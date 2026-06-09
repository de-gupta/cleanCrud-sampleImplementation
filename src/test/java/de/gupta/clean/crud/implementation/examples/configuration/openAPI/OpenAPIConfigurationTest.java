package de.gupta.clean.crud.implementation.examples.configuration.openAPI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenAPIConfigurationTest
{
	@Test
	void rewritesCrudOperationIdsFromConcretePaths()
	{
		final var openAPI = new OpenAPI().paths(new Paths()
				.addPathItem("/note/delete/{id}", new PathItem().delete(new Operation().operationId("deleteById_4")))
				.addPathItem("/note/delete/batch",
						new PathItem().delete(new Operation().operationId("deleteAllById_4")))
				.addPathItem("/task/fetch/{id}", new PathItem().get(new Operation().operationId("findById_3")))
				.addPathItem("/task/fetch", new PathItem().get(new Operation().operationId("findAll_3")))
				.addPathItem("/task/update/{id}", new PathItem()
						.patch(new Operation().operationId("updateById_3"))
						.put(new Operation().operationId("putAtId_3"))));

		new OpenAPIConfiguration().customizeOperationIds().customise(openAPI);

		assertEquals("note.delete.id", openAPI.getPaths().get("/note/delete/{id}").getDelete().getOperationId());
		assertEquals("note.delete.batch", openAPI.getPaths().get("/note/delete/batch").getDelete().getOperationId());
		assertEquals("task.fetch.id", openAPI.getPaths().get("/task/fetch/{id}").getGet().getOperationId());
		assertEquals("task.fetch", openAPI.getPaths().get("/task/fetch").getGet().getOperationId());
		assertEquals("task.update.id", openAPI.getPaths().get("/task/update/{id}").getPatch().getOperationId());
		assertEquals("task.replace.id", openAPI.getPaths().get("/task/update/{id}").getPut().getOperationId());
	}
}