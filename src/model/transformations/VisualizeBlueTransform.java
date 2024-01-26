package model.transformations;

import model.IImageState;

/**
 * Class that performs a greyscale shift for an Image object using the blue channel.
 */
public class VisualizeBlueTransform extends AbsVisualizeTransform {

  /**
   * Empty constructor.
   */
  public VisualizeBlueTransform() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  @Override
  protected int getParam(IImageState image, int i, int j) {
    return image.getBlueChannel(i, j);
  }
}
