package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemWithImages;
import com.javierito.javierito_importer.domain.models.ItemModels.ListItems;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemRepository;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;
import com.javierito.javierito_importer.infrastructure.mappers.ItemMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository implements IItemDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemMapper itemMapper;

    private final IItemRepository itemRepository;

    public ItemRepository(IItemRepository itemRepository){this.itemRepository = itemRepository;}


    @Override
    public int insertItem(NewItem insertItemDTO){

        int value = 0;

        StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("usp_create_item");
        // IN
        // Item
        procedureQuery.registerStoredProcedureParameter("p_item_name", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_alias", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_description", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_model", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_price", BigDecimal.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_wholesalePrice", BigDecimal.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_barePrice", BigDecimal.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_brandID", Integer.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_subcategoryID", Integer.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_dateManufacture", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_itemAddressID", Short.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_userID", Long.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_acronym", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_purchasePrice", BigDecimal.class, ParameterMode.IN);

        // Stock
        procedureQuery.registerStoredProcedureParameter("p_item_branchOfficeID", Long.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_quantity", Integer.class, ParameterMode.IN);

        // Item Image
        procedureQuery.registerStoredProcedureParameter("item_paths", String[].class, ParameterMode.IN);

        // Barcode
        procedureQuery.registerStoredProcedureParameter("item_barcodes", String[].class, ParameterMode.IN);

        // OUT
        procedureQuery.registerStoredProcedureParameter("p_count", Integer.class, ParameterMode.OUT);

        procedureQuery.setParameter("p_item_name", insertItemDTO.getName());
        procedureQuery.setParameter("p_item_alias", insertItemDTO.getAlias());
        procedureQuery.setParameter("p_item_description", insertItemDTO.getDescription());
        procedureQuery.setParameter("p_item_model", insertItemDTO.getModel());
        procedureQuery.setParameter("p_item_price", insertItemDTO.getPrice());
        procedureQuery.setParameter("p_item_wholesalePrice", insertItemDTO.getWholesalePrice());
        procedureQuery.setParameter("p_item_barePrice", insertItemDTO.getBarePrice());
        procedureQuery.setParameter("p_item_brandID", insertItemDTO.getBrandID());
        procedureQuery.setParameter("p_item_subcategoryID", insertItemDTO.getSubCategoryID());
        procedureQuery.setParameter("p_item_dateManufacture", insertItemDTO.getDateManufacture());
        procedureQuery.setParameter("p_item_itemAddressID", insertItemDTO.getItemAddressID());
        procedureQuery.setParameter("p_item_userID", insertItemDTO.getUserID());
        procedureQuery.setParameter("p_item_acronym", insertItemDTO.getAcronym());
        procedureQuery.setParameter("p_item_purchasePrice", insertItemDTO.getPurchasePrice());

        procedureQuery.setParameter("p_item_branchOfficeID", insertItemDTO.getBranchOfficeID());
        procedureQuery.setParameter("p_quantity", insertItemDTO.getQuantity());

        procedureQuery.setParameter("item_paths", insertItemDTO.getPathItems());

        procedureQuery.setParameter("item_barcodes", insertItemDTO.getBarcodes());

        procedureQuery.setParameter("p_count", value);

        procedureQuery.execute();

        int result = (int) procedureQuery.getOutputParameterValue("p_count");

        return result;
    }

    @Override
    public List<ListItems> getAllItems(int offset, int limit, String param) {
        String sql = "SELECT * FROM ufc_get_items(?, ?, ?)";

        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, limit)
                .setParameter(2, offset)
                .setParameter(3, param)
                .getResultList();
        List<ListItems> items = new ArrayList<>();

        for (Object[] row : results) {
            ListItems item = new ListItems();
            item.setItemID(((Long) row[0]));
            item.setName((String) row[1]);
            item.setDescription((String) row[2]);
            item.setModel((String) row[3]);
            item.setPrice((BigDecimal) row[4]);
            item.setWholesalePrice((BigDecimal) row[5]);
            item.setBarePrice((BigDecimal) row[6]);
            item.setPurchasePrice((BigDecimal) row[7]);
            item.setBrand((String) row[8]);
            item.setCategory((String) row[9]);
            item.setSubCategory((String) row[10]);
            item.setDateManufacture((String) row[11]);
            item.setItemImage((String) row[12]);
            item.setAddress((String) row[13]);
            item.setTotalStock((Integer) row[14]);
            items.add(item);
        }
        return items;
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
        item.setItemImages((String[]) result[15]);

        return item;
    }

    @Override
    public ItemUpdate updateItemById(ItemUpdate itemDTO) {

        String sql = "SELECT * FROM ufc_update_item_by_id(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                .getSingleResult();

        if (result == null) {
            return null;
        }

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
        updatedItem.setItemImages((String[]) result[15]);

        return updatedItem;
    }

    @Override
    public Item deleteItem(Item item) {
        return itemMapper.toItem(itemRepository.save(itemMapper.toItemEntity(item)));
    }

    @Override
    public Item getItem(Long id) {
        return itemMapper.toItem(itemRepository.getById(id)) ;
    }

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
}
