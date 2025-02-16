package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.BranchOffice;

import java.util.ArrayList;

public interface IBranchOfficeService {

    ArrayList<BranchOffice> getAll();
    BranchOffice getById(int id);
    BranchOffice createBranchOffice(BranchOffice branchOffice, ArrayList<String> pathImages);
    BranchOffice updateBranchOffice(BranchOffice branchOffice);
    boolean removeBranchOffice(BranchOffice branchOffice);
}
