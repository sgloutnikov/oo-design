package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class DependencyParser extends BaseParser
{
   Hashtable<String, UMLType> objecttable;
   Element pkg;

   public DependencyParser(Hashtable<String, UMLType> objecttable, Element pkg)
   {
      this.objecttable = objecttable;
      this.pkg = pkg;
   }

   public void processDependencys()
   {
      List<Element> classes = getDependency();
      for (Element e : classes)
      {
         Element supplier = getElements(e, "name", "Supplier").get(0);
         Element client = getElements(e, "name", "Client").get(0);

         UMLType supplierType = objecttable.get(supplier.getTextContent());
         UMLType clientType = objecttable.get(client.getTextContent());
      }

   }

   private List<Element> getDependency()
   {
      return getElements(pkg, "type", "UMLDependency");
   }

}
