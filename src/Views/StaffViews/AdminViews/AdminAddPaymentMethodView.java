/**
 @author Scott Cheng
 @version 1.0
 @since 2024-04-01
 */

package Views.StaffViews.AdminViews;

import Database.DataStructs.PaymentMethod_T;
import Database.DataStructs.PaymentType;
import Views.UIQueryView;

import java.util.Scanner;

/**
 * view for admin to add a payment method
 */
public class AdminAddPaymentMethodView extends UIQueryView {
    private PaymentMethod_T paymentMethod_T;
    private String paymentMethodName;
    private PaymentType paymentType;
    private int user_request;

    public AdminAddPaymentMethodView() {
        this.myViewName = this.getClass().getCanonicalName();
    }

    @Override
    public void query() {
        Scanner sc = new Scanner(System.in);

        System.out.print("New Payment Method Name: ");
        paymentMethodName = sc.nextLine();

        System.out.println("Payment Types:");
        System.out.println("1. " + PaymentType.CREDIT_CARD);
        System.out.println("2. " + PaymentType.DEBIT_CARD);
        System.out.println("3. " + PaymentType.QR);
        System.out.print("> ");
        this.user_request = sc.nextInt();

    }

    @Override
    public ViewStatus handleQuery() {

        switch (user_request) {
            case 1:
                paymentType = PaymentType.CREDIT_CARD;
                break;
            case 2:
                paymentType = PaymentType.DEBIT_CARD;
                break;

            case 3:
                paymentType = PaymentType.QR;
                break;

            default:
                System.out.println("Payment Type invalid!");
                return ViewStatus.FAIL_AND_GO_BACK;
        }
        this.paymentMethod_T = new PaymentMethod_T(paymentMethodName, paymentType);

        if (this.paymentMethod_T.isInDB()) {
            System.out.println("Payment method with this type already exist!");
            return ViewStatus.FAIL_AND_GO_BACK;
        }
        if (paymentMethod_T.addMeToDB()) {
            System.out.println("Payment method added");
            return ViewStatus.SUCCESS_AND_GO_BACK;
        }
        return ViewStatus.FAIL_AND_GO_BACK;
    }

}
