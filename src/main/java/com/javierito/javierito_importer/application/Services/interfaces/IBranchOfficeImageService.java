package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

import java.util.ArrayList;

public interface IBranchOfficeImageService {
    ArrayList<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId);
    BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage);
}
