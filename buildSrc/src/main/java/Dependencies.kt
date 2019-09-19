object ApplicationId {
    val application_id = "xyz.belvi.zerofinder"
}

object Releases {
    val version_code = 3
    val version_name = "beta 1.3"
}

object Modules {
    val domain = ":domain"
}

object SDKVersions {
    val min_sdk = 18
    val target_sdk = 29
    var compile_sdk = 29
}


object Versions {
    val kotlin = "1.2.71"
    val gradle = "3.2.1"
    val realm = "5.3.0"
    val retrofit = "2.4.0"
    val retrofit_logger = "3.10.0"
    val android_arch = "1.1.1"
    val room = "2.1.0-alpha06"

    val support = "28.1.1"
    val design = "1.0.0-rc01"
    val constrainst_layout = "2.0.0-alpha2"

    val lottie = "2.5.1"
    val glide = "4.6.1"
    val rx_android = "2.0.1"
    val rx_java = "2.1.8"

    val calligraphy = "2.3.0"
    val pager_indicator = "1.0.3"
    val espresso = "3.0.2"
    val espresso_contrib = "2.0"
    val junit = "4.12"
    val mockito = "2.13.0"
    val mockito_core = "2.+"
    val support_test = "1.0.2"
    val appcompat = "1.1.0-alpha01"
}

object ClassPaths {
    val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlin_ext = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    val gradle_build = "com.android.tools.build:gradle:${Versions.gradle}"
    val realm = "io.realm:realm-gradle-plugin:${Versions.realm}"
}

object Deps {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofix_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"



    val room = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val room_rx = "androidx.room:room-rxjava2:${Versions.room}"
    val room_guava = "androidx.room:room-guava:${Versions.room}"
    val room_compile = "androidx.room:room-compiler:${Versions.room}"

    val lifecycle_runtime = "android.arch.lifecycle:runtime:${Versions.android_arch}"
    val lifecycle_ext = "android.arch.lifecycle:extensions:${Versions.android_arch}"
    val lifecycle_compiler = "android.arch.lifecycle:compiler:${Versions.android_arch}"


    val retrofit_http_logger = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_logger}"


    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    var glide = "com.github.bumptech.glide:glide:${Versions.glide}"


    val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rx_java}"
    val rx_bind = "com.jakewharton.rxbinding:rxbinding:0.4.0"

    val calligraphy = "uk.co.chrisjenx:calligraphy:${Versions.calligraphy}"
    val pager_indicator = "com.romandanylyk:pageindicatorview:${Versions.pager_indicator}"


}


object SupportLibraries {

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val chrome_tab = "com.android.support:customtabs:${Versions.support}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview_v7 = "com.android.support:cardview-v7:${Versions.support}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainst_layout}"
}



object TestDeps {
    val junit = "junit:junit:${Versions.junit}"
    val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val espresso_intents = "com.android.support.test.espresso:espresso-intents:${Versions.espresso}"
    val espresso_contrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso_contrib}"
    val mocito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    val arch_testing = "android.arch.core:core-testing:${Versions.android_arch}"
    val support_test = "com.android.support.test:runner:${Versions.support_test}"
}