package Views.StaffViews.BranchManagerViews;

import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Database.DataStructs.MenuItem_T.CATEGORIES;
import Main.SharedResources;
import Views.UIQueryView;

import java.util.Scanner;

public class BranchManagerAddMenuItemView extends UIQueryView {
    private MenuItem_T menuItemT;
    private String itemName;
    private String itemDesc;
    private float price;
    private CATEGORIES category;

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

        System.out.print(   "1. DRINK\n" +
                                "2. NA\n" +
                                "3. SET_MEAL\n" +
                                "4. SIDE \n> " );
                switch (sc.nextInt()) {
                case 1:
                    category = (MenuItem_T.CATEGORIES.DRINK);
                    break;
                case 2:
                    category = (MenuItem_T.CATEGORIES.NA);
                    break;
                case 3:
                    category = (MenuItem_T.CATEGORIES.SET_MEAL);
                    break;
                case 4:
                    category = (MenuItem_T.CATEGORIES.SIDE);
                    break;
                default:
                    System.out.println("Not valid category");
                    break;
                }   
    }

    @Override
    public ViewStatus handleQuery() {

        this.branchT = SharedResources.getCurrentStaffBranchT();

//        init menuItem
        this.menuItemT = new MenuItem_T(
                this.price, MenuItem_T.AVAILABILITY.AVAILABLE,
                this.itemDesc, this.itemName, this.category
        );

        if (this.branchT == null) {
            System.out.println("You have no branch, you cannot add");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        if (!this.branchT.addMenuItem(menuItemT)) {
            System.out.println("Item already exists in branch");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        System.out.println("Item added to branch");
        return ViewStatus.FAIL_AND_GO_BACK;
    }
}
