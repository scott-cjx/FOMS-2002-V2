
``` mermaid
flowchart TD

MainView(["MainView \n{UIMenuView}"])

CustomerBaseView(["CustomerBaseView \n{UIMenuWithExtraView}"])

CustomerChooseBranchView(["CustomerChooseBranchView \n{UIQueryView}"])

CustomerOrderNewView(["***CustomerOrderNewView \n{***UIMenuView}"])

BranchDisplayMenuView(["***BranchDisplayMenuView \n{UIView}"])

CustomerPickupNewView(["***CustomerPickupNewView \n{UIMenuView}"])

getOrderNumber(["***getOrderNumber \n{UIQueryView}"])

StaffBaseView(["StaffBaseView \n{UIMenuWithExtraView}"])

AccountLoginAsStaffView(["AccountLoginAsStaffView \n{UIQueryView}"])

StaffBaseView_staffObj{{"StaffBaseView.staffObj \n{IBackendView}"}}

AccountSettingsView(["AccountSettingsView \n{UIMenuView}"])

AccountLogoutNoView(["AccountLogoutNoView \n{UIView}"])

AccountChangePasswordView(["AccountChangePasswordView \n{UIQueryView}"])

MainView ---> | User: 1 | CustomerBaseView
CustomerBaseView ---> | Branch_T: null | CustomerChooseBranchView
CustomerChooseBranchView ----> CustomerBaseView
CustomerBaseView ---> | User: 1 | CustomerOrderNewView
CustomerBaseView ---> | User: 2 | CustomerPickupNewView
CustomerPickupNewView ---> getOrderNumber ---> CustomerBaseView


CustomerOrderNewView ----> |  | BranchDisplayMenuView


MainView ---> | User: 2 | StaffBaseView
StaffBaseView ---> | User_T: !null | AccountSettingsView
StaffBaseView ---> | User_T: null | AccountLoginAsStaffView
AccountLoginAsStaffView --->  StaffBaseView
StaffBaseView_staffObj ---> | extraSubView | StaffBaseView
AccountSettingsView ---> | User: 1 | AccountLogoutNoView
AccountSettingsView ---> | User: 2 | AccountChangePasswordView
AccountSettingsView ---> | User: 3+ | StaffBaseView_staffObj



```
