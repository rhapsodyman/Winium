package video;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;

public class ScreenRecordingExample {

	//private static final double FRAME_RATE = 30;
	private static final int SECONDS_TO_RUN_FOR = 5;
	private static final String outputFilename = "D://mydesktop.mp4";
	private static Dimension screenBounds;
	private static  IRational FRAME_RATE = IRational.make(10, 1);

	public static void main(String[] args) throws IOException {

		// let's make a IMediaWriter to write the file.
		final IMediaWriter writer = ToolFactory.makeWriter(outputFilename);

		screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

		// We tell it we're going to add one video stream, with id 0, at position 0, and that it will have a fixed frame rate of FRAME_RATE.
		writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, FRAME_RATE, screenBounds.width, screenBounds.height);

		long startTime = System.nanoTime();

		for (int index = 0; index < SECONDS_TO_RUN_FOR * FRAME_RATE.getNumerator(); index++) {
			// take the screen shot
			BufferedImage screen = getDesktopScreenshot(index);

			// convert to the right image type
			BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

			// encode the image to stream #0
			writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

			// sleep for frame rate milliseconds
			try {
				Thread.sleep((long) (1000 / FRAME_RATE.getNumerator()));
			}
			catch (InterruptedException e) {
				// ignore
			}
		}

		// tell the writer to close and write the trailer if needed
		writer.close();
	}

	
	/*public static void main(String[] args) throws Exception {
		File videoFolder = new File("D:/convertedVideo");
		videoFolder.mkdirs();
		ImageToVideoConverter converter = new ImageToVideoConverter("D:/captures", videoFolder.getAbsolutePath() + "/myVideo");
		converter.convert();
	}*/
	
	public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

		BufferedImage image;
		// if the source image is already the target type, return the source image
		if (sourceImage.getType() == targetType) {
			image = sourceImage;
		}

		// otherwise create a new image of the target type and draw the new image
		else {
			image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}

		return image;
	}

	private static BufferedImage getDesktopScreenshot(int index) throws IOException {
		try {
			Robot robot = new Robot();
			Rectangle captureSize = new Rectangle(screenBounds);
			BufferedImage capture = robot.createScreenCapture(captureSize);
			ImageIO.write(capture, "png", new File("D:/captures/pic" + index + ".png"));
			return capture;
		}
		catch (AWTException e) {
			e.printStackTrace();
			return null;
		}
	}
}