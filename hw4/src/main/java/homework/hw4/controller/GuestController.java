package homework.hw4.controller;

import homework.hw4.model.Banned;
import homework.hw4.model.Guest;
import homework.hw4.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestController {
    @Autowired
    private GuestService guestService;

    @GetMapping(value = "/guest/")
    public List<Guest> showGuest() {
        return (List<Guest>) guestService.findAll();
    }

    @GetMapping(value = "/guest/{id}")
    public Guest getGuest(@PathVariable int id) {
        return guestService.findById(id).orElse(null);
    }

    @GetMapping(value = "/guest/{id}/delete")
    public void deleteGuest(@PathVariable int id) {
        guestService.deleteById(id);
    }



}
