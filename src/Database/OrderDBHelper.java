/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Database;

import Backend.Branch;
import Database.DataStructs.Branch_T;
import Database.DataStructs.IDatabaseItem_T;
import Database.DataStructs.OrderStatus;
import Database.DataStructs.Order_T;

/**
 * helper for order database
 */
public class OrderDBHelper extends DatabaseHelper {
    public OrderDBHelper(String databaseString) {
        super(databaseString);
    }

    public boolean isInDatabase(Order_T orderPartial) {
        Order_T orderTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            orderTemp = (Order_T) this.databaseObject.get(i);
            if (orderPartial.getOrderId().equals(orderTemp.getOrderId())) {
                return true;
            }
        }
        return false;
    }

    public int idxInDatabase_orderId(Order_T orderPartial) {
        Order_T orderTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            orderTemp = (Order_T) this.databaseObject.get(i);
            if (orderPartial.getOrderId().equals(orderTemp.getOrderId())) {
                return i;
            }
        }

        return -1;
    }

    public String printIfBranchIfNewOrder(Branch_T branchPartial) {
        String ret = "";

        Order_T orderTemp;
        int j = 0;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            orderTemp = (Order_T) this.databaseObject.get(i);
            // Match branch
            if (branchPartial.getBranchUUID().equals(orderTemp.getBranchId())) {
                // Match IN_PROGRESS order status
                if (orderTemp.getOrderStatus() == OrderStatus.IN_PROGRESS){
                    ret += j + ": " + orderTemp.getOrderId().toString() + "\n";
                    j++;
                }
            }
        }

        return ret;
    }

    public String printIfBranch(Branch_T branchPartial) {
        String ret = "";

        Order_T orderTemp;
        int j = 0;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            orderTemp = (Order_T) this.databaseObject.get(i);
            if (branchPartial.getBranchUUID().equals(orderTemp.getBranchId())) {
                ret += j + ": " + orderTemp.getOrderId().toString() + "\n";
                j++;
            }
        }

        return ret;

    }

    public boolean addToDatabase(Order_T orderFull) {
        if (this.isInDatabase(orderFull)) {
//            order exists
            return false;
        }
        return this.databaseObject.add(orderFull);
    }

    public int idxInDatabaseIfBranchIfNewOrderByOccuranceIndex(Branch_T branchPartial, int occuranceIndex) {
        Order_T orderTemp;
        int j=0;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            orderTemp = (Order_T) this.databaseObject.get(i);
            // Match branch
            if (branchPartial.getBranchUUID().equals(orderTemp.getBranchId())) {
                // Match IN_PROGRESS order status
                if (orderTemp.getOrderStatus() == OrderStatus.IN_PROGRESS){
                    // Match occuranceIndex, the J value from above function. 
                    if (j == occuranceIndex){
                        return i;
                    }
                    else{
                        j++;
                    }
                }
            }
        }

        return -1;
    }
}
