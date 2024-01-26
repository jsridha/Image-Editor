package controller.text.commands;

import java.util.Objects;
import java.util.Scanner;

import model.IImageDB;
import model.IImageState;
import model.transformations.ColorTransform;
import model.transformations.ITransformation;
import view.IView;

/**
 * Requests image to transform and name to store result under.
 */
public class LinearColor implements ICommand {

  private double[][] colormatrix;

  /**
   * Constructs a LinearColor object. Based on which type of color
   * transformation is being applied, sets colormatrix field.
   *
   * @param type - String containing the type of filter being applied.
   */
  public LinearColor(String type) {
    if (type.equalsIgnoreCase("greyscale")) {
      this.colormatrix = new double[][] {{(0.2126), (0.7152), (0.0722)},
                                         {(0.2126), (0.7152), (0.0722)},
                                         {(0.2126), (0.7152), (0.0722)}};
    }

    else if (type.equalsIgnoreCase("sepia")) {
      this.colormatrix = new double[][] {{(0.393), (0.769), (0.189)},
                                         {(0.349), (0.686), (0.168)},
                                         {(0.272), (0.7152), (0.131)}};
    }
  }

  @Override
  public void run(Scanner scan, IImageDB model, IView view) throws IllegalStateException {
    Objects.requireNonNull(scan);
    IImageDB model1 = Objects.requireNonNull(model);
    Objects.requireNonNull(view);

    if (!scan.hasNext()) {
      throw new IllegalStateException("Second argument must be the "
              + "ID of the image to be modified.");
    }
    String idImage = scan.next();

    if (!scan.hasNext()) {
      throw new IllegalStateException("Third argument must be the ID for the resulting image.");
    }
    String newId = scan.next();

    IImageState sourceImage = model1.getImage(idImage);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    ITransformation filter = new ColorTransform(this.colormatrix);
    IImageState visualizedImage = filter.run(sourceImage);

    model1.loadImage(newId, visualizedImage);
  }
}
