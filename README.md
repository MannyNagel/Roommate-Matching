# Roommate-Matching
Goal: Given a list of people, their requests and the quantity and size of rooms, calculate the optimal rooming assignments.

In many programs and camps, a dreaded moment for the staff and campers is rooming assignments. Each camper places requests for who he/she would like to room with and an administrator is left with a tangled mess trying to make everyone happy. 

I was this administrator one summer, working out rooming requests for a small travel program of 40 campers. Even with my advanced spreadsheets, the graph was still very complicated, often leaving at least some kids in tears. 

After speaking with other administrators in similar programs, I realized that nobody has a truly efficient way of accomplishing this task. Some groups even try spreading out flashcards on a table and working everything out by hand!

As a budding algorithmic thinker, I realized there must be a better way. This problem maps out like a directed graph, each node representing a person and an arrow connecting them to someone they requested. The question that lies before me is to at a bare minimum streamline this process using technology and ideally find an efficient algorithm to solve it. 

Objectives
  1. Feasible Solution: Ensure every camper receives at least one request
  2. Optimal Solution: Once fulfilled [1], Maximize requests fulfilled
  3. Track Diversity: Often camps don’t merely want to group all friends together, but rather to encourage and foster new friendships.       The nuances of this consideration are too subjective to solve with an algorithm, but providing the metrics on the diversity             concerning different categories (school, hometown, etc…) is important to track.
