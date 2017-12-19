package service.controllers;

import service.assemblers.EntryPointAssembler;
import service.resources.EntryPointResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EntryPointController {

    private final EntryPointAssembler entryPointAssembler;

    @Autowired
    public EntryPointController(EntryPointAssembler entryPointAssembler) {
        this.entryPointAssembler = entryPointAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public EntryPointResource entryPoint() {
        return entryPointAssembler.buildEntryPoint();
    }
}
