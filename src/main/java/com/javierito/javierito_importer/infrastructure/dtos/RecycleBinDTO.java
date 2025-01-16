package com.javierito.javierito_importer.infrastructure.dtos;

import java.time.LocalDateTime;

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
