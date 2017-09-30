# BasePopup
抽象出一个方便自定义的Basepopup类，更加方便的创建出一个popup以及动画效果

---

[![](https://jitpack.io/v/razerdp/BasePopup.svg)](https://jitpack.io/#razerdp/BasePopup)

## 请注意：

**如果您是从低于v1.3.0版本升级过来的，请查看改动日志，从v1.3.0版本开始，对于一些误导性的问题和方法名字进行了改动，这将会导致该版本前的方法需要重新复写**

[改动日志](https://github.com/razerdp/BasePopup/blob/master/CHANGELOG-CN.md)


**针对Android 7.0的问题，官方对于popup在7.0确实是有问题的，具体是showAsDropDown方法有问题**

**@link https://code.google.com/p/android/issues/detail?id=221001**


---

## 最新改动：

1.8.3:
  - 构造器不再限定为activity，context采用弱引用

1.8.1~1.8.2:
  - 取消版本号带"v"的问题
  - 修复了展示popupWindow时会导致退出沉浸状态的问题
  - 部分问题暂时无法修复（如input method和full screen的冲突导致无法重新适应布局的问题）

v1.8.0:
 - 集中修复了offset计算问题、7.0的showAsDropDown的问题，如果您还有什么疑问，请在issue里面提出

v1.7.2:
 - 目前仅加了针对4.3的崩溃修复
 - 感谢[@hshare](https://github.com/hshare) 提交的pr
 - 对于issue里面的问题，感谢各位的反馈，最近有点忙，稍后会集中处理的-V-感谢大家的支持


### 最低SDK版本要求 : API 11

# 依赖  [![](https://jitpack.io/v/razerdp/BasePopup.svg)](https://jitpack.io/#razerdp/BasePopup)
#### 请注意【1.8.1之前记得带"v"，如"v1.8.0"，1.8.1之后不需要】

**Step 1.**

**添加Jitpack到您的root gradle，如果无法导包，一般情况下都是这个原因，请仔细检查**

```xml
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

**Step 2.**

添加依赖（请把最新版替换成上面的jitpack标签所示版本）

```xml
	dependencies {
	        compile 'com.github.razerdp:BasePopup:最新版'
	}
```

# 使用方法

----------

**Step 1:**

像您平时定制activity布局文件一样定制您的popup布局（请注意，展示动画的那个view必须是popupview的子view）

etc.
```xml
<?xml version="1.0" encoding="utf-8"?>

<!--根布局，常用作蒙层（就是变暗的背景）-->
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#8f000000">
    
    <!--播放动画的内容，可以认为是popup的主要内容布局-->
    <RelativeLayout
        android:id="@+id/popup_anima"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/bg_dialog"
        android:layout_centerInParent="true"
        android:layout_margin="25dp">
        
        
        <... many views>
        
        

    </RelativeLayout>
</RelativeLayout>
```
![image](https://github.com/razerdp/BasePopup/blob/master/img/etc.png)


**Step 2:**

新建一个类继承Basepopup

**Step 3:**

实现必要的几个方法：

`initShowAnimation()`:初始化一个进入动画，该动画将会用到`initAnimaView()`返回的view

`onCreatePopupView()`:初始化您的popupwindow界面，建议直接使用`createPopupById()`

`getClickToDismissView()`:如果有需要的话，可以使用这个方法返回一个点击dismiss popupwindow的view(也许是遮罩层也许是某个view，这个随您喜欢)

例如

```java
public class DialogPopup extends BasePopupWindow implements View.OnClickListener{

    private TextView ok;
    private TextView cancel;

    public DialogPopup(Activity context) {
        super(context);

        ok= (TextView) findViewById(R.id.ok);
        cancel= (TextView) findViewById(R.id.cancel);

        setViewClickListener(this,ok,cancel);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    protected View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok:
                Toast.makeText(getContext(),"click the ok button",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                Toast.makeText(getContext(),"click the cancel button",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
```

**Step 4:**

把您刚才实现的popup给new出来并调用show方法

例如

```java
    DialogPopup popup = new DialogPopup(context);
    popup.showPopupWindow();
```

# 一些例子
![image](https://github.com/razerdp/BasePopup/blob/master/img/comment_popup_with_exitAnima.gif)
![image](https://github.com/razerdp/BasePopup/blob/master/img/scale_popup.gif)
![image](https://github.com/razerdp/BasePopup/blob/master/img/slide_from_bottom_popup.gif)
![image](https://github.com/razerdp/BasePopup/blob/master/img/input_popup.gif)
![image](https://github.com/razerdp/BasePopup/blob/master/img/list_popup.gif)
![image](https://github.com/razerdp/BasePopup/blob/master/img/menu_popup.gif)

例子更新日志:

https://github.com/razerdp/BasePopup/blob/master/UpdateLog.md

# 方法介绍：
本项目拥有的方法如下(后续的更新没有在这里写了，详情看demo或者更新日志，demo一般情况下都会包括各种情况)：

 - 必须实现的抽象方法：
	+ onCreatePopupView()：得到popupwindow的主体，一般是在xml文件写好然后inflate出来并返回，推荐使用createPopupById()方法以减少代码
	+ initAnimaView()：得到用于展示动画的view，一般为了做到蒙层效果，我们的xml都会给一个灰色半透明的底层然后才是给定展示的popup（详情见demo）
	+ initShowAnimation()：展示popup的动画
	+ getClickToDismissView()：点击触发dismiss的view
 - 非必须实现的公有方法：
	+ initShowAnimator()：同getShowAnimation，不过有些时候用animator更加的丰富
	+ getInputView()：得到给定需要输入的view，一般用于包含edittext的popup
	+ initExitAnimation()：popup执行dismiss时的退出动画
	+ initExitAnimator()：同上
	+ setAutoShowInputMethod()：是否自动弹出输入法
	+ setAdjustInputMethod()：popup是否随着输入法弹出而自适应
	+ getPopupViewById()：工具方法，不用写那么多LayoutInflate.from(context)
	+ setViewClickListener()：工具方法，用于方便您设置onClickListener（多个View共用一个listener哦）
	+ setNeedPopupFade()：设置popup是否淡入淡出，默认为淡入淡出(这个参数将会对整个popup动画哦)
	+ setPopupAnimaStyle()：设定您喜欢的popup动画style(就跟您平时使用popup一样弄得动画style)
	+ 以及各种getter/setter
 - show方法：
	+ showPopupWindow():默认将popup显示到当前窗口
	+ showPopupWindow(int res)：将popup显示到对应的id控件上
	+ showPopupWindow(View v)：将popup显示到view上
 - 一些别的方法：
 	+ setPopupWindowFullScreen(boolean)：popup是否可以覆盖状态栏（全屏）

# 代码解析：
http://www.jianshu.com/p/069f57e14a9c

### 打赏（您的支持是我维护的动力-V-愿意的话，给个零食呗）
![wechat](https://github.com/razerdp/BasePopup/blob/master/img/wechat.jpg)



### License
Apache-2.0
