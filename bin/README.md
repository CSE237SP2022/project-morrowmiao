# project-morrowmiao
### Introduction:
We designed a parking lot simulator. The program, after setting the dimensions of the parking lot, will act as a parkinglot gatekeeper. The users will be prompted to enter strings/ints to complete the action of parking or leaving.
Code-wise, there will be a user-interface that's just the main class, which uses a parking lot class to keep the records. We ll try to make the parking lot class OOP.

### Usage:
Run the script, and follow the prompts. Do not cancel/close any of the windows. The program will crash!

### Features:
1. The user can select a specific slot in the parking lot the park, or let the program park it to the first avaliable slot.
2. When user returned with the same name, the program will find the user from its record and ask if the user wants to exit.
3. The user can set the dimension of the parking lot. It can have multiple levels. 
4. add carsize, and large-size cars requires more spaces. (implemented the new slot search algorithm)
5. add timer, so that the users will pay based on the amount of time parked before they leave.


### Todos:
1. defensive coding (invalid inputs,closed windows)
2. allow each user to park multiple cars
3. modify the price so that it is per second per slot

