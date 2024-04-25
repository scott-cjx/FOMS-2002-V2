/**
 Represents a MenuItem
 @author Scott Cheng
 @version 1.0
 @since 2024-03-22
 */

package Database.DataStructs;

import Main.SharedResources;

import java.util.UUID;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getBranchUUID() {
        return branchUUID;
    }

    public void setBranchUUID(UUID branchUUID) {
        this.branchUUID = branchUUID;
    }

    public UUID getMenuItemUUID() {
        return menuItemUUID;
    }

    public void setMenuItemUUID(UUID menuItemUUID) {
        this.menuItemUUID = menuItemUUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CATEGORIES getCategory() {
        return category;
    }

    public void setCategory(CATEGORIES category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public AVAILABILITY getAvailability() {
        return availability;
    }

    public void setAvailability(AVAILABILITY availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "MenuItem_T{" +
                "availability=" + availability.toString() +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
//                ", branchUUID=" + branchUUID +
                '}';
    }

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

    @Override
    public boolean addMeToDB() {
        return SharedResources.getMenuDBHelper().addToDatabase(this);
    }
}
