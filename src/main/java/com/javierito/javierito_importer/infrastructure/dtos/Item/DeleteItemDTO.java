package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteItemDTO {

    private Long ItemID;
    private Short Status;
    private LocalDateTime LastUpdate;
    private Long UserID;

}
