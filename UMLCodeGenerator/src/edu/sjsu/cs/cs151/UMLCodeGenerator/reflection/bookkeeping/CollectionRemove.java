package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class CollectionRemove extends UMLMethod
{
   private String field;

   public CollectionRemove(UMLVariable field)
   {
      super("removeFrom"
            + String.valueOf(field.getName().charAt(0)).toUpperCase()
            + field.getName().substring(1), "public", "boolean");
      this.field = field.getName();
      parameters.add(new UMLVariable("o", "", "Object", ""));
   }

   public CollectionRemove(String type, String fieldname)
   {
      super("removeFrom" + String.valueOf(fieldname.charAt(0)).toUpperCase()
            + fieldname.substring(1), "public", "boolean");
      this.field = fieldname;
      parameters.add(new UMLVariable("o", "", type, ""));
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append(getMethodDeclaration() + " {\n");
      result.append("      return " + field + ".remove(o);\n");
      result.append("   }\n\n");

      return result.toString();
   }
}
