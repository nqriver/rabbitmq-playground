# About
This application demonstrates simple usecase for RabbitMQ and MongoDB along with SpringBoot.
Producer-ms simulates periodic supplying of some whether measurements, while Consumer-ms receives them throgh Rabbit exchange, stores in MongoDB and exposes simple REST API to play with them. 