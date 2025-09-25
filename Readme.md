# 🎬 BookMyShow Backend Clone (Spring Boot)

This is a backend clone of BookMyShow, built using **Spring Boot**.  
The project is currently under development and aims to include user management, movie listings, show scheduling, seat booking, and payment features.

---

## 🚀 Tech Stack
- Spring Boot  
- Spring Data JPA (Hibernate)  
- MySQL  
- Spring Security  
- Maven  
---

## 📂 Current Modules

### ✅ Models Implemented
- Booking  
- Movie  
- Payment  
- Screen  
- Seat  
- Show  
- ShowSeat  
- Theater  
- User  

### ✅ DTOs Implemented
- BookingDto  
- BookingRequestDto  
- MovieDto  
- PaymentDto  
- ScreenDto  
- SeatDto  
- ShowDto  
- ShowSeatDto  
- TheaterDto  
- UserDto  

### ✅ Repositories Implemented
- BookingRepo  
- MovieRepository  
- PaymentRepository  
- ScreenRepository  
- ShowRepository  
- ShowSeatRepository  
- TheaterRepository  
- UserRepository  

### ✅ Exception Handling Implemented
- ErrorResponse  
- GlobalExceptionHandler  
- ResourceNotFoundException  
- SeatUnavailableException  

---
### ✅ Services Implemented
- BookingService
- MovieService
- ShowService
- TheaterService
- UserService

---
### ✅ Controller Implemented
- MoviesController
- BookingController
- ShowController
- TheaterController
- UserController

---


## 📌 Project Status
- Models ✅ (completed)  
- DTOs ✅ (completed)  
- JPA Repositories ✅ (completed)  
- Exception Handling ✅ (completed)  
- Services ✅ (completed)  
- Controllers ⏳ 
- Authentication & Authorization ⏳  

---
 ## API Endpoints
🎟️ Booking APIs (/api/bookings)

POST /create → Create a new booking

GET /{id} → Get booking by ID

GET /number/{bookingNumber} → Get booking by booking number

PUT /cancel/{id} → Cancel booking by ID

----

🎬 Movie APIs (/api/movies)

POST / → Create a new movie

GET / → Get all movies

GET /id/{id} → Get movie by ID

GET /Language/{language} → Get movies by language

GET /genre/{Genre} → Get movies by genre

GET /title/{Title} → Get movie by title

PUT /Update/{id} → Update movie by ID

DELETE /Delete/{id} → Delete movie by ID

🎭 Show APIs (/api/Show)

POST /CreateShow → Create a new show

GET /ShowId/{id} → Get show by ID

GET / → Get all shows

GET /ShowsTime/{start}/{end} → Get shows between two dates

🎟️ Theater APIs (/api/theaters)

POST / → Create a new theater

PUT /Update/{id} → Update theater by ID

DELETE /delete/{id} → Delete theater by ID

GET / → Get all theaters

GET /city/{city} → Get theaters by city

👤 User APIs (/api/users)

POST / → Create a new user

PUT /update/{id} → Update user by ID

GET /get/{id} → Get user by ID

DELETE /delete/{id} → Delete user by ID

GET / → Get all users

GET /email/{email} → Get user by email



## ⚙️ Setup Instructions
1. Clone the repository:

git clone https://github.com/PranavX500/BookMYShow-Backend-clone.git
   cd bookmyshow-backend
