package com.vidhlakh.compression;

import java.io.File;
import java.io.IOException;

/**
 * This interface CompressionStrategy has compress method declaration for 
 * any algorithm to be inherited and reuse it later.
 * This interface a use of Strategy pattern
 * @author Vidhya Lakshmi
 * @version 1.0
 *
 */
public interface CompressionStrategy {
	
	/**Declaration of compress method to perform compression with 2 parameters. 
	 * @param input Input file to be compressed.
	 * @param output Output file with specific name and (inputfile path)path to be
	 *  created  after compression.
	 * @throws IOException throws an exception for error in accessing the file.
	 */
	void compress(File input, File output ) throws IOException;

}
