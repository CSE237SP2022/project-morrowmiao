# project-morrowmiao
### Introduction:
We designed a parkinglot simulator. The program, after setting the dimensions of the parkinglot, will act as the a parkinglot gatekeeper. The users will be prompt to enter strings/ints to complete the action of parking or leaving.
Code-wise, there will be a user-interface that's just the main class, which uses a parkinglot class to keep the records. We ll try to make the parkinglot class OOP.

### Usage:
Run the script, and follow the prompts. Do not cancel/close any of the windows. THe program will crash!

### Features:
1. The user can select a sepecific slot in the parking lot the park, or let the program park it to the first avaliable slot.
2. When user returned with the same name, the program will find the user from its record and ask if the user wants to exit.
3. The user can set the dimension of the parkinglot. It can have multiple levels. 

### Todos:
1. defensive coding (invalid inputs,closed windows)
2. allow each user to part multiple cars
3. add carsize, and large-size cars requires more spaces. (needs new slot search algorithm)
4. add timer, so that the user will 