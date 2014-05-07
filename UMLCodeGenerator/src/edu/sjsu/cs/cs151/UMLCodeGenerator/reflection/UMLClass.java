package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

import java.util.Collection;
import java.util.HashSet;

import edu.sjsu.cs.cs151.UMLCodeGenerator.reflection.bookkeeping.DefaultConstructor;

public class UMLClass extends UMLType
{
   private Collection<UMLVariable> variables;

   public UMLClass(String name, String scope)
   {
      super(name, scope);
      methods.add(new DefaultConstructor(getName(), variables));
      variables = new HashSet<UMLVariable>();
   }

   public UMLClass(String name)
   {
      this(name, "public");
   }

   public String generateCode()
   {
      StringBuffer result = new StringBuffer();

      boolean first = true;

      for (String s : getImports())
      {
         result.append("import " + s + ";\n");
      }

      result.append(getScope() + " class " + getName());

      if (this.getParent() != null)
      {
         result.append(" extends " + this.getParent().getName());
      }
      processInterfaces(this.getInterfaces(), result);
      result.append(" {\n\n");
      processVariables(variables, result);
      result.append("\n");
      processMethods(methods, result);

      result.append("}\n");

      return result.toString();
   }

   private void processMethods(Collection<UMLMethod> methods,
         StringBuffer result)
   {
      for (UMLMethod method : methods)
         result.append("   " + method.generateCode());
   }

   private void processVariables(Collection<UMLVariable> vars,
         StringBuffer result)
   {
      for (UMLVariable var : vars)
         result.append("   " + var.generateCode());
   }

   private void processInterfaces(Collection<UMLInterface> ifaces,
         StringBuffer result)
   {
      boolean first = true;
      for (UMLInterface iface : ifaces)
      {
         if (first)
         {
            result.append(" implements");
            first = false;
         }
         result.append(" " + iface.getName() + ",");
      }
      if (ifaces.size() > 0)
         result.deleteCharAt(result.length() - 1);
   }

   public void addVariable(UMLVariable var)
   {
      variables.add(var);
   }
}
