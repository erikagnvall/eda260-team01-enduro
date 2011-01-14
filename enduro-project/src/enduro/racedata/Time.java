package enduro.racedata;

/**
 * Utility class for representing time
 */
public class Time {
	
	private int hour;
	private int min;
	private int sec;
	
	/**
	 * Creates a new Time object.
	 * @param hour The hour of a day. Should be in the interval 0-23.
	 * @param min The minute of an hour. Should be in the interval 0-59.
	 * @param sec The second of a minute. Should be in the interval 0-59.
	 */
	public Time(int hour, int min, int sec) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}
	
	/**
	 * Returns a string representation of this Time object.
	 * @return A string on the format 'hh.mm.ss'.
	 */
	public String toString() {
		return String.format("%02d.%02d.%02d", hour, min, sec);
	}
	
}
