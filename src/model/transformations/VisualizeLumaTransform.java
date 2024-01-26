package model.transformations;

import java.util.Objects;

import model.IImageState;

/**
 * Class that performs a greyscale shift for an Image object using the luma parameter.
 */
public class VisualizeLumaTransform extends AbsVisualizeTransform {

  /**
   * Constructor for Transformation Object.
   */
  public VisualizeLumaTransform() {
    //Empty constructor included per JavaStyle auto-grader requirement.
  }

  @Override
  protected int getParam(IImageState image, int i, int j) {
    IImageState image1 = Objects.requireNonNull(image);

    int red = image1.getRedChannel(i, j);
    int blue = image1.getBlueChannel(i, j);
    int green = image1.getGreenChannel(i, j);

    return (int) ((0.2126 * red) + (0.7152 * green) + (0.0722 * blue));
  }
}
