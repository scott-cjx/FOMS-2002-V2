/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews;

import Backend.Staff;
import Database.DataStructs.StaffType;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.AccountViews.AccountSettingsView;
import Views.AccountViews.AccountLoginAsStaffView;
import Views.IBackendView;
import Views.UIMenuWithExtraView;
import Views.UIQueryView;
import Views.UIView;

/**
 * Base View for Staff
 */
public class StaffBaseView extends UIMenuWithExtraView {
    private User_T currUser;
    private Staff staffObj;

    public StaffBaseView() {
       this.myViewName = this.getClass().getCanonicalName();
        this.subViews = new UIView[] {
                new AccountSettingsView()
        };
        this.myViewOptions = new String[] {
                "My Account Settings",
        };
    }

    @Override
    public void show() {
        System.out.println();
        currUser.printUser();
        super.show();
    }

    @Override
    public ViewStatus showAndQuery() {
        currUser = SharedResources.getCurrentUserT();

        if (currUser == null ||
                currUser.getStaffType() == StaffType.NORMAL_STAFF ||
                currUser.getStaffType() == StaffType.NA
        ) {
            ViewStatus viewStatus;

            UIQueryView login = new AccountLoginAsStaffView();
            viewStatus = login.showAndQuery();

            if (viewStatus == ViewStatus.SUCCESS_AND_GO_BACK) {
                currUser = SharedResources.getCurrentUserT();

            } else if (viewStatus == ViewStatus.FAIL_AND_GO_BACK) {
//                failed to log in
                return ViewStatus.FAIL_AND_GO_BACK;
            }
        }

        // IBackendView has functions for generating extra view options
        IBackendView objWithExtraViews = SharedResources.getCurrStaffB();

        if (objWithExtraViews != null) {
            this.myExtraViewOptions = objWithExtraViews.getViewOptions();
            this.myExtraSubViews = objWithExtraViews.getSubViews();
        }

//        continue as staff
        super.showAndQuery();

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }
}
