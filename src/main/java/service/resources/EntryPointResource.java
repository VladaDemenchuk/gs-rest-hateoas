package service.resources;

import org.springframework.hateoas.ResourceSupport;

public class EntryPointResource extends ResourceSupport {

   private final String name;
   private final String description;

   public EntryPointResource(String name, String description) {
      super();
      this.name = name;
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public String getName() {
      return name;
   }
   
   
}
