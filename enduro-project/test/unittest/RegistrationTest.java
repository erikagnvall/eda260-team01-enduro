package unittest;

import enduro.Registration;
import enduro.racedata.Time;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

public class RegistrationTest {

	private Registration reg;

	@Before
	public void setUp() {
		try {
			reg = new Registration(
					"./test/unittest/unit-test-files/registrationtest.txt");
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	@Test
	public void testWriteStartTime() {
		reg.registerTime(1, new Time(12, 00, 42));
		reg.close();

		try {
			BufferedReader in = new BufferedReader(
					new FileReader("./test/unittest/unit-test-files/"
							+ "registrationtest.txt"));
			assertEquals("1; 12.00.42", in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
