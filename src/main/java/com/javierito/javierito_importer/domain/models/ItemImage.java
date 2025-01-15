package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemImage {

    private long id;
    private String pathImage;
    private long itemID;

}
