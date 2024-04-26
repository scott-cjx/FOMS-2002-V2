/**
 @author Scott Cheng
 @author Bryan Wong
 @version 1.0
 @since 2024-04-22
 */

package Views.StaffViews.NormalStaffViews;

import Backend.Branch;
import Backend.NormalStaff;
import Backend.Staff;
import Main.SharedResources;
import Views.UIView;

/**
 * view to display all orders
 */
public class StaffDisplayOrdersView extends UIView {
    NormalStaff normalStaff;
    Branch branch;

    boolean onlyShowNew;

    public StaffDisplayOrdersView() {
       this.myViewName = this.getClass().getCanonicalName();
       this.onlyShowNew = true;
    }

    public StaffDisplayOrdersView(boolean onlyShowNew) {
        this.myViewName = this.getClass().getCanonicalName();
        this.onlyShowNew = onlyShowNew;
    }

    @Override
    public void show() {

        // If false, print everything matching branch
        if (!onlyShowNew){
            // Returns a string in format of: displayIndex] orderID
            String ret;
            ret = SharedResources.getOrderDBHelper().printIfBranch(
                    SharedResources.getCurrentStaffBranchT()
                    );
            if (ret.isEmpty()){
                System.out.println("No Orders Match");
            } else {
                System.out.println(ret);
            }
        } 
        // Print everything matching branch, that is OrderStatus.IN_PROGRESS
        else {
            String ret;
            ret = SharedResources.getOrderDBHelper().printIfBranchIfNewOrder(
                    SharedResources.getCurrentStaffBranchT()
                    );

            if (ret.isEmpty()){
                System.out.println("No Orders Match");
            } else {
                System.out.println(ret);
            }
        }
    }
    
    /*  We are able to filter if in branch, 
        then print. 

        getCurrentStaffBranchT() might not be working as intended...
        How to grab data from DB? is there DB functions or manually interact with ArrayList?
        How to filter between ned and old orders?

        public enum OrderStatus {
            NOT_ORDERED,
            IN_PROGRESS,
            READY_TO_PICKUP,
            COMPLETED,
            EXPIRED
        }
    */

    @Override
    public ViewStatus showAndQuery() {
        super.showAndQuery();
        return ViewStatus.OK;
    }
}
