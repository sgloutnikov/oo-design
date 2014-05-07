package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.Hashtable;
import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLInterface;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLPackage;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;

public class RealizationParser extends BaseParser
{

   private Element packageElement;
   private Hashtable<String, UMLType> objectTable;
   private UMLPackage root;

   public RealizationParser(Hashtable<String, UMLType> objTable,
         Element pkgElement, UMLPackage root)
   {
      packageElement = pkgElement;
      objectTable = objTable;
      this.root = root;
   }

   private List<Element> getRealizations(Element pkgElement)
   {
      return getElements(pkgElement, "type", "UMLRealization");
   }

   private UMLType getClient()
   {
      List<Element> client = getElements(packageElement, "name", "Client");
      String clientKey = client.get(0).getTextContent();
      return objectTable.get(clientKey);
   }

   private UMLType getSupplier()
   {
      List<Element> supplier = getElements(packageElement, "name", "Supplier");
      String supplierKey = supplier.get(0).getTextContent();
      return objectTable.get(supplierKey);
   }

   public void processRealizations()
   {
      List<Element> l_Realizations = getRealizations(packageElement);

      for (Element i : l_Realizations)
      {
         UMLType l_client = getClient();
         UMLInterface l_supplier = (UMLInterface) getSupplier();
         if (l_supplier instanceof UMLInterface)
         {
            l_client.addInterface(l_supplier);
         }
      }

   }
}
