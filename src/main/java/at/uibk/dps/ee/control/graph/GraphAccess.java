package at.uibk.dps.ee.control.graph;

import java.util.Set;
import java.util.function.BiConsumer;

import net.sf.opendse.model.Dependency;
import net.sf.opendse.model.Task;

/**
 * Interface for the classes offering a thread-safe access to the enactment
 * graph.
 * 
 * @author Fedor Smirnov
 *
 */
public interface GraphAccess {

	public class EdgeTupleAppl {
		protected final Task src;
		protected final Task dst;
		protected final Dependency edge;

		public EdgeTupleAppl(Task src, Task dst, Dependency edge) {
			super();
			this.src = src;
			this.dst = dst;
			this.edge = edge;
		}

		public Task getSrc() {
			return src;
		}

		public Task getDst() {
			return dst;
		}

		public Dependency getEdge() {
			return edge;
		}

	}

	/**
	 * Returns the edgeTuples (end nodes + edge) for all out edges of the given node
	 * 
	 * @param node the given node
	 * @return the edgeTuples (end nodes + edge) for all out edges of the given node
	 */
	Set<EdgeTupleAppl> getOutEdges(Task node);

	/**
	 * Performs the given write operation on the in edges of the given node.
	 * 
	 * @param writeOperation the operation to perform
	 * @param node           the function node whose in edges the operation is
	 *                       performed on
	 */
	void writeOperationNodeInEdges(BiConsumer<Set<Dependency>, Task> writeOperation, Task node);

}
