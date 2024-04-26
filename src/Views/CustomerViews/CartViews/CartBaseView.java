/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.CustomerViews.CartViews;

import Database.DataStructs.Order_T;
import Main.SharedResources;
import Views.MenuViews.MenuDisplayView;
import Views.UIMenuView;
import Views.UIView;

/**
 * base view for the cart
 */
public class CartBaseView extends UIMenuView {
    public CartBaseView() {
       this.myViewName = this.getClass().getCanonicalName();
        this.myViewOptions = new String[] {
                "View Menu",
                "View Cart",
                "Add to Cart",
                "Remove from Cart",
                "Amend from Cart",
                "Pay",
        };
        this.subViews = new UIView[] {
                new MenuDisplayView(false),
                new CartDisplayView(),
                new CartAddItemView(),
                new CartRemoveItemView(),
                new CartUpdateItemView(),
                new CartCheckoutView()
        };

        if (SharedResources.getCurrentCustomerOrder() == null) {
            SharedResources.setCurrentCustomerOrder(new Order_T());
        }
    }

    @Override
    public ViewStatus showAndQuery() {
        UIView getIfTakeAway = new CartQueryTakeawayView();
        getIfTakeAway.showAndQuery();

        return super.showAndQuery();
    }
}
