package regressiontest;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
public @interface ListTest {

	public static String testLocation = "acceptanceTest/result/";
	public static enum Sorter{lap, marathon};
	
	Sorter sorter() default Sorter.lap; //lap, marathon allowed
	String[] nameList() default {"17_unit/namnfil.txt"}; //list of input files
	String[] startList() default {"17_unit/starttider.txt"}; //list of input files
	String[] finishList() default {"17_unit/maltider1.txt", "17_unit/maltider2.txt"}; //list of input files
	String resultLocation() default "result.temp";
}
