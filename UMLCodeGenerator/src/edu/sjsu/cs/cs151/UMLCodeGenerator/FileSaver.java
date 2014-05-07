package edu.sjsu.cs.cs151.UMLCodeGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver
{
   private BufferedWriter out;
   private String fileName;

   public FileSaver()
   {
      out = null;
      fileName = "";
   }

   public void setFile(String fileName)
   {
      try
      {
         File f = new File(fileName);
         if (!f.exists())
            f.getParentFile().mkdirs();
         out = new BufferedWriter(new FileWriter(f));
         this.fileName = fileName;
      } catch (FileNotFoundException fnf)
      {
         System.out.println(fnf.getMessage());
         fnf.printStackTrace(System.out);
         // throw new RuntimeException("File not found: " + fileName);
      } catch (IOException ioe)
      {
         System.out.println(ioe.getMessage());
         ioe.printStackTrace(System.out);
         // throw new RuntimeException("IOException on file: " + fileName);
      }
   }

   public String getFileName()
   {
      return fileName;
   }

   public void writeToFile(String text)
   {
      try
      {
         out.write(text);
      } catch (IOException ioe)
      {
         throw new RuntimeException("IOException on file: " + this.fileName);
      }
   }

   public void closeFile()
   {
      try
      {
         out.close();
      } catch (IOException ioe)
      {
         throw new RuntimeException("IOException on closing the file.");
      }
   }

   public void flushStream()
   {
      try
      {
         out.flush();
      } catch (IOException ioe)
      {
         throw new RuntimeException("IOException on flushing the stream.");
      }
   }
}
