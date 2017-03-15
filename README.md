# KotlinPreferences Object
Kotlin Android Library, that makes preference usage simple and fun.

[![](https://jitpack.io/v/MarcinMoskala/KotlinPreferencesObject.svg)](https://jitpack.io/#MarcinMoskala/KotlinPreferencesObject)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/MarcinMoskala/KotlinPreferencesObject.svg?branch=master)](https://travis-ci.org/MarcinMoskala/KotlinPreferencesObject)

With KotlinPreferencesObject, you can define different preference fields this way:

```kotlin
object Pref: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
}
```

And use it this way:

```kotlin
if(Pref.canEatPie) //...
```

Or with static import:

```kotlin
import com.projectpackage.Pref.canEatPie
```

use it this way:

```kotlin
if(canEatPie) //...
```

Here are other preference definition examples: (see [full example](https://github.com/MarcinMoskala/KotlinPreferencesObject/blob/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences/ExampleConfig.kt) and [usage](https://github.com/MarcinMoskala/KotlinPreferencesObject/tree/master/kotlinpreferences-lib/src/androidTest/java/com/marcinmoskala/kotlinpreferences))

```kotlin
object UserPref: PreferenceHolder() {
    var canEatPie: Boolean by bindToPreferenceField(true)
    var allPieInTheWorld: Long by bindToPreferenceField(0)

    var isMonsterKiller: Boolean? by bindToPreferenceFieldNullable()
    var monstersKilled: Int? by bindToPreferenceFieldNullable()
    var experience: Float? by bindToPreferenceFieldNullable()
    var className: String? by bindToPreferenceFieldNullable()

    var character: Character? by bindToPreferenceFieldNullable()
    var savedGame: Game? by bindToPreferenceFieldNullable()
}
```

There must be application Context added to PreferenceHolder companion object. Example:

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHolder.setContext(applicationContext)
    }
}
```

It it suggested to do it in project Application class, but it can also be done in BaseActivity or just first activity starting app. As an alternative, there can also be added KotlinPreferencesObjectApplication as an name in application in AndroidManifest:

```
android:name=".KotlinPreferencesObjectApplication"
```

## Install

To add KotlinPreferences to the project, add in build.gradle file:

```groovy
dependencies {
    compile 'com.github.marcinmoskala:kotlinpreferencesobject:v0.01'
}
```

And while library is located on JitPack, remember to add on module build.gradle (unless you already have it):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```