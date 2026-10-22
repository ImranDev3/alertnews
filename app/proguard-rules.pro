# Retrofit + Gson
-keepattributes Signature, *Annotation*, EnclosingMethod, InnerClasses
-keep class com.alertnews.app.data.model.** { *; }
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.**
-keepclasseswithmembers,includedescriptorclasses class * {
    @retrofit2.http.* <methods>;
}
