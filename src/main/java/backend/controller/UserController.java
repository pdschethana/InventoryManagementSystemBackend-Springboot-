package backend.controller;
import backend.exception.InventoryNotFoundException;
import backend.model.InventoryModel;
import backend.model.UserModel;
import backend.repository.InventoryRepository;
import backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
     private UserRepository userRepository;
    //insert
    @PostMapping("/user")
    public UserModel newUserModel(@RequestBody UserModel newUserModel){
        return userRepository.save(newUserModel);
    }
}
