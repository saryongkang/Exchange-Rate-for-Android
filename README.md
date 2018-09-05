# Example Project for TransferWise
Coded by Sa-ryong Kang

## What I've made: Exchange Rate Viewer

- Used API: Currency Layer
http://currencylayer.com/

## Philosophy: Clean Architecture

### Presentation Layer
- View: Activity/Framework, XML with Data Binding
- ViewModel: expose event to View via LiveData, call UseCase with mainly LiveData and sometimes RxJava

### Domain Layer
- Use Case, which also has role of Translator

### Data Layer
- Repository, abstracts data access

## Used Libraries:
- Android Architecture Component (LifeCycle, LiveData)
- Data Binding
- RxJava
- Realm
- Dagger
- Retrofit
- Glide
- Kotshi (Moshi without reflection. More than 2x faster than gson)
- KTX
- Leak Canary
- Timber
- Robolectric
- Mockito

## How to install:

Nothing special!
This project was developed by Android Studio 3.1.4. and no another setting is required.

## Known Problem
To run unit test for [CurrencyListViewModel], you need to comment 1 line on init method.