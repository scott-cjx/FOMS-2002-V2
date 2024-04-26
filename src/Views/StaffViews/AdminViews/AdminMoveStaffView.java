/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Backend.Branch;
import Database.DataStructs.Branch_T;
import Database.DataStructs.StaffType;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.AccountViews.AccountDisplayAllView;
import Views.BranchViews.BranchDisplayAllView;
import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;

/**
 * view for admin to move staff to another branch
 */
public class AdminMoveStaffView extends UIQueryView {
    private String username;
    private int userIdx;
    private String branchName;
    private int branchIdx;
    private User_T userPartialT;
    private Branch_T branchPartialT;


    public AdminMoveStaffView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        UIView seeAllAccounts = new AccountDisplayAllView();
        seeAllAccounts.showAndQuery();

        Scanner sc = new Scanner(System.in);

        System.out.print("> ");
        userIdx = sc.nextInt();

        UIView seeAllBranches = new BranchDisplayAllView();
        seeAllBranches.showAndQuery();

        System.out.print("> ");
        branchIdx = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {


        if (this.userIdx < 1 || this.userIdx > SharedResources.getUserDatabaseHelper().getAllFromDatabase().size()) {
            System.out.println("Not valid user");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.userPartialT = (User_T) SharedResources.getUserDatabaseHelper().getFromDatabase(this.userIdx-1);

        if (this.branchIdx < 1 || this.branchIdx > SharedResources.getBranchDBHelper().getAllFromDatabase().size()) {
            System.out.println("Not valid branch");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.branchPartialT = (Branch_T) SharedResources.getBranchDBHelper().getFromDatabase(this.branchIdx - 1 );

//        this.branchPartialT = new Branch_T(branchName);
        if (!this.branchPartialT.isInDB()) {
            System.out.println("branch not found!");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        int userIdx = SharedResources.getUserDatabaseHelper().idxInDatabase_username(userPartialT, true);
        int branchIdx = SharedResources.getBranchDBHelper().idxInDatabase_branchName(branchPartialT, true);

        Branch_T tmpbranchT = (Branch_T) SharedResources.getBranchDBHelper().getFromDatabase(branchIdx);
        User_T tmpUserT = (User_T) SharedResources.getUserDatabaseHelper().getFromDatabase(userIdx);

        if(tmpUserT.getStaffType() == StaffType.NA || tmpUserT.getStaffType() == StaffType.ADMIN) {
            System.out.println("Admin/ Uninitialized Staff cannot be transferred");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        if (
                tmpUserT.getStaffType() == StaffType.NORMAL_STAFF &&
                Branch.maxNormalStaff(branchPartialT) < (branchPartialT.getNormalStaffArr().size() + 1)
        ) {
            System.out.println("Quota for branch met, unable to move...");
            return ViewStatus.FAIL_AND_GO_BACK;
        }

//        remove from old branch side
        if (tmpUserT.getBranchT() != null) {
            tmpUserT.getBranchT().removeBranchManger(tmpUserT);
            tmpUserT.getBranchT().removeNormalStaff(tmpUserT);
        }

//        set new branch from user side
        tmpUserT.setBranchT(tmpbranchT);
        tmpUserT.setBranchUUID(tmpbranchT.getBranchUUID());

//        set new user from new branch side
        switch (tmpUserT.getStaffType()) {
            case BRANCH_MANAGER -> {
                tmpbranchT.addBranchManager(tmpUserT);
            }
            case NORMAL_STAFF -> {
                tmpbranchT.addNormalStaff(tmpUserT);
            }
            default -> {
                return ViewStatus.FAIL_AND_GO_BACK;
            }
        }

        System.out.println("Staff transferred successfully");

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
