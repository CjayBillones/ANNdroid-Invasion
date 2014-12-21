package ANNdroid.src.util;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Utility{

	public static BufferedImage scaleImage(BufferedImage img, int width, int height){
		double widthScaleFactor = width / (double)img.getWidth();
		double heightScaleFactor = height / (double)img.getHeight();
		double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		AffineTransform at = new AffineTransform();
		at.scale(scaleFactor, scaleFactor);

		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		
		return (img = scaleOp.filter(img, null));
	}

}