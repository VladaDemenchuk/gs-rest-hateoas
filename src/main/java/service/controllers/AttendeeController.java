package service.controllers;

import service.assemblers.AttendeeAssembler;
import service.repository.SimpleRepository;
import service.resources.AttendeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;


@RestController
@ExposesResourceFor(AttendeeResource.class)
@RequestMapping("/attendees")
public class AttendeeController {

    @Autowired
    private SimpleRepository simpleRepository;

    @Autowired
    private AttendeeAssembler attendeeAssembler;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Resources<AttendeeResource> attendees() {
        return attendeeAssembler.toEmbeddedList(simpleRepository.getAttendees().values());
    }

    @RequestMapping(path = "/event/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resources<AttendeeResource> attendees(@PathVariable Integer id) {
        return attendeeAssembler.toEmbeddedList(simpleRepository.getAttendeesByEvent(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AttendeeResource attendee(@PathVariable Integer id) {
        return attendeeAssembler.toResource(simpleRepository.getAttendees().get(id));
    }
}
