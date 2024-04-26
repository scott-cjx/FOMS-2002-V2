/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Backend;

import Database.DataStructs.User_T;
import Views.StaffViews.NormalStaffViews.StaffDisplayOrdersView;
import Views.StaffViews.NormalStaffViews.StaffProcessOrdersView;
import Views.UIView;

/**
 * Normal Staff Backend Class of staff
 */
public class NormalStaff extends Staff {

    /**
     * gives UIMenuExtraView its extra Views options
     * @return getViewOptions
     */
    @Override
    public String[] getViewOptions() {
        return new String[] {
                "View New Orders",
                "Process Order",
                "View All Orders",
        };
    }

    /**
     * gives UIMenuExtraView its extra Views
     * @return getSubViews
     */
    @Override
    public UIView[] getSubViews() {
        return new UIView[] {
                new StaffDisplayOrdersView(true),
                new StaffProcessOrdersView(),
                new StaffDisplayOrdersView(false),
        };
    }
}
