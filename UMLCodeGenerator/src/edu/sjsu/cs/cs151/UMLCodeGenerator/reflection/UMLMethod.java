package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

import java.util.Collection;
import java.util.HashSet;

public class UMLMethod extends UMLElement
{
   protected Collection<UMLVariable> parameters;
   private String returntype;
   private String returnvalue;
   private String classifier = null;

   private void initParamSet()
   {
      parameters = new HashSet<UMLVariable>();
   }

   public UMLMethod(String name, String scope, String returntype)
   {
      super(name, scope);
      initParamSet();
      parameters = new HashSet<UMLVariable>();
      this.returntype = returntype;
      if (returntype.equals("int") || returntype.equals("short")
            || returntype.equals("byte") || returntype.equals("long")
            || returntype.equals("double") || returntype.equals("float")
            || returntype.equals("float"))
         returnvalue = "0";
      else if (returntype.equals("boolean"))
         returnvalue = "false";
      else if (!returntype.equals("void"))
         returnvalue = "null";
   }

   public UMLMethod(String name, String scope, String returntype,
         String classifier)
   {
      this(name, scope, returntype);
      this.classifier = classifier;
   }

   public UMLMethod(String name, String scope)
   {
      super(name, scope);
      initParamSet();
      returntype = "void";
   }

   public UMLMethod(String name)
   {
      super(name, "public");
      initParamSet();
      returntype = "void";
   }

   public void addParameter(UMLVariable param)
   {
      parameters.add(param);
   }

   public void setReturnType(String type)
   {
      if (type != null)
      {
         returntype = type;
         if (returntype.equals("int") || returntype.equals("short")
               || returntype.equals("byte") || returntype.equals("long")
               || returntype.equals("double") || returntype.equals("float")
               || returntype.equals("float"))
            returnvalue = "0";
         else if (returntype.equals("boolean"))
            returnvalue = "false";
         else if (!returntype.equals("void"))
            returnvalue = "null";
      }
   }

   public String generateCode()
   {
      StringBuffer sbuf = new StringBuffer();
      sbuf.append(getMethodDeclaration()
            + "\n   {\n   ///TODO: Add Code Here\n");
      
      if (returnvalue != null)
         sbuf.append("   return " + returnvalue + ";\n");

      sbuf.append("   }\n");
      return sbuf.toString();
   }

   public String getReturnValue()
   {
      return returnvalue;
   }

   public String getReturnType()
   {
      return returntype;
   }

   public String getMethodDeclaration()
   {
      StringBuffer sbuf = new StringBuffer();
      sbuf.append(this.getScope() + " ");
      if (classifier != null)
         sbuf.append(this.classifier + " ");

      sbuf.append(returntype + " " + this.getName() + "(");
      for (UMLVariable v : parameters)
         sbuf.append(v.getType() + " " + v.getName() + ",");

      // remove extra comma at end.
      if (parameters.size() > 0)
         sbuf.deleteCharAt(sbuf.length() - 1);

      sbuf.append(")");

      return sbuf.toString();
   }

   public int countParams()
   {
      return parameters.size();
   }

   public void setClassifier(String classifier)
   {
      this.classifier = classifier;
   }
}
