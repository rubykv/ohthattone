package com.rubykv.ohthattone.nlp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

@Service
public class DocumentCategorizerService {

	public String getCategory(String input) throws IOException {
		File file = ResourceUtils.getFile("src/main/resources/nlp-model/en-trained-model.bin");
		InputStream in = new FileInputStream(file);
		DoccatModel m = new DoccatModel(in);
		DocumentCategorizerME myCategorizer = new DocumentCategorizerME(m);
		
		String[] inputText = input.split(" ");
		double[] outcomes = myCategorizer.categorize(inputText);
		String category = myCategorizer.getBestCategory(outcomes);
		return category;
	}
}
