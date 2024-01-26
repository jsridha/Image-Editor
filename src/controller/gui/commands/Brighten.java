package controller.gui.commands;

import java.util.Objects;

import model.IImageDB;
import model.IImageState;
import model.transformations.BrightenTransform;
import model.transformations.ITransformation;

/**
 * Graphical command pattern for requesting user
 * input to perform a brightening/darkening
 * transformation on an image currently in the model.
 */
public class Brighten implements ICommand {

  private final int value;

  public Brighten( int value) {
    this.value = value;
  }

  @Override
  public void run(IImageDB model, String imageID, String newImage) throws IllegalStateException {
    Objects.requireNonNull(model);
    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation brighten = new BrightenTransform(this.value);
    IImageState brightenedImage = brighten.run(sourceImage);

    model.loadImage(newImage, brightenedImage);
  }
}
