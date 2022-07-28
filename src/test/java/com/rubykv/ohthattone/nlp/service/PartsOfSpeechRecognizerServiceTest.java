package com.rubykv.ohthattone.nlp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PartsOfSpeechRecognizerServiceTest {
	
	@InjectMocks
	PartsOfSpeechRecognizerService partsOfSpeechRecognizerService;
	
	@Test
	public void testFetchVerbAndAdjective() throws IOException {
		List<String> verbAndAdjs = partsOfSpeechRecognizerService.fetchVerbAndAdjective("Your Service is bad");
		assertTrue(verbAndAdjs.contains("is"));
		assertTrue(verbAndAdjs.contains("bad"));
		assertEquals(2, verbAndAdjs.size());
	}
	
	@Test
	public void testFetchVerbAndAdjectiveForNouns() throws IOException {
		List<String> verbAndAdjs = partsOfSpeechRecognizerService.fetchVerbAndAdjective("Your Cable Service");
		assertEquals(0, verbAndAdjs.size());
	}
	
	@Test
	public void testFetchVerbAndAdjectiveForAdVerb() throws IOException {
		List<String> verbAndAdjs = partsOfSpeechRecognizerService.fetchVerbAndAdjective("I'm not happy");
		assertTrue(verbAndAdjs.contains("not"));
		assertTrue(verbAndAdjs.contains("happy"));
		assertEquals(2, verbAndAdjs.size());
	}
}
