/**
 @author Scott Cheng
 @author Bryan Wong
 @version 1.0
 @since 2024-04-22
 */

package Database;

import Database.DataStructs.PaymentMethod_T;

/**
 * helper for payment methods database
 */
public class PaymentMethodDBHelper extends DatabaseHelper {
    public PaymentMethodDBHelper(String databaseString) {
        super(databaseString);
    }

    public boolean isInDatabase(PaymentMethod_T paymentMethodPartial) {
        PaymentMethod_T paymentMethodTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            paymentMethodTemp = (PaymentMethod_T) this.databaseObject.get(i);
            if (
                    (paymentMethodPartial.getPaymentName().equals(paymentMethodTemp.getPaymentName()) &&
                            paymentMethodPartial.getPaymentType().equals(paymentMethodTemp.getPaymentType()))
            ) {
                return true;
            }
        }
        return false;
    }

    public int idxInDatabase_PaymentName_Type(PaymentMethod_T paymentMethodPartial, boolean getFirst) {
        int idx = -1;
        PaymentMethod_T paymentMethodTemp;
        for (int i = 0; i < this.databaseObject.size(); i++) {
            paymentMethodTemp = (PaymentMethod_T) this.databaseObject.get(i);
            if (paymentMethodPartial.getPaymentName().equals(paymentMethodTemp.getPaymentName()) &&
                    paymentMethodPartial.getPaymentType().equals(paymentMethodTemp.getPaymentType()))
            {
                idx = i;
                if (getFirst) {
                    return idx;
                }
            }
        }
        return idx;
    }

    public boolean addToDatabase(PaymentMethod_T newPaymentMethod) {
        if (this.isInDatabase(newPaymentMethod)) {
//            same paymentName and paymentType exists
            return false;
        }
        return this.databaseObject.add(newPaymentMethod);
    }
}
