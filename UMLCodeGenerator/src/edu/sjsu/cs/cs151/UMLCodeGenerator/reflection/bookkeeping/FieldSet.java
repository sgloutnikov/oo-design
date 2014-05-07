package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class FieldSet extends UMLMethod
{
   private UMLVariable field;

   public FieldSet(UMLVariable field)
   {
      super("set" + String.valueOf(field.getName().charAt(0)).toUpperCase()
            + field.getName().substring(1), "public", "void");
      this.field = field;
      parameters.add(new UMLVariable(field.getName(), "", field.getType(), ""));
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append(getMethodDeclaration() + " {\n");
      result.append("      this." + field.getName() + " = " + field.getName()
            + ";");
      result.append("\n   }\n");

      return result.toString();
   }

}
