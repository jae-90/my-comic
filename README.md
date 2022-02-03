# My Comic

> My Comic is an Android app that show information about a specific comic using the Marvel API.


---

## Features

- Show a cover image of a Spider-man comic.
- Brief description of the comic.
- Ability to see appearing characters of the comic.
- Ability to see the selected character in detail.


---

**Demo**

![DEMO GIF](gif/my_comic_demo.gif)
---

## Built with

> The structure of this app is architectured using MVVM (Model-View-Viewmodel) pattern with variety of Android Jetpack's Architecture Components.

* [Android Jetpack - Architecture Component](https://developer.android.com/topic/libraries/architecture)
* [Android Jetpack - ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Android Jetpack - Navigation](https://developer.android.com/guide/navigation)
* [Android Jetpack - LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [MockK](https://mockk.io)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)


---

### Requirement

- In order to use the api-keys, `apikey.properties` file is necessary with keys of:
> API_KEY_PUBLIC = "xxxxx"
> API_KEY_PRIVATE = "xxxxx"
- Put necessary values and add the file into the root of the project.


---

### Need improvements on

- Data model mapping/cleanup.
- Other network interceptors (429, 503, etc) for better network error handling.
- Custom exception for better exception handling at error datastate.
- DB/caching functionality.
- DataBinding for better view - data communication.
- More UI/Unit tests.
- And many more!


---

## Author

* **Jae Lee**
