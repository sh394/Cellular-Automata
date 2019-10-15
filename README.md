simulation
====

This project implements a cellular automata simulator.

Names:
* Riley Cohen - Rsc30
* Kyle Hong - sh394
* Ben Lawrence - bcl19

### Timeline

Start Date: September 15, 2019

Finish Date: September 29, 2019

Hours Spent: 90-100 hours amongst all three team members. About 30 hours spent per team member.

### Primary Roles
Riley worked primarily on the front end and the main controller.

Ben worked primarily on the backend.

Kyle worked primarily on the rules, XML files, and configuration.

### Resources Used
We collaborated with each other. Oracle's documentation of Java was used.

### Running the Program

Main class: Controller.java

Data files needed: Fire.properties, GameOfLife.properties, Percolation.properties, 
Segregation.properties, SimulationTypes.properties, UINumberConstants.properties, 
UIText.properties, Water.properties, main.css

Interesting data files: UIText.properties is an interesting data file because it easily scales to hold text in other languages. It is interesting how Java has a built in ability to pick a language based on properties. This is also very scalable which is nice.

Features implemented:
* Backend: the five main simulations
* Configuration: the five main simulations, reading XML files, error checking(invalid or no simulation type given, cell locations given that are outside the bounds of the grid's size, appropriate default values when parameter values are invalid or not given), saving current state as an XML
* Visualization: the five main simulations, users can interact with the simulation dynamically to change it's parameters

Assumptions or Simplifications:

Known Bugs:
* Backend: This does not check for valid data so the user could send only a partially filled arraylist, which would cause the backend to crash.
* Configuration: Does not check for all file format types that are not XML when user is selecting

Extra credit: None


### Impressions

#### Riley

I enjoyed this project, as it demonstrated the MVC model and allowed us to 'divide and conquer' – so to speak – the project. Love the project.

#### Kyle
This project was definitely challenging in terms of both designing and coding as part of a team. Although we were not able to add all of the new features, I think we, in general, planned our design well and worked efficiently because our code was scalable for the most part and required no drastic changes to add new features. I just wish we could have spent more time on it.

#### Ben
I enjoyed this project but wish I could have spent more time on it. It is an interesting concept and I enjoyed spending a great amount exploring different graph types for the backend. I ended up learning a lot about how to implement a graph well. Sadly this also hurt me since it meant I didn't have as much time to spend on other features. I did feel like I learned a lot about making classes scalable as the graph and cells had to extend to any type and variety of simulation. I also learned about working well with a team over a long period of time.