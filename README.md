# Weather Forecast App

## Description
- Simple Weather forecast application
- fetched from Open Weather Api
- consisted of two screens: Search screen and result screen

## Architecture
- consists of UI layer, Repository layer, and Data layer
- UI layer consists of:
  - main activity and view model
  - compose screens and custom components
  - ui related models
- Repository layer
  - abstraction between UI and data layer
  - transform response into limited result types
- Data layer
  - remote data source retrofit interface
  - data response models
  - dependency injection (use koin due to its ease of setup)

## Running the app
- create apikey.properties file at the root of the project
- add API_KEY value to the properties file i.e. API_KEY="abc"
- sync and rebuild the project to generate BuildConfig
- run the application