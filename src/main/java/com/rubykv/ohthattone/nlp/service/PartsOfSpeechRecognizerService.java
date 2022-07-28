package com.rubykv.ohthattone.nlp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

@Service
public class PartsOfSpeechRecognizerService {

	private static final String ADVERB = "_RB";
	private static final String VERB = "_VB";
	private static final String ADJECTIVE = "_JJ";

	public List<String> fetchVerbAndAdjective(String input) throws IOException {
		File file = ResourceUtils.getFile("src/main/resources/nlp-model/en-pos-maxent.bin");
		InputStream in = new FileInputStream(file);
		POSModel model = new POSModel(in);
		POSTaggerME tagger = new POSTaggerME(model);
		WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
		String[] tokens = whitespaceTokenizer.tokenize(input);
		String[] tags = tagger.tag(tokens);
		POSSample posSample = new POSSample(tokens, tags);

		List<String> verbsAndAdj = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(posSample.toString());
		while (tokenizer.hasMoreTokens()) {
			String temp = tokenizer.nextToken();
			if (temp.contains(ADJECTIVE) || temp.contains(VERB) || temp.contains(ADVERB)) {
				verbsAndAdj.add(temp.split("_")[0]);
			}
		}
		return verbsAndAdj;
	}
}
