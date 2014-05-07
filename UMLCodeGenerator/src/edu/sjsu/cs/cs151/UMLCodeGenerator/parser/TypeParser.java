package edu.sjsu.cs.cs151.UMLCodeGenerator.parser;

import java.util.List;

import org.w3c.dom.Element;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLType;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class TypeParser extends BaseParser
{

   protected void processMethods(Element parentclass, UMLType curclass)
   {
      List<Element> methods = getMethods(parentclass);
      for (Element i : methods)
      {
         String methodname = getElements(i, "name", "Name").get(0)
               .getTextContent();
         String methodclassifier = getMethodClassifier(i);
         UMLMethod meth = new UMLMethod(methodname);
         if (methodclassifier != null)
            meth.setClassifier(methodclassifier);
         processParams(i, meth);
         curclass.addMethod(meth);
      }
   }

   private String getMethodClassifier(Element i)
   {
      String ret = null;
      List<Element> classifiers = getElements(i, "name", "OwnerScope");
      if (classifiers.size() > 0
            && (classifiers.get(0).getTextContent().equals("skClassifier")))
         ret = "static";

      return ret;
   }

   
   private List<Element> getMethods(Element classobj)
   {
      return getElements(classobj, "type", "UMLOperation");
   }

   private void processParams(Element method, UMLMethod meth)
   {
      List<Element> params = getParams(method);
      for (Element i : params)
      {
         String paramname = getParamName(i);
         String paramtype = getParamType(i);
         if (checkReturn(i))
         {
            meth.setReturnType(paramtype);
         } else
         {
            meth.addParameter(new UMLVariable(paramname, paramtype));
         }
      }
   }

   private boolean checkReturn(Element i)
   {
      boolean ret = false;
      List<Element> checker = getElements(i, "type",
            "UMLParameterDirectionKind");
      if (checker != null && checker.size() > 0)
      {
         for (Element p : checker)
            if (p.getTextContent().equalsIgnoreCase("pdkReturn"))
               ret = true;
      }

      return ret;
   }

   private String getParamType(Element i)
   {
      String ret = null;
      List<Element> paramtypes = getElements(i, "name", "TypeExpression");
      if (paramtypes != null && paramtypes.size() > 0)
         ret = paramtypes.get(0).getTextContent();

      return ret;
   }

   private String getParamName(Element i)
   {
      String ret = "";
      List<Element> paramnames = getElements(i, "name", "Name");
      if(paramnames != null && paramnames.size() > 0)
         ret = paramnames.get(0).getTextContent();
      
      return ret;
      
   }
   
   private List<Element> getParams(Element methobj)
   {
      return getElements(methobj, "type", "UMLParameter");
   }
}
