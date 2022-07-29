package com.rubykv.ohthattone.nlp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DocumentCategorizerServiceTest {

	@InjectMocks
	DocumentCategorizerService documentCategorizerService;
	
	@Test
	public void testGetCategory() throws IOException {
		//data - will not recommend
		String category = documentCategorizerService.getCategory("I will not recommend this product");
		assertEquals("Angry", category);
	}
	
	@Test
	public void testGetCategoryForPolitics() throws IOException {
		//data - Deputy Prime Minister
		String category = documentCategorizerService.getCategory("went for a walk");
		assertEquals("Neutral", category);
	}
	
	@Test
	public void testGetCategoryForAngry() throws IOException {
		//data - will not recommend
		String category = documentCategorizerService.getCategory("It's so bad");
		assertEquals("Angry", category);
	}
}
