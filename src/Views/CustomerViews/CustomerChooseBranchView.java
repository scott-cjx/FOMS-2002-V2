/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews;

import Backend.Branch;
import Database.BranchDBHelper;
import Database.DataStructs.Branch_T;
import Database.DataStructs.IDatabaseItem_T;
import Main.SharedResources;
import Views.BranchViews.BranchDisplayAllView;
import Views.UIQueryView;
import Views.UIView;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * view that gets the branch the customer is at
 */
public class CustomerChooseBranchView extends UIQueryView {
    private final ArrayList<IDatabaseItem_T> branchArr;
    private final BranchDBHelper branchDBHelper;
    private Branch_T branch;
    private int user_request;

    public CustomerChooseBranchView() {
       this.myViewName = this.getClass().getCanonicalName();

        SharedResources.setCurrentCustBranchT(null);
        branchDBHelper = SharedResources.getBranchDBHelper();
        branchArr = branchDBHelper.getAllFromDatabase();
    }

    @Override
    public void show() {
        UIView viewBranches = new BranchDisplayAllView();
        viewBranches.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Branch: ");
        this.user_request = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {
        if ((this.user_request) < 1 || (this.user_request) > (this.branchArr.size()) ) {
            System.out.println("Failed to select branch");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        branch = (Branch_T) branchDBHelper.getFromDatabase(this.user_request - 1);
        SharedResources.setCurrentCustBranchT(branch);

        SharedResources.setCurrentBranchB(new Branch(branch));

        System.out.println("Chosen Branch: " + branch.getBranchName());
        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
