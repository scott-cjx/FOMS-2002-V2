/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views;

import Main.SharedResources;

import java.util.Scanner;

/**
 * View controller to display and query user in form of menu
 */
public class UIMenuView extends UIView {
    protected String[] myViewOptions;
    protected boolean isBaseView = false;

    protected int user_request;

    public UIMenuView() {
        this.myViewOptions = new String[] {
                "There are no options in this view..."
        };
    }

    @Override
    public void show() {
        int i = 0;
        System.out.println();
        System.out.println(this.getClass().getCanonicalName());
//        System.out.println(this.myViewName);
        System.out.print((i) + ": ");
        if (isBaseView) {
            System.out.println("< Exit");
        } else {
            System.out.println("< Back");
        }
        for (; i < this.myViewOptions.length; i++) {
            System.out.print((i+1) + ": ");
            System.out.println(this.myViewOptions[i]);
        }
    }

    /**
     * @implNote This method need not be overloaded
     * @return the user's request, -1 if its to go back 1 level
     */
    @Override
    final public void query() {
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");

        this.user_request = -1;
        try {
            this.user_request = sc.nextInt();
        } catch (Exception e) {
//            we can either request user to input again OR
//            we can assume user requests to go back 1 menu
//            ^ this will also be checked by subclasses'
//            `handleQuery` methods, if out of range from `myViewOptions`
//            System.out.println("invalid input... try again");
        }
    }

    /**
     * @implNote This method need not be overloaded
     * @return viewStatus
     */
    @Override
    final public ViewStatus handleQuery() {
//        if user_request is less than 0, there's an error

        if (this.user_request < 0) {
            return ViewStatus.ERROR;
        } else if (this.user_request == 0) {
//            if user request is 0, they want to go back
            return ViewStatus.GO_BACK;
        } else {

            if (!hasNextView()) return ViewStatus.ERROR;

//            create new view
            UIView subView = this.getNextView();

//            if view exists, call `showAndQuery`
            if (subView != null) {
                return subView.showAndQuery();
            }

//            should not reach here, if it does, there's an unknown error
            return ViewStatus.ERROR;
        }
    }

    public boolean hasNextView() {
//            if view is unable to provide what user request, send error
        if (this.subViews == null ||
                this.subViews.length < 1 ||
                (this.user_request - 1) > (this.subViews.length - 1)
        ) {
            return false;
        }
        return true;
    }

    public UIView getNextView() {
        UIView subView = this.subViews[this.user_request-1];
        return subView;
    }

    @Override
    public ViewStatus showAndQuery() {
        ViewStatus status;

        while (true) {
            this.show();
            this.query();

            if (isBaseView && this.user_request < 1) {
                status = ViewStatus.EXIT;
            } else {
                status = this.handleQuery();
            }

            switch (status) {
                case ViewStatus.ERROR -> {
                    System.out.println("Error <" + SharedResources.getErrorMessage() + ">");
                    SharedResources.setErrorMessage("");
                    return status;
                }
                case ViewStatus.EXIT -> {
                    System.out.println("Exiting...");
                    return status;
                }
                case ViewStatus.GO_BACK -> {
                    return ViewStatus.OK;
                }
//                note that there is a bug that requires the JumpTo to be target + 1 level after,
//                else it will go to MainView
                case ViewStatus.JUMP_TO -> {
                    if (SharedResources.getJumpToView().isEmpty()) {
                        System.out.println("JumpTo not specified");
                        return ViewStatus.OK;
                    }

                    if (SharedResources.getJumpToView().equals(this.myViewName)) {
                        SharedResources.setJumpToView("");
                        return ViewStatus.OK;
                    }
                    return ViewStatus.JUMP_TO;
                }
                default -> {
//                    return ViewStatus.OK;
                }
            }
        }
    }
}