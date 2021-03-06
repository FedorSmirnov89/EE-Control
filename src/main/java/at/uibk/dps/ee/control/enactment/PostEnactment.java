package at.uibk.dps.ee.control.enactment;

import at.uibk.dps.ee.control.management.EnactmentQueues;
import net.sf.opendse.model.Task;

/**
 * Interface for the operations which are to be performed after the enactment
 * associated with a task has been completed (such as putting the task into a
 * queue of the {@link EnactmentQueues}).
 * 
 * @author Fedor Smirnov
 */
public interface PostEnactment {

  /**
   * Performs the required post enactment operation for the given task.
   * 
   * @param enactedTask the task which was just enacted.
   */
  void postEnactmentTreatment(Task enactedTask);
}
