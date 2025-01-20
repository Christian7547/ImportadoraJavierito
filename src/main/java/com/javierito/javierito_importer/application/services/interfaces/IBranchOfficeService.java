package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

import java.util.ArrayList;

public interface IBranchOfficeService {

    BranchOffice createBranchOffice(BranchOffice branchOffice, ArrayList<String> pathImages);
}
