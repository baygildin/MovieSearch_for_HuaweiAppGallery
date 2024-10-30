# --- Правила для Retrofit ---
# --- Новый префикс для классов
-packageobfuscationdictionary search_obfuscation.txt

# Сохраняем все интерфейсы и методы Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Сохраняем анонимные внутренние классы Retrofit (например, Callback)
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# --- Правила для Gson ---
# Сохраняем все классы и поля, аннотированные для Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
-keepattributes *Annotation*



# --- Правила для стандартных библиотек (если необходимо) ---
-dontwarn javax.annotation.**
# --- Правила для Dagger Hilt ---
-keep class dagger.hilt.** { *; }
-dontwarn dagger.hilt.**
-keep class javax.inject.** { *; }
-dontwarn javax.inject.**
# --- Правила для Room ---
-keepclassmembers class * extends androidx.room.RoomDatabase {
    *;
}
-keepclassmembers class * extends androidx.room.RoomDatabase {
    public static ** RoomDatabase_Impl;
}
-keep class androidx.room.** { *; }
-dontwarn androidx.room.RoomDatabase