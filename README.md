# Simple Weather
Weather forecast application with a clean UI style

## Libraries and Services Used
* **Jetpack Navigation Components** - navigation functionality between elements of the application
* **Material Design Components** - standardised UI design
* **Retrofit** - networking and messaging
* **Dagger2** - dependency injection

## Architectural Approach
* Model-View-ViewModel structure
* Repository pattern to separate ViewModel from remote services
* Observer pattern using LiveData to notify UI of data retrieved by the ViewModel

## UI Elements
* Navigation components graph to support navigation between fragments and activities
* RecyclerView using GridLayoutManager
* Collapsing appbar layout
* CardView


![screenshot](https://github.com/chrisc3456/simpleweather/blob/master/screenshot_main.png)
![screenshot](https://github.com/chrisc3456/simpleweather/blob/master/screenshot_detail.png)
