# ALM-Car-Rental
John Abbott College's Full Stack Developer (FSD-03) Programming-III Car Rental Project

Our task is to create a Car Rental Website using the tools and technologies that we learned about in class and the Full Stack Developer program, such as Spring Data JPA / Hibernate, Spring Boot DevTools, Thymeleaf, MySQL Driver, Lombok, Spring Web, Validation, Spring Security among others.
Setup Amazon S3 server for storing images.
Setup Amazon DB for handle our MySql instance
Use Heroku for hosting our java project
For handling payment use Stripe API

It will be needed at least the following pages:
/Index
/login
/register
/booking (wizard: search by dates, select vehicle, select options, payment)
/vehicles
/admin/vehicles/  list / add /edit / delete (CRUD)
/admin/vehicles/
/admin/vehicles/add
/admin/vehicles/edit?id=234     /admin/vehicles/edit/234
/admin/vehicles/delete?id=234
/admin/user

The Index page will be the landing page, where you start the journey to rent the vehicle. You’ll have the menu options to do your booking as a member (you’ll need to login) or as a guest (no login required). If it is your first visit and want to become a member you’ll need to register first. In those cases you’ll be redirected to the mentioned pages.

You can start your booking by selecting the pickup and return place from the agency locations, also you have to select the pickup and return dates and times, and after that you can select the vehicle type you are interested in. 

When you select the vehicle type you’ll be redirected to the vehicle page, where you will be able to see the available models (so far we’ve been thinking of economy, standard, luxury, SUV, truck) and their specifications so you can then select the vehicle of your preference. 
	
Then you can select the options, at this moment we think this will be for the options of protection/insurance plans that the customer prefers. Some customers will prefer no insurance because their credit card has an insurance plan, others just a simple insurance to cover damages to others, and others will like to pay for a full plan to cover themselves and the vehicle too.

Finally, you can review and make your reservation at the checkout, where the customer will fill all the information, including the credit card for the purpose of the reservation. 

Most of the Car Rental websites use a 4 or 5 steps process for the booking. Four steps (Budget, Avis, National, Hertz): reserve date(s)/time(s) and location(s), select a car, rental options and finally information/checkout. Five (Enterprise): rental details, pickup-return, vehicle, extras and finally reserve. We decided to use a 4 steps process.

The admin/vehicles is an only admin accessible page to manage the vehicle fleet.
