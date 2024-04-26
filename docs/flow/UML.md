---
title: Views
---


``` mermaid
classDiagram

    class IBackendView~Interface~{
        + getViewOptions() String[]
        + getSubViews() UIView[]
    }

    class IDisplay~Interface~{
        + show() void
    }

    class UIView {
        # subViews UIView[]
        # myViewName String

        + UIView()
        + show() void
        + query() void
        + handleQuery() ViewStatus
        + showAndQuery() ViewStatus

    }

    class UIQueryView {
        + UIQueryView()
    }

    class UIMenuView {
        # myViewOptions String[]
        # isBaseView boolean
        # user_request int

        + show() void
    }

    class UIMenuWithExtraView {
        # myExtraViewOptions String[]
        # myExtraSubViews UIView[]

        + UIMenuWithExtraView()
        + show() void
        + hasNextView() boolean
        + getNextView() UIView
    }

    
    UIView ..> IDisplay~Interface~ : implements
    UIQueryView --|> UIView : extends
    UIMenuView --|> UIView : extends
    UIMenuWithExtraView -- |> UIMenuView : extends

```
