

package com.soap.sparknotesfinal.gui;


/*
 * HelloWorldSwing.java requires no other files.
 */

import com.soap.sparknotesfinal.selenium.ScrapeSparkNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    ScrapeSparkNotes fetcher = new ScrapeSparkNotes();

    public void createAndShowGUI() {
        boolean click;
        JPanel panel;
        JFrame frame;



         int xsize = 500; int ysize = 500; frame = new JFrame(); panel = new JPanel();
         JLabel label = new JLabel("Enter your book name:", JLabel.CENTER);

         JTextField name = new JTextField();
        panel.add(label);
        panel.add(name);
        JButton ok = new JButton("Submit");
        panel.add(ok);
        JTextArea summary = new JTextArea();
        panel.add(summary);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String summarycontents = fetcher.getSummary(name.getText());
                summary.setText(summarycontents);
            }
        });





          panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


          frame.setSize(xsize, ysize);
          panel.setLayout(new GridLayout(5, 5)); frame.add(panel, BorderLayout.CENTER);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setTitle("Maze");
          frame.setVisible(true);
    }

    public static void main(String[] args) {
        HelloWorldSwing helloWorld = new HelloWorldSwing();

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                helloWorld.createAndShowGUI();
            }
        });
    }
}