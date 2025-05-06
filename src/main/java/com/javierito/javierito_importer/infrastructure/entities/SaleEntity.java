package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sale")
@Data
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "employeeID", nullable = false)
    private long employeeId;

    @Column(name = "clientID", nullable = false)
    private long clientId;

    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "percentageDiscount", nullable = false)
    private BigDecimal percentageDiscount;
}
