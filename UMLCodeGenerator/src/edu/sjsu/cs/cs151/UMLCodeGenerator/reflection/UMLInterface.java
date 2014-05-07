package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

import java.util.Collection;

public class UMLInterface extends UMLType
{
   private Collection<UMLInterface> interfaces;
   private Collection<UMLMethod> methods;

   public UMLInterface(String name, String scope)
   {
      super(name, scope);
   }

   public UMLInterface(String name)
   {
      this(name, "public");
   }

   public String generateCode()
   {
      interfaces = getInterfaces();
      methods = getMethods();

      StringBuffer sb = new StringBuffer();

      for (String s : getImports())
      {
         sb.append("import " + s + ";\n");
      }

      sb.append(this.getScope() + " interface " + this.getName());
      if (interfaces.size() == 0)
      {
         sb.append(" {\n\n");
      } else
      {
         sb.append(" extends");
         for (UMLInterface i : interfaces)
         {
            sb.append(" " + i.getName() + ",");
         }
         sb.deleteCharAt(sb.length() - 1);
         sb.append(" {\n\n");
      }

      for (UMLMethod m : methods)
      {
         sb.append("   " + m.getMethodDeclaration() + ";\n");
      }
      sb.append("\n}\n");

      return sb.toString();
   }

}
