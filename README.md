# NASA Image of the Day App

This Android application fetches the NASA Image of the Day using the NASA API and displays it along with its title, description, and date. The project is written in Kotlin and utilizes several libraries and the MVVM architecture for efficient code organization.

## Features

- **NASA API Integration**: The app makes API calls to the NASA API to retrieve the Image of the Day.

- **Image Display**: It displays the Image of the Day in an ImageView using the Glide library for efficient image loading.

- **Title, Description, and Date**: The app also displays the title, description, and date associated with the Image of the Day.

- **MVVM Architecture**: The project follows the Model-View-ViewModel (MVVM) architecture pattern, separating business logic from UI components.

- **LiveData and Data Binding**: LiveData is used to observe and update UI components, and Data Binding is utilized for efficient data binding between the UI and ViewModel.

## Libraries Used

- [Retrofit](https://square.github.io/retrofit/): For making API calls to fetch NASA data.

- [Glide](https://github.com/bumptech/glide): For loading and caching images efficiently.

## ViewModel

The ViewModel in this project handles the following tasks:

- Making API calls to fetch NASA data.

- Checking for an internet connection.

- Storing and retrieving data using SharedPreferences.

- Updating LiveData properties for the UI.

## Usage

To use this project, follow these steps:

1. Clone the repository to your local machine:

2. Open the project in Android Studio.

3. Build and run the app on your Android device or emulator.

4. The app will fetch and display the NASA Image of the Day along with its title, description, and date.

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
