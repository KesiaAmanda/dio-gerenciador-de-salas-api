package one.digitalinnovation.saladereuniao.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.saladereuniao.exception.ResourceNotFoundException;
import one.digitalinnovation.saladereuniao.model.Room;
import one.digitalinnovation.saladereuniao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/room")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById (@PathVariable Long id) throws ResourceNotFoundException {
        Room room = verifyIfExists(id);
        return ResponseEntity.ok().body(room);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id,
                                           @Valid @RequestBody Room newRoom) throws ResourceNotFoundException{
        Room room = verifyIfExists(id);
        room.setId(id);
        return ResponseEntity.ok().body(roomService.updateRoom(room));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException{
        Room room = verifyIfExists(id);
        roomService.deleteRoom(id);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Room verifyIfExists(Long id) throws ResourceNotFoundException{
        Room room = roomService.getRoomById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Room not found: "+ id));
        return room;
    }

}
