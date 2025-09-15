/*package backend.controller;

import backend.model.InventoryModel;
import backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import java.nio.file.Paths;

//insert
@RestController
@CrossOrigin("http://localhost:3005")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;
    @PostMapping("/inventory")
    public InventoryModel newInventoryModel(@RequestBody InventoryModel newInventoryModel){
        return inventoryRepository.save(newInventoryModel);
    }
    @PostMapping("/inventory/itemImg")
    public String itemImage(@RequestParam("file")MultipartFile file){
        String folder="src/main/uploads/";
        String itemImage = file.getOriginalFilename();
        try{
            File uploadDir=new File(folder);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            file.transferTo(Paths.get(folder+itemImage));
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file;" +itemImage;
        }
        return itemImage;
    }
}*/

/*package backend.controller;

import backend.model.InventoryModel;
import backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
// allow frontend React app
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Insert new inventory
    @PostMapping("/inventory")
    public InventoryModel newInventoryModel(@RequestBody InventoryModel newInventoryModel) {
        return inventoryRepository.save(newInventoryModel);
    }

    // Upload item image
    @PostMapping("/inventory/itemImg")
    public String itemImage(@RequestParam("file") MultipartFile file) {
        String folder = "uploads/"; // create uploads folder at project root
        String itemImage = file.getOriginalFilename();

        try {
            File uploadDir = new File(folder);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // create directory if missing
            }

            File destination = new File(uploadDir, itemImage);
            file.transferTo(destination);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + itemImage;
        }

        return itemImage; // return only filename
    }
}*/


/*package backend.controller;

import backend.model.InventoryModel;
import backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3006")
// allow frontend React app
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Insert new inventory
    @PostMapping("/inventory")
    public InventoryModel newInventoryModel(@RequestBody InventoryModel newInventoryModel) {
        return inventoryRepository.save(newInventoryModel);
    }

    // Upload item image
    @PostMapping("/inventory/itemImg")
    public String itemImage(@RequestParam("file") MultipartFile file) {
        // ✅ Save under src/main/uploads
        String folder = System.getProperty("user.dir") + "/src/main/uploads/";
        String itemImage = file.getOriginalFilename();

        try {
            File uploadDir = new File(folder);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // create folder if missing
            }

            File destination = new File(uploadDir, itemImage);
            file.transferTo(destination);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + itemImage;
        }

        return itemImage; // return only filename
    }
}*/

package backend.controller;

import backend.exception.InventoryNotFoundException;
import backend.model.InventoryModel;
import backend.repository.InventoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // allow frontend React app
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Insert new inventory
    @PostMapping("/inventory")
    public InventoryModel newInventoryModel(@RequestBody InventoryModel newInventoryModel) {
        return inventoryRepository.save(newInventoryModel);
    }

    // Upload item image
    @PostMapping("/inventory/itemImg")
    public String itemImage(@RequestParam("file") MultipartFile file) {
        // ✅ Save under src/main/uploads
        String folder = System.getProperty("user.dir") + "/src/main/uploads/";
        String itemImage = file.getOriginalFilename();

        try {
            File uploadDir = new File(folder);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // create folder if missing
            }

            File destination = new File(uploadDir, itemImage);
            file.transferTo(destination);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + itemImage;
        }

        // ✅ Return just the filename
        return itemImage;
    }

    // Display Data

    @GetMapping("/inventory")
    List<InventoryModel> getAllItems() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/inventory/{id}")
    InventoryModel getItemId(@PathVariable Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
    }

    private final String UPLOAD_DIR = "src/main/uploads/";

    @GetMapping("/uploads/{filename}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String filename) {
        File file = new File(UPLOAD_DIR + filename);
        if (!file.exists()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new FileSystemResource(file));

    }

    // update data
   /* @PostMapping("/inventory/{id}")
    public InventoryModel updateItem(
            @RequestPart(value="itemDetaills") String itemDetails,
            @RequestPart(value="file",required = false)MultipartFile file,
            @PathVariable Long id
    ){
        System.out.println("Item Details:" +itemDetails);
        id(file!=null){
            System.out.println("File received:" +file.getOriginalFilename());
        }else{
            System.out.println("No file uploaded");
        }
        ObjectMapper mapper=new ObjectMapper();
        InventoryModel newInventory;
        try{}
        newInventory=mapper.readValue(itemDetails,InventoryModel.class);
    }catch(Exception e){
        throw new RuntimeException("Error Passing Details", e);
    }
    return inventoryRepository.findById(id).map(existingInventory ->{
        existingInventory.setItemId(newInventory.getItemId());
        existingInventory.setItemName(newInventory.getItemName());
        existingInventory.setItemCategory(newInventory.getItemCategory());
        existingInventory.setItemQty(newInventory.getItemQty());
        existingInventory.setItemDetails(newInventory.getItemDetails());

        if(file !=null && !file.isEmpty()){
            String folder="src/main/uploads/";
            String itemImage= file.getOriginalFilename();
            try{
                file.transferTo(Paths.get(folser + itemImage));
                existingInventory.setItemImage(itemImage);

            }catch (IOException e){
                throw new RuntimeException("Error saving uploaded file",e);
            }

        }
        return inventoryRepository.save(existingInventory);
    }).orElseThrow(()-> new InventoryNotFoundException(id));

}*/

// update function

    @PutMapping("/inventory/{id}")
    public InventoryModel updateItem(
            @RequestPart(value = "itemDetails") String itemDetails,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @PathVariable Long id
    ) {
        System.out.println("Item Details: " + itemDetails);
        if (file != null) {
            System.out.println("File received: " + file.getOriginalFilename());
        } else {
            System.out.println("No file uploaded");
        }

        ObjectMapper mapper = new ObjectMapper();
        InventoryModel newInventory;
        try {
            newInventory = mapper.readValue(itemDetails, InventoryModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing details", e);
        }

        return inventoryRepository.findById(id).map(existingInventory -> {
            existingInventory.setItemId(newInventory.getItemId());
            existingInventory.setItemName(newInventory.getItemName());
            existingInventory.setItemCategory(newInventory.getItemCategory());
            existingInventory.setItemQty(newInventory.getItemQty());
            existingInventory.setItemDetails(newInventory.getItemDetails());

            if (file != null && !file.isEmpty()) {
                String folder = System.getProperty("user.dir") + "/src/main/uploads/";
                String itemImage = file.getOriginalFilename();

                try {
                    File uploadDir = new File(folder);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    file.transferTo(new File(folder + itemImage));
                    existingInventory.setItemImage(itemImage);
                } catch (IOException e) {
                    throw new RuntimeException("Error saving uploaded file", e);
                }
            }

            return inventoryRepository.save(existingInventory);
        }).orElseThrow(() -> new InventoryNotFoundException(id));
    }
}


