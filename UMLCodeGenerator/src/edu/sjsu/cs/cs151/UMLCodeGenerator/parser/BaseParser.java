package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class BaseParser
{

   private Hashtable<String, UMLType> objecttable;

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
}
