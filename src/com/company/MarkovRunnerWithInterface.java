package com.company;

import edu.duke.*;

public class MarkovRunnerWithInterface {

	// Method to compare the normal Markov Model with a more efficient version using a HashMap to store previous character combinations
	public void compareMethods(){
		FileResource fr = new FileResource("data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		int size = 1000;
		int seed = 42;

		long startTime1 = System.nanoTime();

		System.out.println("Model starts: " + System.nanoTime());
		MarkovModel mModel = new MarkovModel(2);
		runModel(mModel, st, size, seed);
		runModel(mModel, st, size, seed);
		runModel(mModel, st, size, seed);
		runModel(mModel, st, size, seed);
		runModel(mModel, st, size, seed);

		long startTime2 = System.nanoTime();

		EfficientMarkovModel em = new EfficientMarkovModel(2);
		em.setTraining(st, 2);
		runModel(em, st, size, seed);
		runModel(em, st, size, seed);
		runModel(em, st, size, seed);
		runModel(em, st, size, seed);
		runModel(em, st, size, seed);

		System.out.println("MarkovModel finished in: " + (startTime2 - startTime1) / 1000000000.00 + " seconds");
		System.out.println("EfficientMarkovModel with HashMap finished in: " + (System.nanoTime() - startTime2) / 1000000000.00 + " seconds");
	}

    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 1; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }

	// Method to run the model
	public void runMarkov() {
        FileResource fr = new FileResource("data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');

		int size = 200;
		int seed = 531;

        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);

		MarkovModel mThree = new MarkovModel(3);
		runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

		EfficientMarkovModel em = new EfficientMarkovModel(5);
		em.setTraining(st, 5);
		runModel(em, st, size, seed);
    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
}
