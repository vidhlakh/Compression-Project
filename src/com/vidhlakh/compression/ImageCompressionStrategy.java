package com.vidhlakh.compression;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;
/**
 * This class TextCompressionStrategy makes use of Strategy pattern 
 * by inheriting the interface CompressionStrategy
 * @author Vidhya Lakshmi
 *
 */
public class ImageCompressionStrategy implements CompressionStrategy
{
	/**This compress method perform compression for input Image file  
	 * @param input Input file to be compressed.
	 * @param output Output file with specific name and (input file path)path to be
	 *  created  after compression.
	 * @throws IOException throws an exception for error in accessing the file.
	 */

	@Override
	public void compress(File input, File output ) throws IOException{
		// TODO Auto-generated method stub
		
		
	    BufferedImage image = ImageIO.read(input);

	    OutputStream os = new FileOutputStream(output);
	    
	    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
	    ImageWriter writer = (ImageWriter) writers.next();

	    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	    writer.setOutput(ios);

	    ImageWriteParam param = writer.getDefaultWriteParam();

	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    param.setCompressionQuality(0.05f);  // Change the quality value you prefer
	    writer.write(null, new IIOImage(image, null, null), param);

	    os.close();
	    ios.close();
	    writer.dispose();
	}

}
