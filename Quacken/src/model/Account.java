
package model;

import exceptions.*;
import java.util.Observable;
import java.io.Serializable;

public class Account extends Observable implements Serializable 
{
   private static final long serialVersionUID = 1L;
   private String name;
   private double balance;
   
   public Account(String actName)
   {
      this.name = actName;
      balance = 0;
   }
   
   public void withdraw(double theAmount) throws InvalidAmount, NegativeBalance
   {
      if(theAmount < 0)
      {
         throw new InvalidAmount();
      }
      else if(balance < theAmount)
      {
         throw new NegativeBalance();
      }
      else
      {
         balance = balance - theAmount;
         setChanged();
         notifyObservers();
      }
   }
   
   public void deposit(double theAmount) throws InvalidAmount
   {
      if(theAmount < 0)
      {
         throw new InvalidAmount();
      }
      else
      {
         balance = balance + theAmount;
         setChanged();
         notifyObservers();
      }
   }
   
   public String getName()
   {
      return name;
   }
   
   public double getBalance()
   {
      return balance;
   }
}