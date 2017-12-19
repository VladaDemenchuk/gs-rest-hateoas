package service.controllers;

import service.assemblers.EventAssembler;
import service.repository.SimpleRepository;
import service.resources.EventResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(EventResource.class)
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private SimpleRepository simpleRepository;

    @Autowired
    private EventAssembler eventAssembler;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Resources<EventResource> events() {
        return eventAssembler.toEmbeddedList(simpleRepository.getEvents().values());
    }

    @RequestMapping(path = "/attendee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resources<EventResource> events(@PathVariable Integer id) {
        return eventAssembler.toEmbeddedList(simpleRepository.getEventsByAttendee(id));
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public EventResource event(@PathVariable Integer id) {
        return eventAssembler.toResource(simpleRepository.getEvents().get(id));
    }


}
