package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLInterface;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class InterfaceParser extends TypeParser
{
   Hashtable<String, UMLType> objecttable;
   Element pkg;
   UMLPackage root;

   public InterfaceParser(Hashtable<String, UMLType> objecttable, Element pkg,
         UMLPackage root)
   {
      this.objecttable = objecttable;
      this.pkg = pkg;
      this.root = root;
   }

   private List<Element> getInterfaces()
   {
      return getElements(pkg, "type", "UMLInterface");
   }

   public void processInterfaces()
   {
      List<Element> classes = getInterfaces();
      for (Element i : classes)
      {
         String guid = i.getAttribute("guid");
         String classname = getElements(i, "name", "Name").get(0)
               .getTextContent();
         UMLInterface curinterface = new UMLInterface(classname);
         processMethods(i, curinterface);

         objecttable.put(guid, curinterface);
         root.addFile(curinterface);
      }
   }
}
