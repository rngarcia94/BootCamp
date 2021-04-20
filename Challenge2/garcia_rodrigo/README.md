API Developed by Garcia, Rodrigo Nahuel for MeLi's Bootcamp second challenge.

The entire API works with just one controller.

The request are key sensitive and some use accents marks.

The code have comments explaining each method.

All the important clases reach 100% method coverage and more than 90% line coverage, except validationUtils(81%) and FileUtils(72%).

Here are some payload for testing.
When coping the payload be carefull with blanks.

001-Get All Hotels: http://localhost:8080/api/v1/hotels/

This method shows the entire database booked and not booked hotels. (I did it this way because i find usefull a method for showing everything).

002-Get Filtered Hotels: http://localhost:8080/api/v1/hotels/?dateFrom=10/02/2021&dateTo=19/03/2021&destination=Buenos Aires

This method only show not booked rooms. This is a better method to find an especfic room based on the need of the client.


003-Book Room: http://localhost:8080/api/v1/booking

Payload:
{
    "userName" : "arjona@gmail.com",
    "booking" : {
        "dateFrom" : "12/02/2021",
        "dateTo" : "15/02/2021",
        "destination" : "Buenos Aires",
        "hotelCode" : "HB-0001",
        "peopleAmount" : 1,
        "roomType" : "Single",
        "people" : [
            {
                "dni" : "123456789",
                "name" : "juan",
                "lastname" : "Gomez",
                "birthDate" : "10/11/1984",
                "mail" : "juan@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "4444-5555-6666-7777",
            "dues" : 10
        }
    }
}

004-Get All Flights: http://localhost:8080/api/v1/flights

005-Get Filtered Flights: http://localhost:8080/api/v1/flights?destination=Buenos Aires&dateFrom=15/02/2021&dateTo=28/02/2021

006-Reserve Flights: http://localhost:8080/api/v1/flight-reservation

Payload:
{
    "userName" : "arjona@gmail.com",
    "flightReservation" : {
        "dateFrom" : "15/02/2021",
        "dateTo" : "15/02/2021",
        "origin" : "Bogotá",
        "destination" : "Buenos Aires",
        "flightNumber": "BOBA-6567",
        "seats" : 1,
        "seatType" : "Business",
        "people" : [
            {
                "dni" : "123456789",
                "name" : "juan",
                "lastname" : "Gomez",
                "birthDate" : "10/11/1984",
                "mail" : "juan@gmail.com"
            }
        ],
        "paymentMethod" : {
            "type" : "CREDIT",
            "number" : "4444-5555-6666-7777",
            "dues" : 10
        }
    }
}
