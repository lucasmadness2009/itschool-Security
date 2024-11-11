package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.Image;
import ro.itschool.service.ImageService;

import java.util.Base64;

@Controller
@RequestMapping("/welcome")
@RequiredArgsConstructor
public class WelcomeController {

  private final ImageService imageService;

  @GetMapping
  public String displayImage(Model model) {
    Image image = imageService.getImageAsEntity(1L);
    String base64Image = Base64.getEncoder().encodeToString(image.getData());
    model.addAttribute("image", base64Image);
    return "welcome";
  }
}
