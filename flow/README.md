# Flow

This file shows the flow of the program...

This is outdated, but there's MORE views extended to this...

This just shows the general gist of the program flow...

## Unable to View charts

View this from the Github repository if the charts are not generating for you.

![img](../uml/View_Flow_Diagram.png)


``` mermaid
flowchart TD

MainView(["MainView \n{UIMenuView}"])

CustomerBaseView(["CustomerBaseView \n{UIMenuView}"])

StaffBaseView(["StaffBaseView \n{UIMenuWithExtraView}"])

MainView ---> | User: 1 | CustomerBaseView ---> s1(Abstracted...)
MainView ---> | User: 2 | StaffBaseView ---> s2(Abstracted...)

```


``` mermaid
flowchart TD

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

MainView                    ---> | ... Abstracted | StaffBaseView
StaffBaseView               ---> | User_T: NOT null | AccountSettingsView
StaffBaseView               ---> | User_T: null | AccountLoginAsStaffView
AccountLoginAsStaffView     --->  StaffBaseView
StaffBaseView_staffObj      ---> | extraSubView | StaffBaseView
AccountSettingsView         ----> | User: Log Out | AccountLogoutNoView
AccountLogoutNoView         ---> MainView

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
```


``` mermaid
flowchart TD

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

MainView                    ---> | ... Abstracted | StaffBaseView
StaffBaseView               ---> | User_T: NOT null | AccountSettingsView
StaffBaseView               ---> | User_T: null | AccountLoginAsStaffView
AccountLoginAsStaffView     --->  StaffBaseView
StaffBaseView_staffObj      ---> | extraSubView | StaffBaseView
AccountSettingsView         ----> | User: Log Out | AccountLogoutNoView
AccountLogoutNoView         ---> MainView

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


```