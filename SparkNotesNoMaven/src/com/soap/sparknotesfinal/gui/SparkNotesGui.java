
package com.soap.sparknotesfinal.gui;

/*
 * HelloWorldSwing.java requires no other files.
 */

import com.soap.sparknotesfinal.selenium.ScrapeSparkNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SparkNotesGui {
	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	ScrapeSparkNotes fetcher = new ScrapeSparkNotes();

	public void createAndShowGUI() {
		JPanel panel;
		JFrame frame;
		boolean shouldFill = true;
		boolean shouldWeightX = true;

		int xsize = 500;
		int ysize = 500;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			// natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		JLabel label = new JLabel("Enter your book name:", JLabel.CENTER);
		if (shouldWeightX) {
			c.weightx = 0.0;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);

		JTextField bookName = new JTextField();
		panel.add(bookName);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(bookName, c);

		JButton ok = new JButton("Submit");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(ok, c);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		JTextArea summary = new JTextArea();
		c.ipady = 250; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		
		panel2.add(summary, c);
		panel2.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String summarycontents = fetcher.getSummary(bookName.getText());
				summary.setText(summarycontents);
			}
		});

		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		frame.setSize(xsize, ysize);
		// panel.setLayout(new GridLayout(5, 5));
		frame.add(panel, BorderLayout.NORTH);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));

		frame.add(panel2, BorderLayout.CENTER);
		
		frame.setTitle("Maze");
		frame.setVisible(true);
		
		JScrollPane scroll = new JScrollPane (summary, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

				frame.add(scroll);
	}

	public static void main(String[] args) {
		SparkNotesGui helloWorld = new SparkNotesGui();

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				helloWorld.createAndShowGUI();
			}
		});
	}
}