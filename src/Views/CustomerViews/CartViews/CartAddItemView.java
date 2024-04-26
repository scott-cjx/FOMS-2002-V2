/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews.CartViews;

import Database.DataStructs.MenuItem_T;
import Main.SharedResources;
import Views.MenuViews.MenuDisplayView;
import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;


/**
 * view for the cart to add item
 */
public class CartAddItemView extends UIQueryView {
    private int itemIdx;
    private int itemQn;
    private MenuItem_T menuItemT;
    public CartAddItemView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void show() {
        UIView menuView = new MenuDisplayView(false);
        menuView.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Add: ");
        this.itemIdx = sc.nextInt();

        System.out.print("Quantity: ");
        this.itemQn = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {
//      add to order

        if (this.itemIdx < 1 || this.itemIdx > SharedResources.getMenuDBHelper().getAllFromDatabase().size()) {
            System.out.println("Not valid item");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.menuItemT = (MenuItem_T) SharedResources.getMenuDBHelper().getFromDatabase(this.itemIdx-1);
        if (this.menuItemT.getAvailability() != MenuItem_T.AVAILABILITY.AVAILABLE) {
            System.out.println("item not available...");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        SharedResources.getCurrentCustomerOrder().addItemToOrder(menuItemT, itemQn);

        System.out.println("item added successfully");

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
