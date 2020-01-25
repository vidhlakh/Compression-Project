package com.vidhlakh.compression;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**This is the main compression application which perform 
 * compression for a text or image file.
 * 
 * @author Vidhya Lakshmi
 *
 */
public class CompressionApplicationMain {

	private JFrame frame;
	private JLabel lblEnterFileName;
	public File input ,output;
	public String filetype;
	/**btnNewButton
	 * Button Compress
	 * to perform compression by calling compress method which is 
	 * decoupled form this application.
	 * When button is pressed, 
	 * Object is obtained by calling getInstance method @see {@link CompressFactory#getInstance(String)}
	 */
	private JButton btnNewButton;
	/**btnAddFile
	 * When AddFile button is pressed , file explorer is opened using JFileChooser
	 * Selected file is passed to compress method when compress button is pressed.
	 * 
	 */
	private JButton btnAddFile;
	/**
	 * Launch the application to compress a file. 
	 * 
	 */
	
	/**The entry point for the Compression application. The main method instantiates the
     * application's frame window and displays it.
     
     * @param args Not used.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompressionApplicationMain window = new CompressionApplicationMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CompressionApplicationMain() {
		initialize();
	}

	/** Initialize method
	 * Initialize the contents of the frame. 
	 * Default parameters are set for a frame.
	 *Action of Button Compress calls compress method 
	 * in Text or image compression strategy class
	 * Object is created by calling getInstance method using Factory Design pattern
	 * Decoupling of the method implementation is achieved.
	 * Object creation is decided at runtime depending on parameter filetype
	 * Action of Button AddFile opens FileExplorer.
	 * Gets the selected file and passed to the compress method.
	 */
	
	
	void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setBounds(100, 100, 948, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Compress");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		 
		btnNewButton.addActionListener(
		new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					CompressFactory compressobject = new CompressFactory();
					  //private CompressionStrategy strategy;
					  //At runtime compression strategy can be selected depending on the filetype
					  
					CompressionStrategy strategy=compressobject.getInstance(filetype);
				    strategy.compress(input,output);
				    
					JOptionPane.showMessageDialog(null, "Compression successful");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(), e1, "Dialog",JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				}catch(NullPointerException n)
			    {
			    	
					JOptionPane.showMessageDialog(new JFrame(), n, "Dialog",JOptionPane.ERROR_MESSAGE);
			    }
				
			}
		});
		btnNewButton.setBounds(430, 167, 134, 59);
		frame.getContentPane().add(btnNewButton);
		btnAddFile = new JButton("Choose File ");
		btnAddFile.addActionListener(new ActionListener() {
			/** Javadoc)
			 *  @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
			JFileChooser jFileChooser = new JFileChooser();
			int result=jFileChooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION )
			{
				
				input = jFileChooser.getSelectedFile();
				lblEnterFileName.setText(input.toString());
				String extension = Utils.getExtension(input);
				
				if (extension != null) {
			        if (extension.equals(Utils.tiff) ||
			            extension.equals(Utils.tif) ||
			            extension.equals(Utils.gif) ||
			            extension.equals(Utils.jpeg) ||
			            extension.equals(Utils.jpg) ||
			            extension.equals(Utils.png)) {
			                filetype="Image";
			        } else  if (extension.equals(Utils.txt)){
			        	filetype="Text";
			        }else filetype = extension;
			}
				output=Utils.getOutputFile(input,extension);
				
				
				
				
			}
			}
		});
		btnAddFile.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddFile.setBounds(378, 55, 214, 47);
		frame.getContentPane().add(btnAddFile);
		
	    lblEnterFileName = new JLabel("Enter File Name: ");
		lblEnterFileName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterFileName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterFileName.setBounds(49, 62, 286, 32);
		frame.getContentPane().add(lblEnterFileName);
	
}
}
