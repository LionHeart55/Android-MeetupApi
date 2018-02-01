#Project-2 Story Finder Finale

#### Youtube video Link (required)

<a href="http://www.youtube.com/watch?feature=player_embedded&v=25RdthXnkkM" target="_blank"><img src="http://img.youtube.com/vi/25RdthXnkkM/0.jpg" alt="Project 2" width="240" height="180" border="10" /></a>

https://www.youtube.com/watch?v=25RdthXnkkM&feature=youtu.be
#### Implementation details (recommended)

In this app I used:
- Google location api - to get the coordinates from the device.
- Rest api using Google's volley - to query the meetup api.
- Graphic controls like - NetworkImage and Web view.
- WebView to load content with static html and from URLs. and add a progress dialog while loading.
- Google map api - to show the location of the event venue. Also added marker with Venue name and address.
- Slash screan 
- Transitions between views - implemented a few transitions and placed them in a BaseActivity from which my Views extended.
- Material design - with styles defined in card layout xml files, defining colors, round corners, border etc...


## Problem statement:
You are to build on your project1 by adding real world functionality into your app. You must query an appropriate API, and populate cards. By default, you must work with the Meetup API. But you can decide to use any other API after asking on Piazza(IMDB,etc)


Here are the **inputs** into your program:

* Search keywords(obtained from the SearchView widget)
* Device location(Latitude and longitude) (This can vary based on the API you use, ask permission on Piazza before using a new API).

**Outputs**:

* List of cards
* Cards should contain adequate material based on the API you decide to use(look at the meetup example below).

#####Example API: Meetup API

* If you use the Meetup API, your cards should have Pictures of the Meetup, number of attendies, name, ID(obtained from the API), description, Link, etc.
* The cards populated should use the devices location in order to suggest cards nearby.
* You will require an API key to be able to query the meetup api: https://secure.meetup.com/meetup_api/key/
* You can play with the Meetup console to play around with the API: https://secure.meetup.com/meetup_api/console/
* You could also use Postman to play around with the API.


##Requirements:
These requirements are divided into tiers, you are required to complete all the tiers for a full grade.

**Tier 1 (25% of grade acheived):**

* You are able to query necessary APIs using Volley
* You print the Json onto the console

**Tier 2 (60% of grade acheived):**

* The app converts the Json into class objects
* The app uses the search widget to query the api
* You are able to access the location sensors on the device to find the Latitude and Longitude. Log the location into the console even if your API does not require location.

**Tier 3 (100% of grade acheived):**

* The app works seamlessly.
* Real time search.
* Your cards have the necessary fields based on the API you use(look at the meetup example mentioned above).
* Material design and aesthetically pleasing.

**Optional requirements**(to make up for less work done in project 1)

* Support for the links to open in Webview.
* Additional functionality depending on API you use:
* Eg: A map view if using Meetup API
* Another Ex: Ability to view videos if using the IMDB API


##Submission
* You must push your code onto GitHub before the deadline.
* You must create a Video (minimum 30 seconds, upto 10 mins max) that demonstrates the app working. This video is your chance to explain to us how you implemented the requirements and any other project details. Upload the video as an Unlisted video on Youtube, and add its link onto this README.
* Document any and all **extra work**(outside of the requirements) into the video.


## Note
* Make sure your last push is before the deadline. Your last push will be considered as your final submission.
* Post questions on Piazza if you have any questions.
* Please contact the course staff if you run into issues. We are here to help you!
