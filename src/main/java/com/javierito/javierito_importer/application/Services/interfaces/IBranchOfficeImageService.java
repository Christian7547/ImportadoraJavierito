package com.javierito.javierito_importer.application.Services.interfaces;


import com.javierito.javierito_importer.domain.models.BranchOfficeImage;

import java.util.ArrayList;
import java.util.List;

public interface IBranchOfficeImageService {
    ArrayList<BranchOfficeImage> getImagesByBranchOfficeId(int branchOfficeId);
    BranchOfficeImage createBranchOfficeImage(BranchOfficeImage branchOfficeImage);
    void removeBranchOfficeImage(int imageId);
    void checkIfImagesStatus(List<BranchOfficeImage> images, int officeId);
    boolean existsByPath(String path);
}
