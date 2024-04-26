/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Views.AccountViews.AccountDisplayAllView;
import Views.UIMenuView;
import Views.UIView;

/**
 * base view for admin to manage accounts
 */
public class AdminManageAccountsView extends UIMenuView {
    public AdminManageAccountsView() {
        this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "Add",
                "Remove",
                "Change Username",
                "Reset Password",
                "See All",
        };
        this.subViews = new UIView[] {
                new AdminAddAccountView(),
                new AdminRemoveAccountView(),
                new AdminChangeAccountUsernameView(),
                new AdminResetAccountPasswordView(),
                new AccountDisplayAllView(),
        };
    }
}
