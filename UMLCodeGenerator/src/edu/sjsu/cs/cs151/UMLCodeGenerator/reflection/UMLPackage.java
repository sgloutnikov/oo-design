package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

import java.util.Collection;
import java.util.HashSet;
import java.io.File;

import edu.sjsu.cs.cs151.UMLCodeGenerator.FileSaver;

public class UMLPackage extends UMLElement
{
   private Collection<UMLType> files;
   // private FileSaver fs;
   private String path;
   private FileSaver fs;

   public UMLPackage(String name)
   {
      super(name, "");
      path = "";
      files = new HashSet<UMLType>();
      fs = new FileSaver();
   }

   public UMLPackage(String name, Collection<UMLType> files)
   {
      super(name, "");
      this.files = files;
   }

   public String generateCode()
   {
      for (UMLType i : files)
      {
         fs.setFile(path + File.separator + getName() + File.separator + i.getName() + ".java");
         // System.out.println(i.generateCode());
         fs.writeToFile("package " + getName() + ";\n\n");
         fs.writeToFile(i.generateCode());
         fs.flushStream();
         fs.closeFile();
      }
      return "";
   }

   public void addFile(UMLType filetoadd)
   {
      files.add(filetoadd);
   }

   public boolean containsFile(UMLType theFile)
   {
      return files.contains(theFile);
   }
   
   public void setPath(String path)
   {
      this.path = path;
   }
   
   public String getPath()
   {
      return this.path;
   }
}
