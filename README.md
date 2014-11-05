ScrollView
==========
This is a ScrollView for Android, it allows to have a listner on the scrolleView. It also help to know the views that are visible or not.


Example
=======
HorizontalScrollView
``` xml
    <com.lib.scrollview.HScrollView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent">

        <com.lib.scrollview.SynchroLayout
            android:id="@+id/container"
            android:layout_width="wrap_content"
            android:layout_height="fill_parent"
            android:orientation="horizontal">
            
            
                  <different element to add>
                  
                  
         </com.lib.scrollview.SynchroLayout>
    </com.lib.scrollview.HScrollView>
```
VerticalScrollView

``` xml
    <com.lib.scrollview.VScrollView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content">

        <com.lib.scrollview.SynchroLayout
            android:id="@+id/container"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            
                <different element to add>
            
            
        </com.lib.scrollview.SynchroLayout>
    </com.lib.scrollview.VScrollView>
            
```
