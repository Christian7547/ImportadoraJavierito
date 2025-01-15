package com.javierito.javierito_importer.domain.ports;


import com.javierito.javierito_importer.domain.models.ItemImage;
import java.util.ArrayList;

public interface IItemImageDomainRepository {

    ItemImage createItemImage(ItemImage itemImage);
    ArrayList<ItemImage> getItemImages();
    ItemImage getItemImage(Long itemImageId);
    ItemImage editItemImage(ItemImage itemImage);
    void removeItemImage(ItemImage itemImage);

}
