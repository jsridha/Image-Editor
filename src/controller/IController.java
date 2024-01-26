//Author: Jay Sridharan

package controller;

/**
 * Interface representing a controller that
 * receives input from the user, parses it,
 * and calls commands from the model as appropriate.
 */
public interface IController {

  /**
   * Method to start the controller searching for input.
   *
   * @throws IllegalStateException if controller is unable to read input or transmit output
   */
  void run();
}
