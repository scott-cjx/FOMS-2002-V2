/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.MainViews;

import Views.CustomerViews.CustomerBaseView;
import Views.StaffViews.StaffBaseView;
import Views.UIMenuView;
import Views.UIView;

/**
 * the Main View of the app. the main page
 */
public class MainView extends UIMenuView {
    public MainView() {
       this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "I am a Customer",
                "I am a Staff",
        };
        this.subViews = new UIView[] {
                new CustomerBaseView(),
                new StaffBaseView()
        };
        this.isBaseView = true;
    }
}
