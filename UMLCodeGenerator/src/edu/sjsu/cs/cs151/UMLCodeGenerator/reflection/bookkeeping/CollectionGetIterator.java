package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class CollectionGetIterator extends UMLMethod
{
   private String field;

   public CollectionGetIterator(UMLVariable field)
   {
      super("iteratorOf"
            + String.valueOf(field.getName().charAt(0)).toUpperCase()
            + field.getName().substring(1), "public", "Iterator");
      this.field = field.getName();
   }

   public CollectionGetIterator(String type, String fieldname)
   {
      super("iteratorOf" + String.valueOf(fieldname.charAt(0)).toUpperCase()
            + fieldname.substring(1), "public", "Iterator");
      this.field = fieldname;
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append(getMethodDeclaration() + " {\n");
      result.append("      return " + field + ".iterator();\n");
      result.append("   }\n\n");

      return result.toString();
   }
}
