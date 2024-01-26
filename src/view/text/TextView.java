package view.text;

import java.io.IOException;
import model.IImageDB;
import view.IView;

/**
 * A basic implementation of a GUIView object.
 * Renders passed String messages to an Appendable.
 */
public class TextView implements IView {

  private final IImageDB state;

  private final Appendable out;

  /**
   * Constructor for view object.
   *
   * @param state - read-only instance of an ImageDatabase
   * @param out - Appendable to write information to.
   */
  public TextView(IImageDB state, Appendable out) {

    if (state == null) {
      throw new IllegalArgumentException("Model not initialized, unable to render view.");
    }
    if (out == null) {
      throw new IllegalArgumentException("Appendable not initialized, unable to render view.");
    }

    this.state = state;
    this.out = out;
  }

  @Override
  public void renderImageList() throws IOException {
    this.out.append(this.state.toString());
    this.out.append("\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
