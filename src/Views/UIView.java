/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views;

/**
 * Ultimate Base View Controller for ALL views in the app
 */
public class UIView implements IUIDisplay{

    /**
     * View status for every view
     */
    public enum ViewStatus {
        OK,
        ERROR,
        EXIT,
        GO_BACK,
        SUCCESS_AND_GO_BACK,
        FAIL_AND_GO_BACK,
        JUMP_TO,
    };

    /**
     * sub view of every view, this will be used to call all subsequent views
     */
    protected UIView[] subViews;

    /**
     * name of current view, this is used to display the current view name
     * AND more importantly, allow views to jump to a views previous to itself
     */
    protected String myViewName;

    /**
     * Base Constructor
     */
    public UIView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    /**
     * by default, UIView has no view, it doesn't display anything
     * This is abstract function for all derived subtype of UIView
     */
    public void show() {
        return;
    }

    /**
     * by default, UIView has no view, it doesn't query for anything
     * This is abstract function for all derived subtype of UIView
     */
    public void query() {
        return;
    }

    /**
     * by default, UIView has no view, it doesn't query for anything
     * This is abstract function for all derived subtype of UIView
     * It returns Exit such that if the subsequent view doesnt handle
     * query properly, it will safely exit
     */
    public ViewStatus handleQuery() {
        return ViewStatus.EXIT;
    }

    /**
     * @implNote This method need not be overloaded
     * @return status of `handleQuery`
     */
    public ViewStatus showAndQuery() {
        this.show();
        this.query();
        return this.handleQuery();
    }
}
