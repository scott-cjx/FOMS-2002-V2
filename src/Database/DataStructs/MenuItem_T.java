/**
 Represents a MenuItem
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database.DataStructs;

import Main.SharedResources;

import java.util.UUID;

/**
 * database type for menu item
 */
public class MenuItem_T implements IDatabaseItem_T {

    /**
     * MenuItem category
     */
    public enum CATEGORIES {
        DRINK,
        NA,
        SET_MEAL,
        SIDE

    }

    /**
     * is item available?
     */
    public enum AVAILABILITY {
        AVAILABLE,
        NA,
        NOT_AVAILABLE
    }

    private UUID menuItemUUID;
    ;
    private float price;
    private AVAILABILITY availability;
    private String description;
    private String name;
    private CATEGORIES category;
    private UUID branchUUID;
    public MenuItem_T() {}
    public MenuItem_T(MenuItem_T obj) {
        this.menuItemUUID = obj.menuItemUUID;
        this.price = obj.price;
        this.availability = obj.availability;
        this.description = obj.description;
        this.name = obj.name;
        this.category = obj.category;
        this.branchUUID = obj.branchUUID;
    }
    public MenuItem_T(float price, AVAILABILITY availability, String description, String name, CATEGORIES category, UUID branchUUID) {
        this.price = price;
        this.availability = availability;
        this.description = description;
        this.name = name;
        this.category = category;
        this.branchUUID = branchUUID;
    }
    public MenuItem_T(float price, AVAILABILITY availability, String description, String name, CATEGORIES category) {
        this.menuItemUUID = UUID.randomUUID();
        this.price = price;
        this.availability = availability;
        this.description = description;
        this.name = name;
        this.category = category;
    }
    public MenuItem_T(UUID menuItemUUID, float price, AVAILABILITY availability, String description, String name, CATEGORIES category) {
        this.menuItemUUID = menuItemUUID;
        this.price = price;
        this.availability = availability;
        this.description = description;
        this.name = name;
        this.category = category;
    }

    /**
     * get name of the menu item
     * @return name of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * set name of the menu item
     * @param name name of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get uuid of branch
     * @return uuid of branch
     */
    public UUID getBranchUUID() {
        return branchUUID;
    }

    /**
     * set uuid of branch
     * @param branchUUID uuid of branch
     */
    public void setBranchUUID(UUID branchUUID) {
        this.branchUUID = branchUUID;
    }

    /**
     * get uuid of the menu item
     * @return uuid of the menu item
     */
    public UUID getMenuItemUUID() {
        return menuItemUUID;
    }

    /**
     * get the description of the menu item
     * @return description of the menu item
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description of the menu item
     * @param description description of the menu item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get item category
     * @return item category
     */
    public CATEGORIES getCategory() {
        return category;
    }

    /**
     * set item category
     * @param category item category
     */
    public void setCategory(CATEGORIES category) {
        this.category = category;
    }

    /**
     * get price of item
     * @return price of item
     */
    public float getPrice() {
        return price;
    }

    /**
     * set price of item
     * @param price price of item
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * get the Availability of item
     * @return Availability of item
     */
    public AVAILABILITY getAvailability() {
        return availability;
    }

    /**
     * set Availability of item
     * @param availability Availability of item
     */
    public void setAvailability(AVAILABILITY availability) {
        this.availability = availability;
    }

    /**
     * string representation of the item
     * @return string representation of the item
     */
    @Override
    public String toString() {
        return "MenuItem_T{" +
                ", availability=" + availability.toString() +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
//                ", category=" + category +
                ", branchUUID=" + branchUUID +
                '}';
    }

    /**
     * pretty string representation of the item
     * @return pretty string representation of the item
     */
    @Override
    public String prettyPrint() {
        String ret;
        ret = "$" + String.format("%.2f", price);

        if (this.getAvailability() == AVAILABILITY.NOT_AVAILABLE) {
            ret += "\t(Not Available)";
        }

        ret += "\t" + name;

        return ret;
    }

    /**
     * add item to database
     * @return true if successful
     */
    @Override
    public boolean addMeToDB() {
        return SharedResources.getMenuDBHelper().addToDatabase(this);
    }
}
