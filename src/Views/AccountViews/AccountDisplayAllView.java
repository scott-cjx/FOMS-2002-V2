/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Database.DataStructs.Branch_T;
import Database.DataStructs.IDatabaseItem_T;
import Database.DataStructs.User_T;
import Database.UserDBHelper;
import Main.SharedResources;
import Views.UIView;

import java.util.ArrayList;

/**
 * view to display all accounts
 */
public class AccountDisplayAllView extends UIView {

    private UserDBHelper userDBHelper;
    private final ArrayList<IDatabaseItem_T> userArr;

    public AccountDisplayAllView() {
        this.myViewName = this.getClass().getCanonicalName();

        userDBHelper = SharedResources.getUserDatabaseHelper();
        userArr = userDBHelper.getAllFromDatabase();
    }

    @Override
    public void show() {
        User_T tmpUser;
        for (int i = 0; i < userArr.size(); i++) {
            tmpUser = (User_T) userDBHelper.getFromDatabase(i);
            System.out.print( (i+1) + ": (" + tmpUser.getStaffType().toChar() + ") -> " + tmpUser.getUsername() + " -> branch: ");
            if (tmpUser.getBranchT() != null) {
                System.out.println(tmpUser.getBranchT().getBranchName());
            } else {
                System.out.println("null");
            }

        }
    }

    @Override
    public ViewStatus showAndQuery() {
        super.showAndQuery();
        return ViewStatus.OK;
    }
}
