package com.javierito.javierito_importer.infrastructure.dtos.ItemAudit;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RecycleBinDTO {

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
