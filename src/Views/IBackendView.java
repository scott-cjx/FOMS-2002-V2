/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views;

/**
 * Interface to make sure every backend object that the UIMenuExtraView get from
 * has valid extra view options and subviews
 */
public interface IBackendView {
    /**
     * gets the view options of the object
     * @return view options of the object
     */
    String[] getViewOptions();

    /**
     * gets the subview of the object
     * @return subview of the object
     */
    UIView[] getSubViews();
}
