/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Main.SharedResources;
import Views.UIView;

/**
 * view to display account information
 */
public class AccountDisplayMyInfoView extends UIView {
    public AccountDisplayMyInfoView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public ViewStatus showAndQuery() {
        System.out.println(SharedResources.getCurrentUserT().toString());
        return ViewStatus.OK;
    }
}
