package fluent.finite.state.machine;

@SuppressWarnings("rawtypes")
public class EnumState extends State<Enum> {

	private final Enum state;

	public EnumState(Enum state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return this.state.name();
	}

	@Override
	public Enum getValue() {
		return this.state;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(State obj) {
		if (this == obj)
			return -1;
		if (!(obj instanceof State))
			return -1;
		EnumState other = (EnumState) obj;
		return this.state.compareTo(other.state);
	}

}
