package model.transformations;

import model.IImageState;


/**
 * Class that performs a greyscale shift for an Image object using the red channel.
 */
public class VisualizeRedTransform extends AbsVisualizeTransform {

  /**
   * Constructor for Transformation Object.
   */
  public VisualizeRedTransform() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  @Override
  protected int getParam(IImageState image, int i, int j) {
    return image.getRedChannel(i, j);
  }
}
