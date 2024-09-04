package fluent.finite.state.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FiniteStateMachineTest {
    private FiniteStateMachineBuilder builder;

    @Test
    public void testFlowTransition() {
        builder = new FiniteStateMachineBuilder();
        builder.addFlow(new StringState("A"), new StringState("B"), new StringState("C"));

        FiniteStateMachine stateMachine = builder.build();
        stateMachine.printGraph();

        assertTrue(stateMachine.transitionTo(new StringState("B")), "The transition to the B state should return 'true'");
        assertEquals("B", stateMachine.getCurrentState().toString());

        assertTrue(stateMachine.transitionTo(new StringState("C")), "The transition to the C state should return 'true'");
        assertEquals("C", stateMachine.getCurrentState().toString());
    }

    @Test
    public void testSucessfulTransitionToInProgressState() {
        builder = new FiniteStateMachineBuilder();
        builder.addFlow(new StringState("OPEN"), new StringState("IN_PROGRESS"), new StringState("CLOSED"));

        FiniteStateMachine stateMachine = builder.build();
        stateMachine.printGraph();

        boolean transitionToPerformed = stateMachine.transitionTo(new StringState("IN_PROGRESS"), state -> !((StringState) state).getValue().equals("CLOSED"));
        assertTrue(transitionToPerformed, "The transition to the IN_PROGRESS state should return 'true'");
    }

    public void testFailedTransitionToOpenState() {
        builder = new FiniteStateMachineBuilder();
        builder.addFlow(new StringState("OPEN"), new StringState("IN_PROGRESS"), new StringState("CLOSED"));

        FiniteStateMachine stateMachine = builder.build();
        stateMachine.printGraph();

        boolean transitionToPerformed = stateMachine.transitionTo(new StringState("OPEN"), state -> !((StringState) state).getValue().equals("CLOSED"));
        assertFalse(transitionToPerformed, "The transition to the OPEN state should return 'false'");
    }
}
