package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class FieldGet extends UMLMethod
{
   private UMLVariable field;

   public FieldGet(UMLVariable field)
   {
      super("get" + String.valueOf(field.getName().charAt(0)).toUpperCase()
            + field.getName().substring(1), "public", field.getType());
      this.field = field;
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append(getMethodDeclaration() + " {\n");
      result.append("      return " + field.getName() + ";");
      result.append("\n   }\n\n");

      return result.toString();
   }
}
