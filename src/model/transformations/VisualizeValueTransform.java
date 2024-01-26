package model.transformations;

import java.util.Objects;
import model.IImageState;

/**
 * Class that performs a greyscale shift for an Image object using the Value parameter.
 */
public class VisualizeValueTransform extends AbsVisualizeTransform {

  /**
   * Constructor for Transformation Object.
   */
  public VisualizeValueTransform() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  @Override
  protected int getParam(IImageState image, int i, int j) {
    IImageState image1 = Objects.requireNonNull(image);

    int red = image1.getRedChannel(i, j);
    int blue = image1.getBlueChannel(i, j);
    int green = image1.getGreenChannel(i, j);

    return Math.max(Math.max(red, blue), green);
  }
}
