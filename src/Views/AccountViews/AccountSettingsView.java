/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Views.UIMenuView;
import Views.UIView;

/**
 * base view for account settings
 */
public class AccountSettingsView extends UIMenuView {
    public AccountSettingsView() {
        this.myViewName = this.getClass().getCanonicalName();
        this.subViews = new UIView[] {
                new AccountLogoutNoView(),
                new AccountChangePasswordView(),
                new AccountDisplayMyInfoView(),
        };
        this.myViewOptions = new String[] {
                "Log Out",
                "Change Password",
                "My Account Info"
        };
    }
}
