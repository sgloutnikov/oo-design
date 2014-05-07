package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLClass;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.CollectionAdd;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.CollectionGetIterator;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.CollectionRemove;

public class AssociationParser extends BaseParser
{

   Hashtable<String, UMLType> objecttable;
   Element pkg;
   UMLPackage root;

   public AssociationParser(Hashtable<String, UMLType> objecttable, Element pkg, UMLPackage root)
   {
      this.objecttable = objecttable;
      this.pkg = pkg;
      this.root = root;
   }

   public void processAssociations()
   {
      List<Element> associations = getAssociations();
      for (Element i : associations)
         processAssociationEnds(i);
   }

   private void processAssociationEnds(Element assoc)
   {
      List<Element> ends = getAssociationEnds(assoc);
      Element connection1 = ends.get(0);
      Element connection2 = ends.get(1);

      processConnection(connection1, connection2);
      processConnection(connection2, connection1);
   }

   private void processConnection(Element container, Element fieldtype)
   {
      String fieldname = getEndFieldName(container);
      String fieldscope = getEndFieldScope(container);
      String multiplicity = getEndMultiplicity(container);

      // gets
      UMLClass containerclass = null;
      String containerguid = getParticipantGuid(container);
      if (container != null && fieldname != null)
      {
         Object containertester = objecttable.get(containerguid);
         if (containertester instanceof UMLClass)
            containerclass = (UMLClass) containertester;
      }

      // gets UMLClass representing field type
      UMLClass fieldclass = null;
      String fieldguid = getParticipantGuid(fieldtype);
      if (fieldguid != null)
      {
         Object fieldtester = objecttable.get(fieldguid);
         if (fieldtester instanceof UMLClass)
            fieldclass = (UMLClass) fieldtester;
      }

      if (containerclass != null && fieldclass != null)
      {
         String typestring = getFieldType(containerclass, fieldclass,
               multiplicity, fieldname);
         if (typestring != null)
         {
            UMLVariable field = new UMLVariable(fieldname, fieldscope,
                  typestring, null);
            fieldclass.addVariable(field);
         }
      }
   }

   private String getFieldType(UMLClass typeclass, UMLClass containingclass,
         String multiplicity, String fieldname)
   {
      String ret = null;
      if (multiplicity != null)
      {
         char num = multiplicity.charAt(multiplicity.length() - 1);
         switch (num)
         {
         case '*':
            ret = "Collection<" + typeclass.getName() + ">";
            containingclass.addImport("java.util.Collection");
            containingclass.addImport("java.util.Iterator");
            containingclass.addMethod(new CollectionAdd(typeclass.getName(),
                  fieldname));
            containingclass.addMethod(new CollectionGetIterator(typeclass
                  .getName(), fieldname));
            containingclass.addMethod(new CollectionRemove(typeclass.getName(),
                  fieldname));
            break;
         case '1':
            ret = typeclass.getName();
            break;
         }
         if (ret == null
               && (multiplicity.contains("..") || multiplicity
                     .contains("[0-9]++")))
            ret = typeclass.getName() + "[]";
      } else
         ret = typeclass.getName();

      return ret;
   }

   private String getEndMultiplicity(Element e)
   {
      String ret = null;
      List<Element> multiplicities = getElements(e, "name", "Multiplicity");
      if (multiplicities.size() > 0)
      {
         ret = multiplicities.get(0).getTextContent();
      }
      return ret;
   }

   private String getParticipantGuid(Element e)
   {
      String ret = null;
      List<Element> participants = getElements(e, "name", "Participant");
      if (participants.size() > 0)
      {
         ret = participants.get(0).getTextContent();
      }
      return ret;
   }

   private String getEndFieldName(Element end)
   {
      String ret = null;
      List<Element> elements = getElements(end, "name", "Name");
      if (elements.size() > 0)
         ret = elements.get(0).getTextContent();

      return ret;
   }

   private String getEndFieldScope(Element end)
   {
      String ret = "public";
      List<Element> elements = getElements(end, "name", "Visibility");
      if (elements.size() > 0)
      {
         String visibility = elements.get(0).getTextContent();
         if (visibility.equals("vkPrivate"))
            ret = "private";
         else if (visibility.equals("vkProtected"))
            ret = "protected";
         else if (visibility.equals("vkPackage"))
            ret = ""; //package scope in java is default (empty) scope.
      }
      return ret;
   }

   private List<Element> getAssociationEnds(Element assoc)
   {
      return getElements(assoc, "type", "UMLAssociationEnd");
   }

   private List<Element> getAssociations()
   {
      return getElements(pkg, "type", "UMLAssociation");
   }
}
