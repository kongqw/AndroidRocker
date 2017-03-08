---
转载请说明出处！
作者：[kqw攻城狮](http://kongqw.github.io/about/index.html)
出处：[个人站](http://kongqw.com/2016/09/01/2016-09-01-Android%E8%87%AA%E5%AE%9A%E4%B9%89%E6%91%87%E6%9D%86/) | [CSDN](http://blog.csdn.net/q4878802/article/details/52402529)

---

[![](https://jitpack.io/v/kongqw/AndroidRocker.svg)](https://jitpack.io/#kongqw/AndroidRocker)

# 部署

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

``` gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

``` gradle
dependencies {
        compile 'com.github.kongqw:AndroidRocker:1.0.1'
}
```

# 使用

## XML

``` xml
<com.kongqw.rockerlibrary.view.RockerView
    android:id="@+id/rockerView"
    android:layout_width="200dp"
    android:layout_height="200dp"
    kongqw:areaBackground="@drawable/default_area_bg"
    kongqw:rockerBackground="@drawable/default_rocker_bg"
    kongqw:rockerRadius="30dp" />
```

## Activity

### 初始化

``` java
RockerView rockerView = (RockerView) findViewById(R.id.rockerView);
```

### 设置回调模式

``` java
// 设置回调模式
rockerView.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
```

### 监听摇动方向

``` java
// 监听摇动方向
rockerView.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, new RockerView.OnShakeListener() {
    @Override
    public void onStart() {
        mLogLeft.setText(null);
    }

    @Override
    public void direction(RockerView.Direction direction) {
        mLogLeft.setText("摇动方向 : " + getDirection(direction));
    }

    @Override
    public void onFinish() {
        mLogLeft.setText(null);
    }
});
```

### 监听摇动角度

``` java
// 监听摇动角度
rockerViewRight.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
    @Override
    public void onStart() {
        mLogRight.setText(null);
    }

    @Override
    public void angle(double angle) {
        mLogRight.setText("摇动角度 : " + angle);
    }

    @Override
    public void onFinish() {
        mLogRight.setText(null);
    }
});
```

-------------------------

# 效果图

![效果图](http://img.blog.csdn.net/20160901182847783)

![效果图](http://img.blog.csdn.net/20160901182902455)

# 源码

[KqwRockerDemo](https://github.com/kongqw/KqwRockerDemo)

喜欢就给个`star`，谢谢！

# 功能

* 支持自适应大小
* 支持2个方向、4个方向、8个方向的摇动监听
* 支持摇动角度获取
* 可选回调模式
* 支持可摇动区域自定义
* 支持摇杆自定义
* 支持设置图片、色值、Share图形

# 使用

``` java
<kong.qingwei.rockerlibrary.RockerView
    android:id="@+id/rockerView_center"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:layout_centerHorizontal="true"
    kongqw:areaBackground="#FF333333"
    kongqw:rockerBackground="#FF987654"
    kongqw:rockerRadius="15dp" />
```


# 参数

|     参数          | 是否必须 |  描述           |
|------------------|------|----------------|
| areaBackground   | 可选 | 可摇动区域的背景 |
| rockerBackground | 可选 | 摇杆的背景      |
| rockerRadius     | 可选 | 摇杆半径        |


# 设置回调方式

``` java
setCallBackMode(CallBackMode mode)
```

参数

|     回调方式                 | 描述               |
|-----------------------------|-------------------|
| CALL_BACK_MODE_MOVE         | 有移动就立刻回调    |
| CALL_BACK_MODE_STATE_CHANGE | 状态有变化的时候回调 |



# 监听摇动角度

返回角度的取值范围：[0°,360°)

![取值范围](http://img.blog.csdn.net/20160901183045786)

``` java
setOnAngleChangeListener(OnAngleChangeListener listener)
```

# 监听摇动方向

``` java
setOnShakeListener(DirectionMode directionMode, OnShakeListener listener)
```

支持监听的方向

| 方向                    |  图              | 描述    |
|------------------------|------------------|------|
| DIRECTION_2_HORIZONTAL |![左右两个方向](http://img.blog.csdn.net/20160901183144896)| 横向 左右两个方向  |
| DIRECTION_2_VERTICAL   |![上下两个方向](http://img.blog.csdn.net/20160901183332084)| 纵向 上下两个方向  |
| DIRECTION_4_ROTATE_0   | ![四个方向](http://img.blog.csdn.net/20160901183347055)          |四个方向          |
| DIRECTION_4_ROTATE_45  | ![四个方向 旋转45°](http://img.blog.csdn.net/20160901183404461)  |四个方向 旋转45°  |
| DIRECTION_8            | ![八个方向](http://img.blog.csdn.net/20160901183419477)          |八个方向          |


方向描述

| 方向                 | 描述 |
|----------------------|-----|
| DIRECTION_LEFT       | 左  |
| DIRECTION_RIGHT      | 右  |
| DIRECTION_UP         | 上  |
| DIRECTION_DOWN       | 下  |
| DIRECTION_UP_LEFT    | 左上 |
| DIRECTION_UP_RIGHT   | 右上 |
| DIRECTION_DOWN_LEFT  | 左下 |
| DIRECTION_DOWN_RIGHT | 右下 |
| DIRECTION_CENTER     | 中间 |


# 例子

``` java
RockerView rockerViewLeft = (RockerView) findViewById(R.id.rockerView_left);
if (rockerViewLeft != null) {
    rockerViewLeft.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
    rockerViewLeft.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, new RockerView.OnShakeListener() {
        @Override
        public void onStart() {
            mLogLeft.setText(null);
        }

        @Override
        public void direction(RockerView.Direction direction) {
            mLogLeft.setText("摇动方向 : " + getDirection(direction));
        }

        @Override
        public void onFinish() {
            mLogLeft.setText(null);
        }
    });
}

RockerView rockerViewRight = (RockerView) findViewById(R.id.rockerView_right);
if (rockerViewRight != null) {
    rockerViewRight.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
        @Override
        public void onStart() {
            mLogRight.setText(null);
        }

        @Override
        public void angle(double angle) {
            mLogRight.setText("摇动角度 : " + angle);
        }

        @Override
        public void onFinish() {
            mLogRight.setText(null);
        }
    });
}
```

