/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */
package Backend;

import Views.StaffViews.BranchManagerViews.BranchManagerAmendMenuView;
import Views.StaffViews.BranchManagerViews.BranchManagerDisplayStaffView;
import Views.UIView;

/**
 * Branch Manger
 */
public class BranchManagerStaff extends NormalStaff {

    /**
     * gives UIMenuExtraView its extra Views options
     * @return getViewOptions
     */
    @Override
    public String[] getViewOptions() {
        String[] myViewOptions = new String[] {
                "Display Staff List",
                "Modify Menu"
        };

        int totalLength = myViewOptions.length + super.getViewOptions().length;
        String[] ourViewOptions = new String[totalLength];
        System.arraycopy(super.getViewOptions(), 0, ourViewOptions, 0, super.getViewOptions().length);
        System.arraycopy(myViewOptions, 0, ourViewOptions, super.getViewOptions().length, myViewOptions.length);

        return ourViewOptions;
    }

    /**
     * gives UIMenuExtraView its extra Views
     * @return getSubViews
     */
    @Override
    public UIView[] getSubViews() {
        UIView[] mySubViews = new UIView[] {
                new BranchManagerDisplayStaffView(),
                new BranchManagerAmendMenuView(),
        };

        int totalLength = mySubViews.length + super.getSubViews().length;
        UIView[] ourSubViews = new UIView[totalLength];
        System.arraycopy(super.getSubViews(), 0, ourSubViews, 0, super.getSubViews().length);
        System.arraycopy(mySubViews, 0, ourSubViews, super.getSubViews().length, mySubViews.length);

        return ourSubViews;
    }
}
