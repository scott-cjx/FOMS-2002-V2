package Views.StaffViews.BranchManagerViews;

import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.UIQueryView;
import Views.MenuViews.MenuDisplayView;

import java.util.Scanner;

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

        int idx = SharedResources.getMenuDBHelper().idxInDatabase_itemUUID(menuItemT, true);
        SharedResources.getMenuDBHelper().removeFromDatabase(idx);
        System.out.println("item removed!");
        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
