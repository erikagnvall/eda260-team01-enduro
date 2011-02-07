package regressiontest;

import org.junit.runners.model.InitializationError;

import enduro.LapRaceSorter;
import enduro.MarathonSorter;
import enduro.Sorter;

public class PVGRunner extends org.junit.runners.BlockJUnit4ClassRunner {

	public static Sorter lapSorter;
	public static Sorter marathonSorter;
	public static boolean testSuccess = true;
	
	public PVGRunner(Class<?> klass) throws InitializationError {
		super(klass);
		testSuccess = true;
		
		if(klass.isAnnotationPresent(ListTest.class)) {
			ListTest t = klass.getAnnotation(ListTest.class);
			
			String[] runners = t.nameList();
			String[] startTimes = t.startList();
			String[] finishTimes = t.finishList();
			lapSorter = new LapRaceSorter();
			marathonSorter = new MarathonSorter();
			
			try {
				for(String runner: runners) {
					marathonSorter.readNameFile(ListTest.testLocation + runner);
					lapSorter.readNameFile(ListTest.testLocation + runner);
				}
				for(String start: startTimes) {
					marathonSorter.readStartFile(ListTest.testLocation + start);
					lapSorter.readStartFile(ListTest.testLocation + start);
				}
				for(String end: finishTimes) {
					marathonSorter.readFinishFile(ListTest.testLocation + end);
					lapSorter.readFinishFile(ListTest.testLocation + end);
				}
				
				lapSorter.createResultFile(t.resultLocation() + ".lap");
				marathonSorter.createResultFile(t.resultLocation() + ".mar");
			} catch(Exception E) {
				testSuccess = false;
			}
			
			
			// ListTest annotation is present - run test
		}
	}

}
