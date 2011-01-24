package acceptanceTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

import enduro.Sorter;

/**
 * naming convention: 
 *  - facit can be named in any way.
 *  - result files must be named after corresponding facit - facit.result
 * 
 *  results and facit must be in explicitly different locations
 *   
 * * @author alexander, mohamed
 *
 */
public class CompareAcceptanceTests {
	
	public static void main(String[] args) {
		
		Sorter sort = new Sorter();
		try {
			sort.readFinishFile("acceptanceTest/result/input_3/maltider.txt");
			sort.readStartFile("acceptanceTest/result/input_3/starttider.txt");
			sort.readNameFile("acceptanceTest/result/input_3/namnfil.txt");
			sort.createResultFile("acceptanceTest/result/resultat_6.txt.result");
		} catch(Exception E) {
			E.printStackTrace();
		}
		/*
		CompareAcceptanceTests tests = new CompareAcceptanceTests("acceptanceTest/facit", "acceptanceTest/result");
		tests.testAllFacit();
		*/
		
		
	}
	
}