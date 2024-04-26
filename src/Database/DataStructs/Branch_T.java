/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database.DataStructs;

import Main.SharedResources;

import java.util.ArrayList;
import java.util.UUID;

/**
 * database type for branch
 */
public class Branch_T implements IDatabaseItem_T {
    private UUID branchUUID;
    public ArrayList<MenuItem_T> menuItems;
    public ArrayList<User_T> normalStaffArr;
    public ArrayList<User_T> branchManagerArr;

    private String branchName;

    public Branch_T() { }

    /**
     * constructor of Branch_T
     * @param branchName name of the branch
     */
    public Branch_T(String branchName) {
        this.branchUUID = UUID.randomUUID();
        this.menuItems = new ArrayList<>();
        this.normalStaffArr = new ArrayList<>();
        this.branchManagerArr = new ArrayList<>();
        this.branchName = branchName;
    }

    /**
     * get arraylist of normal staff
     * @return arraylist of normal staff
     */
    public ArrayList<User_T> getNormalStaffArr() {
        return normalStaffArr;
    }

    /**
     * sets arraylist of normal staff
     * @param normalStaffArr arraylist of normal staff
     */
    public void setNormalStaffArr(ArrayList<User_T> normalStaffArr) {
        this.normalStaffArr = normalStaffArr;
    }

    /**
     * get arraylist of branch manager
     * @return arraylist of branch manager
     */
    public ArrayList<User_T> getBranchManagerArr() {
        return branchManagerArr;
    }

    /**
     * set arraylist of branch manager
     * @param branchManagerArr arraylist of branch manager
     */
    public void setBranchManagerArr(ArrayList<User_T> branchManagerArr) {
        this.branchManagerArr = branchManagerArr;
    }

    /**
     * get UUID of branch
     * @return UUID of branch
     */
    public UUID getBranchUUID() {
        return branchUUID;
    }

    /**
     * set UUID of branch
     * @param branchUUID UUID of branch
     */
    public void setBranchUUID(UUID branchUUID) {
        this.branchUUID = branchUUID;
    }

    /**
     * get name of branch
     * @return name of branch
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * set name of branch
     * @param branchName name of branch
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * get arraylist menu items
     * @return arraylist menu items
     */
    public ArrayList<MenuItem_T> getMenuItems() {
        return menuItems;
    }

    /**
     * set arraylist menu items
     * @param menuItems arraylist menu items
     */
    public void setMenuItems(ArrayList<MenuItem_T> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * remove menu item from name of menu item
     * @param menuItemName name of menu item
     * @return true if action is successful
     */
    public boolean removeMenuItem(String menuItemName) {
        MenuItem_T tempMenuItemT;
        for (int i = 0; i < this.menuItems.size(); i++) {
            tempMenuItemT = this.menuItems.get(i);
            if (
                    tempMenuItemT.getName().equals(menuItemName)
            ) {
                this.menuItems.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * removing menu item based on its index in the database
     * @param idx index of item in database
     * @return true if action is successful
     */
    public boolean removeMenuItem(int idx) {
        this.menuItems.remove(idx);
        return true;
    }

    /**
     * adds menu item
     * @param menuItemT name of menu item
     * @return true if action is successful
     */
    public boolean addMenuItem(MenuItem_T menuItemT) {
        MenuItem_T copyMenuItemT = new MenuItem_T(menuItemT);
        copyMenuItemT.setBranchUUID(this.branchUUID);

        if (!this.hasThisMenuItem(copyMenuItemT)) {
            this.menuItems.add(copyMenuItemT);
            return true;
        }
        return false;
    }

    /**
     * add normal staff to branch
     * @param userObj staff
     * @return true if action is successful
     */
    public boolean addNormalStaff(User_T userObj) {
        User_T tempUser;
        for (int i = 0; i < this.normalStaffArr.size(); i++) {
            tempUser = this.normalStaffArr.get(i);
            if(tempUser.getUsername().equals(userObj.getUsername())) {
                return false;
            }
        }

        this.normalStaffArr.add(userObj);
        return true;
    }

    /**
     * remove normal staff from branch
     * @param userObj staff object
     * @return true if action is successful
     */
    public boolean removeNormalStaff(User_T userObj) {
        User_T tempUser;
        for (int i = 0; i < this.normalStaffArr.size(); i++) {
            tempUser = this.normalStaffArr.get(i);
            if(tempUser.getUsername().equals(userObj.getUsername())) {
                this.normalStaffArr.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * add branch manager to branch
     * @param userObj branch manager object
     * @return true if action is successful
     */
    public boolean addBranchManager(User_T userObj) {
        User_T tempUser;
        for (int i = 0; i < this.branchManagerArr.size(); i++) {
            tempUser = this.branchManagerArr.get(i);
            if(tempUser.getUsername().equals(userObj.getUsername())) {
                return false;
            }
        }

        this.branchManagerArr.add(userObj);
        return true;
    }

    /**
     * remove branch manager from branch
     * @param userObj branch manger object
     * @return true if action is successful
     */
    public boolean removeBranchManger(User_T userObj) {
        User_T tempUser;
        for (int i = 0; i < this.branchManagerArr.size(); i++) {
            tempUser = this.branchManagerArr.get(i);
            if(tempUser.getUsername().equals(userObj.getUsername())) {
                this.branchManagerArr.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * query is the item is in the branch's menu
     * @param menuItemPartialT partial match within menu item object
     * @return true if item is in the branch's menu
     */
    public boolean hasThisMenuItem(MenuItem_T menuItemPartialT) {
        MenuItem_T tempMenuItemT;

        for (int i = 0; i < this.menuItems.size(); i++) {
            tempMenuItemT = this.menuItems.get(i);

            if (
                    tempMenuItemT.getName().equals(menuItemPartialT.getName())
            ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addMeToDB() {
        if (SharedResources.getBranchDBHelper().isInDatabase(this)) return false;

        MenuItem_T tempMenuItemT;
        for (int i = 0; i < menuItems.size(); i++) {
            tempMenuItemT = menuItems.get(i);
            tempMenuItemT.addMeToDB();
        }
        return SharedResources.getBranchDBHelper().addToDatabase(this);
    }

    /**
     * get if item is in the database
     * @return true if item is in database
     */
    public boolean isInDB() {
        return SharedResources.getBranchDBHelper().isInDatabase(this);
    }

    /**
     * a string representation of the object
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "Branch_T{" +
                "branchId=" + branchUUID +
                ", menuItems=" + menuItems +
                ", branchName='" + branchName + '\'' +
                '}';
    }

    /**
     * a pretty string representation of the object
     * @return pretty string representation of the object
     */
    @Override
    public String prettyPrint() {
        return this.getBranchName();
    }
}
