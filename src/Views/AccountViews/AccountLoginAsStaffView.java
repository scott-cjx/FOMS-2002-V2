/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Backend.Admin;
import Backend.BranchManagerStaff;
import Backend.NormalStaff;
import Backend.Staff;
import Database.DataStructs.User_T;
import Database.UserDBHelper;
import Main.SharedResources;
import Views.UIQueryView;

import java.util.Scanner;


/**
 * view for staff to log in to account
 */
public class AccountLoginAsStaffView extends UIQueryView {
    private String username;
    private String password;
    private final UserDBHelper userDBHelper;

    public AccountLoginAsStaffView() {
       this.myViewName = this.getClass().getCanonicalName();
        userDBHelper = SharedResources.getUserDatabaseHelper();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        username = sc.nextLine();

        System.out.print("Password: ");
        password = sc.nextLine();
    }

    @Override
    public ViewStatus handleQuery() {
        User_T userPartial = new User_T(username, password);
        User_T userVerified = userDBHelper.verifyLogin(userPartial);

        if (userVerified != null) {
            SharedResources.setCurrentUserT(userVerified);
            SharedResources.setCurrentStaffBranchT(userVerified.getBranchT());
            this.setStaffObj();

            System.out.println("Logged in successfully");
            return ViewStatus.SUCCESS_AND_GO_BACK;
        }

        System.out.println("Failed to log in");
        return ViewStatus.FAIL_AND_GO_BACK;
    }

    private void setStaffObj() {
        User_T currUser = SharedResources.getCurrentUserT();
        Staff currStaff = null;

        if (currUser != null) {

            switch (currUser.getStaffType()) {
                case NORMAL_STAFF -> {
                    currStaff = new NormalStaff();
                }
                case ADMIN -> {
                    currStaff = new Admin();
                }
                case BRANCH_MANAGER -> {
                    currStaff = new BranchManagerStaff();
                }
            }

            SharedResources.setCurrStaffB(currStaff);
        }
    }
}
