package com.vidhlakh.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
/**
 * This class TextCompressionStrategy makes use of Strategy pattern 
 * by inheriting the interface CompressionStrategy 
 * This class object will be called if input file is Text file 
 * @author Vidhya Lakshmi
 *
 */

public class TextCompressionStrategy implements CompressionStrategy
{
	
	
	
	/** 
	 * @see com.vidhlakh.compression.CompressionStrategy#compress(java.io.File, java.io.File)
	 * This method compress overrides interface and perform compression
	 * for a text file.
	 *  @param input Input file to be compressed.
	 * @param output Output file with specific name and (inputfile path)path to be
	 *  created  after compression.
	 * @throws IOException throws an exception for error in accessing the file.
	 */
	
	@Override
	public void compress(File input, File output) throws IOException {
		  /**
		   * Type buffer of size in order of 1024 to 
		   * store the contents of file temporarily
		   */
		  byte[] buffer = new byte[1024];
		  
		  FileInputStream fis = new FileInputStream(input);
		  FileOutputStream fos = new FileOutputStream(output);
		  GZIPOutputStream gzos = new GZIPOutputStream(fos);
		  int read;
		  while((read = fis.read(buffer))!=-1)
		  {
		  gzos.write(buffer,0,read);
		  }
		  gzos.finish();
		  gzos.close();
		  fos.close();
		  fis.close();

        
        
	}
}

