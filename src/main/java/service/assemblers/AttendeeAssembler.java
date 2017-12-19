package service.assemblers;

import service.controllers.AttendeeController;
import service.controllers.EventsController;
import service.domain.Attendee;
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
public class AttendeeAssembler extends ResourceAssemblerSupport<Attendee, AttendeeResource> {

    private final RelProvider relProvider;

    @Autowired
    public AttendeeAssembler(RelProvider relProvider) {
        super(Attendee.class, AttendeeResource.class);
        this.relProvider = relProvider;
    }

    @Override
    public AttendeeResource toResource(Attendee entity) {
        AttendeeResource attendeeResource = new AttendeeResource();
        attendeeResource.setIdentifier(entity.getIdentifier());
        attendeeResource.setNickname(entity.getNickname());
        attendeeResource.add(linkTo(methodOn(AttendeeController.class).attendee(entity.getIdentifier())).withSelfRel());

        final String eventsRel = relProvider.getCollectionResourceRelFor(EventResource.class);

        attendeeResource.add(linkTo(methodOn(EventsController.class).events(entity.getIdentifier())).withRel(eventsRel));

        return attendeeResource;
    }

    public Resources<AttendeeResource> toEmbeddedList(Iterable<Attendee> entities) {
        final List<AttendeeResource> resources = toResources(entities);
        return new Resources<>(resources, linkTo(AttendeeController.class).withSelfRel());
    }
}

