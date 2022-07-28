package com.rubykv.ohthattone.nlp.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.util.ResourceUtils;

import opennlp.tools.doccat.BagOfWordsFeatureGenerator;
import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.doccat.NGramFeatureGenerator;
import opennlp.tools.ml.EventTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class TrainingModelGenerator {

	public static void main(String[] args) throws IOException {
		generateModel();
	}

	public static void generateModel() throws IOException {

		File file = ResourceUtils.getFile("src/main/resources/training-data.txt");
		InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(file);

		ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
		ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

		int minNgramSize = 1;
		int maxNgramSize = 6;
		DoccatFactory customFactory = new DoccatFactory(new FeatureGenerator[] { new BagOfWordsFeatureGenerator(),
				new NGramFeatureGenerator(minNgramSize, maxNgramSize) });
		TrainingParameters mlParams = new TrainingParameters();
		mlParams.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");
		mlParams.put(TrainingParameters.TRAINER_TYPE_PARAM, EventTrainer.EVENT_VALUE);
		mlParams.put(TrainingParameters.ITERATIONS_PARAM, 10);
		mlParams.put(TrainingParameters.CUTOFF_PARAM, 3);

		DoccatModel model = DocumentCategorizerME.train("en", sampleStream, mlParams, customFactory);

		try (OutputStream modelOut = new BufferedOutputStream(
				new FileOutputStream("src/main/resources/nlp-model/en-trained-model.bin"))) {
			model.serialize(modelOut);
		}
	}
}
