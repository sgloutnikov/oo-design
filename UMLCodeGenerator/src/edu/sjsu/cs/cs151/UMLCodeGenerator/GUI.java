package edu.sjsu.cs.cs151.UMLCodeGenerator;
import edu.sjsu.cs.cs151.UMLCodeGenerator.FileSaver;
import edu.sjsu.cs.cs151.UMLCodeGenerator.parser.MainParser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;

public class GUI extends javax.swing.JFrame {

    private String inputPath;
    private String outputPath;
    JFileChooser outputChooser = new JFileChooser();
    JFileChooser inputChooser = new JFileChooser();

    /** Creates new form Gui */
    public GUI() {
        initComponents();
    }

    private void initComponents() {

        inputPathLabel = new java.awt.Label();
        outputPathLabel = new java.awt.Label();
        inputButton = new java.awt.Button();
        outputButton = new java.awt.Button();
        generateButton = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UML Parser v1.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("UMLParser"); // NOI18N
        setResizable(false);

        inputPathLabel.setText("Select Input Package");

        outputPathLabel.setText("Select Output Path");

        inputButton.setLabel("Input Package");
        inputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputButtonActionPerformed(evt);
            }
        });

        outputButton.setLabel("Output Path");
        outputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputButtonActionPerformed(evt);
            }
        });

        generateButton.setLabel("Generate!");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(generateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(outputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outputButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputButton, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outputPathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputButtonActionPerformed

        int option = inputChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File inFile = inputChooser.getSelectedFile();
            inputPath = inFile.getPath();
        } 
        else {
            return;
        }
    }//GEN-LAST:event_inputButtonActionPerformed

    private void outputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputButtonActionPerformed

        outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = outputChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File outFile = outputChooser.getSelectedFile();
            outputPath = outFile.getPath();
        }
        else {
            return;
        }
    }//GEN-LAST:event_outputButtonActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
       MainParser gen = new MainParser(inputPath);
       gen.getUMLPackage().setPath(outputPath);
       if(gen.getUMLPackage().generateCode().equals(""))
          JOptionPane.showMessageDialog(
                this, 
                "Code generated succesfully!", 
                "Success!",
                JOptionPane.INFORMATION_MESSAGE);
       else
          JOptionPane.showMessageDialog(
                this,
                "An error occurred during the generation process",
                "Error!",
                JOptionPane.ERROR_MESSAGE);
       
    }//GEN-LAST:event_generateButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button generateButton;
    private java.awt.Button inputButton;
    private java.awt.Label inputPathLabel;
    private java.awt.Button outputButton;
    private java.awt.Label outputPathLabel;
    // End of variables declaration//GEN-END:variables

}