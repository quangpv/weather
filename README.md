## Code principal

SOLID

S - Single-responsiblity Principle 

O - Open-closed Principle 

L - Liskov Substitution Principle 

I - Interface Segregation Principle 

D - Dependency Inversion Principle


## Code structure

Clean Architecture with MVVM pattern

<img src='https://github.com/quangpv/weather/blob/main/documents/diagram.png' width='400px'/>

## Framework using

ViewModel 
LiveData 
View binding 
Coroutines 
Retrofit 
Mockito for test.
Dependency Injection (Android support).

## Run step

1. Unit Test
   `./gradlew testDebugUnitTest`
2. Run app
   `./gradlew installDebug`
3. Build release (apk app).
   `./gradlew assembleRelease`
   
## Check list
[v] 1. The application is a simple Android application which is written by Java/Kotlin.

[v] 2. The application is able to retrieve the weather information from OpenWeatherMaps API.

[v] 3. The application is able to allow user to input the searching term.

[v] 4. The application is able to proceed searching with a condition of the search term length must be
from 3 characters or above.

[v] 5. The application is able to render the searched results as a list of weather items.

[v] 6. The application is able to support caching mechanism so as to prevent the app from generating a
bunch of API requests.

[v] 7. The application is able to manage caching mechanism & lifecycle.

[v] 8. The application is able to handle failures.

[v] 9. The application is able to support the disability to scale large text for who can't see the text
clearly.

[x] 10. The application is able to support the disability to read out the text using VoiceOver controls.

