
package model;

import java.io.Serializable;

public class Model implements Serializable 
{
   private static final long serialVersionUID = 1L;
   private String amFileName;
   private boolean unsavedChanges;

   public Model(String fileName)
   {
      this.amFileName = fileName;
   }
   
   public void setFilename(String fileName)
   {
      amFileName = fileName;
   }
   
   public String getFilename()
   {
      return amFileName;
   }
   
   public void setUnsavedChanges(boolean value)
   {
      unsavedChanges = value;
   }
   
   public boolean getUnsavedChanges()
   {
      return unsavedChanges;
   }
}