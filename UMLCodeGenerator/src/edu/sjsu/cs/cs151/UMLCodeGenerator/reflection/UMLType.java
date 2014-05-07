package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class UMLType extends UMLElement
{
   protected Set<UMLMethod> methods;
   protected Set<UMLInterface> interfaces;
   protected Set<String> imports;
   protected UMLType parent;

   public UMLType(String name, String scope)
   {
      super(name, scope);
      methods = new LinkedHashSet<UMLMethod>();
      interfaces = new HashSet<UMLInterface>();
      imports = new HashSet<String>();
   }

   public void addMethod(UMLMethod m)
   {
      methods.add(m);
   }

   public void addInterface(UMLInterface i)
   {
      interfaces.add(i);
   }

   public void addImport(String s)
   {
      imports.add(s);
   }

   public void setParent(UMLType c)
   {
      parent = c;
   }

   protected Collection<UMLInterface> getInterfaces()
   {
      return interfaces;
   }

   protected Collection<UMLMethod> getMethods()
   {
      return methods;
   }

   protected Collection<String> getImports()
   {
      return imports;
   }

   public UMLType getParent()
   {
      return parent;
   }
}
