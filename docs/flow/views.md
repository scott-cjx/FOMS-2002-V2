
``` mermaid
flowchart TD

MainView(["MainView \n{UIMenuView}"])

CustomerBaseView(["CustomerBaseView \n{UIMenuView}"])

StaffBaseView(["StaffBaseView \n{UIMenuWithExtraView}"])

MainView ---> | User: 1 | CustomerBaseView ---> s1(Abstracted...)
MainView ---> | User: 2 | StaffBaseView ---> s2(Abstracted...)

```
