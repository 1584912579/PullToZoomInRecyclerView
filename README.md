# PullToZoomInRecyclerView

- - -
支持下拉放大图片的RecyclerView
## PreView
![](https://github.com/gatsbydhn/PullToZoomInRecyclerView/blob/master/gif/demo.gif)
## 使用
第一步：在根目录的build.gradle文件中加入
`maven { url "https://jitpack.io" }` 效果如下：
```
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```
第二步：添加依赖
```
dependencies {
	        compile 'com.github.gatsbydhn:PullToZoomInRecyclerView:v1.0'
	}
```