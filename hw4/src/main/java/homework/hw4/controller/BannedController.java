package homework.hw4.controller;

import homework.hw4.model.Banned;
import homework.hw4.service.BannedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BannedController {
    @Autowired
    private BannedService bannedService;

    @GetMapping(value = "/banned/")
    public List<Banned> showBanned() {
        return (List<Banned>) bannedService.findAll();
    }

    @GetMapping(value = "/banned/{id}")
    public Banned getBanned(@PathVariable int id) {
        return bannedService.findById(id).orElse(null);
    }

    @GetMapping(value = "/banned/{id}/delete")
    public void deleteBanned(@PathVariable int id) {
        bannedService.deleteById(id);
    }



}
