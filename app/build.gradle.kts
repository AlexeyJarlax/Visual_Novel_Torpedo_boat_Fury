plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.9.21"
}

android {
    namespace = "com.pavlovalexey.torpedo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pavlovalexey.torpedo"
        minSdk = 29
        targetSdk = 34
        versionCode = 6
        versionName = "0.06"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
// Подключение расширений Kotlin для AndroidX Core
    implementation("androidx.core:core-ktx:1.13.0")
// Использование библиотеки для совместимости с темами и стилями в старых версиях Android
    implementation("androidx.appcompat:appcompat:1.6.1")
// Использование библиотеки ConstraintLayout для создания гибких макетов
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
// Использование JUnit для юнит-тестирования кода
    testImplementation("junit:junit:4.13.2")
// Подключение расширений JUnit для тестирования на устройствах Android
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
// Использование Espresso для функционального UI-тестирования Android приложений
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
// Использование Material Design компонентов для UI
    implementation("com.google.android.material:material:1.11.0")
// Процессор аннотаций для компиляции аннотаций библиотеки Glide
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")
// Использование библиотеки Glide для загрузки и отображения изображений
    implementation("com.github.bumptech.glide:glide:4.16.0")
// Использование библиотеки Gson для преобразования Java-объектов в JSON и обратно
    implementation("com.google.code.gson:gson:2.10.1")
// Использование Retrofit для взаимодействия с веб-сервисами на основе RESTful API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Конвертер для использования Gson с Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
// Использование библиотеки OkHttp для работы с HTTP
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
// Использование Timber для логирования
    implementation("com.jakewharton.timber:timber:4.7.1")
// Использование Kotlin Serialization для сериализации/десериализации данных JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
// Использование ViewModel из библиотеки Jetpack для управления данными в UI
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
// Использование Kotlin Extensions для Activity
    implementation("androidx.activity:activity-ktx:1.9.0")
// Использование Koin для внедрения зависимостей в Android приложение
    implementation("io.insert-koin:koin-android:3.3.0")
}