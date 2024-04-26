/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.BranchManagerViews;

import Database.DataStructs.*;
import Views.UIView;
import Main.SharedResources;
import java.util.ArrayList;

/**
 * view for branch manager to display staff
 */
public class BranchManagerDisplayStaffView extends UIView {
    public BranchManagerDisplayStaffView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void show() {
        System.out.println("Display Staff in myBranch");


        /* For each user in DB, check through filter, then print. */
        User_T userTemp;
        ArrayList<IDatabaseItem_T> databaseObject = SharedResources.getUserDatabaseHelper().getAllFromDatabase();
        int displayIndex = 0;
        for (int i = 0; i < databaseObject.size(); i++) {
            userTemp = (User_T) databaseObject.get(i);
            // Filter by branch
            if(userTemp.getBranchT() == SharedResources.getCurrentStaffBranchT()){
                displayUser_T(userTemp,displayIndex);
                displayIndex++;
                //System.out.println(userTemp.prettyPrint());
            }
        }
    }

    private void displayUser_T(User_T tmpUser,int displayIndex){
        int i = displayIndex;

        System.out.print( (i+1) + ": (" + tmpUser.getStaffType().toChar() + ") -> " + tmpUser.getUsername() + " -> branch: ");
        if (tmpUser.getBranchT() != null) {
            System.out.println(tmpUser.getBranchT().getBranchName());
        } else {
            System.out.println("null");
        }
    }

    @Override
    public ViewStatus showAndQuery() {
        super.showAndQuery();
        return ViewStatus.OK;
    }
}
