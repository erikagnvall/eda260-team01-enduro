package regressiontest;

import org.junit.runners.model.InitializationError;

import enduro.LapRaceSorter;
import enduro.MarathonSorter;
import enduro.Sorter;

public class PVGRunner extends org.junit.runners.BlockJUnit4ClassRunner {

	public static Sorter testSorter;
	public static boolean testSuccess = true;
	
	public PVGRunner(Class<?> klass) throws InitializationError {
		super(klass);
		testSuccess = true;
		
		if(klass.isAnnotationPresent(ListTest.class)) {
			ListTest t = klass.getAnnotation(ListTest.class);
			
			String[] runners = t.nameList();
			String[] startTimes = t.startList();
			String[] finishTimes = t.finishList();
			switch(t.sorter()) {
			case lap:
				testSorter = new LapRaceSorter();
				break;
			case marathon:
				testSorter = new MarathonSorter();
				break;
			}
			try {
				for(String runner: runners) {
					testSorter.readNameFile(ListTest.testLocation + runner);
				}
				for(String start: startTimes) {
					testSorter.readStartFile(ListTest.testLocation + start);
				}
				for(String end: finishTimes) {
					testSorter.readFinishFile(ListTest.testLocation + end);
				}
				
				testSorter.createResultFile(t.resultLocation());
			} catch(Exception E) {
				testSuccess = false;
			}
			
			
			// ListTest annotation is present - run test
		}
	}

}
