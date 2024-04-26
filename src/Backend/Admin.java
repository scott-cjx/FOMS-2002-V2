/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Backend;

import Views.StaffViews.AdminViews.AdminManageAccountsView;
import Views.StaffViews.AdminViews.AdminManageBranchView;
import Views.StaffViews.AdminViews.AdminManagePaymentMethodsView;
import Views.StaffViews.AdminViews.AdminManageStaffView;
import Views.UIView;

/**
 * Admin Backend class of Staff
 */
public class Admin extends Staff {

    /**
     * gives UIMenuExtraView its extra Views options
     * @return getViewOptions
     */
    @Override
    public String[] getViewOptions() {
        return new String[] {
                "Manage Accounts",
                "Manage Staff",
                "Manage Branches",
                "Manage Payment Methods",
        };
    }

    /**
     * gives UIMenuExtraView its extra Views
     * @return getSubViews
     */
    @Override
    public UIView[] getSubViews() {
        return new UIView[] {
                new AdminManageAccountsView(),
                new AdminManageStaffView(),
                new AdminManageBranchView(),
                new AdminManagePaymentMethodsView(),
        };
    }
}
