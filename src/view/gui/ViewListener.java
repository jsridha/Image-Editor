package view.gui;

/**
 * Interface defining a listener object
 * for a IGUIView implementation.
 */
public interface ViewListener {

  /**
   * Handles a Load event emitted by the view. Renders image
   * into view and loads image into model
   *
   * @param filepath - filepath of image file to be read in.
   * @param imageID - name of image.
   */
  void handleLoadEvent(String filepath, String imageID);

  /**
   * Handles a Save event emitted by the
   * view. Saves currently rendered image.
   *
   * @param filepath - filepath of image file to be read in.
   */
  void handleSaveEvent(String filepath);

  /**
   * Handles a Brighten event emitted by
   * the view. Renders result and stores it
   * in model.
   *
   * @param value - value to brighten/darken by.
   * @param newImage - name of image.
   */
  void handleBrightenEvent(int value, String newImage);

  /**
   * Handles a Filter event emitted by
   * the view. Renders result and stores it
   * in model.
   *
   * @param type - Type of filter to apply.
   * @param newImage - name of image.
   */
  void handleFilterEvent(String type, String newImage);

  /**
   * Handles a LinearColor event emitted by
   * the view. Renders result and stores it
   * in model.
   *
   * @param type - Type of colormatrix to apply.
   * @param newImage - name of image.
   */
  void handleLinearColorEvent(String type, String newImage);

  /**
   * Handles a Visualize event emitted by
   * the view. Renders result and stores it
   * in model.
   *
   * @param param - parameter to visualize.
   * @param newImage - name of image.
   */
  void handleVisualizeEvent(String param, String newImage);

  /**
   * Handles a RenderNew event emitted by view.
   * Renders the image stored in model at specified key.
   *
   * @param imageID - name of image to be rendered.
   */
  void handleRenderEvent(String imageID);
}
