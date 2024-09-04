package fluent.finite.state.machine;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public abstract class State<T> implements Comparable<State> {

	public abstract T getValue();

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof State))
			return false;
		State other = (State) obj;
		return Objects.equals(getValue(), other.getValue());
	}

}
