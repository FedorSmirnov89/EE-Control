package at.uibk.dps.ee.control.agents;

import java.util.concurrent.ExecutorService;

import at.uibk.dps.ee.control.graph.GraphAccess;
import at.uibk.dps.ee.control.management.EnactmentState;
import net.sf.opendse.model.Task;

/**
 * The {@link AgentActivationExtraction} is responsible for the activation of
 * the {@link AgentExtraction}s to transmit the data generated by the tasks in
 * the finished queue.
 * 
 * @author Fedor Smirnov
 *
 */
public class AgentActivationExtraction extends AgentContinuous {

	protected final EnactmentState enactmentState;
	protected final ExecutorService executor;
	protected final GraphAccess graphAccess;
	protected final AgentFactoryExtraction agentFactory;

	public AgentActivationExtraction(EnactmentState enactmentState, ExecutorService executor, GraphAccess graphAccess,
			AgentFactoryExtraction agentFactory) {
		this.enactmentState = enactmentState;
		this.executor = executor;
		this.graphAccess = graphAccess;
		this.agentFactory = agentFactory;
	}

	@Override
	protected void repeatedTask() {
		try {
			// takes a finished task from the queue
			Task finishedFunction = enactmentState.takeFinishedTask();
			// finds all of its out edges and start an extraction agent for each of them
			graphAccess.getOutEdges(finishedFunction)
					.forEach(edgeTuple -> executor.submit(agentFactory.createAgentTransmission(edgeTuple)));
		} catch (InterruptedException e) {
			throw new IllegalStateException("Extraction activation agent interrupted.", e);
		}
	}
}
