package mountainhuts;

public class AltitudeRange {
	private int minValue;
	private int maxValue;

	public AltitudeRange(String range) {
		String[] values = range.split("-");
		minValue = Integer.parseInt(values[0]);
		maxValue = Integer.parseInt(values[1]);
	}

	public int getMinValue() {
		return minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	@Override
	public String toString() {
		return minValue + "-" + maxValue;
	}

}
