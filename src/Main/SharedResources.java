/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Main;

import Backend.Branch;
import Backend.Staff;
import Database.*;
import Database.DataStructs.Branch_T;
import Database.DataStructs.Order_T;
import Database.DataStructs.PaymentMethod_T;
import Database.DataStructs.User_T;

/**
 * Static class to share resources for all objects in app.
 * This is like a main.h file
 */
public class SharedResources {
    private static UserDBHelper userDatabaseHelper;
    private static OrderDBHelper orderDBHelper;
    private static BranchDBHelper branchDBHelper;
    private static MenuDBHelper menuDBHelper;
    private static PaymentMethodDBHelper paymentMethodDBHelper;
    private static PaymentMethod_T currentPaymentMethod;
    private static Staff currStaffB;
    private static User_T currentUserT;
    private static Branch_T currentCustBranchT;
    private static Branch_T currentStaffBranchT;
    private static Branch currentBranchB;
    private static Order_T currentCustomerOrder;
    private static String jumpToView = "";
    private static String errorMessage = "";
    public static PaymentMethod_T getCurrentPaymentMethod() {
        return currentPaymentMethod;
    }

    public static void setCurrentPaymentMethod(PaymentMethod_T currentPaymentMethod) {
        SharedResources.currentPaymentMethod = currentPaymentMethod;
    }

    public static PaymentMethodDBHelper getPaymentMethodDBHelper() {
        return paymentMethodDBHelper;
    }
    public static void setPaymentMethodDBHelper(PaymentMethodDBHelper paymentMethodDBHelper) {
        SharedResources.paymentMethodDBHelper = paymentMethodDBHelper;
    }

    public static UserDBHelper getUserDatabaseHelper() {
        return userDatabaseHelper;
    }

    public static void setUserDatabaseHelper(UserDBHelper userDatabaseHelper) {
        SharedResources.userDatabaseHelper = userDatabaseHelper;
    }

    public static OrderDBHelper getOrderDBHelper() {
        return orderDBHelper;
    }

    public static void setOrderDBHelper(OrderDBHelper orderDBHelper) {
        SharedResources.orderDBHelper = orderDBHelper;
    }

    public static BranchDBHelper getBranchDBHelper() {
        return branchDBHelper;
    }

    public static void setBranchDBHelper(BranchDBHelper branchDBHelper) {
        SharedResources.branchDBHelper = branchDBHelper;
    }

    public static MenuDBHelper getMenuDBHelper() {
        return menuDBHelper;
    }

    public static void setMenuDBHelper(MenuDBHelper menuDBHelper) {
        SharedResources.menuDBHelper = menuDBHelper;
    }

    public static Staff getCurrStaffB() {
        return currStaffB;
    }

    public static void setCurrStaffB(Staff currStaffB) {
        SharedResources.currStaffB = currStaffB;
    }

    public static User_T getCurrentUserT() {
        return currentUserT;
    }

    public static void setCurrentUserT(User_T currentUserT) {
        SharedResources.currentUserT = currentUserT;
    }

    public static Branch_T getCurrentCustBranchT() {
        return currentCustBranchT;
    }

    public static void setCurrentCustBranchT(Branch_T currentCustBranchT) {
        SharedResources.currentCustBranchT = currentCustBranchT;
    }

    public static Branch getCurrentBranchB() {
        return currentBranchB;
    }

    public static void setCurrentBranchB(Branch currentBranchB) {
        SharedResources.currentBranchB = currentBranchB;
    }

    public static String getJumpToView() {
        return jumpToView;
    }

    public static void setJumpToView(String jumpToView) {
        SharedResources.jumpToView = jumpToView;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }

    public static void setErrorMessage(String errorMessage) {
        SharedResources.errorMessage = errorMessage;
    }

    public static Order_T getCurrentCustomerOrder() {
        return currentCustomerOrder;
    }

    public static void setCurrentCustomerOrder(Order_T currentCustomerOrder) {
        SharedResources.currentCustomerOrder = currentCustomerOrder;
    }

    public static Branch_T getCurrentStaffBranchT() {
        return currentStaffBranchT;
    }

    public static void setCurrentStaffBranchT(Branch_T currentStaffBranchT) {
        SharedResources.currentStaffBranchT = currentStaffBranchT;
    }
}
