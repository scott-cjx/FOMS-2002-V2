/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database;

import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;

/**
 * helper for menu database
 */
public class MenuDBHelper extends DatabaseHelper {
    public MenuDBHelper(String databaseString) {
        super(databaseString);
    }

    public boolean isInDatabase(MenuItem_T menuItemPartial) {
        MenuItem_T menuItemTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            menuItemTemp = (MenuItem_T) this.databaseObject.get(i);
            if (menuItemPartial.getMenuItemUUID().equals(menuItemTemp.getMenuItemUUID())) {
                return true;
            }
        }
        return false;
    }

    public int idxInDatabase_itemUUID(MenuItem_T menuItemPartial, boolean getFirst) {
        int idx = -1;
        MenuItem_T itemTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            itemTemp = (MenuItem_T) this.databaseObject.get(i);
            if (menuItemPartial.getMenuItemUUID().equals(itemTemp.getMenuItemUUID())) {
                idx = i;
                if (getFirst) {
                    return idx;
                }
            }
        }
        return idx;
    }

    public boolean addToDatabase(MenuItem_T menuItemFull) {
        if (this.isInDatabase(menuItemFull)) {
//            menuItemId exists
            return false;
        }
        return this.databaseObject.add(menuItemFull);
    }
}
