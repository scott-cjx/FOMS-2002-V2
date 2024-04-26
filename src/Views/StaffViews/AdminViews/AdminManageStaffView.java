/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Views.AccountViews.AccountDisplayAllView;
import Views.AccountViews.AccountDisplayFilteredView;
import Views.UIMenuView;
import Views.UIView;

/**
 * base view for admin to manage staff
 */
public class AdminManageStaffView extends UIMenuView {
    public AdminManageStaffView() {
        this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "Change Staff Type",
                "Move Staff To Branch",
                "See All",
                "See All (Filtered)",
        };
        this.subViews = new UIView[] {
                new AdminChangeStaffTypeView(),
                new AdminMoveStaffView(),
                new AccountDisplayAllView(),
                new AccountDisplayFilteredView(),
        };
    }
}
