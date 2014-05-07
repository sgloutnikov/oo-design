package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class CollectionAdd extends UMLMethod
{
   private String field;

   public CollectionAdd(UMLVariable field)
   {
      super("addTo" + String.valueOf(field.getName().charAt(0)).toUpperCase()
            + field.getName().substring(1), "public", "void");
      this.field = field.getName();
      parameters.add(new UMLVariable("o", "", field.getName(), ""));
   }

   public CollectionAdd(String type, String fieldname)
   {
      super("addTo" + String.valueOf(fieldname.charAt(0)).toUpperCase()
            + fieldname.substring(1), "public", "void");
      this.field = fieldname;
      parameters.add(new UMLVariable("o", "", type, ""));
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append(getMethodDeclaration() + " {\n");
      result.append("      " + field + ".add(o);\n");
      result.append("   }\n\n");

      return result.toString();
   }
}
