package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ItemAudit {

    private Long id;
    private Long itemID;
    private String itemName;
    private String actionType;
    private LocalDateTime actionDate;
    private Short branchOfficeId;
    private String branchName;
    private Integer oldQuantity;
    private Integer newQuantity;
    private Long userId;
    private String userName;

}
