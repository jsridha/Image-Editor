import org.junit.Test;

import controller.gui.GUIController;
import controller.gui.GraphicsController;
import model.IImageDB;
import model.IImageState;
import model.ImageDataBase;
import view.MockView;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the graphical view implementation of IView.
 */
public class GraphicViewTest {

  /**
   * Tests the wiring between the controller and graphical view.
   */
  @Test
  public void testMockView() {
    IImageDB model = new ImageDataBase();
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log);
    GUIController controller = new GraphicsController(model, mockView);

    controller.run();
    controller.handleLoadEvent("./res/example.ppm", "example");

    IImageState image = model.getImage("example");
    StringBuilder expected = new StringBuilder();

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int red = image.getRedChannel(j, i);
        int green = image.getGreenChannel(j, i);
        int blue = image.getBlueChannel(j, i);

        expected.append(String.format("%d %d %d ",red, green, blue));
      }
    }

    assertEquals(expected.toString(), log.toString());
  }

}
