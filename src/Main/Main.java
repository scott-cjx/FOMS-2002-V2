/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Main;

import Database.*;
import Database.DataStructs.*;
import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Views.MainViews.MainView;
import Views.UIView;

/**
 * the main object of app. everything starts here.
 */
public class Main {
    public static UIView mainView;
    public static UserDBHelper userDatabaseHelper;
    public static BranchDBHelper branchDBHelper;
    public static OrderDBHelper orderDBHelper;
    public static MenuDBHelper menuDBHelper;
    public static PaymentMethodDBHelper paymentMethodDBHelper;

    public static void main(String[] args) {
        userDatabaseHelper = new UserDBHelper("./Data/userDatabase.ser");
        branchDBHelper = new BranchDBHelper("./Data/branchDatabase.ser");
        orderDBHelper = new OrderDBHelper("./Data/orderDatabase.ser");
        menuDBHelper = new MenuDBHelper("./Data/menuDatabase.ser");
        paymentMethodDBHelper = new PaymentMethodDBHelper("./Data/paymentMethodDatabase.ser");

//        try catch block but to catch most exceptions, but there should not have any.
        try {
//            open and get from database
            open_databases();

            debug_addStubData();
            showAllDatabases();

//            call main display function, do not modify
            mainView = new MainView();
            mainView.showAndQuery();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close_databases();
        }
    }

    public static void showAllDatabases() {
        System.out.println("User database: " + SharedResources.getUserDatabaseHelper().getAllFromDatabase().size());
        SharedResources.getUserDatabaseHelper().printAllInDatabase(false);

        System.out.println("Menu database: " + SharedResources.getMenuDBHelper().getAllFromDatabase().size());
        SharedResources.getMenuDBHelper().printAllInDatabase(false);

        System.out.println("Branch database: " + SharedResources.getBranchDBHelper().getAllFromDatabase().size());
        SharedResources.getBranchDBHelper().printAllInDatabase(false);

        System.out.println("Order database: " + SharedResources.getOrderDBHelper().getAllFromDatabase().size());
        SharedResources.getOrderDBHelper().printAllInDatabase(false);

        System.out.println("Payment Method database: " + paymentMethodDBHelper.getAllFromDatabase().size());
        paymentMethodDBHelper.printAllInDatabase(false);
    }

    public static void debug_addStubData() {
        User_T user;
        String username;

        username = "admin";
        user = new User_T(username, "admin", true, StaffType.ADMIN);
        user.addMeToDB();

        Branch_T branch;
        MenuItem_T menuItem;

        branch = new Branch_T("Changi City Point");
        menuItem = new MenuItem_T(10.10f, MenuItem_T.AVAILABILITY.NOT_AVAILABLE, "Hot Tacos", "Hot Tacos", MenuItem_T.CATEGORIES.SET_MEAL);
        branch.addMenuItem(menuItem);
        menuItem = new MenuItem_T(2.10f, MenuItem_T.AVAILABILITY.AVAILABLE, "Ice Milo", "Ice Milo", MenuItem_T.CATEGORIES.DRINK);
        branch.addMenuItem(menuItem);
        menuItem = new MenuItem_T(0.90f, MenuItem_T.AVAILABILITY.AVAILABLE, "Cheese Fries", "Cheese Fries", MenuItem_T.CATEGORIES.SIDE);
        branch.addMenuItem(menuItem);
        branch.addMeToDB();

        branch = new Branch_T("Jurong Point");
        menuItem = new MenuItem_T(11.10f, MenuItem_T.AVAILABILITY.NOT_AVAILABLE, "Cold Tacos", "Cold Tacos", MenuItem_T.CATEGORIES.SET_MEAL);
        branch.addMenuItem(menuItem);
        menuItem = new MenuItem_T(2.50f, MenuItem_T.AVAILABILITY.AVAILABLE, "Ice Teh", "Ice Teh", MenuItem_T.CATEGORIES.DRINK);
        branch.addMenuItem(menuItem);
        menuItem = new MenuItem_T(11.90f, MenuItem_T.AVAILABILITY.AVAILABLE, "Curly Fries", "Curly Fries", MenuItem_T.CATEGORIES.SIDE);
        branch.addMenuItem(menuItem);
        branch.addMeToDB();

        PaymentMethod_T paymentMethod;
        paymentMethod = new PaymentMethod_T("Master", PaymentType.CREDIT_CARD);
        paymentMethod.addMeToDB();

        paymentMethod = new PaymentMethod_T("Master", PaymentType.DEBIT_CARD);
        paymentMethod.addMeToDB();

        paymentMethod = new PaymentMethod_T("Visa", PaymentType.CREDIT_CARD);
        paymentMethod.addMeToDB();

        paymentMethod = new PaymentMethod_T("Paylah", PaymentType.QR);
        paymentMethod.addMeToDB();

        paymentMethod = new PaymentMethod_T("Paynow", PaymentType.QR);
        paymentMethod.addMeToDB();

        for (int i = 0; i < 2; i++) {
            username = "user" + i;
            user = new User_T(username, "password", true, StaffType.NORMAL_STAFF);
            user.setAge(21);
            user.setGender(1); //1 male
            user.setBranchT((Branch_T) SharedResources.getBranchDBHelper().getFromDatabase(i%2));
            user.addMeToDB();
        }
        
        for (int i = 2; i < 4; i++) {
            username = "user" + i;
            user = new User_T(username, "password", true, StaffType.BRANCH_MANAGER);
            user.setAge(36);
            user.setGender(2); // 2 female
            user.setBranchT((Branch_T) SharedResources.getBranchDBHelper().getFromDatabase(i%2));
            user.addMeToDB();
        }
    }

    private static void open_databases() {
        userDatabaseHelper.getFromDb();
        branchDBHelper.getFromDb();
        orderDBHelper.getFromDb();
        menuDBHelper.getFromDb();
        paymentMethodDBHelper.getFromDb();

        SharedResources.setUserDatabaseHelper(userDatabaseHelper);
        SharedResources.setBranchDBHelper(branchDBHelper);
        SharedResources.setOrderDBHelper(orderDBHelper);
        SharedResources.setMenuDBHelper(menuDBHelper);
        SharedResources.setPaymentMethodDBHelper(paymentMethodDBHelper);
    }

    private static void close_databases() {
        userDatabaseHelper.saveToDb();
        userDatabaseHelper = null;
        SharedResources.setUserDatabaseHelper(null);

        branchDBHelper.saveToDb();
        branchDBHelper = null;
        SharedResources.setBranchDBHelper(null);

        orderDBHelper.saveToDb();
        orderDBHelper = null;
        SharedResources.setOrderDBHelper(null);

        menuDBHelper.saveToDb();
        menuDBHelper = null;
        SharedResources.setMenuDBHelper(null);

        paymentMethodDBHelper.saveToDb();
        paymentMethodDBHelper = null;
        SharedResources.setPaymentMethodDBHelper(null);
    }
}