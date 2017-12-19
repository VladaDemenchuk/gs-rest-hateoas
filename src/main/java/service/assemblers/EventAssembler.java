package service.assemblers;

import service.controllers.AttendeeController;
import service.controllers.EventsController;
import service.domain.Event;
import service.resources.AttendeeResource;
import service.resources.EventResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class EventAssembler extends ResourceAssemblerSupport<Event, EventResource> {

    private final RelProvider relProvider;


    @Autowired
    public EventAssembler(RelProvider relProvider) {
        super(Event.class, EventResource.class);
        this.relProvider = relProvider;
    }


    @Override
    public EventResource toResource(Event entity) {
        EventResource eventResource = new EventResource();
        eventResource.setIdentifier(entity.getIdentifier());
        eventResource.setName(entity.getName());

        eventResource.add(linkTo(methodOn(EventsController.class).event(entity.getIdentifier())).withSelfRel());

        final String attendeesRel = relProvider.getCollectionResourceRelFor(AttendeeResource.class);
        eventResource.add(linkTo(methodOn(AttendeeController.class).attendees(entity.getIdentifier())).withRel(attendeesRel));

        return eventResource;
    }

    public Resources<EventResource> toEmbeddedList(Iterable<Event> entities) {
        final List<EventResource> resources = toResources(entities);
        return new Resources<>(resources, linkTo(EventsController.class).withSelfRel());
    }
}
