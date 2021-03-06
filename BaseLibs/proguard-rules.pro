# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
#
# Add any project specific keep options here:
#
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interfaces
# class:

-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#Gson相关
   -keepattributes *Annotation*
   -keep class sun.misc.Unsafe {*;}
   -keep class com.idea.fifaalarmclock.entity.***
   -keep class com.google.gson.stream.** {*;}

#OkHttp相关
   -dontwarn com.squareup.okhttp.**
   -keep class com.squareup.okhttp.** {*;}
   -dontwarn okio.**

# universal-image-loader 混淆
   -dontwarn com.nostra13.universalimageloader.**
   -keep class com.nostra13.universalimageloader.** {*;}

