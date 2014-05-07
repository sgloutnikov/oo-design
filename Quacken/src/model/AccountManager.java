
package model;

import java.util.*;
import java.io.Serializable;

public class AccountManager extends Model implements Serializable, Observer 
{
   private static final long serialVersionUID = 1L;
   private Map<String, Account> accounts;
   
   public AccountManager(String fileName)
   {
      super(fileName);
      accounts = new HashMap<String, Account>();
      setUnsavedChanges(true);
   }
      
   public Account addAccount(String actName)
   {
      Account newacct = new Account(actName);
      accounts.put(actName, newacct);
      newacct.addObserver(this);
      setUnsavedChanges(true);
      return newacct;
   }
   
   public void addAccount(Account actName)
   {
      accounts.put(actName.getName(), actName);
      actName.addObserver(this);
      setUnsavedChanges(true);
   }
   
   public Set<String> getAccountKeys()
   {      
      return accounts.keySet();
   }
   
   public Account getAccount(String actName)
   {
      return accounts.get(actName);
   }
   
   public void update(Observable observer, Object obj)
   {
      setUnsavedChanges(true);
   }
}