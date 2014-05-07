package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

/**
 * 
 * @author pearce
 */
public class Parser
{
   private Document doc;
   private Element umlPackage;
   private UMLPackage thePackage;
   private Hashtable<String, UMLType> objectTable;

   // private List<Element> umlClasses;
   // private List<Element> umlInterfaces;

   public Parser(String xpdFile)
   {
      initDocument(xpdFile);
      initPackage();
      initThePackage();
      // umlClasses = initUmlClasses(umlPackage);
      // umlInterfaces = initUmlInterfaces(umlPackage);
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

   // assume only one package (if you want)
   private void initPackage()
   {
      List<Element> pkgs = getElements(doc.getDocumentElement(), "type",
            "UMLPackage");
      umlPackage = pkgs.get(0);
   }

   // this is my workhorse:
   private List<Element> getElements(Element parent, String attName,
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
}

// just for testing:
// public void display()
// {
// String indent = "";
// for(Element elem: umlClasses)
// {
// List<Element> names = getElements(elem, "name", "Name");
// System.out.println(names.get(0).getTextContent());
// }
// }
//
// just for testing:
// public static void main(String[] args)
// {
// Parser gen = new Parser(args[0]);
// gen.display();
// }
// }

// NOT USED
//    
// private Hashtable<String, UMLType> initUmlClasses(Element pkg)
// {
// return getElements(pkg, "type", "UMLClass");
// }
//
// private List<Element> initUmlInterfaces(Element pkg)
// {
// return getElements(pkg, "type", "UMLInterface");
// }
//     
//
// public void setOwnedElements() {
// ownedElements = new ArrayList<Element>();
// Element e = doc.getDocumentElement();
// List<Element> sizes = getElements(e, "name", "#OwnedElements");
// for(Element s: sizes) {
// int n = new Integer(s.getTextContent());
// Element p = (Element)s.getParentNode();
// System.out.println(n);
// for(int j = 0; j < n; j++) {
// List<Element> elems = getElements(p, "name", "OwnedElements[" + j + "]");
// ownedElements.addAll(elems);
// }
// }
// }