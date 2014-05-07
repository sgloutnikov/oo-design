package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping;

import java.util.Collection;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLMethod;
import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.UMLVariable;

public class DefaultConstructor extends UMLMethod
{
   private Collection<UMLVariable> vars;

   public DefaultConstructor(String className, Collection<UMLVariable> vars)
   {
      super(className, "", "");
      this.vars = vars;
   }

   // TODO:Default isntanciate vars
   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      result.append("public " + getName() + "() {\n");
      result.append("\n\n   }\n");

      return result.toString();
   }

}
