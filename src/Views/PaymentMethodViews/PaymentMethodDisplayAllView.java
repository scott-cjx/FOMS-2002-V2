/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.PaymentMethodViews;

import Database.DataStructs.IDatabaseItem_T;
import Database.DataStructs.PaymentMethod_T;
import Database.PaymentMethodDBHelper;
import Main.SharedResources;
import Views.UIView;

import java.util.ArrayList;

/**
 * view to display all payment methods
 */
public class PaymentMethodDisplayAllView extends UIView {
    private final ArrayList<IDatabaseItem_T> pmArr;
    private final PaymentMethodDBHelper paymentMethodDBHelper;

    public PaymentMethodDisplayAllView() {
        this.myViewName = this.getClass().getCanonicalName();
        paymentMethodDBHelper = SharedResources.getPaymentMethodDBHelper();
        pmArr = paymentMethodDBHelper.getAllFromDatabase();
    }

    @Override
    public void show() {
        PaymentMethod_T tmpPM;
        for (int i = 0; i < pmArr.size(); i++) {
            tmpPM = (PaymentMethod_T) paymentMethodDBHelper.getFromDatabase(i);
            System.out.println( (i+1) + ": " + tmpPM.getPaymentType() + ", " + tmpPM.getPaymentName() );
        }
    }

    @Override
    public ViewStatus showAndQuery() {
        this.show();
        return ViewStatus.OK;
    }
}
