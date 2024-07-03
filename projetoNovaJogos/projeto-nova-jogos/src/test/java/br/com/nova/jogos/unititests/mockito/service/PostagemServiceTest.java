package br.com.nova.jogos.unititests.mockito.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nova.jogos.service.PostagemService;
import br.com.nova.jogos.unittests.mapper.MockPosts;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PostagemServiceTest {

	MockPosts input;
	
	@InjectMocks
	private PostagemService service;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPosts();
		MockitoAnnotations.openMocks(this);
	
	}

	@Test
	void testFindByid() {
		
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
