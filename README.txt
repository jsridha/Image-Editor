Project Title: Image Editor
Author: Jay Sridharan

Summary of Project
 - The program contained within allows a user to update and transform an image
   contained within a .jpg, .ppm, or .png file. The program allows the user to load images
   into the current instance, where they can then brighten or darken them, as well as
   visualize them along individual R/G/B channels or using the maximum value, intensity
   or luma for each pixel. The user also has the ability to apply a filter transformation to
   blur or sharpen the image, as well as add a linear color transformation for sepia or luma
   greyscale.

   The user is able to interact with the program primarily using a graphical view
   rendered using javax.swing. Additionally, user can run program with a script by launching
   the program using a -file flag, or can interact using the command line by applying a -text
   flag.

--------------------------------------------------------------------------------------

Project Structure:
- src
  - controller
    - IController
      - Interface defining the run() method for a Controller object.
    - gui
      - commands
        - Brighten
          - Parses user input to determine to brighten/darken the image by, then runs
            the transformation on the selected image, storing the result in the model.
        - LinearColor
          - Parses user input to determine what color matrix to use, then runs the
            transformation on the selected image, storing the result in the model.
        - Filter
          - Parses user input to determine what kernel to use, then runs the
            transformation on the selected image, storing the result in the model.
        - ICommand
          - Interface defining command patterns for graphical controller.
        - IOCommand
          - Interface defining I/O command patterns for graphical controller.
        - Load
          - Parses user input to read in an image from a file and load it into the model.
        - Save
          - Parses user input to save an image in the model to a file.
        - Visualize
          - Parses user input to determine which VisualizeTransform object to run, then runs the
            transformation on the selected image, storing the result in the model.
      - GUIController
        - Interface defining the go() method for a controller
          object. Extends the ViewListener and IController interface.
      - GraphicsController
        - Concrete implementation of the IController interface. The go() method begins reading user
          input from a Readable object and runs an appropriate ICommand object based on the input
          received and the model passed to the controller at the time of initialization. Finally,
          directs the view to render the results of the process executed.
    - text
      - commands
        - Brighten
	    - Requests what image to transform, how much to transform
	      it by, and what to name the updated image.
	      Ex: brighten 15 example example-bright
        - Color
          - Requests what image to transform and what to name the updated image.
            Ex: "sepia example example-sepia", "greyscale example example-greyscale"
        - Filter
          - Requests what image to transform and what to name the updated image.
            Ex: "sepia example example-sepia", "greyscale example example-greyscale"
        - ICommand
	      - Interface defining the run() method each command pattern utilizes.
        - Load
	      - Requests filename and name of image to read in.
	        Ex: load example.ppm example
        - Save
	      - Requests name of file to create/write image to, and name of image to save.
	        Ex: save example-bright.ppm example-bright
        - VisualizeRed
        - VisualizeGreen
        - VisualizeBlue
        - VisualizeValue
        - VisualizeIntensity
        - VisualizeValue
	      - All of which request what image to visualize, and what to name the resulting image.
	        Ex: red-component example example-red, blue-component example example-blue,
	            luma-component example example-luma..., etc.
      - TextController
        - Concrete implementation of the IController interface. The go() method begins reading user
          input from a Readable object and runs an appropriate ICommand object based on the input
          received and the model passed to the controller at the time of initialization. Finally,
          directs the view to render the results of the process executed.

  - model
    - transformations
      - BrightenTransformation
	    - Brightens or darkens the selected image by the
	      provided amount. Clamps each channel from 0 - 255.
      - ColorTransformation
        - Performs a linear color transformation on the selected image,
          using the provided transformation matrix.
      - FilterTransformation
        - Performs a filter transformation on the selected image
          using the provided transformation matrix.
      - ICommand
	    - Interface defining the run() method each command pattern utilizes.
      - IOUtil
	    - Object defining static file handling methods for reading IImageState objects
	      from a file, as well as writing an IImageState object to a .ppm file.
      - AbsVisualizeTransformation
	    - Performs a visualization transformation on the selected image. Concrete
	      implementations are as follows for the specific parameters chosen:
          - VisualizeRedTransformation
          - VisualizeGreenTransformation
	      - VisualizeBlueTransformation
          - VisualizeValueTransformation
          - VisualizeIntensityTransformation
          - VisualizeValueTransformation
    - IImage
      - Interface defining methods to update an IImageState object. Extends IImageState.
    - IImageDB
      - Interface defining methods for storing and requesting
        IImageState objects using identifier strings.
    - IImageState
      - Interface defining read-only methods for an IImageState object.
    - Image
      - Conrete implementation of IImage. Represents a matrix of pixels. 
    - IImageDataBase
      - Concrete implementation of IImageDB. Stores IImageState
        objects that can be requested using String identifiers.
    - IPixel
      - Interface defining methods for reading channel values from a pixel.
    - Pixel
      - Concrete implementation of IPixel. Represents a singular pixel in an image.
  - view
    - gui
      - Histogram
        - Extends JPanel to create a histogram component.
      - Canvas
        - Extends JPanel to create a canvas component that an image can be rendered on.
      - GUIView
        - Concrete implementation of IGUIView. Renders a view for the program using Swing,
          and emits events using a button panel.
      - IGUIView
        - Interface defining methods for a graphical IView implementation. Extends IView by adding
          the addViewListeners(), requestFrameFocus(), setVisible(), and renderImage() methods.
      - ViewListener
        - Interface defining methods for an object that
          listens to a View and handles emitted events.
    - text
      - TextView
        - Concrete implementation of IView. Renders a text-based view of the
          database for the user. Additionally prompts the user to enter commands.
    - IView
      - Interface defining methods to render messages and the model state to the view.
    - MockView
      - Mock View object for use in testing the wiring between graphical view and controller.
    -
  - ImageEditor
      - Executable file for the program. Initializes an instance of IImageDB, then based on if
        flag was provided, either starts program to read in text input or using a graphical view.
        If setting up graphical view, initializes GraphicController and GUIView objects.

        If rendering in text view, constructs a readable then parses flag to determine if readable
        should read in contents from a file, or from command line. Finally, constructs an IView object
        appending output to the terminal and a TextController to parse input.

        Finally, runs the controller's run() method.

Additionally, there is an example.ppm file, which I created in GIMP. The image 150px by
150px, and is the base for the various transformed images found in the /res directory.


