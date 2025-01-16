package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.ItemAudit;

import java.util.ArrayList;

public interface IItemAuditDomainRepository {

    ArrayList<ItemAudit> recycleBin ();

}
