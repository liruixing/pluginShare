
# VirtualAPK使用方法：

## Host Project

build.gradle如下所示在宿主项目的根目录中添加依赖项。

``` java
dependencies {
    classpath 'com.didi.virtualapk:gradle:0.9.8.6'
}
```

将插件应用到的应用模块中build.gradle。

```
apply plugin: 'com.didi.virtualapk.host'
```

在Java的应用模块中编译VirtualAPK build.gradle。

``` java
compile 'com.didi.virtualapk:core:0.9.8'
```

PluginManager在中初始化YourApplication::attachBaseContext()。

``` java
@Override
protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    PluginManager.getInstance(base).init();
}
```
混淆：修改proguard规则以保留VirtualAPK相关文件。

```
-keep class com.didi.virtualapk.internal.VAInstrumentation { *; }
-keep class com.didi.virtualapk.internal.PluginContentResolver { *; }

-dontwarn com.didi.virtualapk.**
-dontwarn android.**
-keep class android.** { *; }
```

加载APK

``` java
String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/Test.apk");
File plugin = new File(pluginPath);
PluginManager.getInstance(base).loadPlugin(plugin);

// Given "com.didi.virtualapk.demo" is the package name of plugin APK, 
// and there is an activity called `MainActivity`.
Intent intent = new Intent();
intent.setClassName("com.didi.virtualapk.demo", "com.didi.virtualapk.demo.MainActivity");
startActivity(intent);
```

## Plugin Project

build.gradle如下所示在插件项目的根目录中添加依赖项

``` java
dependencies {
    classpath 'com.didi.virtualapk:gradle:0.9.8.6'
}
```

将插件应用到的应用模块中build.gradle。

```
apply plugin: 'com.didi.virtualapk.plugin'
```
配置VirtualAPK。请记住在末尾加上以下几行build.gradle。


```
virtualApk {
    packageId = 0x6f             // The package id of Resources.
    targetHost='source/host/app' // The path of application module in host project.
    applyHostMapping = true      // [Optional] Default value is true. 
}
```
