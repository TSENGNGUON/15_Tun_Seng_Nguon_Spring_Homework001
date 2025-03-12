package com.example.springhomework001;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    List<Ticket> tickets = new ArrayList<>();
    int id = 5;

    public TicketController(){
        tickets.add(new Ticket(1,"nara","2025", "PP","BMC", 15,true,Status.BOOKED,"A12"));
        tickets.add(new Ticket(2,"koko","2025", "PP","BMC", 15,true,Status.CANCELED,"C44"));
        tickets.add(new Ticket(3,"bopha","2025", "PP","BMC", 15,true,Status.COMPLETED,"B12"));
        tickets.add(new Ticket(4,"bopha","2025", "PP","BMC", 15,true,Status.COMPLETED,"G14"));
    }

//    Create a Ticket
    @PostMapping
    public List<Ticket> createTicket(@RequestBody TicketRequest ticket){
        tickets.add(new Ticket(id++,ticket.getPassengerName(),ticket.getTravelDate(), ticket.getSourceStation(), ticket.getDestinationStation(), ticket.getPrice(), ticket.isPaymentStatus(),ticket.getTicketStatus(),ticket.getSeatNumber()));
        return tickets;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets(){
        ApiResponse<List<Ticket>> response = new ApiResponse<>(true,"success",LocalDateTime.now(),HttpStatus.OK,tickets);
        return ResponseEntity.ok(response);
    }

    //Retrieve a Ticket by ID (using @PathVariable)
    @GetMapping("/{ticket-id}")
    public ResponseEntity<ApiResponse<Ticket>> getTicketById(@PathVariable("ticket-id") int ticketId){
//        boolean isFound = false;
        for(Ticket ticket : tickets){
            if (ticket.getTicketId() == ticketId){
//                isFound=true;
//                return ticket;
                ApiResponse<Ticket> response = new ApiResponse<>(true, "Ticket retrieved successfully.", LocalDateTime.now(),HttpStatus.OK,ticket);
                return ResponseEntity.ok(response);
            }
        }

//        System.out.println(isFound);
//        if(!isFound){
//            return ResponseEntity.notFound().build();

        return null;
    }


    //Search for a Ticket by Passenger Name (using @RequestParam)
    @GetMapping("/search")
    public Ticket getTicketByPassengerName(@RequestParam("passengerName") String name){
        for (Ticket ticket : tickets){
            if (ticket.getPassengerName().toLowerCase().contains(name.toLowerCase())){
                return ticket;
            }
        }
        return null;
    }

//    Filter Tickets by Ticket Status and Travel Date (using @RequestParam)
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<Ticket>>> getFilter(@RequestParam Status status,@RequestParam String travelDate){
        ArrayList<Ticket> newArrList = new ArrayList<>();
        ApiResponse<List<Ticket>> response = null;
        for (int i = 0; i < tickets.size(); i++){
           if (tickets.get(i).getTicketStatus().equals(status) && tickets.get(i).getTravelDate().equals(travelDate)){
               newArrList.add(tickets.get(i));
              response = new ApiResponse<>(true,"Tickets filter successfully.",HttpStatus.OK,newArrList,LocalDateTime.now());
           }
        }
        return ResponseEntity.ok(response);
    }


//    Update a Ticket by ID
    @PutMapping("/{ticket-id}")
    public ResponseEntity<ApiResponse<?>> updateById(@PathVariable("ticket-id") int id,@RequestBody TicketRequest ticketRequest){
        for (Ticket ticket : tickets){
            if (ticket.getTicketId() == id){
                ApiResponse response = new ApiResponse(true,"Ticket updated successfully.",HttpStatus.OK,ticket,LocalDateTime.now());
               ticket.setPassengerName(ticketRequest.getPassengerName());
               ticket.setTravelDate(ticketRequest.getTravelDate());
               ticket.setSourceStation(ticketRequest.getSourceStation());
               ticket.setDestinationStation(ticketRequest.getDestinationStation());
               ticket.setPrice(ticketRequest.getPrice());
               ticket.setPaymentStatus(ticketRequest.isPaymentStatus());
               ticket.setTicketStatus(ticketRequest.getTicketStatus());
               ticket.setSeatNumber(ticketRequest.getSeatNumber());

               return ResponseEntity.ok(response);
            }
        }
        return null;
    }

//    Delete a Ticket by ID
    @DeleteMapping("/{ticket-id}")
    public ResponseEntity<ApiResponse<?>> deleteById(@PathVariable("ticket-id") int id){
        for (Ticket ticket : tickets){
            if (ticket.getTicketId() == id){
                tickets.remove(ticket);
               ApiResponse<?> response = new ApiResponse<>(true,"Ticket Deleted Successfully.",HttpStatus.OK,LocalDateTime.now());
               return ResponseEntity.ok(response);
            }
        }
        return null;
    }



    //Update payment status for multiple ticket IDs.
    @PutMapping
    public ResponseEntity<ApiResponse<Ticket>> updateStatusById(@RequestBody TicketUpateStatus ticketUpateStatus){
          ArrayList<TicketUpateStatus> updateStatus = new ArrayList<>();
          updateStatus.add(new TicketUpateStatus(ticketUpateStatus.getId(),ticketUpateStatus.getPaymentStatus()));
          for(Ticket ticket : tickets){
              if (ticket.getTicketId() == ticketUpateStatus.getId()){
                  ticket.setPaymentStatus(ticketUpateStatus.getPaymentStatus());
                  ApiResponse<Ticket> response = new ApiResponse<>(true, "Payment status updated successfully",LocalDateTime.now(), HttpStatus.OK,ticket);
                  return ResponseEntity.ok(response);
              }
          }
          return null;
    }





}
