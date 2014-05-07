package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLClass;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.FieldSet;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.FieldGet;

public class ClassParser extends TypeParser
{

   Hashtable<String, UMLType> objecttable;
   Element pkg;
   UMLPackage root;

   public ClassParser(Hashtable<String, UMLType> objecttable, Element pkg,
         UMLPackage root)
   {
      this.objecttable = objecttable;
      this.pkg = pkg;
      this.root = root;
   }

   private List<Element> getClasses()
   {
      return getElements(pkg, "type", "UMLClass");
   }

   public void processClasses()
   {
      List<Element> classes = getClasses();
      for (Element i : classes)
      {
         String guid = i.getAttribute("guid");
         String classname = getElements(i, "name", "Name").get(0)
               .getTextContent();

         UMLClass curclass = new UMLClass(classname);
         processMethods(i, curclass);
         processAttributes(i, curclass);

         root.addFile(curclass);
         objecttable.put(guid, curclass);
      }
   }
   
   private void processAttributes(Element attribContainer, UMLClass theClass)
   {
      List<Element> attribs = getElements(attribContainer, "type", "UMLAttribute");
      for(Element a: attribs)
      {
         String name = getElements(a, "name", "Name").get(0).getTextContent();
         String vis = getScope(getElements(a, "name", "Visibility"));
         String type = getType(getElements(a, "name", "TypeExpression"));
         
         UMLVariable theVar = new UMLVariable(name, vis, type);
         
         theClass.addVariable(theVar);

         if(!vis.equals("public"))
         {
            theClass.addMethod(new FieldGet(theVar));
            theClass.addMethod(new FieldSet(theVar));
         }
      }
   }
   
   public String getScope(List<Element> vis)
   {
      String result = "";
      
      if(vis == null || vis.size() == 0)
         return result;
      else
      {
         result = "public";
         String tmp = vis.get(0).getTextContent();
         
         if (tmp.equals("vkPrivate"))
            result = "private";
         else if (tmp.equals("vkProtected"))
            result = "protected";
         else if (tmp.equals("vkPackage"))
            result = ""; //package scope in java is default (empty) scope.
      }
      
      return result;
   }
   
   public String getType(List<Element> type)
   {
      String result = "";
      
      if(type == null || type.size() == 0)
         return result;
      else
         return type.get(0).getTextContent();
   }
}
