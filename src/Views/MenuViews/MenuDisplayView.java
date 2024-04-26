/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.MenuViews;

import Backend.Branch;
import Database.DataStructs.Branch_T;
import Main.SharedResources;
import Views.UIView;

/**
 * view to display the menu of a branch
 */
public class MenuDisplayView extends UIView {

    private Branch branch;
    private Branch_T branchT;

    private boolean isStaff;

    public MenuDisplayView() {
        this.myViewName = this.getClass().getCanonicalName();
        this.branchT = SharedResources.getCurrentCustBranchT();
        this.isStaff = false;
    }

    public MenuDisplayView(boolean isStaff) {
        this.myViewName = this.getClass().getCanonicalName();
        this.isStaff = isStaff;
        if (isStaff) {
            this.branchT = SharedResources.getCurrentStaffBranchT();
        } else {
            this.branchT = SharedResources.getCurrentCustBranchT();
        }
    }

    @Override
    public void show() {
        if (isStaff) {
            this.branchT = SharedResources.getCurrentStaffBranchT();
        } else {
            this.branchT = SharedResources.getCurrentCustBranchT();
        }

        Branch.printMenu(this.branchT, true);
    }

    @Override
    public ViewStatus showAndQuery() {
        this.show();
        return ViewStatus.OK;
    }
}
