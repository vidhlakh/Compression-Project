package com.vidhlakh.compression;
/**
 * This class CompressFactory makes use of Factory design pattern 
 * Creates object at runtime 
 * Creates object for TextCompressionStrategy if string is Text 
 * Creates object for ImageCompressionStrategy if string is image 
 * @author Vidhya Lakshmi
 *
 */



public class CompressFactory {
	
	  /**
	 * @param str String is the filetype 
	 *  received depending on the 
	 * input file (text or image file).
	 * @return returns the CompressionStrategy object for any strategy class depending on input.
	 */
	public CompressionStrategy getInstance(String str) {
		  
		     
		  	if (str.equals("Text"))
		  	{
		  		return new TextCompressionStrategy();
		  	}
		  	else if (str.equals("Image"))
		  	{
		  		return new ImageCompressionStrategy();
		  	}
		  	else 
		  	{
		  		throw new NullPointerException("File is not an image or a text file");
		  		
		  	}
		  
	  }
	  
	 
	  }

