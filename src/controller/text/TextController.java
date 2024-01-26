//Author: Jay Sridharan

package controller.text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import controller.IController;
import controller.text.commands.Filter;
import controller.text.commands.LinearColor;
import model.IImageDB;
import controller.text.commands.Brighten;
import controller.text.commands.Load;
import controller.text.commands.Save;
import controller.text.commands.ICommand;
import controller.text.commands.VisualizeBlue;
import controller.text.commands.VisualizeGreen;
import controller.text.commands.VisualizeIntensity;
import controller.text.commands.VisualizeLuma;
import controller.text.commands.VisualizeRed;
import controller.text.commands.VisualizeValue;
import view.IView;

/**
 * Concrete implementation of IController for use with
 * an IModel interface. Parses and validates user input
 * to provide to model, relays model state to an IView
 * object.
 */
public class TextController implements IController {

  private final IImageDB model;
  private final Readable in;
  private final IView view;
  private HashMap<String, ICommand> commandMap;

  /**
   * Constructor for a controller object for use with an IModel object.
   *
   * @param m - An initialized IModel object
   * @param r - Readable containing user input
   * @param v - An initialized IView object
   */
  public TextController(IImageDB m, Readable r, IView v) {
    this.model = Objects.requireNonNull(m);
    this.in = Objects.requireNonNull(r);
    this.view = Objects.requireNonNull(v);

    this.commandMap = new HashMap<String, ICommand>();
    this.commandMap.put("brighten", new Brighten());
    this.commandMap.put("value-component", new VisualizeValue());
    this.commandMap.put("luma-component", new VisualizeLuma());
    this.commandMap.put("intensity-component", new VisualizeIntensity());
    this.commandMap.put("red-component", new VisualizeRed());
    this.commandMap.put("green-component", new VisualizeGreen());
    this.commandMap.put("blue-component", new VisualizeBlue());
    this.commandMap.put("load", new Load());
    this.commandMap.put("save", new Save());
    this.commandMap.put("blur", new Filter("blur"));
    this.commandMap.put("sharpen", new Filter("sharpen"));
    this.commandMap.put("greyscale", new LinearColor("greyscale"));
    this.commandMap.put("sepia", new LinearColor("sepia"));
  }

  /**
   * Helper method to render current model state in view.
   *
   * @throws IllegalStateException If an IO exception is thrown by view
   */
  private void viewImages() throws IllegalStateException {
    try {
      this.view.renderMessage("The images currently in the model are:\n");
      this.view.renderImageList();
    }
    catch (IOException e) {
      throw new IllegalStateException("Error thrown when attempting to render model.");
    }
  }

  /**
   * Helper method to render messages in view.
   *
   * @param message - Message to render
   * @throws IllegalStateException If an IO exception is thrown by view
   */
  private void viewMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    }
    catch (IOException e) {
      throw new IllegalStateException("Error thrown when attempting to render message.");
    }
  }

  @Override
  public void run() {
    //Render opening UI for user.
    viewMessage("What would you like to do?\n");

    // Begin reading input from user.
    Scanner scan = new Scanner(this.in);
    // While there is still input to be read.
    while (scan.hasNext()) {
      // Read the next item in as a string
      String command = scan.next();

      // Check if the user is attempting to quit the program.
      if (command.equalsIgnoreCase("q")
              || command.equalsIgnoreCase("quit")) {
        return;
      }

      // Select command from commandMap. If unable to find
      // command pattern, print message and request new input.
      ICommand commandToRun = this.commandMap.getOrDefault(command, null);
      if (commandToRun == null) {
        viewMessage("Invalid command. Try again.");
        continue;
      }

      // Try to run command pattern.
      try {
        commandToRun.run(scan, this.model, this.view);
      }
      // Render message for exceptions thrown, then continue program.
      catch (Exception e) {
        viewMessage(e.getMessage() + "\n");
      }

      // Render list of images currently in model.
      viewImages();
      viewMessage("What would you like to do?\n");
    }
  }
}

