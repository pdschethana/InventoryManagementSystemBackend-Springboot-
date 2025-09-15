/*package backend.exception;

public class InventoryNotFoundException extends RuntimeException{
    public InventoryNotFoundException(Long id){
        super("could not find id " +id);
    }
    public InventoryNotFoundException(String message){
        super(message);
    }
}*/

package backend.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(Long id) {
        super("Could not find item with id " + id);
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }
}

