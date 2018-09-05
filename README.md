# Example Project for TransferWise
Coded by Sa-ryong Kang

## Clean Architecture

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



    