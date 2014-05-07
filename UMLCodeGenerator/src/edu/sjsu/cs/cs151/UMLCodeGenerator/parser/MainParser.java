package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class MainParser
{
   private Document doc;
   // I assume 1 package but many is easy to handle
   private Element umlPackage;
   private UMLPackage thePackage;
   private Hashtable<String, UMLType> objecttable;

   public MainParser(String xpdFile)
   {
      objecttable = new Hashtable<String, UMLType>();
      initDocument(xpdFile);
      initPackage();
      initThePackage();
   }

   private void initThePackage()
   {
      List<Element> nameles = getElements(umlPackage, "name", "Name");
      String packname = nameles.get(0).getTextContent();
      thePackage = new UMLPackage(packname);
      initTheTypes(); // thePackage.types = ...
   }

   // In progress
   private void initTheTypes()
   {
      // thePackage.addFile(filetoadd);
      (new ClassParser(objecttable, umlPackage, thePackage)).processClasses();
      (new InterfaceParser(objecttable, umlPackage, thePackage))
            .processInterfaces();
      (new AssociationParser(objecttable, umlPackage, thePackage))
            .processAssociations();
      (new RealizationParser(objecttable, umlPackage, thePackage))
            .processRealizations();
      (new DependencyParser(objecttable, umlPackage)).processDependencys();
      (new GeneralizationParser(objecttable, umlPackage, thePackage))
            .processGeneralizations();
      // processAssociations();

      //List<Element> packageobjs = getElements(umlPackage, "name",
      //      "OwnedElements\\[*\\]");

   }

   public UMLPackage getUMLPackage()
   {
      return thePackage;
   }

   private void initDocument(String xpdFile)
   {
      try
      {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         // obtain parser:
         DocumentBuilder builder = factory.newDocumentBuilder();
         // parse an xml document into a DOM tree:
         doc = builder.parse(new File(xpdFile));
      } catch (Exception e)
      {
         System.err.println(e.getMessage());
         System.exit(1);
      }
   }

   // this is my workhorse:
   protected List<Element> getElements(Element parent, String attName,
         String attValue)
   {
      List<Element> result = new ArrayList<Element>();
      NodeList elems = parent.getElementsByTagName("*");
      for (int i = 0; i < elems.getLength(); i++)
      {
         Node node = elems.item(i);
         if (node.getNodeType() == Node.ELEMENT_NODE)
         {
            Element e = (Element) node;
            String s = e.getAttribute(attName);
            if (s.equals(attValue))
            {
               result.add(e);
            }
         }
      }
      return result;
   }

   // assume only one package (if you want)
   private void initPackage()
   {
      List<Element> pkgs = getElements(doc.getDocumentElement(), "type",
            "UMLPackage");
      umlPackage = pkgs.get(0);
   }

   // just for testing:
   public void display()
   {
      String indent = "";
      // for(Element elem: umlClasses)
      // {
      // List<Element> names = getElements(elem, "name", "Name");
      // System.out.println(names.get(0).getTextContent());
      // }
      Set<String> keys = objecttable.keySet();
      for (String key : keys)
      {
         UMLType curtype = objecttable.get(key);
      }
   }

   // just for testing:
   public static void main(String[] args)
   {
      File testfile = new File("demo" + File.separator + "demo.uml.xml");
      MainParser gen = new MainParser("demo" + File.separator + "demo.uml.xml");
      // Parser gen = new Parser(args[0]);
      gen.display();

      gen.getUMLPackage().generateCode();

   }
}