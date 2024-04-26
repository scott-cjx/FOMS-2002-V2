/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Views.PaymentMethodViews.PaymentMethodDisplayAllView;
import Views.UIMenuView;
import Views.UIView;

/**
 * view for admin to manage payment methods
 */
public class AdminManagePaymentMethodsView extends UIMenuView {
    public AdminManagePaymentMethodsView() {
        this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "Add",
                "Remove",
                "See All",
        };
        this.subViews = new UIView[] {
                new AdminAddPaymentMethodView(),
                new AdminRemovePaymentMethodView(),
                new PaymentMethodDisplayAllView()
        };
    }
}