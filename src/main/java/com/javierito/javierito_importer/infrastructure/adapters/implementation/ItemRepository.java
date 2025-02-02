package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemRepository;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;
import com.javierito.javierito_importer.infrastructure.mappers.ItemMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
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
    public int insertItem(InsertItemDTO insertItemDTO){

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
        procedureQuery.registerStoredProcedureParameter("p_item_weight", BigDecimal.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_dateManufacture", String.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_itemAddressID", Short.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_item_userID", Long.class, ParameterMode.IN);

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
        procedureQuery.setParameter("p_item_weight", insertItemDTO.getWeight());
        procedureQuery.setParameter("p_item_dateManufacture", insertItemDTO.getDateManufacture());
        procedureQuery.setParameter("p_item_itemAddressID", insertItemDTO.getItemAddressID());
        procedureQuery.setParameter("p_item_userID", insertItemDTO.getUserID());

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
    public List<ItemsDTO> getAllItems(int offset, int limit, String param) {
        String sql = "SELECT * FROM ufc_get_items(?, ?, ?)";

        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, limit)
                .setParameter(2, offset)
                .setParameter(3, param)
                .getResultList();
        List<ItemsDTO> items = new ArrayList<>();

        for (Object[] row : results) {
            ItemsDTO item = new ItemsDTO();
            item.setItemID(((Long) row[0]));
            item.setName((String) row[1]);
            item.setDescription((String) row[2]);
            item.setModel((String) row[3]);
            item.setPrice((BigDecimal) row[4]);
            item.setWholesalePrice((BigDecimal) row[5]);
            item.setBarePrice((BigDecimal) row[6]);
            item.setBrand((String) row[7]);
            item.setCategory((String) row[8]);
            item.setSubCategory((String) row[9]);
            item.setDateManufacture((String) row[10]);
            item.setItemImage((String) row[11]);
            item.setAddress((String) row[12]);
            item.setTotalStock((Integer) row[13]);
            items.add(item);
        }
        return items;
    }

    @Override
    public ItemDTO getItemById(Long itemID) {

        String sql = "SELECT * FROM ufc_get_item_by_id(?)";

        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, itemID)
                .getSingleResult();

        if (result == null) {
            return null;
        }

        ItemDTO item = new ItemDTO();
        item.setItemID((Long) result[0]);
        item.setName((String) result[1]);
        item.setAlias((String) result[2]);
        item.setDescription((String) result[3]);
        item.setModel((String) result[4]);
        item.setPrice((BigDecimal) result[5]);
        item.setWholesalePrice((BigDecimal) result[6]);
        item.setBarePrice((BigDecimal) result[7]);
        item.setBrandID((Integer) result[8]);
        item.setSubCategoryID((Short) result[9]);
        item.setWeight((BigDecimal) result[10]);
        item.setDateManufacture((String) result[11]);
        item.setItemAddressID((Short) result[12]);
        item.setUserID((Long) result[13]);
        item.setItemImages((String[]) result[14]);

        return item;
    }

    @Override
    public UpdateItemDTO updateItemById(UpdateItemDTO itemDTO) {

        String sql = "SELECT * FROM ufc_update_item_by_id(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] result = (Object[]) entityManager.createNativeQuery(sql)
                .setParameter(1, itemDTO.getItemID())
                .setParameter(2, itemDTO.getName())
                .setParameter(3, itemDTO.getAlias())
                .setParameter(4, itemDTO.getDescription())
                .setParameter(5, itemDTO.getModel())
                .setParameter(6, itemDTO.getPrice())
                .setParameter(7, itemDTO.getWholesalePrice())
                .setParameter(8, itemDTO.getBarePrice())
                .setParameter(9, itemDTO.getBrandID())
                .setParameter(10, itemDTO.getSubCategoryID())
                .setParameter(11, itemDTO.getWeight())
                .setParameter(12, itemDTO.getDateManufacture())
                .setParameter(13, itemDTO.getItemAddressID())
                .setParameter(14, itemDTO.getUserID())
                .setParameter(15, itemDTO.getItemImages())
                .getSingleResult();

        if (result == null) {
            return null;
        }

        UpdateItemDTO updatedItem = new UpdateItemDTO();
        updatedItem.setItemID((Long) result[0]);
        updatedItem.setName((String) result[1]);
        updatedItem.setAlias((String) result[2]);
        updatedItem.setDescription((String) result[3]);
        updatedItem.setModel((String) result[4]);
        updatedItem.setPrice((BigDecimal) result[5]);
        updatedItem.setWholesalePrice((BigDecimal) result[6]);
        updatedItem.setBarePrice((BigDecimal) result[7]);
        updatedItem.setBrandID((Integer) result[8]);
        updatedItem.setSubCategoryID((Short) result[9]);
        updatedItem.setWeight((BigDecimal) result[10]);
        updatedItem.setDateManufacture((String) result[11]);
        updatedItem.setItemAddressID((Short) result[12]);
        updatedItem.setUserID((Long) result[13]);
        updatedItem.setItemImages((String[]) result[14]);

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
}
