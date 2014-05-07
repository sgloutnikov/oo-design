
package gui;

import model.*;

public class AccountViewFrame extends javax.swing.JInternalFrame 
{
   private static final long serialVersionUID = 1L;
   private String title = null;
   private AccountView contentpane;
   
   public AccountViewFrame(Account act)
   {
      initComponents();
      contentpane = new AccountView(act);
      title = act.getName();
      this.add(contentpane);
      contentpane.setVisible(true);
      this.setVisible(true);
   }

   public String getTitle()
   {
      return title;
   }
   
   private void initComponents() 
   {
      setClosable(true);
      setIconifiable(true);
      setMaximizable(true);
      setResizable(true);
      setTitle(getTitle());
      setMaximumSize(new java.awt.Dimension(300, 250));
      setMinimumSize(new java.awt.Dimension(300, 250));
      setNormalBounds(new java.awt.Rectangle(0, 0, 300, 0));
      setPreferredSize(new java.awt.Dimension(300, 250));
      getAccessibleContext().setAccessibleName(getTitle());

      pack();
  }
}