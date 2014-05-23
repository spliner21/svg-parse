package pl.spliner21.svg_parser;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Library's "view". Created just for testing
 * @author spliner21
 *
 */
public class SVGParseMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnOpen;
	private SVGTree debugTree;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SVGParseMain frame = new SVGParseMain();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Starts the already initialized frame, making it
	 * visible and ready to interact with the user.
	 */
	public void start() {
	   setVisible(true);
	}

	/**
	 * Constructor for the SVGParseMain
	 */
	public SVGParseMain() {
	   initGUI();  // Call the initialization method.
	}

	/**
	 * Initialize the GUI components but do not start the frame. 
	 * This method could be public if desired.
	 */
	private void initGUI() {
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setBounds(100, 100, 450, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTextPane txtpnEnterPathTo = new JTextPane();
		txtpnEnterPathTo.setBackground(UIManager.getColor("Viewport.background"));
		txtpnEnterPathTo.setText("Enter path to SVG file to be parsed:");
		contentPane.add(txtpnEnterPathTo, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setEditable(false);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		btnOpen = new JButton("Open");
		contentPane.add(btnOpen, BorderLayout.SOUTH);
		
		button = new JButton("...");
		contentPane.add(button, BorderLayout.EAST);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fOpenDialog = new JFileChooser();
				fOpenDialog.showOpenDialog(null);
				textField.setText(fOpenDialog.getSelectedFile().getAbsolutePath());				
			}
		});
		
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = textField.getText();
				try {
					debugTree = new SVGTree(path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.print(debugTree.toString());
			}
		});
	}
}
