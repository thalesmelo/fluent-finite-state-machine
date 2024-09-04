package fluent.finite.state.machine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class Graph {

	// LinkedHashMap will keep the order of the inserted nodes
	private Map<State, Set<State>> verteces = new LinkedHashMap<>();

	public Graph() {
	}

	public synchronized void addEdge(State stateA, State stateB) {
		addVertex(stateA);
		addVertex(stateB);
		this.verteces.get(stateA).add(stateB);
	}

	private void addVertex(State state) {
		if (!verteces.containsKey(state)) {
			verteces.put(state, new TreeSet<State>());
		}
	}

	// Function to print adjacency list representation of a graph
	public void printGraph() {
		for (Entry<State, Set<State>> vertex : verteces.entrySet()) {
			for (State edge : vertex.getValue()) {
				System.out.printf("%s ——> %s\t", vertex.getKey(), edge);
			}
		}
		System.out.println();
	}

	public int size() {
		return verteces.size();
	}

	public boolean hasEdge(State currentState, State newState) {
		Set<State> currentStateEdges = verteces.get(currentState);
		return currentState != null && currentStateEdges.contains(newState);
	}

}
