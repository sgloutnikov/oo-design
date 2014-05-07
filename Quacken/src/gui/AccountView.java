
package gui;

import model.*;
import exceptions.*;
import java.util.Observable;
import javax.swing.JOptionPane;

public class AccountView extends javax.swing.JPanel implements java.util.Observer 
{
   private static final long serialVersionUID = 1L;
   private Account activeAct;
   private javax.swing.JTextField amountBox;
   private javax.swing.JTextField balanceBox;
   private javax.swing.JButton withdrawButton;
   private javax.swing.JButton depositButton;
   private javax.swing.JLabel amountLabel;
   private javax.swing.JLabel balanceLabel;
   
   public AccountView(Account act)
   {
      this.activeAct = act;
      act.addObserver(this);
      initComponents();
   }
   
   public void update(Observable test, Object observer)
   {
      balanceBox.setText(String.valueOf(activeAct.getBalance()));
   }
   
   private void initComponents() {

      amountLabel = new javax.swing.JLabel();
      balanceLabel = new javax.swing.JLabel();
      withdrawButton = new javax.swing.JButton();
      depositButton = new javax.swing.JButton();
      amountBox = new javax.swing.JTextField();
      balanceBox = new javax.swing.JTextField();

      setMaximumSize(new java.awt.Dimension(300, 250));
      setPreferredSize(new java.awt.Dimension(300, 250));

      amountLabel.setText("amount = $");

      balanceLabel.setText("balance = $");

      withdrawButton.setText("withdraw");
      withdrawButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processWithdrawl(evt);
         }
      });

      depositButton.setText("deposit");
      depositButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            processDeposit(evt);
         }
      });

      balanceBox.setText(String.valueOf(activeAct.getBalance()));
      balanceBox.setEditable(false);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(120, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(withdrawButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(depositButton))
               .addComponent(balanceLabel)
               .addComponent(amountLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
               .addComponent(amountBox)
               .addComponent(balanceBox, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(85, 85, 85)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(amountLabel)
               .addComponent(amountBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(withdrawButton)
               .addComponent(depositButton))
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(balanceLabel)
               .addComponent(balanceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(200, Short.MAX_VALUE))
      );
   }
   private void processWithdrawl(java.awt.event.ActionEvent evt) 
   {
      try
      {
         double theAmount = Double.parseDouble(amountBox.getText());
         activeAct.withdraw(theAmount);
      }
      catch(java.lang.NumberFormatException ex)
      {
         JOptionPane.showMessageDialog(this, "Your withdrawal must be a number.", "Withdraw Error", JOptionPane.ERROR_MESSAGE);
      }
      catch(NegativeBalance ex)
      {
         JOptionPane.showMessageDialog(this, "This account does not have enough funds to withdraw this amount.", "Withdraw Error",  JOptionPane.ERROR_MESSAGE);
      }
      catch(InvalidAmount ex)
      {
         JOptionPane.showMessageDialog(this, "Invalid Amount Detected. Please try again.", "Withdraw Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   private void processDeposit(java.awt.event.ActionEvent evt) 
   {
      try
      {
         double theAmount = Double.parseDouble(amountBox.getText());
         activeAct.deposit(theAmount);
      }
      catch(java.lang.NumberFormatException ex)
      {
         JOptionPane.showMessageDialog(this, "Your deposit must be a number.", "Deposit Error", JOptionPane.ERROR_MESSAGE);
      }
      catch(InvalidAmount ex)
      {
         JOptionPane.showMessageDialog(this, "Invalid Amount Detected. Please try again.", "Deposit Error",  JOptionPane.ERROR_MESSAGE);
      }
   }
}