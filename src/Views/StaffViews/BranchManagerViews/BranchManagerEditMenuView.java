package Views.StaffViews.BranchManagerViews;

import Database.DataStructs.Branch_T;
import Database.DataStructs.MenuItem_T;
import Database.DataStructs.User_T;
import Main.SharedResources;
import Views.UIQueryView;
import Views.MenuViews.MenuDisplayView;
import Database.DataStructs.Branch_T;

import java.util.Scanner;

import javax.swing.text.html.Option;

public class BranchManagerEditMenuView extends UIQueryView {
    private MenuItem_T menuItemT;
    private int itemIdx;
    private int Option;
    
    private Branch_T branchT;

    public BranchManagerEditMenuView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        this.branchT = SharedResources.getCurrentStaffBranchT();
        Scanner sc = new Scanner(System.in);

        MenuDisplayView menuDisplayView = new MenuDisplayView(true);
        menuDisplayView.show();        

        System.out.print("item to edit > ");
        itemIdx = sc.nextInt();

        this.menuItemT = branchT.getMenuItems().get(itemIdx-1);
        System.out.println(menuItemT.toString());
        System.out.print(   "1. Edit Name\n" +
                            "2. Edit Description\n" +
                            "3. Edit Price\n" +
                            "4. Edit Category\n" +
                            "5. Edit Availability\n" +
                            "6. Edit All \n> ");
        Option = sc.nextInt();
        sc.nextLine();
        switch (Option) {
            case 1:
                System.out.print("Item Name: ");
                menuItemT.setName(sc.nextLine());
                break;
            case 2:
                System.out.print("Item Description: ");
                menuItemT.setDescription(sc.nextLine());
                break;
            case 3:
                System.out.print("Item Price: ");
                menuItemT.setPrice(sc.nextFloat());        
                break;
            case 4:
                getOptions(1);
                break;
            case 5:
                getOptions(2);
                break;
            case 6:
                System.out.print("Item Name: ");
                menuItemT.setName(sc.nextLine());
                System.out.print("Item Description: ");
                menuItemT.setDescription(sc.nextLine());
                System.out.print("Item Price: ");
                menuItemT.setPrice(sc.nextFloat());
                getOptions(1);
                getOptions(2);                                
                break;
            default:
                System.out.println("Not valid item type");
                break;
        }

    }

    @Override
    public ViewStatus handleQuery() {
        return ViewStatus.OK;
    }

    private void getOptions(int Option) {
        Scanner sc = new Scanner(System.in);
        switch (Option) {
            case 1:
            System.out.print(   "1. DRINK\n" +
                                "2. NA\n" +
                                "3. SET_MEAL\n" +
                                "4. SIDE \n> " );
                switch (sc.nextInt()) {
                case 1:
                    menuItemT.setCategory(MenuItem_T.CATEGORIES.DRINK);
                    break;
                case 2:
                    menuItemT.setCategory(MenuItem_T.CATEGORIES.NA);
                    break;
                case 3:
                    menuItemT.setCategory(MenuItem_T.CATEGORIES.SET_MEAL);
                    break;
                case 4:
                    menuItemT.setCategory(MenuItem_T.CATEGORIES.SIDE);
                    break;
                default:
                    System.out.println("Not valid category");
                    break;
                }   
                break;
            case 2:
                System.out.print("1. AVAILABLE\n" +
                                 "2. NA\n" +
                                 "3. NOT_AVAILABLE \n> " );
                switch (sc.nextInt()) {
                    case 1:
                        menuItemT.setAvailability(MenuItem_T.AVAILABILITY.AVAILABLE);
                        break;
                    case 2:
                        menuItemT.setAvailability(MenuItem_T.AVAILABILITY.NA);
                        break;
                    case 3:
                        menuItemT.setAvailability(MenuItem_T.AVAILABILITY.NOT_AVAILABLE);
                        break;
                    default:
                        System.out.println("Not valid availability");
                        break;
                }
                break;
            default:
                break;
        }
    }
}
