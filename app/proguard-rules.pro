# Annotations
-keepattributes Signature
-keepattributes Exceptions

-dontwarn com.google.common.**

# Sugar ORM
-keep public class * extends com.orm.SugarRecord { *; }
-keep class com.orm.** { *; }

# About Libraries
-keep class .R
-keep class **.R$* {
    <fields>;
}
