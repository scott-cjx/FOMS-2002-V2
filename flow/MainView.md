
``` mermaid
flowchart TD

MainView(["MainView \n{UIMenuView}"])

CustomerBaseView(["CustomerBaseView \n{UIMenuView}"])

StaffBaseView(["StaffBaseView \n{UIMenuWithExtraView}"])

MainView ---> | User: 1 | CustomerBaseView ---> CustomerView
MainView ---> | User: 2 | StaffBaseView ---> StaffView

subgraph CustomerView

    CustomerBaseView(["CustomerBaseView \n{UIMenuView}"])
    CustomerChooseBranchView(["CustomerChooseBranchView \n{UIQueryView}"])
    CustomerPickupOrderView(["CustomerPickupOrderView \n{UIQueryView}"])
    OrderPickupOkView(["OrderPickupOkView \n{UIView}"])
    CartBaseView(["CartBaseView \n{UIMenuView}"])
    CartDisplayView(["CartDisplayView \n{UIView}"])
    BranchDisplayMenuView{{"BranchDisplayMenuView \n{IBackendView}"}}
    CartOrderQueryChoiceView(["CartOrderQueryChoiceView \n{UIQueryView}"])
    OrderPayBaseView(["OrderPayBaseView \n{UIView}"])
    OrderPayProcessView(["OrderPayProcessView \n{UIQueryView}"])
    OrderRecieptView(["OrderRecieptView \n{UIView}"])
    CartOrderAmendView(["CartOrderAmendView \n{UIQueryView}"])

    CustomerBaseView            ---> | Branch: null | CustomerChooseBranchView
    CustomerChooseBranchView    ---> CustomerBaseView
    CustomerBaseView            ------> | User: Order | CartBaseView
    CustomerBaseView            ---> | User: Pick-Up | CustomerPickupOrderView
    CustomerPickupOrderView     ---> OrderPickupOkView
    OrderPickupOkView           ---> CustomerBaseView

    CartBaseView           ---> | User: View Menu | BranchDisplayMenuView
    BranchDisplayMenuView       ---> CartBaseView

    CartBaseView                ---> | User: Add | BranchDisplayMenuView
    CartBaseView                ---> | User: Amend | CartDisplayView
    CartBaseView                ---> | User: Remove | CartDisplayView
    CartBaseView                ---> | User: Pay | OrderPayBaseView
    CartBaseView                ---> | User: View Cart | CartDisplayView
    BranchDisplayMenuView       ----> | CtxUser: View | CartBaseView
    BranchDisplayMenuView       ---> CartBaseView

    OrderPayBaseView            ---> OrderPayProcessView
    OrderPayProcessView         ---> OrderRecieptView
    OrderRecieptView            --> CustomerBaseView

    BranchDisplayMenuView       ---> | CtxUser: Add | CartOrderQueryChoiceView
    CartDisplayView             ---> | CtxUser: Remove | CartOrderQueryChoiceView
    CartDisplayView             ---> | CtxUser: Amend | CartOrderQueryChoiceView
    CartDisplayView             ---> CartBaseView
    CartOrderQueryChoiceView        --> | CtxUser: Amend | CartOrderAmendView
    CartOrderQueryChoiceView        ----> CartBaseView
    CartOrderAmendView              --> CartBaseView
end

subgraph StaffView

    MainView(["MainView \n{UIMenuView}"])
    StaffBaseView(["StaffBaseView \n{UIMenuWithExtraView}"])
    AccountLoginAsStaffView(["AccountLoginAsStaffView \n{UIQueryView}"])
    StaffBaseView_staffObj{{"StaffBaseView.staffObj \n{IBackendView}"}}
    AccountSettingsView(["AccountSettingsView \n{UIMenuView}"])
    AccountLogoutNoView{{"AccountLogoutNoView \n{IBackendView}"}}
    AccountChangePasswordView(["AccountChangePasswordView \n{UIQueryView}"])
    Admin{{"Admin.Views \n{Backend.Staff}"}}
    NormalStaff{{"NormalStaff.Views \n{Backend.Staff}"}}
    BranchManager{{"BranchManager.Views \n{Backend.Staff}"}}
    AdminManageAccounts{{"Admin.Manage_Accounts \n{IBackendView}"}}
    AdminManageStaff{{"Admin.Manage_Staff \n{IBackendView}"}}
    AdminManageBranches{{"Admin.Manage_Branches \n{IBackendView}"}}
    AdminManagePaymentMethods{{"Admin.Manage_Payment_Methods \n{IBackendView}"}}

    BranchManagerDisplayStaffList{{"Display Staff List \n{IBackendView}"}}
    BranchManagerModifyMenu{{"Modify Menu \n{IBackendView}"}}

    NormalStaffViewOrders{{"View Orders \n{IBackendView}"}}
    NormalStaffProcessOrder{{"Process Order \n{IBackendView}"}}

    StaffBaseView               ---> | User_T: NOT null | AccountSettingsView
    StaffBaseView               ---> | User_T: null | AccountLoginAsStaffView
    AccountLoginAsStaffView     --->  StaffBaseView
    StaffBaseView_staffObj      ---> | extraSubView | StaffBaseView
    AccountSettingsView         ----> | User: Log Out | AccountLogoutNoView

    AccountSettingsView         ---> | User: Change Password | AccountChangePasswordView
    AccountChangePasswordView   ---> AccountLogoutNoView
    AccountSettingsView         ---> | User: Backend.Staff.View.viewOptions | StaffBaseView_staffObj

    StaffBaseView_staffObj      <-------> | CtxUser: Admin | Admin
    StaffBaseView_staffObj      <---> NormalStaff
    StaffBaseView_staffObj      <----> BranchManager

    Admin                       ---> | User: Manage Accounts | AdminManageAccounts
    Admin                       ---> | User: Manage Staff | AdminManageStaff
    Admin                       ---> | User: Manage Branches | AdminManageBranches
    Admin                       ---> | User: Manage Payment Methods | AdminManagePaymentMethods

    NormalStaff                 ---> | User: View Order | NormalStaffViewOrders
    NormalStaff                 ---> | User: View Order | NormalStaffProcessOrder

    BranchManager               -..-> | extends | NormalStaff

    BranchManager               ---> | Display Staff List | BranchManagerDisplayStaffList
    BranchManager               ---> | Modify Menu | BranchManagerModifyMenu
end

```

MainView                    ---> | ... Abstracted | StaffBaseView
AccountLogoutNoView         ---> MainView