package exponentiation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExpGenerator {

	public static void main (String [] args) {
		
		File output = new File(args[1]);
		FileWriter fw;
		
		try {
			fw = new FileWriter(output);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		//TODO: check if Java 10 allows public final
		final int maxExponent = 1000;

		Graph expGraph = new Graph();

		expGraph.addEdge("0", "1", 0);

		for(int i=1; i<maxExponent/2; i++) {
			String i1 = "" + (i+1);
			//System.out.println(i1);
			String i2 = "" + (i*2);
			expGraph.addEdge(""+i, i1, i);
			expGraph.addEdge(""+i, i2, (i*(1+(Math.log(i)/Math.log(2)))));
		}

		for(int i=maxExponent/2; i<maxExponent; i++) {
			String i1 = "" + (i+1);
			expGraph.addEdge(""+i, i1, i);		
		}
		
		expGraph.dijkstra("0");
		
		for(int i=0; i<=1000; i++) {
			//System.out.println("i");
			//TODO: printPath returns void!
			fw.write(expGraph.printPath(""+i));
		}
	}
}
