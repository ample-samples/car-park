# Post Course – Java Project

### Overview

Design a parking lot using object-oriented principles... Your solution should be in Java.

### Assumptions

- The parking lot can hold motorcycles, cars and vans
- The parking lot has motorcycle spots, car spots and large spots
- A motorcycle can park in any spot
- A car can park in a single compact spot, or a regular spot
- A van can park, but it will take up 3 regular spots
- These are just a few assumptions. Feel free to ask your interviewer about more assumptions as needed
- Vans cannot park across any number of compact spaces
- A van takes up 1 large spot

### Specifications

Here are a few methods that you should be able to run:

- Tell us how many spots are remaining
- Tell us how many total spots are in the parking lot
- Tell us when the parking lot is full
- Tell us when the parking lot is empty
- Tell us when certain spots are full e.g. when all motorcycle spots are taken
- Tell us how many spots vans are taking up

### Plan

#### Structure
- Parking lot class which contains a list of parking spots and contains all methods detailed above
- Parking spot interface
  - Parking spot classes: motorcycle, compact, regular, large
- Vehicle interface
  - Vehicle classes: motorcycle, car, van

#### Functionality
