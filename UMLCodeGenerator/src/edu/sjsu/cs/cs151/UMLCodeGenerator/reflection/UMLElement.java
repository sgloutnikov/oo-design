package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

public abstract class UMLElement
{
   private String name;
   private String scope;

   public UMLElement()
   {
      name = "";
   }

   public UMLElement(String name, String scope)
   {
      this.name = name;
      this.scope = scope;
   }

   public String generateCode()
   {
      String result = "";

      return result;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   protected String getScope()
   {
      return scope;
   }
   
   public void setScope(String scope)
   {
      this.scope = scope;
   }

   public boolean equals(Object o)
   {
      if (o instanceof UMLElement)
         return this.name.equals(((UMLElement) o).getName());
      else
         return false;
   }
}
