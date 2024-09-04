package fluent.finite.state.machine;

import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;

@SuppressWarnings("rawtypes")
public class FiniteStateMachine {

	private State currentState;
	private final Graph graph;
	private final State root;
	private final Semaphore semaphore;

	FiniteStateMachine(Graph graph, State root) {
		this.root = root;
		this.graph = graph;
		this.currentState = root;
		this.semaphore = new Semaphore(1,true);
	}

	public State getCurrentState() {
		return this.currentState;
	}
	public boolean transitionTo(State newState) {
		return transitionTo(newState, (Predicate[]) null);
	}

	public boolean transitionTo(State newState, Predicate... conditions) {
		Objects.requireNonNull(newState);
		try {
			semaphore.acquireUninterruptibly();
			if (graph.hasEdge(currentState, newState) && matchesConditions(conditions)) {
				currentState = newState;
				return true;
			}
		}finally {
			semaphore.release();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean matchesConditions(Predicate[] conditions) {
		if(conditions == null ){
			return true;
		}
        for (Predicate condition : conditions) {
            if (condition.test(currentState)) {
                continue;
            }
            return false;
        }
		return true;
	}

	public void printGraph() {
		this.graph.printGraph();
	}
	
	public void reset() {
		this.currentState = this.root;
	}

}
