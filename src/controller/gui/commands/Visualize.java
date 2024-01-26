package controller.gui.commands;

import java.util.Objects;

import model.IImageDB;
import model.IImageState;
import model.transformations.ITransformation;
import model.transformations.VisualizeBlueTransform;
import model.transformations.VisualizeGreenTransform;
import model.transformations.VisualizeIntensityTransform;
import model.transformations.VisualizeLumaTransform;
import model.transformations.VisualizeRedTransform;
import model.transformations.VisualizeValueTransform;

public class Visualize implements ICommand {

  private final ITransformation visualize;

  /**
   * Constructor for Visualize command pattern. Initializes visualize
   * field with appropriate ITransformation based on parameter indicated.
   *
   * @param param parameter being visualized in transformation.
   */
  public Visualize(String param) {

    if (param.equalsIgnoreCase("red")) {
      this.visualize = new VisualizeRedTransform();
    } else if (param.equalsIgnoreCase("green")) {
      this.visualize = new VisualizeGreenTransform();
    } else if (param.equalsIgnoreCase("blue")) {
      this.visualize = new VisualizeBlueTransform();
    } else if (param.equalsIgnoreCase("luma")) {
      this.visualize = new VisualizeLumaTransform();
    } else if (param.equalsIgnoreCase("intensity")) {
      this.visualize = new VisualizeIntensityTransform();
    } else if (param.equalsIgnoreCase("value")) {
      this.visualize = new VisualizeValueTransform();
    } else {
      throw new IllegalStateException("Invalid parameter. Try again.");
    }
  }

  @Override
  public void run(IImageDB model, String imageID, String newImage) throws IllegalStateException {
    Objects.requireNonNull(model);

    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    // If such an image exists, run transformation and store result.
    else {
      IImageState visualizedImage = visualize.run(sourceImage);
      model.loadImage(newImage, visualizedImage);
    }
  }
}
