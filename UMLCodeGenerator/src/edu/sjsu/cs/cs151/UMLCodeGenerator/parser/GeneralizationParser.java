package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLClass;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLInterface;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class GeneralizationParser extends BaseParser
{

   Hashtable<String, UMLType> objecttable;
   Element pkg;
   UMLPackage root;

   public GeneralizationParser(Hashtable<String, UMLType> objecttable,
         Element pkg, UMLPackage root)
   {
      this.objecttable = objecttable;
      this.pkg = pkg;
      this.root = root;
   }

   private List<Element> getGeneralizations()
   {
      return getElements(pkg, "type", "UMLGeneralization");
   }

   public void processGeneralizations()
   {
      List<Element> gens = getGeneralizations();
      for (Element i : gens)
      {
         Element child = getElements(i, "name", "Child").get(0);
         // System.out.println("Child: " + child);
         Element parent = getElements(i, "name", "Parent").get(0);

         UMLType childType = objecttable.get(child.getTextContent());
         UMLType parentType = objecttable.get(parent.getTextContent());

         if (childType instanceof UMLClass)
         {
            childType.setParent(parentType);
         } else
         {
            // System.out.println("childType: " + childType);
            childType.addInterface((UMLInterface) parentType);
         }
      }
   }

}
