package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Account;
import model.AccountManager;

public class Desktop extends javax.swing.JFrame implements
      java.awt.event.ActionListener {
   private static final long serialVersionUID = 1L;
   private javax.swing.JMenuBar menuBar;
   private javax.swing.JDesktopPane desktop;
   private javax.swing.JMenu fileMenu;
   private javax.swing.JMenu accountsMenu;
   private javax.swing.JMenu helpMenu;
   private javax.swing.JMenuItem newMI;
   private javax.swing.JMenuItem saveMI;
   private javax.swing.JMenuItem saveAsMI;
   private javax.swing.JMenuItem openMI;
   private javax.swing.JMenuItem quitMI;
   private javax.swing.JMenuItem newAccountMI;
   private javax.swing.JMenuItem aboutMI;
   private javax.swing.JSeparator accountsSeperator;

   private AccountManager currentAccount = null;

   public Desktop() {
      initComponents();
   }

   private void initComponents() {
      // NETBEANS GENERATION
      desktop = new javax.swing.JDesktopPane();
      menuBar = new javax.swing.JMenuBar();
      fileMenu = new javax.swing.JMenu();
      newMI = new javax.swing.JMenuItem();
      openMI = new javax.swing.JMenuItem();
      saveMI = new javax.swing.JMenuItem();
      saveAsMI = new javax.swing.JMenuItem();
      quitMI = new javax.swing.JMenuItem();
      accountsMenu = new javax.swing.JMenu();
      newAccountMI = new javax.swing.JMenuItem();
      accountsSeperator = new javax.swing.JSeparator();
      helpMenu = new javax.swing.JMenu();
      aboutMI = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("Quacken - 1.0");
      setBackground(new java.awt.Color(255, 255, 255));
      setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      setMinimumSize(new java.awt.Dimension(800, 400));
      addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
            doCloseWindow(evt);
         }
      });

      desktop.setPreferredSize(new java.awt.Dimension(800, 600));
      getContentPane().add(desktop, java.awt.BorderLayout.CENTER);

      fileMenu.setText("File");

      newMI.setText("New");
      newMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processNewManager(evt);
         }
      });
      fileMenu.add(newMI);

      saveMI.setText("Save");
      saveMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processSave(evt);
         }
      });
      fileMenu.add(saveMI);

      saveAsMI.setText("Save As...");
      saveAsMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processSaveAs(evt);
         }
      });
      fileMenu.add(saveAsMI);

      openMI.setText("Open");
      openMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processOpen(evt);
         }
      });
      fileMenu.add(openMI);

      quitMI.setText("Quit");
      quitMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processApplicationExit(evt);
         }
      });
      fileMenu.add(quitMI);
      menuBar.add(fileMenu);

      accountsMenu.setText("Accounts");

      newAccountMI.setText("New Account");
      newAccountMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processNewAccount(evt);
         }
      });
      accountsMenu.add(newAccountMI);
      accountsMenu.add(accountsSeperator);

      menuBar.add(accountsMenu);

      helpMenu.setText("Help");

      aboutMI.setText("About");
      aboutMI.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processAbout(evt);
         }
      });
      helpMenu.add(aboutMI);

      menuBar.add(helpMenu);

      setJMenuBar(menuBar);

      pack();
   }

   private void processNewManager(java.awt.event.ActionEvent evt) {

      promptSaveChanges();
      removeAccountManager();
      Object mainInput = JOptionPane
            .showInputDialog(
                  this,
                  "Enter a name for this account manager. \nIt will also be saved to the system initally under this name.",
                  "New Account Manager", JOptionPane.QUESTION_MESSAGE);
      if (mainInput != null) {
         String filename = (String) mainInput;
         if (filename.length() > 0) {
            currentAccount = new AccountManager(filename);
            currentAccount.setUnsavedChanges(true);
         }
         else {
            JOptionPane.showMessageDialog(this, "Filename can not be empty.",
                  "Filename Error", JOptionPane.ERROR_MESSAGE);
            processNewManager(evt);
            return;
         }
      }
   }

   private void processApplicationExit(java.awt.event.ActionEvent evt) {
      promptSaveChanges();
      System.exit(0);
   }

   private void promptSaveChanges() {
      boolean savechanges = false;

      if (currentAccount != null && currentAccount.getUnsavedChanges()) {
         if ((JOptionPane.showConfirmDialog(this,
               "You have unsaved changes, do you want to save?",
               "Unsaved Changes", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
            ;
         savechanges = true;
      }

      if (savechanges) {
         saveAccountManager(currentAccount.getFilename());
      }
   }

   private void printNoAccountManagerError() {
      JOptionPane.showMessageDialog(this,
            "In order to start you must create or load a new Account Manager.",
            "Start Error!", JOptionPane.ERROR_MESSAGE);
   }

   private void processNewAccount(java.awt.event.ActionEvent evt) {
      if (currentAccount == null) {
         printNoAccountManagerError();
         return;
      }

      Object mainInput = (JOptionPane.showInputDialog(this,
            "Enter an Account Name.", "New Account",
            JOptionPane.QUESTION_MESSAGE));
      if (mainInput != null) {
         String actName = (String) mainInput;
         if (actName.length() > 0) {
            Account newAct = currentAccount.addAccount(actName);
            AccountViewFrame accountview = new AccountViewFrame(
                  currentAccount.getAccount(actName));
            accountview.setVisible(true);
            desktop.add(accountview);
            accountsMenu.add(getNewAccountMenuItem(actName));
            try {
               accountview.setSelected(true);
            }
            catch (Exception ex) {
               System.out.println(": ERROR :>> " + ex);
            }
         }
         else {
            JOptionPane.showMessageDialog(this,
                  "You must enter an Account Name or press Cancel.",
                  "Account Error", JOptionPane.ERROR_MESSAGE);
            processNewAccount(evt);
            return;
         }
      }
   }

   private void processSave(java.awt.event.ActionEvent evt) {
      if (currentAccount == null) {
         printNoAccountManagerError();
         return;
      }
      saveAccountManager(currentAccount.getFilename());
   }

   private void saveAccountManager(String filename) {
      try {
         ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
               filename));
         currentAccount.setUnsavedChanges(false);
         os.writeObject(currentAccount);
         os.close();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void removeAccountManager() {
      Component[] cmp = accountsMenu.getMenuComponents();
      for (int i = 2; i < cmp.length; i++) {
         accountsMenu.remove(cmp[i]);
      }

      accountsMenu.repaint();
      desktop.removeAll();
      desktop.repaint();
      currentAccount = null;
   }

   private void processSaveAs(java.awt.event.ActionEvent evt) {
      String theFile;
      if (currentAccount == null) {
         printNoAccountManagerError();
         return;
      }
      JFileChooser chooser = new JFileChooser();
      chooser.showSaveDialog(this);
      theFile = chooser.getSelectedFile().getName();
      saveAccountManager(theFile);
   }

   private void processOpen(java.awt.event.ActionEvent evt) {
      try {
         JFileChooser chooser = new JFileChooser();
         int ret = chooser.showOpenDialog(this);

         if (ret == JFileChooser.APPROVE_OPTION) {
            ObjectInputStream inStream = new ObjectInputStream(
                  new FileInputStream(chooser.getSelectedFile()));
            Object obj = inStream.readObject();
            if (obj instanceof AccountManager) {
               promptSaveChanges();
               removeAccountManager();
               currentAccount = (AccountManager) obj;
               Set<String> accountKeys = currentAccount.getAccountKeys();
               for (String key : accountKeys) {
                  currentAccount.getAccount(key).addObserver(currentAccount);
                  accountsMenu.add(getNewAccountMenuItem(key));
               }
            }
            else {
               JOptionPane.showMessageDialog(this,
                     "Not a Valid Quacken File! Please select a correct file.",
                     "Account Manager Error!", JOptionPane.ERROR_MESSAGE);
            }
         }
      }
      catch (Exception ex) {
         JOptionPane.showMessageDialog(this,
               "Not a Valid Quacken File! Please select a correct file.",
               "Account Manager Error!", JOptionPane.ERROR_MESSAGE);
      }

   }

   private void doCloseWindow(java.awt.event.WindowEvent evt) {
      promptSaveChanges();
      System.exit(0);
   }

   private void processAbout(java.awt.event.ActionEvent evt) {
      JOptionPane.showMessageDialog(this,
            "Quacken 1.0 (Fall 2008) \n\nPowered by BGC Global.", "About",
            JOptionPane.INFORMATION_MESSAGE);
   }

   public JMenuItem getNewAccountMenuItem(String accountname) {
      JMenuItem item = new JMenuItem();
      item.setText(accountname);
      item.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            AccountViewFrame accountframe = new AccountViewFrame(currentAccount
                  .getAccount(evt.getActionCommand()));
            accountframe.setVisible(true);
            desktop.add(accountframe);
            try {
               accountframe.setSelected(true);
            }
            catch (Exception ex) {
               System.out.println(": ERROR : " + ex);
            }
         }
      });

      return item;
   }

   public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new Desktop().setVisible(true);
         }
      });
   }

   public void actionPerformed(ActionEvent e) {
   }
}