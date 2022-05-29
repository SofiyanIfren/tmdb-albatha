<h1 align="center">
  <br>
  <a href="https://www.albathanext.com/"><img src="https://static.wixstatic.com/media/43d7b1_dae03c43e8644ebdb3bbecffa55370c8~mv2.png/v1/fill/w_107,h_119,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/A-Next.png" alt="Markdownify" width="200"></a>
  <br>
  TMDB - Al Batha Next
  <br>
</h1>

<h4 align="center">A application based on the<a href="https://www.themoviedb.org/" target="_blank">Movie Data Base Project</a>.</h4>

<p align="center">
  <a href="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"
        alt="Java"></a>
  <a href="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB">
    <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"
        alt="React"></a>
</p>

<p align="center">
  <a href="#key-features">Key Features</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#webservices">Webservices</a> •
  <a href="#credits">Credits</a> •
  <a href="#react-components">React Components</a> •
  <a href="#data-objects">Data Objects</a> •
  <a href="#deployment">Deployment</a> •
  <a href="#improvements">Improvements (soon)</a>
</p>

## Key Features

* Discover on the main page the 10 most popular movies on TMDB
* Search Bar for getting your favorite movies details
* Booking seats for a specific movie (In Progress)

* Administration page : View all booking and details
* Administration page : Search for a specific booking
* Administration page : Cancel and update bookings (For admins only)

## How To Use

To clone and run this application, you'll need these applications to be intalled on your computer :

- Git as versioning tool
- PostgreSQL 14+ version
- Maven 3+ version
- Java 17+ version
- Node 16+ version
- Npm 8+ version
- React 18+ version

> **Note**
> If you don't know how to install react, [take a look here](https://www.freecodecamp.org/news/install-react-with-create-react-app/)
  
Then, from your command line:

```bash
# Clone this repository
$ git clone https://github.com/amitmerchant1990/electron-markdownify

# Go into the server repository
$ cd tmdb-server

# Run the Spring Boot application with Maven
$ mvn clean package

# Go into the client repository
$ cd tmdb-client

# Install dependencies
$ npm install

# Run the app
$ npm start
```

> **Note**
> If you're using Linux Bash for Windows, [see this guide](https://www.howtogeek.com/261575/how-to-run-graphical-linux-desktop-applications-from-windows-10s-bash-shell/).

## Webservices

The webservices consumed by the back-end are (no Swagger API integration for the moment) :

### Movie Webservices : Fetched directly from TMDB API (stateless)

* GET /movie/discover - List the first 10 movies, by popularity
* GET /movie/search/{movieName} - Search a movie by name
* GET /movie/details/{movieId} - Get the full details of a given movie

### User Webservices : Managing Users

* GET /user/all - List all the users
* POST /user/create - Create a new user
* GET /user/search/email/{email} - Search a user by email
 
### Booking Webservices : Managing Bookings

* GET /booking/create/{userEmail}/{movieId}/{numberOfSeats}/{date}/{isActive} - Create a booking for a user related to a specific movie, needs the User to be registered
* GET /booking/all - List all bookings
* GET /booking/all/email/{email} - List all bookings related to a user email
* GET /booking/all/email/id/{email}/{movieId} - List all bookings related to a user email and a specific movie
* PUT /booking/cancel/{bookingId} - Cancel and archivate a booking (no deletion, for data purposes)
* GET /booking/update/{booking} - Update a booking
* GET /booking/search/{keyword} - Search a bookoing list with a specific given keyword

### Administration Webservices : For admin purposes, you need to add a security tool if you want to restrict this access (eg. Spring Security)

* GET /admin/fake/create - [Administration/Testing purpose ONLY] Create fake datas (hardcoded) for manipulation purposes
* GET /admin/fake/delete - [Administration/Testing purpose ONLY] Delete fake datas (hardcoded) for manipulation purposes

## React Components

For simplicity purposes, React components are made with the [Material UI Library](https://mui.com/), using CSS in JS. Here is a list of the main components :

- AppComponent (the App.js default component, entry point of the whole application)
- AppTabComponent, tabs for changing pages
- AdminPageComponent, administration page with fake datas generation. No security added for the moment
- MainPageComponent, the main page where movies are displayed for the user. Register and Booking functionnalities in progress
- SearchBarComponent, the search component tool of the application
- MovieComponent
- BookingComponent

Utilitaries (constants and functions) are stored in the Utils package. There, you can find the server urls that you may have to replace if you need to communicate with the server.

## Data Objects

> **Note**
> BEWARE : The spring properties are setted to create-drop, it means the datas will be suppressed at each spring boot application server restart ! If you want to change this, update the application.properties file before packaging and deploying your app !

List of the objects presents in the Postgres database, listed as models in the back end :

- TMDB Objects are stateless, no storage in the database is required, only a mapping with Jackson to generate JSON objects to the React client :
  + Movie
  + Genre, associated to a movie
  + ProductionCompany, associated to a Movie
  + ProductionCompany, associated to a Movie
  + SpokenLanguage, associated to a Movie
  + MovieDetails, linked to a Movie by it ID

- Internal Objects, stored in the local database :
  + User
  + Booking

## Deployment

For deploying the backend server, change the database accesses in the application.properties file and the api key in the TmdbConstants.java, then from your command line:

```bash
# Package the server app into a jar file
$ mvn clean package
```

Get the gnerated JAR and deploy it on your server : 

```bash
# Deploy the app on localhost 8080 by default
$ nohup java -jar tmdb-0.0.1-SNAPSHOT.jar  &
```

The server port is configured in the application.porperties file (8080 by default). 

For deploying the react app client, send your files with SCP or SFTP for example (all the client-tmdb repository) to your server then deploy it with node. You can also use a packagin tool before sending you datas. Then, from the local repository :

```bash
# Install the dependencies
$ npm install

# Start the app
$ npm start
```

> **Note**
> IMPORTANT : Don't forget to modify the server URI in the Xonstants.js file ! Plus, if there is no data in the app, it is normal, just create them from the administration page ;)

The frontend app is available on the default port 3000. Enjoy !


## Improvements to add

- Build Docker Image for deployment

- BACK-END : Documentation tools (automated documentation generation) : Swagger, Tattletale 
- BACK-END : Add a custom ResponseEntity to wrap webservices responses
- BACK-END : Implement tests

- FRONT-END : decouple js from css in react components (readability)
- FRONT-END : Implement tests

