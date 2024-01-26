package view;

import java.io.IOException;

/**
 * Interface containing methods required to write to view.
 */
public interface IView {

  /**
   * Renders a list of the Images in the current model.
   * @throws IOException if transmission of the list to the provided data destination fails
   */
  void renderImageList() throws IOException;

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
