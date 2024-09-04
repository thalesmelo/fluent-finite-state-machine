package fluent.finite.state.machine;

public class StringState extends State<String> {

	private final String state;

	public StringState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return this.state;
	}

	@Override
	public String getValue() {
		return this.state;
	}

	@Override
	public int compareTo(@SuppressWarnings("rawtypes") State obj) {
		if (this == obj)
			return -1;
		if (!(obj instanceof State))
			return -1;
		StringState other = (StringState) obj;
		return this.state.compareTo(other.state);
	}

}
