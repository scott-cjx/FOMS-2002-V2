/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Database.DataStructs.Branch_T;
import Views.UIQueryView;

import java.util.Scanner;

/**
 * view for admin to open a new branch
 */
public class AdminOpenBranchView extends UIQueryView {
    private String branchName;
    private Branch_T branchT;
    public AdminOpenBranchView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("branch name: ");
        branchName = sc.nextLine();
    }

    @Override
    public ViewStatus handleQuery() {
        this.branchT = new Branch_T(branchName);

        if (this.branchT.isInDB()) {
            System.out.println("branch exists!");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        if (this.branchT.addMeToDB()) {
            return ViewStatus.SUCCESS_AND_GO_BACK;
        }

        return ViewStatus.FAIL_AND_GO_BACK;
    }
}
