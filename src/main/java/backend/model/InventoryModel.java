package backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class InventoryModel {
    @Id
    @GeneratedValue
    private Long Id;
    private String itemId;
    private String itemName;
    private String itemImage;
    private String itemCategory;
    private String itemQty;
    private String itemDetails;

    //create public class for display function
    public InventoryModel(){

    }
// constructor
    public InventoryModel(Long id, String itemId,String itemName, String itemImage, String itemCategory, String itemQty, String itemDetails) {
        Id = id;
        this.itemId = itemId;
        this.itemName=itemName;
        this.itemImage = itemImage;
        this.itemCategory = itemCategory;
        this.itemQty = itemQty;
        this.itemDetails = itemDetails;
    }

    // create getters and setters to use in update function


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }


    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

}
