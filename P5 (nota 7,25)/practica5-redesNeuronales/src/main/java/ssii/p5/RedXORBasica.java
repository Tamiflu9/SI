package ssii.p5;

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
 *
 */
public class RedXORBasica 
{
	public static double XOR_IDEAL[][] = {{ 0.0 },{ 1.0 },{ 1.0 },{ 0.0 } };
	public static double XOR_INPUT[][] = {{ 0.0, 0.0 },{ 1.0, 0.0 },{ 0.0, 1.0 },{ 1.0, 1.0 } };

	public static void main( String[] args )
	{
		BasicNetwork network = crearEstructuraRed();
		NeuralDataSet trainingSet = entrenarRed(network);
		probarRed(network, trainingSet);
	}

	private static void probarRed(BasicNetwork network,
			NeuralDataSet trainingSet) {
		System.out.println("Neural Network Results:");
		for(MLDataPair pair: trainingSet ) {
			final MLData output = network.compute(pair.getInput());
			System.out.println(pair.getInput().getData(0) + "," + 
			pair.getInput().getData(1) + ", actual=" + output.getData(0) 
			+ ",ideal=" +pair.getIdeal().getData(0));

		}
	}

	private static NeuralDataSet entrenarRed(BasicNetwork network) {
		NeuralDataSet trainingSet = new BasicNeuralDataSet(XOR_INPUT, XOR_IDEAL);
		final Train train = new ResilientPropagation(network,
				trainingSet);

		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while(train.getError() > 0.0001);
		return trainingSet;
	}

	private static BasicNetwork crearEstructuraRed() {
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, true,2));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
		network.getStructure().finalizeStructure();
		network.reset();
		return network;
	}
}
