package com.rubykv.ohthattone.nlp.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenizerServiceTest {

	@InjectMocks
	TokenizerService tokenizerService;

	@Test
	public void testIsAngryWithAngryText() {
		assertTrue(tokenizerService.isAngry("Your Service is bad"));
	}
	
	@Test
	public void testIsAngryWithHappyText() {
		assertFalse(tokenizerService.isAngry("Your Service is good"));
	}
}
