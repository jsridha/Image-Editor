package controller.gui.commands;

import model.IImageDB;
import model.IImageState;
import model.transformations.IOUtil;

/**
 *
 */
public class Save implements IOCommand {

  @Override
  public void run(IImageDB model, String filepath, String imageID) throws IllegalStateException {
    // Check to make sure filepath and imageID have been passed.
    if (filepath.isEmpty()) {
      throw new IllegalStateException("Must provide filepath of where to save image!");
    }
    if (imageID.isEmpty()) {
      throw new IllegalStateException("Must provide the name of the image to be saved!");
    }

    String[] components = filepath.split("\\.");
    // Extension should always be last thing following a period in filepath.
    String extension = components[components.length - 1];

    IImageState sourceImage = model.getImage(imageID);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified ID does not exist.");
    }

    // Save image to location specified.
    if (extension.equals("ppm")) {
      IOUtil.writeToPPM(filepath, sourceImage);
    } else {
      IOUtil.writeNonPPM(filepath, sourceImage, extension);
    }
  }
}
