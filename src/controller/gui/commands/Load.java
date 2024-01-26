package controller.gui.commands;

import model.IImageDB;
import model.IImageState;
import model.transformations.IOUtil;

public class Load implements IOCommand {
  @Override
  public void run(IImageDB model, String filepath, String imageID) throws IllegalStateException {
    // Check to make sure a non-empty filepath and imageID have been passed.
    if (filepath.isEmpty()) {
      throw new IllegalStateException("Must provide filepath for image to load in!");
    } if (imageID.isEmpty()) {
      throw new IllegalStateException("Must provide a name for loaded image!");
    }

    // Extract extension from the filepath in order
    // to determine what type of file is being loaded.
    String[] components = filepath.split("\\.");
    // Extension should always be last thing following a period in filepath.
    String extension = components[components.length - 1];
    IImageState sourceImage;

    // Read in image from file, then load image into database.
    if (extension.equals("ppm")) {
      sourceImage = IOUtil.readPPM(filepath);
    } else {
      sourceImage = IOUtil.readNonPPM(filepath);
    }

    model.loadImage(imageID, sourceImage);
  }
}
