/**
 @author Scott Cheng
 @author Bryan Wong
 @version 1.0
 @since 2024-04-22
 */

package Views.StaffViews.NormalStaffViews;

import Backend.Branch;
import Backend.NormalStaff;
import Database.DataStructs.OrderStatus;
import Database.DataStructs.Order_T;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;

/**
 * View for staff to process order
 */
public class StaffProcessOrdersView extends UIQueryView {
    NormalStaff normalStaff;
    Branch branch;

    int user_request;

    public StaffProcessOrdersView() {
       this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void show() {
        UIView newOrdersView = new StaffDisplayOrdersView(true);
        newOrdersView.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Process: ");
        user_request = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {
//        todo process order, 
//        todo ensure user only selects index range

    // Invalid user input, user request is out of size of DB.
    if (this.user_request < 0 || this.user_request >= SharedResources.getOrderDBHelper().getAllFromDatabase().size());


    // From user request, obtain index to selected order.
    int idx = SharedResources.getOrderDBHelper().idxInDatabaseIfBranchIfNewOrderByOccuranceIndex(
            SharedResources.getCurrentStaffBranchT(), this.user_request
    );

    // Order not found
    if (idx == -1){
        System.out.println("Order not found of db index: " + idx);
        return ViewStatus.FAIL_AND_GO_BACK;
    }

    // Process order
    Order_T order = (Order_T) SharedResources.getOrderDBHelper().getFromDatabase(idx);
    order.setOrderStatus(OrderStatus.READY_TO_PICKUP);

    return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
