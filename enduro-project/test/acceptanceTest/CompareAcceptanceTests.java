package acceptanceTest;

import junit.framework.TestCase;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Parameterized;


/**
 * naming convention: 
 *  - facit can be named in any way.
 *  - result files must be named after corresponding facit - facit.result
 * 
 *  results and facit must be in explicitly different locations
 *   
 * @author alexander, mohamed
 *
 */
public class CompareAcceptanceTests extends TestCase  {
	public static void main(String... args) {
		junit.textui.TestRunner.run(CompareAcceptanceTests.class);
	}
	
	@Test public void testJUnit() {
		try {
			//BlockJUnit4ClassRunner runner = new BlockJUnit4ClassRunner(JUnitAcceptanceTest.class);
			Parameterized runner = new Parameterized(JUnitAcceptanceTest.class);
			RunNotifier runnote = new RunNotifier();
			Listener listen = new Listener();
			runnote.addListener(listen);
			runner.run(runnote);
			assertTrue(!listen.failure);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			assertTrue(false);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			assertTrue(false);
		}
	}
	
}

class Listener extends RunListener {
	public boolean failure=false;
	
	public void testFailure(Failure failure) {
		this.failure=true;
	}
}