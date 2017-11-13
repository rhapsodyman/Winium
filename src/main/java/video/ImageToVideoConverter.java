//Ended up not using this guy, maybe find use for it later

package video;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

public class ImageToVideoConverter   {

	private static Logger logger = Logger.getLogger(ImageToVideoConverter.class);

	protected File inputDirectory;
	protected File outputVideo;
	protected Dimension screenBounds;
	protected int imageType;
	protected boolean readyToConvert = true;

	protected IMediaWriter writer;

	protected List<File> imageList;

	public ImageToVideoConverter(String inputDir, String videoName) {

		this.inputDirectory = new File(inputDir);
		this.outputVideo = new File(videoName + ".mp4");
		this.imageList = new LinkedList<File>();

		generateListOfImages(this.inputDirectory, this.imageList);

		try {
			this.screenBounds = getImageDimensionAndType(this.imageList.get(0));
		} catch (IOException e) {
			//logger.warn("Cannot determine the input image dimensions for " + this.imageList.get(0).getAbsolutePath());
			//logger.warn(e);
			readyToConvert = false;
		}

		this.writer = ToolFactory.makeWriter(this.outputVideo.getAbsolutePath());

		//logger.info("Ready to start converting images in " + inputDir + " into " + this.outputVideo.getAbsolutePath());

	}

	public void convert() throws Exception {
		logger.info("Starting to generation test video " + this.outputVideo.getName());

		this.writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, screenBounds.width, screenBounds.height);

		try {
			// writer.encodeVideo(0, getTitleFrame(), 0, TimeUnit.MILLISECONDS);

			int index = 0;

			for (File image : this.imageList) {
				BufferedImage currentFrame = ImageIO.read(image);

				int frameIndex = 300 * index;
				writer.encodeVideo(0, currentFrame, frameIndex, TimeUnit.MILLISECONDS);

				index++;
			}
		} catch (Exception e) {
			//logger.warn("Something went wrong, and video may be corrupted. Check the movie "
			//		+ this.outputVideo.getAbsolutePath());
			//logger.warn(e);
			e.printStackTrace();
			//return "error";
		} finally {
			writer.close();
			//logger.info("Video conversion done for " + this.outputVideo.getName());
			//return "done";
		}

	}

	protected Dimension getImageDimensionAndType(File exampleImage) throws IOException {
		BufferedImage image = ImageIO.read(exampleImage);
		this.imageType = image.getType();
		//logger.debug("Image type " + this.imageType);
		//logger.debug("Image dimensions " + image.getWidth() + "X" + image.getHeight());
		return new Dimension(image.getWidth(), image.getHeight());
	}

	protected void generateListOfImages(File sourceDir, List<File> acceptedList) {
		for (File file : sourceDir.listFiles()) {
			if (file.isFile() && (FilenameUtils.getExtension(file.getName()).equals("png"))) {
				//logger.debug("file.getName() :" + file.getAbsolutePath());
				imageList.add(file);
			}
		}
	}

}
