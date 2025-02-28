package com.example.shop_connectingsql.service.image;

import com.example.shop_connectingsql.dto.ImageDto;
import com.example.shop_connectingsql.exceptions.ResourceNotFoundException;
import com.example.shop_connectingsql.model.Image;
import com.example.shop_connectingsql.model.Product;
import com.example.shop_connectingsql.repository.ImageRepository;
import com.example.shop_connectingsql.service.product.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final IProductService productService;

    public ImageService(ImageRepository imageRepository, IProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No image found with id" + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete,()-> {
            throw new ResourceNotFoundException("No image found with id" + id);
        });
    }

    @Override
    public List<ImageDto> saveImages(Long productId, List<MultipartFile> files) {
        //from product repo we get product's data from database.
        //We need this to bind in the image class to certain product
        Product product = productService.getProductById(productId);

        // it's a list of images (new container) to show a client
        List<ImageDto> savedImageDto = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                //creates a new object for Image class
                Image image = new Image();

                // setting parameters (name, type, blob) from a new image
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));

                //(attaching a label): You’re saying, This image (label) belongs to the particular T-Shirt (box/product).
                image.setProduct(product);


                // Setting url to image entity
                //variable to store url
                String buildDownloadUrl = "/api/v1/images/image/download/";

                //variable to store url for downloading and set to image object entity, and further to database
                String downloadUrl = buildDownloadUrl+image.getId();

                //saving
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                //saving second time images in the image repository, because at the first time id wasn't available
                imageRepository.save(savedImage);



                //Creates a new object for Image dto
                ImageDto imageDto = new ImageDto();

                //setting parameters (id, name ...) to dto to show client
                imageDto.setId(savedImage.getId());
                imageDto.setFileName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());

                //saving to the list of ImageDto before the loop
                savedImageDto.add(imageDto);

            }   catch(IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId); //it refers to the method above with findById() method inside
        try {
            
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
