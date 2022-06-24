package org.sk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sk.entity.Entity;
import org.sk.service.SkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    private final SkService skService;

    @Autowired
    public Controller(SkService skService) {
        this.skService = skService;
    }

    @PostMapping("/modify")
    ResponseEntity<Object> add(@RequestBody String data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> result = mapper.readValue(data, Map.class);
        int id = result.get("id");
        int numberToAdd = result.get("add");

        Entity entityFromDB = skService.findById(id);
        if (entityFromDB == null)
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        entityFromDB.setCurrent(entityFromDB.getCurrent() + numberToAdd);
        Entity updated = skService.update(id, entityFromDB);
        return updated != null ? new ResponseEntity<>(mapper.writeValueAsString(updated.getObj()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}
