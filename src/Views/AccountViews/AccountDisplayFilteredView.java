/**
 @author Scott Cheng
 @author Bryan Wong
 @author Iain Lim
 @version 1.0
 @since 2024-04-01
 */

package Views.AccountViews;

import Main.SharedResources;
import Views.UIView;
import Views.BranchViews.BranchDisplayAllView;
import Views.UIQueryView;
import java.util.Scanner;
import java.util.ArrayList;

import Database.DataStructs.IDatabaseItem_T;
import Database.DataStructs.StaffType;
import Database.DataStructs.User_T;

/**
 * view to display the accounts filtered
 */
public class AccountDisplayFilteredView extends UIQueryView {
    private int filterBy;
    private int filterFor;
    private int error;


    public AccountDisplayFilteredView() {
        this.myViewName = this.getClass().getCanonicalName();
        filterBy = -1;
    }

    // TODO make it fully dynamic
    @Override
    public void show(){
        System.out.println("Possible Filter Conditions:");
        System.out.println("1: Branch");
        System.out.println("2: Role");
        System.out.println("3: Gender");
        System.out.println("4: Age");
    }

    @Override
    public void query(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Filter by: ");
        filterBy = Integer.parseInt(sc.nextLine());
        System.out.println("");


        switch (filterBy){
            case 1:
                System.out.println("Branches:");
                new BranchDisplayAllView().showAndQuery();

                break;
            case 2: // Staff Type
                System.out.println("Staff Types:");
                System.out.println("1: "+ StaffType.ADMIN.toString());
                System.out.println("2: "+ StaffType.BRANCH_MANAGER.toString());
                System.out.println("3: "+ StaffType.NORMAL_STAFF.toString());
                break;
            case 3: // Gender
                System.out.println("Genders:");
                System.out.println("1: Male");
                System.out.println("2: Female");
                break;
            case 4: // Age Group
                System.out.println("AgeGroup:");
                System.out.println("1: 18-29");
                System.out.println("2: 30-39");
                System.out.println("3: 40-49");
                System.out.println("4: 50-59");
                System.out.println("5: 60+");
                break;
            default: // Erronous Input
                System.out.println("Incorrect Condition Entered");
                error = 1;
                break;
        }
        if (error == 1){
            return;
        }

        System.out.print("Filter for: ");
        filterFor = Integer.parseInt(sc.nextLine());

    }

    @Override
    public ViewStatus handleQuery() {

        if (error==1){
            return ViewStatus.FAIL_AND_GO_BACK;
        }

        /* For each user in DB, check through filter, then print. */
        User_T userTemp;
        ArrayList<IDatabaseItem_T> databaseObject = SharedResources.getUserDatabaseHelper().getAllFromDatabase();
        int displayIndex = 0;
        for (int i = 0; i < databaseObject.size(); i++) {
            userTemp = (User_T) databaseObject.get(i);
            
            switch (filterBy){
                case 1: // Branch
                    if(userTemp.getBranchT() == SharedResources.getBranchDBHelper().getFromDatabase(filterFor-1)){
                        displayUser_T(userTemp,i);
                        //System.out.println(userTemp.prettyPrint());
                    }
                    break;
                case 2: // Staff Type
                    StaffType targetType = StaffType.NA;
                    switch (filterFor){
                        case 1:
                            targetType = StaffType.ADMIN;
                            break;
                        case 2:
                            targetType = StaffType.BRANCH_MANAGER;
                            break;
                        case 3:
                            targetType = StaffType.NORMAL_STAFF;
                            break;
                    }
                    if(userTemp.getStaffType() == targetType){
                        displayUser_T(userTemp,i);
                        //System.out.println(userTemp.prettyPrint());
                    }
                    break;
                case 3: // Gender //TODO determine gender values
                    if (userTemp.getGender() == filterFor){
                        displayUser_T(userTemp,i);
                        //System.out.println(userTemp.prettyPrint());
                    }
                    break;
                case 4: // Age Group
                    int lowerBound = 0, upperBound = 0;
                    switch (filterFor){
                        case 1:
                            lowerBound = 18; upperBound = 29;
                            break;
                        case 2:
                            lowerBound = 30; upperBound = 39;
                            break;
                        case 3:
                            lowerBound = 40; upperBound = 49;
                            break;
                        case 4:
                            lowerBound = 50; upperBound = 59;
                            break;
                        case 5:
                            lowerBound = 60; upperBound = 999;
                            break;
                    }
                    if( lowerBound <= userTemp.getAge() && userTemp.getAge() <= upperBound){
                        displayUser_T(userTemp,i);
                        //System.out.println(userTemp.prettyPrint());
                    }
                default: // Should not occur
                    break;
            }
        }

        return ViewStatus.SUCCESS_AND_GO_BACK;
    }


    private void displayUser_T(User_T tmpUser,int displayIndex){
            int i = displayIndex;

            System.out.print( (i+1) + ": (" + tmpUser.getStaffType().toChar() + ") -> " + tmpUser.getUsername() + " -> branch: ");
            if (tmpUser.getBranchT() != null) {
                System.out.println(tmpUser.getBranchT().getBranchName());
            } else {
                System.out.println("null");
            }
    }

        /* Steps Required */
        // Get user input for type of filter
        // Search and filter only passing a condition
        // PrettyPrint resultant ones.

        /* Notes, each query and solve needs to be distinct
         * DB should have functions to print unique types
         */



    // @Override
    // public ViewStatus showAndQuery() {
        // Currently its print everything as prettyPrint
        //SharedResources.getUserDatabaseHelper().printAllInDatabase(true);

     

        // return ViewStatus.OK;
    // }
}