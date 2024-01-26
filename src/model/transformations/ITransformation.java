package model.transformations;

import model.IImageState;

/**
 * Interface to represent transformations performed on an Image.
 */
public interface ITransformation {

  /**
   * Performs the relevant transformation on the passed IImageState object.
   *
   * @param image the image to be modified.
   * @return a modified version of the passed image. Does not mutate the original.
   */
  IImageState run(IImageState image);

}
