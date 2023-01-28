# Reviewer
## Description
The Reviewer app is a developed backend and mobile part for reviewing movies, series and games.

## Backend
The backend is written in the Kotlin programming language, the Ktor library was used here.
Implemented only the ability to register and log in, as well as the creation of a token. Data is not encrypted.

For launching the backend you need to follow this steps:
1. Create 2 environment variables with names *postgres_user* ("postgres" by default) and *postgres_password*.
2. In file follow the directory *reviewer-backend/src/main/kotlin/ru/reviewer/**Application.kt*** change the database settings (host and port).
3. In the same file you can change web-server settings (port and host parameters)


Backend endpoints:

|         | Movies         | Shows         | Games         | Accounts      |
|---------|----------------|---------------|---------------|---------------|
| Create  | /movies/add    | /shows/add    | /games/add    |               |
| Delete  | /movies/delete | /shows/delete | /games/delete |               |
| Get     | /movies/fetch  | /shows/fetch  | /games/fetch  | /users/get    |
| Sign In |                |               |               | /login        |
| Sign Up |                |               |               | /registration |


## Mobile
Mobile application only contains authorization features, list of movies, series and games. Also you can see details page of content. The token is stored in environment variables and when you re-enter the application, you do not need to go through the authorization process.

Libraries: Retrofit, Dagger-Hilt, RXJava, Glide.

You need to change host and port for connecting to API, it's located in *reviewermobile/common/**Constants.kt***. If you are using *localhost* as your address on web-server, then don't change the parameters ([Why 10.0.2.2?](https://stackoverflow.com/questions/9808560/why-do-we-use-10-0-2-2-to-connect-to-local-web-server-instead-of-using-computer)).

User Interface:

| Login | Movies List | Movie Details |
|-------|-------------|---------------|
|<img src="https://storage.yandexcloud.net/learning-bucket/GitHub/reviewer/login.png" width="220" height="400">|<img src="https://storage.yandexcloud.net/learning-bucket/GitHub/reviewer/movies_screen.png" width="220" height="400">|<img src="https://storage.yandexcloud.net/learning-bucket/GitHub/reviewer/movie_detail.png" width="220" height="400">|

## Architecture
<img src="https://storage.yandexcloud.net/learning-bucket/GitHub/reviewer/Architecture.png" width="400" height="400">

## Features

| Feature             | Is Implemented |
|---------------------|----------------|
| Auth                |       ✅       |
| Tokens              |       ✅       |
| Create, delete, get |       ✅       |
| Content list        |       ✅       |
| Details             |       ✅       |
| Add reviews         |       ❌       |
| Custom lists        |       ❌       |
| Profile             |       ❌       |