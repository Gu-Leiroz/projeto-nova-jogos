package br.com.nova.jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nova.jogos.data.vo.v1.PostagemVO;
import br.com.nova.jogos.service.PostagemService;
import br.com.nova.jogos.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/postagem/v1")
@CrossOrigin("*")
@Tag(name = "Posts", description = "Endpoints para os posts dos jogos")
public class PostagemController {

	@Autowired
	PostagemService service;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
				 consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Cria os Posts", description = "Cria os Posts",
			tags = {"Posts"}, 
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = 
							@Content(schema = @Schema(implementation = PostagemVO.class))
							
			),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unautherized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)

	public PostagemVO create(@RequestBody PostagemVO postagem) {
		return service.create(postagem);
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Acha o Post por Id", description = "Acha o Post por Id",
	tags = {"Posts"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = 
					@Content(schema = @Schema(implementation = PostagemVO.class))
					
	),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unautherized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
)
	public PostagemVO findById(@PathVariable(value = "id") Long id) {
		return service.findByid(id);
	}
	

	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Puxa lista dos Posts", description = "Puxa lista das Posts",
	tags = {"Posts"}, 
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = {
					@Content(
						mediaType = "application/json",
						array = @ArraySchema(schema = @Schema(implementation = PostagemVO.class))
					)
	}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Unautherized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
)
	public ResponseEntity<PagedModel<EntityModel<PostagemVO>>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction)
				? Direction.DESC : Direction.ASC;
		
		Pageable pagable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
		return ResponseEntity.ok(service.findAll(pagable));
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
				consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Atualiza os Posts", description = "Atualiza os Posts",
			tags = {"Posts"}, 
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = 
							@Content(schema = @Schema(implementation = PostagemVO.class))
							
			),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unautherized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)

	public PostagemVO update(@RequestBody PostagemVO postagem) {

		return service.update(postagem);
	}

	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Deleta os Posts pelo Id", description = "Deleta os Posts pelo Id",
			tags = {"Posts"}, 
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unautherized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
		)

	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.ok("Postagem com o id " + id + " foi excluída com sucesso.");

	}

}
