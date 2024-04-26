/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Database.DataStructs.StaffType;
import Database.DataStructs.User_T;
import Views.CustomerViews.CartViews.CartDisplayView;
import Views.UIQueryView;
import Views.UIView;

import java.util.Scanner;


/**
 * view for admin to add an account
 */
public class AdminAddAccountView extends UIQueryView {
    private User_T userT;
    private String username;
    private String staffType;

    public AdminAddAccountView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        username = sc.nextLine();
    }

    @Override
    public ViewStatus handleQuery() {
        this.userT = new User_T(username, "password", true, StaffType.NA);

        if (this.userT.isInDB()) {
            System.out.println("username already exist!");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        if (userT.addMeToDB()) {
            System.out.println("user added");
            return ViewStatus.SUCCESS_AND_GO_BACK;
        }
        return ViewStatus.FAIL_AND_GO_BACK;
    }
}
