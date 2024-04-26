/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views;

/**
 * UIMenuView which supports extra views passed by an object (for dynamic views)
 */
public class UIMenuWithExtraView extends UIMenuView {

    /**
     * extra view options that this view controller will request from an object
     */
    protected String[] myExtraViewOptions;

    /**
     * extra views that this view controller will request from an object
     */
    protected UIView[] myExtraSubViews;

    /**
     * default constructor
     */
    public UIMenuWithExtraView() {
        super();
    }

    /**
     * override show method to include the extra views
     */
    @Override
    public void show() {
        int i = 0;
        System.out.println();
        System.out.println(this.myViewName);
        System.out.print((i) + ": ");
        System.out.println("< Back");
        for (; i < this.myViewOptions.length; i++) {
            System.out.print((i+1) + ": ");
            System.out.println(this.myViewOptions[i]);
        }

        if (this.myExtraViewOptions == null || this.myExtraViewOptions.length == 0) return;

        int j = 0;
        for (; j < this.myExtraViewOptions.length; i++, j++) {
            System.out.print((i+1) + ": ");
            System.out.println(this.myExtraViewOptions[j]);
        }
    }

    /**
     * override the hasNextView method to let UIMenuView controller know that
     * there is more views than without the extra
     * @return
     */
    @Override
    public boolean hasNextView() {
//            if view is unable to provide what user request, send error
        if (this.subViews == null || this.myExtraSubViews == null) return false;

        return this.subViews.length >= 1 &&
                (this.user_request - 1) <= (this.subViews.length + this.myExtraSubViews.length - 1);
    }

    /**
     * returns the next view for the UIMenuView controller call
     * @return
     */
    @Override
    public UIView getNextView() {
        UIView subView;
        int requestedViewIdx = this.user_request-1;

        if (this.subViews.length-1 < requestedViewIdx) {
            requestedViewIdx -= this.subViews.length;
            subView = this.myExtraSubViews[requestedViewIdx];
        } else {
            subView= this.subViews[requestedViewIdx];
        }

        return subView;
    }
}
