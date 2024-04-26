/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews;

import Database.DataStructs.Branch_T;
import Main.SharedResources;
import Views.CustomerViews.CartViews.CartBaseView;
import Views.UIMenuView;
import Views.UIQueryView;
import Views.UIView;


/**
 * base view for customer menu
 */
public class CustomerBaseView extends UIMenuView {
    private Branch_T currBranch;
    public CustomerBaseView() {
       this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "Re-select Branch",
                "New Order",
                "View Previous Order",
                "Pick Up Order"
        };
        this.subViews = new UIView[] {
                new CustomerChooseBranchView(),
                new CartBaseView(),
                new CustomerViewPreviousOrderView(false),
                new CustomerViewPreviousOrderView(true),
        };
    }

    @Override
    public ViewStatus showAndQuery() {
        currBranch = SharedResources.getCurrentCustBranchT();

        if (currBranch == null) {
            ViewStatus viewStatus;

            UIQueryView chooseBranch = new CustomerChooseBranchView();
            viewStatus = chooseBranch.showAndQuery();

            if (viewStatus == ViewStatus.SUCCESS_AND_GO_BACK) {
                currBranch = SharedResources.getCurrentCustBranchT();

            } else if (viewStatus == ViewStatus.FAIL_AND_GO_BACK) {
                return ViewStatus.FAIL_AND_GO_BACK;
            }
        }

        // continue as user without branch
        super.showAndQuery();

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
