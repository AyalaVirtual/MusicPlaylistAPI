# Music Playlist

A RestAPI designed to allow users to register and login to their individual profiles and search for different songs and music genres and add them to a saved list of favorites.

## Technologies Used

* Java 17
* Maven
* Spring Boot
* Spring Data (JPA)
* Spring Security
* JSON Web Tokens
* H2 Database
* Postman



## General Approach

I started off by creating my user stories and acceptance criteria. This helped me visualize my end goals and outline what I wanted to achieve with this project. I also created a spreadsheet of all my HTTP methods and endpoints to keep track of which methods were supposed to hit which endpoint.

Finally, I created an ERD (entity relationship diagram) to plan my different models and their relevant attributes. This helped me to visualize their relationships to one another and plan out how I was going to link the corresponding tables in the database.


<img src="./images/PlaylistERD.png" alt="ERD">



## User Stories 

<b>User Story 1:</b>
As a user, I want to be able to search different genres so that I can discover new music. (GET all songs by genre ID)

<b>Acceptance Criteria:</b>
* User can filter search results by genre.
* Search results should display a list of all songs that match the user's search criteria.


<b>User Story 2:</b>
As a user, I want to be able to search for specific songs so that I can listen to my favorites. (GET individual song by genre ID & song ID)

<b>Acceptance Criteria:</b>
* User can filter search results by genre & song.
* Search results should return an exact match to the user's search criteria.


<b>User Story 3:</b>
As a user, I want to create a profile so that I can save my favorite songs in playlists organized by genre. (POST song to genre)

<b>Acceptance Criteria:</b> 
* User can register and login to their profile.
* User can save specific songs to their profile’s playlist.



## HTTP Endpoints

| Request Type | URL                    | Functionality              | Access  | 
|--------------|------------------------|----------------------------|---------|
| POST         | /auth/users/register/  | Register a new user        | Public  |
| POST         | /auth/users/login/     | Login a user               | Public  |
| POST         | /api/genres/           | Create genre               | Private |
| GET          | /api/genres/           | Get all genres             | Private |
| GET          | /api/genres/1/         | Get single  genre          | Private |
| PUT          | /api/genres/1/         | Update single genre        | Private |
| DELETE       | /api/genres/1/         | Delete single genre        | Private |
| POST         | /api/genres/1/songs/   | Create a song in a genre   | Private |
| GET          | /api/genres/songs/     | Get all songs              | Private |
| GET          | /api/genres/1/songs/1/ | Get a song from a genre    | Private |
| PUT          | /api/genres/1/songs/1/ | Update a song from a genre | Private |
| DELETE       | /api/genres/1/songs/1/ | Delete a song from a genre | Private |



## Major Hurdles

At first, I struggled with getting my POST requests to work because Postman kept giving me a 403 Forbidden error. I was able to remove a line of code in the user authorizations that fixed this error.

When I first  tried to add the option to search for, save, update, and delete songs, I wasn't able to get the CRUD methods working after adding the security and authentication layer. I have since fixed these issues and added the necessary logic to make the app fully functional. Users can now search for, create, update, and delete genres and songs from their account's list of favorites. 



### Links
* User Stories - https://docs.google.com/document/d/1SGBLGTsmVIoI15HafljtCOIrUpOUmSgHz7q_doDW2vI/edit?usp=sharing

* HTTP requests/endpoints spreadsheet - https://docs.google.com/spreadsheets/d/1fRC1rdl435kJwRgIQfqUqSPaAvpXt2WSC11jCMXAtT8/edit?usp=sharing

* ERD (entity relationship diagram) - https://lucid.app/lucidchart/25ce1cef-2bbe-49c0-a567-cecbd7df60e2/edit?viewport_loc=664%2C250%2C756%2C423%2C0_0&invitationId=inv_0cab7add-6d52-48b0-9665-1d940a3d0319



### Special Thanks

* Suresh Sigera - my instructor who not only taught me all the concepts used in this project, but also wrote the code I used in the security package handling authorization. [GitHub](https://github.com/sureshmelvinsigera)

* Elizabeth Yang - my partner in pair programming during class, who helped me to refactor CRUD methods relevant to the user, which I used as a reference when writing the CRUD methods for this project. [GitHub](https://github.com/lizabawa) 


### Author

:woman_technologist: Erica Ayala

* [LinkedIn](https://www.linkedin.com/in/ayalavirtual)

* [GitHub](https://www.github.com/AyalaVirtual) 



