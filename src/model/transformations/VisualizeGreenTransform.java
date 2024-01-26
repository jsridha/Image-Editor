package model.transformations;

import model.IImageState;

/**
 * Class that performs a greyscale shift for an Image object using the green channel.
 */
public class VisualizeGreenTransform extends AbsVisualizeTransform {

  /**
   * Constructor for Transformation Object.
   */
  public VisualizeGreenTransform() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  @Override
  protected int getParam(IImageState image, int i, int j) {
    return image.getGreenChannel(i, j);
  }
}
