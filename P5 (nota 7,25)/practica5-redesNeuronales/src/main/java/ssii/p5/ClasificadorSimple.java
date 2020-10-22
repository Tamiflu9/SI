package ssii.p5;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.neural.data.NeuralData;
import org.encog.neural.data.NeuralDataPair;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

/*
 * Encog(tm) Java Examples v3.2
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-examples
 *
 * Copyright 2008-2013 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 *
 * Codigo adaptado por Jorge J. Gomez Sanz
 * Reusando código de Manuel Pascual López 
 *
 */
public class ClasificadorSimple {


	/**
	 * The neural network will learn these patterns.
	 */
	public static final double[][] RESULT = {
			// arriba, abajo, izquierda, derecha
			{ 1.0, 0.0, 0.0, 0.0 },
			{ 0.0, 1.0, 0.0, 0.0 },
			{ 0.0, 0.0, 1.0, 0.0 },
			{ 0.0, 0.0, 0.0, 1.0 },
			{ 1.0, 0.0, 0.0, 0.0 },
			{ 0.0, 1.0, 0.0, 0.0 },
			{ 0.0, 0.0, 1.0, 0.0 },
			{ 0.0, 0.0, 1.0, 0.0 },
			{ 0.0, 0.0, 0.0, 1.0 },
			{ 0.0, 0.0, 1.0, 0.0 },
			{ 0.0, 0.0, 1.0, 0.0 }};
	

	/**
	 * The neural network will be tested on these patterns, to see which of the last
	 * set they are the closest to.
	 */
	public static final double[][] INPUT = { 
			getImage("images/arriba.png"), 
			getImage("images/abajo.png"),
			getImage("images/izquierda.png"), 
			getImage("images/derecha.png"), 
			getImage("images/arriba1.png"), 
			getImage("images/abajo1.png"),
			getImage("images/izquierda1.png"), 
			getImage("images/izquierdaa.png"), 
			getImage("images/derecha1.png"),
			getImage("images/izquierda2.png"),
			getImage("images/izquierda3.png"),  
			};


	private static int imheight;

	private static double[] getImage(String path) {
		double[] b = null;
		imheight=0;
		try {
			BufferedImage bi = ImageIO.read(new File(path));
			int height = bi.getHeight();
			imheight=height;
			int width = bi.getWidth();
			b = new double[height*width];
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					if (bi.getRGB(j, i) != -1)
						b[i*height+j]=1.0;
					else
						b[i*height+j]=0.0;
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	public static void main(String[] args) {
		BasicNetwork network = crearEstructuraRed();
		NeuralDataSet trainingSet = entrenarRed(network);
		testNetworkwithTrainingExamples(network, trainingSet);
		// probar una imagen suelta no entrenada previamente para ver cómo funciona
		classifyOneImage(network,"images/izquierdaa.png");		
	}

	private  static String getStringFromArray(double array[]) {
		String s="";
		for (double d: array) {
			s=s+d+",";
		}
		return s;				
	}
	
	private  static String getStringImageFromArray(double array[]) {
		String s="";
		int counter=0;
		for (double d: array) {
			s=s+(int)d+"";
			counter++;
			if (counter%imheight==0)
				s=s+"\n";
		}
		return s;				
	}
	
	
	private static void testNetworkwithTrainingExamples(BasicNetwork network, NeuralDataSet trainingSet) {
		System.out.println("Neural Network Results:");
		for (MLDataPair pair : trainingSet) {
			final MLData output = network.compute(pair.getInput());
			System.out.println(getStringImageFromArray(pair.getInput().getData()) + 
					", computed="
					+ getStringFromArray(output.getData())
					+"\n" + ",ideal=" +
					getStringFromArray(pair.getIdeal().getData()));

		}
						
	}

	private static MLData classifyOneImage(BasicNetwork network, String imagePath) {
		MLData image=new org.encog.neural.data.basic.BasicNeuralData(getImage(imagePath));
		MLData output = network.compute(image);		
		System.out.println(getStringImageFromArray(image.getData()) + 
				", computed="
				+ getStringFromArray(output.getData()));
		return output;
	}
	
	

	private static NeuralDataSet entrenarRed(BasicNetwork network) {
		int lastlength=-1;
		int counter=0;
		for (double [] image:INPUT) {
			
			if (lastlength==-1)
					lastlength=image.length;
			if (lastlength!=image.length)
				throw new RuntimeException("La imagen "+counter+ " (siendo 0 la primera imagen) tiene un tamaño distinto al resto");
				counter++;
		}
		NeuralDataSet trainingSet = new BasicNeuralDataSet(INPUT, RESULT);
		final Train train = new ResilientPropagation(network, trainingSet);

		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while (train.getError() > 0.0001);
		return trainingSet;
	}

	private static BasicNetwork crearEstructuraRed() {
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, true, INPUT[0].length));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		//network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, RESULT[0].length));
		network.getStructure().finalizeStructure();
		network.reset();
		return network;
	}
}
