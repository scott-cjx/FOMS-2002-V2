/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews;

import Database.DataStructs.OrderStatus;
import Database.DataStructs.Order_T;
import Database.OrderDBHelper;
import Main.SharedResources;
import Views.UIQueryView;

import java.util.Scanner;
import java.util.UUID;


/**
 * view for a customer to see a previous view
 */
public class CustomerViewPreviousOrderView extends UIQueryView {
    private boolean toPickup;
    private String orderId;

    public CustomerViewPreviousOrderView() {
       this.myViewName = this.getClass().getCanonicalName();
    }

    public CustomerViewPreviousOrderView(boolean toPickup) {
        this.myViewName = this.getClass().getCanonicalName();
        this.toPickup = toPickup;
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Order Id: ");
        this.orderId = sc.nextLine();
    }

    @Override
    public ViewStatus handleQuery() {

        Order_T orderPartialT = new Order_T();
        orderPartialT.setOrderId(UUID.fromString(this.orderId));

        OrderDBHelper orderDBHelper = SharedResources.getOrderDBHelper();

        int itemIdxInDb;
        itemIdxInDb = orderDBHelper.idxInDatabase_orderId(orderPartialT);
        if (itemIdxInDb == -1) {
            System.out.println("Order does not exist!");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        Order_T orderT = (Order_T) orderDBHelper.getFromDatabase(itemIdxInDb);

        if (toPickup) {
            if (orderT.getOrderStatus() == OrderStatus.READY_TO_PICKUP) {
                orderT.setOrderStatus(OrderStatus.COMPLETED);
                System.out.println("Order Complete");
                return ViewStatus.SUCCESS_AND_GO_BACK;
            }

            System.out.println("Order Not Ready");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        System.out.println(orderT.prettyPrint());

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
