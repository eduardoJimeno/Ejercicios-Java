package com.formacion.bosonit.backendFront.repository;

import com.formacion.bosonit.backendFront.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Integer>{
}
