# LabelView

一个Android的标签View，标签区域在右上角，可设置成圆角，可添加文字。

A label widget for Android, label area in the upper right corner, can be set to a rounded corner, you can add text.


<image src="./images/device-2018-04-10-204040.png" width="270" height="540" >
<image src="./images/ezgif-5-3da993fa95.gif" width="270" height="540" >


<video src="./images/Screenrecorder-2018-04-10-19-47-13-333.mp4" width="270" height="540"  controls="controls">
Your browser does not support the video tag.
</video>

# Usage

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```
	dependencies {
	        compile 'com.github.zhoushengming:LabelView:1.0'
	}
```