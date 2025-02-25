package com.example.dream_shop_connectingsql.service.image;


import com.example.dream_shop_connectingsql.dto.ImageDto;
import com.example.dream_shop_connectingsql.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List <ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file,  Long imageId);
}
