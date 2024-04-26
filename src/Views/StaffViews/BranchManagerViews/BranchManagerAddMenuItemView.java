/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.BranchManagerViews;

import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Main.SharedResources;
import Views.UIQueryView;

import java.util.Scanner;

/**
 * view for branch manager to add manu item to branch
 */
public class BranchManagerAddMenuItemView extends UIQueryView {
    private MenuItem_T menuItemT;
    private String itemName;
    private String itemDesc;
    private float price;

    private Branch_T branchT;

    public BranchManagerAddMenuItemView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Item Name: ");
        itemName = sc.nextLine();

        System.out.print("Item Description: ");
        itemDesc = sc.nextLine();

        System.out.print("Item Price: ");
        price = sc.nextFloat();
    }

    @Override
    public ViewStatus handleQuery() {

        this.branchT = SharedResources.getCurrentStaffBranchT();

//        init menuItem
        this.menuItemT = new MenuItem_T(
                this.price, MenuItem_T.AVAILABILITY.AVAILABLE,
                this.itemDesc, this.itemName, MenuItem_T.CATEGORIES.NA
        );

        if (this.branchT == null) {
            System.out.println("You have no branch, you cannot add");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        if (!this.branchT.addMenuItem(menuItemT)) {
            System.out.println("menuItem already exists in branch");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        System.out.println("menuItem added to branch");
        return ViewStatus.FAIL_AND_GO_BACK;
    }
}
