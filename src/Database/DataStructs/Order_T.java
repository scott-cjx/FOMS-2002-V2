/**
 * @author Scott CJX
 */

package Database.DataStructs;

import Main.SharedResources;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * database type for order
 */
public class Order_T implements IDatabaseItem_T {
    private boolean isTakeaway;
    private UUID orderId;
    private UUID branchId;
    private UUID customerId;
    private Timestamp ts;
    private ArrayList<MenuItem_T> menuItems;
    private ArrayList<Integer> menuItemsQuantity;
    private OrderStatus orderStatus;

    /**
     * if the order is empty
     * @return true if the order is empty
     */
    public boolean isEmpty() {
        return (this.menuItems.isEmpty());
    }

    /**
     * default constructor for order
     */
    public Order_T() {
        this.orderId = UUID.randomUUID();
        this.ts = new Timestamp(System.currentTimeMillis());
        this.menuItems = new ArrayList<>();
        this.menuItemsQuantity = new ArrayList<>();
    }

    /**
     * method to add menu item to the order
     * @param menuItemT menu item from branch
     * @param quantity number of items to add
     * @return true if action is successful
     */
    public boolean addItemToOrder(MenuItem_T menuItemT, int quantity) {

        MenuItem_T itemTemp;
        for (int i = 0; i < this.menuItems.size(); i++) {
            itemTemp = (MenuItem_T) this.menuItems.get(i);
            if (menuItemT.getMenuItemUUID().equals(itemTemp.getMenuItemUUID())) {
                System.out.println("item already in cart");
                return false;
            }
        }

        this.menuItems.add(menuItemT);
        this.menuItemsQuantity.add(quantity);

        return true;
    }

    /**
     * get the list of quantity of items in order
     * @return list of quantity of items in order
     */
    public ArrayList<Integer> getMenuItemsQuantity() {
        return menuItemsQuantity;
    }

    /**
     * set quantity of items in menu order
     * @param menuItemsQuantity quantity of items in menu order
     */
    public void setMenuItemsQuantity(ArrayList<Integer> menuItemsQuantity) {
        this.menuItemsQuantity = menuItemsQuantity;
    }

    /**
     * get the status of the order
     * @return status of the order
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * set status of the order
     * @param orderStatus status of the order
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * get id of order
     * @return id of order
     */
    public UUID getOrderId() {
        return orderId;
    }

    /**
     * set id of order
     * @param orderId id of order
     */
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    /**
     * get id of branch
     * @return id of branch
     */
    public UUID getBranchId() {
        return branchId;
    }

    /**
     * set id of branch
     * @param branchId id of branch
     */
    public void setBranchId(UUID branchId) {
        this.branchId = branchId;
    }

    /**
     * get id of customer
     * @return id of customer
     */
    public UUID getCustomerId() {
        return customerId;
    }

    /**
     * get id of customer
     */
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    /**
     * gets time stamp of order
     * @return time stamp of order
     */
    public Timestamp getTs() {
        return ts;
    }

    /**
     * set time stamp of order
     * @param ts time stamp of order
     */
    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    /**
     * get menu items in the order
     * @return menu items in the order
     */
    public ArrayList<MenuItem_T> getMenuItems() {
        return menuItems;
    }

    /**
     * sets menu items in the order
     * @param menuItems menu items in the order
     */
    public void setMenuItems(ArrayList<MenuItem_T> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * get string representation of order
     * @return string representation of order
     */
    @Override
    public String toString() {
        return "Order_T{" +
                "isTakeaway=" + isTakeaway +
                ", orderId=" + orderId +
                ", branchId=" + branchId +
                ", customerId=" + customerId +
                ", ts=" + ts +
                ", menuItems=" + menuItems +
                ", menuItemsQuantity=" + menuItemsQuantity +
                ", orderStatus=" + orderStatus +
                '}';
    }

    /**
     * pretty string representation of order
     * @return pretty string representation of order
     */
    @Override
    public String prettyPrint() {
        return this.toString();
    }

    /**
     * representation of order in receipt
     * @return
     */
    public String printReceipt() {
        String ret = "";
        if (menuItems.size() < 1) {
            ret += "Your Cart Is Empty...";
            return ret;
        }

        ret += "Order ID: " + this.orderId.toString() + "\n";
        if (this.isTakeaway) {
            ret += "=> Takeaway \n";
        } else {
            ret += "=> Dine In \n";
        }

        for (int i = 0; i < menuItems.size(); i++) {
            ret += (
                    (i+1) +
                            ": " + menuItems.get(i).getName() +
                            "\t -> " + menuItemsQuantity.get(i) +
                            " * $" + menuItems.get(i).getPrice() +
                            "\n"
            );
        }
        return ret;
    }

    /**
     * get if order is takeaway
     * @return true if order is takeaway
     */
    public boolean isTakeaway() {
        return isTakeaway;
    }

    /**
     * set if order is takeaway
     * @param takeaway if order is takeaway
     */
    public void setTakeaway(boolean takeaway) {
        isTakeaway = takeaway;
    }

    /**
     * print the order
     * @return order in string format
     */
    public String printOrder() {
        String ret = "";
        for (int i = 0; i < menuItems.size(); i++) {
            ret += (
                (i+1) +
                ": " + menuItems.get(i).getName() +
                "\t -> " + menuItemsQuantity.get(i) +
                " * $" + menuItems.get(i).getPrice() +
                "\n"
            );
        }

        if (menuItems.size() < 1) {
            ret += "Your Cart Is Empty...";
        }

        return ret;
    }

    @Override
    public boolean addMeToDB() {
        return SharedResources.getOrderDBHelper().addToDatabase(this);
    }
}
