package ssii.p5;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

public class RedANDBasica {

	public static double AND_IDEAL[][] = {{ 0.0 },{ 0.0 },{ 0.0 },{ 1.0 } };
	public static double AND_INPUT[][] = {{ 0.0, 0.0 },{ 1.0, 0.0 },{ 0.0, 1.0 },{ 1.0, 1.0 } };

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
		NeuralDataSet trainingSet = new
				BasicNeuralDataSet(AND_INPUT, AND_IDEAL);
		final Train train = new ResilientPropagation(network,
				trainingSet);

		int epoch = 1;
		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:"
					+ train.getError());
			epoch++;
		} while(train.getError() > 0.0001);
		return trainingSet;
	}

	private static BasicNetwork crearEstructuraRed() {
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, true,2));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 4));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 6));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false,1));
		network.getStructure().finalizeStructure();
		network.reset();
		return network;
	}
}
