package service.assemblers;

import service.resources.AttendeeResource;
import service.resources.EventResource;
import service.resources.EntryPointResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class EntryPointAssembler {

    private final RelProvider relProvider;
    private final EntityLinks entityLinks;

    @Autowired
    public EntryPointAssembler(RelProvider relProvider, EntityLinks entityLinks) {
        this.relProvider = relProvider;
        this.entityLinks = entityLinks;
    }

    public EntryPointResource buildEntryPoint() {
        final List<Link> links = asList(
                entityLinks.linkToCollectionResource(EventResource.class).withRel(relProvider.getCollectionResourceRelFor(EventResource.class)),
                entityLinks.linkToCollectionResource(AttendeeResource.class).withRel(relProvider.getCollectionResourceRelFor(AttendeeResource.class))
        );
        final EntryPointResource resource = new EntryPointResource("App One", "Developed by Vlada Demenchuk");
        resource.add(links);
        return resource;
    }
}
