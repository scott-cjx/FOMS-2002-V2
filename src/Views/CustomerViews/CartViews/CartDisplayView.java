/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews.CartViews;

import Database.DataStructs.Order_T;
import Main.SharedResources;
import Views.UIView;

/**
 * view for customer to display all in the cart
 */
public class CartDisplayView extends UIView {

    public CartDisplayView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public ViewStatus showAndQuery() {
        System.out.println("Cart: ");
        Order_T orderT = SharedResources.getCurrentCustomerOrder();
        String orderPrintable = orderT.printOrder();
        System.out.println(orderPrintable);

        return ViewStatus.OK;
    }
}
