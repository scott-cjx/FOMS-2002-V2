/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Main.SharedResources;
import Views.UIView;

/**
 * no view for account to log out
 */
public class AccountLogoutNoView extends UIView {
    public AccountLogoutNoView() {
        this.myViewName = this.getClass().getCanonicalName();
    }
    @Override
    public ViewStatus showAndQuery() {

//        log out
        SharedResources.setCurrentUserT(null);
        SharedResources.setCurrentStaffBranchT(null);

        SharedResources.setJumpToView("Views.Main.MainView");
        return ViewStatus.JUMP_TO;
    }
}
