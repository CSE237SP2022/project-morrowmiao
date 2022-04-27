# project-morrowmiao
### Introduction:
We designed a parking lot simulator. The program, after setting the dimensions of the parking lot, will act as a parkinglot gatekeeper. The users will be prompted to enter strings/ints to complete the action of parking or leaving.
Code-wise, there will be a user-interface that's just the main class, which uses a parking lot class to keep the records. We ll try to make the parking lot class OOP.

### Usage:
Run the script, and follow the prompts. Do not cancel/close any of the windows. The program will crash!

## Iteration 1
### Features:
1. The user can select a specific slot in the parking lot the park, or let the program park it to the first avaliable slot.
2. When user returned with the same name, the program will find the user from its record and ask if the user wants to exit.
3. The user can set the dimension of the parking lot. It can have multiple levels. 

### Todos:
1. defensive coding (invalid inputs,closed windows)
2. allow each user to park multiple cars
3. add car size so that different cars occupy different amount of slots
4. add unit testing
5. add a timer for the parkinglot to collect money

## Iteration 2
### Features:
1. The user can select a specific slot in the parking lot the park, or let the program park it to the first avaliable slot.
2. When user returned with the same name, the program will find the user from its record and ask if the user wants to exit.
3. The user can set the dimension of the parking lot. It can have multiple levels. 
4. added carsize, and large-size cars requires more spaces. (implemented the new slot search algorithm)
5. added timer, so that the users will pay based on the amount of time parked before they leave.

### Todos:
1. defensive coding (invalid inputs,closed windows)
2. allow each user to park multiple cars
3. add more unit testing to cover all functions
4. bugfixes

## Iteration 3
### Features:
1. The user should be able to select a specific slot in the parking lot the park, or let the program park it to the first avaliable slot.
2. When user returned with the same name, the program should be able to find the user from its record and ask if the user wants to exit with a car or park one more car.
3. The user should be able to set the dimension of the parking lot. It can have multiple levels, rows, and slots (the parking lot size is 3 dimensional). 
4. larger-sized cars should requires more spaces. (implemented the new slot search algorithm)
5. added timer, so that the parkinglot should be able to charge the user based on the amount of time parked before they leave for each car they parked.
6. Tested the user interface, now the user should be able to follow clearer instructions.


