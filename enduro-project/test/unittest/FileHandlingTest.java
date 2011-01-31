package unittest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

import enduro.IOHandler;

public class FileHandlingTest {

	@Test
	public void testSingleFinishFile() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"list.txt")));
			out.println("./test/unittest/unit-test-files/fakeName.txt");
			out.println("./test/unittest/unit-test-files/fakeStart.txt");
			out.println("./test/unittest/unit-test-files/fakeFinish.txt");
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			IOHandler.main(null);
			BufferedReader in = new BufferedReader(new FileReader(
					"./test/unittest/unit-test-files/ResultFile.txt"));
			in.readLine();

		} catch (Exception e) {
			fail("Failed to write to file");
			e.printStackTrace();
		}
	}

	@Test
	public void testMultipleFinishFiles() {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"list.txt")));
			out.println("./test/unittest/unit-test-files/fakeName.txt");
			out.println("./test/unittest/unit-test-files/fakeStart.txt");
			out.println("./test/unittest/unit-test-files/fakeFinish (copy).txt");
			out.println("./test/unittest/unit-test-files/fakeFinish (another copy).txt");
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
