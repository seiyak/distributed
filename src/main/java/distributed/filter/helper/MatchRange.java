package distributed.filter.helper;

public class MatchRange {

	private final int start;
	private final int end;

	public MatchRange(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

}
