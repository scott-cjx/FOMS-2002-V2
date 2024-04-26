/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Database.DataStructs.StaffType;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.AccountViews.AccountDisplayAllView;
import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;

/**
 * view for admin to change a staff type
 */
public class AdminChangeStaffTypeView extends UIQueryView {
    private String username;
    private int userIdx;
    private int user_request;
    private User_T userPartialT;
    public AdminChangeStaffTypeView() {
        this.myViewName = this.getClass().getCanonicalName();
    }


    @Override
    public void show() {
        UIView seeAllAccounts = new AccountDisplayAllView();
        seeAllAccounts.showAndQuery();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("username: ");
        this.userIdx = sc.nextInt();

        System.out.println("Staff type:");
        System.out.println("1. Admin");
        System.out.println("2. Branch Manager");
        System.out.println("3. Staff");
        System.out.print("> ");

        this.user_request = sc.nextInt();
    }

    @Override
    public ViewStatus handleQuery() {

        if (this.userIdx < 1 || this.userIdx > SharedResources.getUserDatabaseHelper().getAllFromDatabase().size()) {
            System.out.println("Not valid user");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.userPartialT = (User_T) SharedResources.getUserDatabaseHelper().getFromDatabase(this.userIdx-1);

        int idx = SharedResources.getUserDatabaseHelper().idxInDatabase_username(userPartialT, true);

        User_T tempUserT;
        tempUserT = (User_T) SharedResources.getUserDatabaseHelper().getFromDatabase(idx);

        switch (user_request) {
            case 1 -> {
                tempUserT.setStaffType(StaffType.ADMIN);
            }
            case 2 -> {
                tempUserT.setStaffType(StaffType.BRANCH_MANAGER);
            }
            case 3 -> {
                tempUserT.setStaffType(StaffType.NORMAL_STAFF);
            }
            default -> {
                System.out.println("Staff Type invalid");
                return ViewStatus.FAIL_AND_GO_BACK;
            }
        }

        SharedResources.getUserDatabaseHelper().updateDataInDatabase(idx, tempUserT);

        System.out.println("changed staff type successfully");
        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
