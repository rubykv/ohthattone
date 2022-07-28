package com.rubykv.ohthattone.nlp.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import opennlp.tools.tokenize.SimpleTokenizer;

@Service
public class TokenizerService {
	SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
	String[] angerTokens = { "bad", "complaint", "complain", "unhappy", "discriminated", "refund", "quit" };

	public boolean isAngry(String input) {
		boolean isAngry = false;
		String tokens[] = tokenizer.tokenize(input);

		for (String token : tokens) {
			isAngry = Arrays.stream(angerTokens).anyMatch(token::equals);
		}
		return isAngry;
	}
}
