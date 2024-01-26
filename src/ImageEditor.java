// Author: Jay Sridharan

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.gui.GraphicsController;
import controller.IController;
import controller.text.TextController;
import model.IImageDB;
import model.ImageDataBase;
import view.gui.GUIView;
import view.IView;
import view.text.TextView;

/**
 * Executable program for HW 10.
 */
public class ImageEditor {

  /**
   * Main function for executable.
   * @param args passed arguments when executable is run.
   */
  public static void main(String []args) {
    IImageDB model = new ImageDataBase();
    IController controller;

    // Check if user has provided additional arguments at time of execution.
    if (args.length > 0) {
      Readable in;
      String flag = args[0];
      switch (flag) {
        // If user has provided a script file, initialize Readable using file contents.
        case "-file":
          try {
            in = new FileReader(args[1]);
            break;
          } catch (IOException e) {
            // If unable to read file in, throw exception.
            throw new IllegalStateException("Unable to locate file at specified location.");
          }
        // Set Readable to read contents from command line.
        case "-text":
          in = new InputStreamReader(System.in);
          break;
        // If passed an undefined indicator flag, throw exception.
        default:
          throw new IllegalStateException(flag + " is not a recognized mode. Please try again.");
      }

      // Initialize view and controller.
      IView view = new TextView(model, System.out);
      controller = new TextController(model, in, view);
      // Start controller.
      controller.run();
    }

    // If no arguments were passed, initialize GUI and controller.
    else {
      GUIView newView = new GUIView();
      controller = new GraphicsController(model, newView);
      controller.run();
    }
  }
}
