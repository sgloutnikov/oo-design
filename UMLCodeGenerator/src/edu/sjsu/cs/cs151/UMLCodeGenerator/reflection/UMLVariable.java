package edu.sjsu.cs.cs151.UMLCodeGenerator.reflection;

public class UMLVariable extends UMLElement
{
   private String type;
   private String initValue;

   public UMLVariable(String name, String scope, String type, String initValue)
   {
      super(name, scope);
      this.type = type;
      this.initValue = initValue;
   }
   
   public UMLVariable(String name, String scope, String type)
   {
      setName(name);
      setScope(scope);
      this.type = type;
      
      if (type.equals("int") || type.equals("short")
            || type.equals("byte") || type.equals("long")
            || type.equals("double") || type.equals("float")
            || type.equals("float"))
         initValue = "0";
      else if (type.equals("boolean"))
         initValue = "false";
      else if (!type.equals("void"))
         initValue = "null";      
   }

   public UMLVariable(String name, String type)
   {
      super(name, "");
      this.type = type;
   }

   public String getType()
   {
      return type;
   }

   public String getInitValue()
   {
      return initValue;
   }

   public String generateCode()
   {
      StringBuffer sbuf = new StringBuffer();
      if(this.getScope() != "" && this.getScope() != null)
         sbuf.append(this.getScope() + " ");
      if(this.getType() != "" && this.getType() != null)
         sbuf.append(this.getType() + " ");
      sbuf.append(this.getName());
      if (initValue != null && initValue.length() > 0)
         sbuf.append(" = " + this.getInitValue());

      sbuf.append(";\n");
      return sbuf.toString();
   }
}
