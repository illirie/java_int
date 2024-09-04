package homework.hw4.controller;

import homework.hw4.model.Room;
import homework.hw4.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping(value = "/room/")
    public List<Room> getAllRooms() {
        return (List<Room>) roomService.findAll();
    }


}
