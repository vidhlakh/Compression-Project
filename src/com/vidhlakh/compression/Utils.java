package com.vidhlakh.compression;

import java.io.File;
/**
 * This class Utils has methods to provide the extension of the 
 * input file and provide output file with modified input file name
 * and output path same as same file path.
 * @author Vidhya Lakshmi
 *
 */

public class Utils {
	
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";
    public final static String txt = "txt";
    
    
    /**
     * @param  f File for we need to get the extension. 
     * @return returns the extension of the file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    /**
     * @param f Get the input file for getting the file name 
     * and path to set output path same as input path
     * and set output file name as inputfilename_compressed. 
     * so that its easy for user to view the output file. 
     * @param ext Extension to set extension for the output file.
     * @return returns the output file name and path that is created 
     * after compression is performed.
     */
    public static File getOutputFile(File f,String ext) {
		
    	File dir = f.getParentFile();
		
		
		String filename = f.getName();     // full file name
		int iend = filename.indexOf("."); //this finds the first occurrence of "." 
		//in string thus giving you the index of where it is in the string

		// Now iend can be -1, if lets say the string had no "." at all in it i.e. no "." is found. 
		//So check and account for it.

		String subString = null;
		if (iend != -1) 
		{
		    subString= filename.substring(0 , iend); //this will give abc
		}
		String outputfilename = subString+"_compressed"+"."+ext;
        return new File(dir,outputfilename);
    }
}
