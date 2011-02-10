package unittest.misc;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import enduro.IOHandler;

public class FileHandlingTest {
/*
	@Test
	public void testSingleFinishFile() {
		PrintWriter out = null;
		try {
			new File("list.txt").delete();
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"list.txt")));
			out.println("acceptanceTest/result/17_unit/namnfil.txt");
			out.println("acceptanceTest/result/17_unit/starttider.txt");
			out.println("acceptanceTest/result/17_unit/maltider1.txt");
			out.println("acceptanceTest/result/17_unit/maltider2.txt");
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			IOHandler.main(null);

		} catch (Exception e) {
			fail("Failed to write to file");
			e.printStackTrace();
		}
	}
*/
	@Test
	public void testMultipleFinishFiles() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"list.txt")));
			out.println("acceptanceTest/result/17_unit/namnfil.txt");
			out.println("acceptanceTest/result/17_unit/starttider.txt");
			out.println("acceptanceTest/result/17_unit/maltider1.txt");
			out.println("acceptanceTest/result/17_unit/maltider2.txt");
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			IOHandler.main(null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
