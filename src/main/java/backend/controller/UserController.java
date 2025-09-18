/*package backend.controller;
import backend.exception.InventoryNotFoundException;
import backend.exception.UserNotFoundException;
import backend.model.InventoryModel;
import backend.model.UserModel;
import backend.repository.InventoryRepository;
import backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
/*import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //insert
    @PostMapping("/user")
    public UserModel newUserModel(@RequestBody UserModel newUserModel) {
        return userRepository.save(newUserModel);
    }

    //user Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel loginDetails) {
        UserModel user = userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email not found :" + loginDetails.getEmail()));
        // check the pw is matches
        if (user.getPassword().equals(loginDetails.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successfull");
            response.put("id", user.getId());// return user id
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Message", "invalid credentials"));


        }

}
}


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel loginDetails) {
        UserModel user = userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email not found :" + loginDetails.getEmail()));

        // check if the password matches
        if (user.getPassword().equals(loginDetails.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId()); // return user id
            return ResponseEntity.ok(response); // ✅ success case
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials")); // ✅ failure case
        }
    }
    // Display
    @GetMapping
    List<UserModel>getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    UserModel getUserId(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    // update
    @PutMapping("/user/{id}")
    public UserModel updateProfile(@RequestBody UserModel newUserModel, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(userModel -> {
                    userModel.setFullName(newUserModel.getFullName());
                    userModel.setEmail(newUserModel.getEmail());
                    userModel.setPassword(newUserModel.getPassword());
                    userModel.setPhone(newUserModel.getPhone());
                    return userRepository.save(userModel);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }
// delete
   @DeleteMapping("/user/{id}")
    String deleteProfile(@PathVariable Long id){
        if(! userRepository.existsById(id)){
            throw new InventoryNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user account" +id+ "deleted";
    }
// check email
    @GetMapping("/checkEmail")
    public boolean checkEmailExist(@RequestParam String email){
        return userRepository.existByEmail(email);
    }

}*/


package backend.controller;

import backend.exception.InventoryNotFoundException;
import backend.exception.UserNotFoundException;
import backend.model.UserModel;
import backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000") // ✅ allow React frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Register new user
    @PostMapping("/user")
    public UserModel createUser(@RequestBody UserModel user) {
        return userRepository.save(user);
    }

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserModel loginDetails) {
        UserModel user = userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Email not found: " + loginDetails.getEmail()));

        if (user.getPassword().equals(loginDetails.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }
    }

    // ✅ Get all users
    @GetMapping
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    @GetMapping("/user/{id}")
    public UserModel getUserId(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // ✅ Update profile
    @PutMapping("/user/{id}")
    public UserModel updateProfile(@RequestBody UserModel newUserModel, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(userModel -> {
                    userModel.setFullName(newUserModel.getFullName());
                    userModel.setEmail(newUserModel.getEmail());
                    userModel.setPassword(newUserModel.getPassword());
                    userModel.setPhone(newUserModel.getPhone());
                    return userRepository.save(userModel);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // ✅ Delete profile
    @DeleteMapping("/user/{id}")
    public String deleteProfile(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new InventoryNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User account " + id + " deleted";
    }

    // ✅ Check if email exists
    @GetMapping("/checkEmail")
    public Map<String, Boolean> checkEmailExist(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        return Map.of("exists", exists);
    }
}



