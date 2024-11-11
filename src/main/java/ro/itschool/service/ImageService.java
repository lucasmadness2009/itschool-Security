package ro.itschool.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.entity.Image;
import ro.itschool.repository.ImageRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imageRepository;

  public ResponseEntity<String> saveImage(final MultipartFile file) {
    try {
      Image image = new Image();
      image.setName(file.getOriginalFilename());
      image.setData(file.getBytes());

      imageRepository.save(image);
      return new ResponseEntity<>("Image uploaded successfully!", HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<byte[]> getImage(final Long id) {
    Image image = imageRepository.findById(id).orElse(null);
    if (image != null) {
      return ResponseEntity.ok()
              .header("Content-Type", "image/jpeg") // Adjust the content type as needed
              .body(image.getData());
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public Image getImageAsEntity(final Long id) {
    return imageRepository.findById(id).orElse(null);
  }
}
