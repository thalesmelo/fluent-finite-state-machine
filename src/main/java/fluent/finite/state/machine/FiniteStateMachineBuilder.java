package fluent.finite.state.machine;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class FiniteStateMachineBuilder {

	private State root;
	private Graph graph = new Graph();

	public FiniteStateMachine build() {
		if(graph.size()==0) {
			throw new IllegalStateException("FiniteStateMachine has no flow");
		}
		return new FiniteStateMachine(graph,root);
	}

	public void addFlow(State... states) {
		if (Objects.requireNonNull(states).length < 2) {
			throw new IllegalStateException("Flow has less than 2 elements");
		}

		validateSameInitialState(states);
		if (root == null) {
			root = states[0];
		}
		for (int i = 1; i < states.length; i++) {
			graph.addEdge(states[i - 1], states[i]);
		}
	}

	private void validateSameInitialState(State... states) {
		if (root != null && root.equals(states[0])) {
			throw new IllegalStateException("Flow does not have the initial state as " + root);
		}
	}

}
