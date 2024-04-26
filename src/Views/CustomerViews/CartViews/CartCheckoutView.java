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
 * view to checkout from the customer's cart
 */
public class CartCheckoutView extends UIQueryView {
    private int user_request;
    public CartCheckoutView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void show() {
        PaymentMethodDisplayAllView paymentMethodDisplayView = new PaymentMethodDisplayAllView();
        paymentMethodDisplayView.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Pay By: ");
        this.user_request = sc.nextInt();
    }

    @Override
    public ViewStatus showAndQuery() {
        if (SharedResources.getCurrentCustomerOrder().isEmpty()) {
            System.out.println("Nothing to checkout");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        return super.showAndQuery();
    }

    @Override
    public ViewStatus handleQuery() {
//        pay
        System.out.println("Success...");
        String orderPrintable = SharedResources.getCurrentCustomerOrder().printReceipt();

        SharedResources.getCurrentCustomerOrder().setOrderStatus(OrderStatus.IN_PROGRESS);
        SharedResources.getCurrentCustomerOrder().setBranchId(
                SharedResources.getCurrentCustBranchT().getBranchUUID()
        );

        SharedResources.getCurrentCustomerOrder().addMeToDB();
        SharedResources.setCurrentCustomerOrder(new Order_T());

        System.out.println(orderPrintable);

        SharedResources.setJumpToView("Views.CustomerViews.CartViews.CartBaseView");

//        todo save the order, purge from cache

        return ViewStatus.JUMP_TO;
    }
}
