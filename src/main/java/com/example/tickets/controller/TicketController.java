package com.example.tickets.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.tickets.model.Ticket;
import com.example.tickets.service.TicketService;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/search")
    public ModelAndView showSearchPage() {
        List<Ticket> tickets = ticketService.findAllTickets();
        
        // 按车次分组并排序
        Map<String, List<Ticket>> groupedTickets = tickets.stream()
            .collect(Collectors.groupingBy(ticket -> ticket.getTrain().getTrainNumber()));
        
        groupedTickets.forEach((key, value) -> 
            value.sort((t1, t2) -> {
                int rank1 = getSeatTypeRank(t1.getSeatType());
                int rank2 = getSeatTypeRank(t2.getSeatType());
                return Integer.compare(rank1, rank2);
            })
        );

        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("groupedTickets", groupedTickets);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView searchTickets(@RequestParam String origin, @RequestParam String destination) {
        List<Ticket> tickets = ticketService.findTickets(origin, destination);
        // 按车次分组并排序
        Map<String, List<Ticket>> groupedTickets = tickets.stream()
            .collect(Collectors.groupingBy(ticket -> ticket.getTrain().getTrainNumber()));
        
        groupedTickets.forEach((key, value) -> 
            value.sort((t1, t2) -> {
                int rank1 = getSeatTypeRank(t1.getSeatType());
                int rank2 = getSeatTypeRank(t2.getSeatType());
                return Integer.compare(rank1, rank2);
            })
        );

        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("groupedTickets", groupedTickets);
        return modelAndView;
    }

    private int getSeatTypeRank(Ticket.SeatType seatType) {
        return switch (seatType) {
            case BUSINESS_CLASS -> 1;
            case FIRST_CLASS -> 2;
            case SECOND_CLASS -> 3;
            default -> Integer.MAX_VALUE;
        }; // In case there's an undefined seat type
    }
}
