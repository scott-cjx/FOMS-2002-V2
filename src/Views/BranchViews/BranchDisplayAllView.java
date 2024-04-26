/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.BranchViews;

import Database.BranchDBHelper;
import Database.DataStructs.Branch_T;
import Database.DataStructs.IDatabaseItem_T;
import Main.SharedResources;
import Views.UIView;

import java.util.ArrayList;

/**
 * view to display all branches
 */
public class BranchDisplayAllView extends UIView {
    private final ArrayList<IDatabaseItem_T> branchArr;
    private final BranchDBHelper branchDBHelper;

    public BranchDisplayAllView() {
        this.myViewName = this.getClass().getCanonicalName();

        branchDBHelper = SharedResources.getBranchDBHelper();
        branchArr = branchDBHelper.getAllFromDatabase();
    }

    @Override
    public void show() {
        Branch_T tmpBranch;
        for (int i = 0; i < branchArr.size(); i++) {
            tmpBranch = (Branch_T) branchDBHelper.getFromDatabase(i);
            System.out.println( (i+1) + ": " + tmpBranch.getBranchName() );
        }
    }

    @Override
    public ViewStatus showAndQuery() {
        super.showAndQuery();
        return ViewStatus.OK;
    }
}
