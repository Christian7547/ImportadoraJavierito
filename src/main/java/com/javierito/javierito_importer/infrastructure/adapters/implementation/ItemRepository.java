package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.*;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ItemMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository implements IItemDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final JsonConverter jsonConverter;
    @Autowired
    private ItemMapper itemMapper;

    private final IItemRepository itemRepository;

    public ItemRepository(JsonConverter jsonConverter, IItemRepository itemRepository){
        this.jsonConverter = jsonConverter;
        this.itemRepository = itemRepository;}


    @Override
    public int insertItem(NewItem newItem){

        StoredProcedureQuery q = entityManager
                .createStoredProcedureQuery("usp_create_item");

        q.registerStoredProcedureParameter("p_item_name", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_alias", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_description", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_model", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_price", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_wholesaleprice", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_bareprice", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_brandid", Integer.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_subcategoryid", Short.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_datemanufacture", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_itemaddressid", Short.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_userid", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_acronym", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_purchaseprice", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_status", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_transmission", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_cylinder_capacity", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_traction", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_series", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_fuel", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("item_paths", String[].class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_item_branchofficeid", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_quantity",   Integer.class,  ParameterMode.IN);
        q.registerStoredProcedureParameter("item_barcodes", String[].class, ParameterMode.IN);
        q.registerStoredProcedureParameter("p_count", Integer.class, ParameterMode.INOUT);


        q.setParameter("p_item_name", newItem.getName());
        q.setParameter("p_item_alias", newItem.getAlias());
        q.setParameter("p_item_description", newItem.getDescription());
        q.setParameter("p_item_model", newItem.getModel());
        q.setParameter("p_item_price", newItem.getPrice());
        q.setParameter("p_item_wholesaleprice", newItem.getWholesalePrice());
        q.setParameter("p_item_bareprice", newItem.getBarePrice());
        q.setParameter("p_item_brandid", newItem.getBrandID());
        q.setParameter("p_item_subcategoryid", newItem.getSubCategoryID());
        q.setParameter("p_item_datemanufacture", newItem.getDateManufacture());
        q.setParameter("p_item_itemaddressid", newItem.getItemAddressID());
        q.setParameter("p_item_userid", newItem.getUserID());
        q.setParameter("p_item_acronym", newItem.getAcronym());
        q.setParameter("p_item_purchaseprice", newItem.getPurchasePrice());
        q.setParameter("p_item_status", newItem.getItemStatus().toString());
        q.setParameter("p_item_transmission", newItem.getTransmission() != null ? newItem.getTransmission() : null);
        q.setParameter("p_item_cylinder_capacity", newItem.getCylinderCapacity());
        q.setParameter("p_item_traction", newItem.getTraction() != null ? newItem.getTraction().toString() : null);
        q.setParameter("p_item_series", newItem.getItemSeries());
        q.setParameter("p_item_fuel", newItem.getFuel());

        q.setParameter("item_paths", newItem.getPathItems());
        q.setParameter("p_item_branchofficeid", newItem.getBranchOfficeID().longValue());
        q.setParameter("p_quantity", newItem.getQuantity());
        q.setParameter("item_barcodes", newItem.getBarcodes());

        q.setParameter("p_count", 0);
        q.execute();
        return (Integer) q.getOutputParameterValue("p_count");
    }


    @Override
    public List<ListItems> getAllItems(int limit,
                                       int offset,
                                       @Nullable String param,
                                       @Nullable String subCategory,
                                       @Nullable String brand,
                                       @Nullable String itemStatus) {

        String sql = "SELECT * FROM ufc_get_items(:p_limit, :p_offset, :param, :p_subcategory, :p_brand, :p_itemstatus)";

        Query query = entityManager.createNativeQuery(sql, ListItems.class);
        query.setParameter("p_limit", limit);
        query.setParameter("p_offset", offset);
        query.setParameter("param", param);
        query.setParameter("p_subcategory", subCategory);
        query.setParameter("p_brand", brand);
        query.setParameter("p_itemstatus", itemStatus);

        List<ListItems> listItems = query.getResultList();

        return listItems.isEmpty() ? new ArrayList<>() : listItems;
    }

    @Override
    public ItemAllInfo itemAllInfo(Long id) {

        String query = "SELECT * FROM ufc_get_item_by_id_allinfo(:p_itemid)";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("p_itemid", id);
        Object[] result = (Object[]) nativeQuery.getSingleResult();

        return ItemAllInfo.builder()
                .itemId((Long) result[0])
                .name((String) result[1])
                .alias((String) result[2])
                .description((String) result[3])
                .model((String) result[4])
                .price((BigDecimal) result[5])
                .wholesalePrice((BigDecimal) result[6])
                .barePrice((BigDecimal) result[7])
                .purchasePrice((BigDecimal) result[8])
                .brandName((String) result[9])
                .subCategoryName((String) result[10])
                .dateManufacture((String) result[11])
                .itemAddressName((String) result[12])
                .acronym((String) result[13])
                .itemStatus((String) result[14])
                .transmission((String) result[15])
                .cylinderCapacity((String) result[16])
                .traction((String) result[17])
                .itemSeries((String) result[18])
                .fuel((String) result[19])
                .itemImages(List.of((String[]) result[20]))
                .totalStock((Long) result[21])
                .branchStocks((String) result[22])
                .registerDate((Timestamp) result[23])
                .build();
    }


    @Override
    public ItemWithImages getItemById(Long itemID) {

        String sql = "SELECT * FROM ufc_get_item_by_id(?)";

        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, itemID)
                .getSingleResult();

        if (result == null) {
            return null;
        }

        ItemWithImages item = new ItemWithImages();
        item.setItemID((Long) result[0]);
        item.setName((String) result[1]);
        item.setAlias((String) result[2]);
        item.setDescription((String) result[3]);
        item.setModel((String) result[4]);
        item.setPrice((BigDecimal) result[5]);
        item.setWholesalePrice((BigDecimal) result[6]);
        item.setBarePrice((BigDecimal) result[7]);
        item.setPurchasePrice((BigDecimal) result[8]);
        item.setBrandID((Integer) result[9]);
        item.setSubCategoryID((Short) result[10]);
        item.setDateManufacture((String) result[11]);
        item.setItemAddressID((Short) result[12]);
        item.setUserID((Long) result[13]);
        item.setAcronym((String) result[14]);
        item.setItemStatus(result[15] != null ? ((String) result[15]).charAt(0) : null);
        item.setTransmission((String) result[16]);
        item.setCylinderCapacity((String) result[17]);
        item.setTraction(result[18] != null ? ((String) result[18]).charAt(0) : null);
        item.setItemSeries((String) result[19]);
        item.setFuel((String) result[20]);
        item.setItemImages((String[]) result[21]);

        return item;
    }

    @Override
    public List<RecycleBin> getRecycleBin(int limit, int offset, String param, String subCategory, String brand, String itemStatus) {

        String sql = "SELECT * FROM public.ufc_get_deleted_items(:p_limit, :p_offset, :param, :p_subcategory, :p_brand, :p_itemstatus)";

        Query query = entityManager.createNativeQuery(sql, RecycleBin.class);
        query.setParameter("p_limit", limit);
        query.setParameter("p_offset", offset);
        query.setParameter("param", param);
        query.setParameter("p_subcategory", subCategory);
        query.setParameter("p_brand", brand);
        query.setParameter("p_itemstatus", itemStatus);

        List<RecycleBin> listItems = query.getResultList();

        return listItems.isEmpty() ? new ArrayList<>() : listItems;
    }

    @Override
    public Long countAllItemsRecycleBin() {
        long totalItems = itemRepository.countAllInRecycleBin();
        return totalItems;
    }


    @Override
    public ItemUpdate updateItemById(ItemUpdate itemDTO) {

        String sql = "SELECT * FROM ufc_update_item_by_id(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, itemDTO.getItemID())
                .setParameter(2, itemDTO.getName())
                .setParameter(3, itemDTO.getAlias())
                .setParameter(4, itemDTO.getDescription())
                .setParameter(5, itemDTO.getModel())
                .setParameter(6, itemDTO.getPrice())
                .setParameter(7, itemDTO.getWholesalePrice())
                .setParameter(8, itemDTO.getBarePrice())
                .setParameter(9, itemDTO.getPurchasePrice())
                .setParameter(10, itemDTO.getBrandID())
                .setParameter(11, itemDTO.getSubCategoryID())
                .setParameter(12, itemDTO.getAcronym())
                .setParameter(13, itemDTO.getDateManufacture())
                .setParameter(14, itemDTO.getItemAddressID())
                .setParameter(15, itemDTO.getUserID())
                .setParameter(16, itemDTO.getItemImages())
                .setParameter(17, itemDTO.getItemStatus() != null ? String.valueOf(itemDTO.getItemStatus()) : null)
                .setParameter(18, itemDTO.getTransmission())
                .setParameter(19, itemDTO.getCylinderCapacity())
                .setParameter(20, itemDTO.getTraction() != null ? String.valueOf(itemDTO.getTraction()) : null)
                .setParameter(21, itemDTO.getItemSeries())
                .setParameter(22, itemDTO.getFuel())
                .getSingleResult();

        if (result == null) return null;

        ItemUpdate updatedItem = new ItemUpdate();
        updatedItem.setItemID((Long) result[0]);
        updatedItem.setName((String) result[1]);
        updatedItem.setAlias((String) result[2]);
        updatedItem.setDescription((String) result[3]);
        updatedItem.setModel((String) result[4]);
        updatedItem.setPrice((BigDecimal) result[5]);
        updatedItem.setWholesalePrice((BigDecimal) result[6]);
        updatedItem.setBarePrice((BigDecimal) result[7]);
        updatedItem.setPurchasePrice((BigDecimal) result[8]);
        updatedItem.setBrandID((Integer) result[9]);
        updatedItem.setSubCategoryID((Short) result[10]);
        updatedItem.setDateManufacture((String) result[11]);
        updatedItem.setItemAddressID((Short) result[12]);
        updatedItem.setUserID((Long) result[13]);
        updatedItem.setAcronym((String) result[14]);

        if (result[15] != null) {
            String statusStr = result[15].toString();
            updatedItem.setItemStatus(statusStr.isEmpty() ? null : statusStr.charAt(0));
        } else {
            updatedItem.setItemStatus(null);
        }

        updatedItem.setTransmission((String) result[16]);
        updatedItem.setCylinderCapacity((String) result[17]);

        if (result[18] != null) {
            String tractionStr = result[18].toString();
            updatedItem.setTraction(tractionStr.isEmpty() ? null : tractionStr.charAt(0));
        } else {
            updatedItem.setTraction(null);
        }

        updatedItem.setItemSeries((String) result[19]);
        updatedItem.setFuel((String) result[20]);
        updatedItem.setItemImages((String[]) result[21]);
        return updatedItem;
    }

    @Override
    public void deleteItem(DeleteItem item) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_delete_item");

        query.registerStoredProcedureParameter("p_item_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);

        query.setParameter("p_item_id", item.getItemId());
        query.setParameter("p_user_id", item.UserId);

        query.execute();
    }

    @Override
    public void restoreItem(DeleteItem item) {

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_restore_item");

        query.registerStoredProcedureParameter("p_item_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);

        query.setParameter("p_item_id", item.getItemId());
        query.setParameter("p_user_id", item.UserId);

        query.execute();
    }

    @Override
    public void deleteItemPermanently(DeleteItem item) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_delete_item_permanently");

        query.registerStoredProcedureParameter("p_item_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);

        query.setParameter("p_item_id", item.getItemId());
        query.setParameter("p_user_id", item.UserId);

        query.execute();
    }

    @Override
    public Item getItem(Long id) {
        return itemMapper.toItem(itemRepository.getById(id)) ;
    }

    @Override
    public String findLastBarcodeByAcronym(String acronym) {
        String sql = """
        SELECT b.barcode
        FROM "Barcode" b
        JOIN "Stock" s ON b."stockID" = s.id
        JOIN "Item" i ON s."itemID" = i.id
        WHERE i.acronym = :acronym
        ORDER BY b.barcode DESC
        LIMIT 1
        """;

        try {
            return (String) entityManager.createNativeQuery(sql)
                    .setParameter("acronym", acronym)
                    .getSingleResult();
        } catch (NoResultException e) {
            return acronym + "-0000000";
        }
     }

    @Override
    public Integer countAllItems() {
        int totalItems = itemRepository.countAll();
        if(totalItems == 0){
            return 0;
        }
        return totalItems;
    }

    @Override
    public ItemAcronym getItemAcronym(Long id) {
        return itemMapper.toItemAcronym(itemMapper.toItem(itemRepository.getById(id)));
    }

    @Override
    public BarcodeItemInfo getItemBarcodeInfo(String barcode) {

        String sql = "SELECT * FROM public.ufc_get_item_by_barcode(:p_barcode)";

        Query query = entityManager.createNativeQuery(sql, BarcodeItemInfo.class);
        query.setParameter("p_barcode", barcode);

        List<BarcodeItemInfo> resultList = query.getResultList();
        return resultList.getFirst();
    }
}
