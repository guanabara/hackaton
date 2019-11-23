# Mindera Room/Device Availability System

## Motivation
> Have you ever received someone on Mindera for a meeting or interview and found yourself going through
> all the rooms to see if there's an empty one for you to use? So did we. We aim to develop an easy system 
> able to detect if a meeting room if free or occupied in all Mindera offices.
> We also propose detecting if our gaming consoles are in use. Because who doesn't hate to go all the way up or down 
> to PS4 just to find it being used by someone else ?

## Goal
The final goal for this project is to able to:
* See all rooms and their current/future availability.
* Book rooms via multiple clients (slack, Mindera People Android/iOS apps, devices installed on the entrance of each room)
* Automatically detect room presence.
* Collect metrics over rooms and devices utilization.

## System Architecture
![logo]

[logo]: ./readme/arch.jpg

## Room presence detection
At this point you should be wondering how on earth we'll be automatically detect room presence. We plan to strategically 
install (body heat or motion) sensors that will inform a sensor hub their detection status. The sensor hub will then
publish presence updates events to a message queue. These events will be consumed by a software component (identified by
data writer in the previous image) that will stored them in a monitoring system (such as Statful or Prometheus). This 
same software system will be responsible to hold State information about each of the configured rooms and made them 
available via a REST API.   

## What is done and next steps
At the moment there's an API (with in-memory only) providing details about reservations and able to make room 
reservations. Room status is available via Mindera People. There's also a slack handle to occupy and free rooms.

Next steps include service hosting, implement a metric system, create a new Slack handle that will answer the 
following question: "Which rooms are available?".   
 
  
