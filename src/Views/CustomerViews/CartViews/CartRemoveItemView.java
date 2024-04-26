/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews.CartViews;

import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;

/**
 * view for cart to remove item
 */
public class CartRemoveItemView extends UIQueryView {
    private int user_request;
    public CartRemoveItemView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void show() {
        UIView cartView = new CartDisplayView();
        cartView.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Remove: ");
        this.user_request = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {
//      remove from cart

//        Order_T orderPartialT = new Order_T();
//        orderPartialT.setOrderId(UUID.nameUUIDFromBytes(orderId.getBytes()));
//
//        OrderDBHelper orderDBHelper = SharedResources.getOrderDBHelper();
//
//        int itemIdxInDb;
//        itemIdxInDb = orderDBHelper.idxInDatabase_orderId(orderPartialT);
//        if (itemIdxInDb == -1) {
//            System.out.println("Order does not exist!");
//            return ViewStatus.FAIL_AND_GO_BACK;
//        }
//
//        Order_T orderT = (Order_T) orderDBHelper.getFromDatabase(itemIdxInDb);
//        System.out.println(orderT.prettyPrint());
//
        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
