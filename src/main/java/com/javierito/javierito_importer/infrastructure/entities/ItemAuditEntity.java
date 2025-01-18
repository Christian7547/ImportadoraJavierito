package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "ItemAudit")
@Data
public class ItemAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "itemID", nullable = false)
    private Long itemID;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "actionType", nullable = false)
    private String actionType;

    @Column(name = "actionDate", nullable = false)
    private LocalDateTime actionDate;

    @Column(name = "BranchOfficeId", nullable = false)
    private Short branchOfficeId;

    @Column(name = "branchName", nullable = false)
    private String branchName;

    @Column(name = "oldQuantity")
    private Integer oldQuantity;

    @Column(name = "newQuantity")
    private Integer newQuantity;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

}
