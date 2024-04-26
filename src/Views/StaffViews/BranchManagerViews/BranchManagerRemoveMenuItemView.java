/**
 @author Scott Cheng
 @author Jung Kit
 @version 1.0
 @since 2024-04-22
 */

package Views.StaffViews.BranchManagerViews;

import Backend.Branch;
import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.MenuViews.MenuDisplayView;
import Views.UIQueryView;
import Views.UIView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * view for branch manager to remove menu item
 */
public class BranchManagerRemoveMenuItemView extends UIQueryView {
    private MenuItem_T menuItemT;
    private int itemIdx;

    private Branch_T branchT;

    public BranchManagerRemoveMenuItemView() {
        this.myViewName = this.getClass().getCanonicalName();
    }
    @Override
    public void query() {

        MenuDisplayView menuDisplayView = new MenuDisplayView(true);
        menuDisplayView.show();

        Scanner sc = new Scanner(System.in);

        System.out.print("Removing item > ");
        itemIdx = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {

        if (this.itemIdx < 1 || this.itemIdx > SharedResources.getMenuDBHelper().getAllFromDatabase().size()) {
            System.out.println("Not valid item");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.menuItemT = (MenuItem_T) SharedResources.getMenuDBHelper().getFromDatabase(this.itemIdx-1);
        this.branchT = SharedResources.getCurrentStaffBranchT();

        int idx = SharedResources.getMenuDBHelper().idxInDatabase_itemUUID(menuItemT, true);
        branchT.removeMenuItem(idx);
        System.out.println("item removed!");
        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}