# Mindera Room/Device Availability System

## Motivation
> Be able to easily check if a meeting room if free or occupied in Mindera offices.
> Also, who doesn't hate to hit PS4 and find it occupied by other weaker players ? ;)
>
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
