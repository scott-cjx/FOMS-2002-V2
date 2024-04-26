/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews.CartViews;

import Database.DataStructs.OrderStatus;
import Database.DataStructs.Order_T;
import Main.SharedResources;
import Views.PaymentMethodViews.PaymentMethodDisplayAllView;
import Views.UIQueryView;

import java.util.Scanner;

/**
 * view to ask if customer is dining in or takeaway
 */
public class CartQueryTakeawayView extends UIQueryView {
    private int user_request;
    public CartQueryTakeawayView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[1] Dine In?");
        System.out.print("> ");
        this.user_request = sc.nextInt();
    }

    @Override
    public ViewStatus showAndQuery() {
        if (SharedResources.getCurrentCustomerOrder().isEmpty()) {
            SharedResources.setCurrentCustomerOrder(new Order_T());
        }

        SharedResources.getCurrentCustomerOrder().setTakeaway((this.user_request == 1));
        return super.showAndQuery();
    }

    @Override
    public ViewStatus handleQuery() {
//        pay
       return ViewStatus.OK;
    }
}
