package com.example.restaurant.menu;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepo){
        menuRepository = menuRepo;
    }
    @GetMapping("/menu/new")
    public String initMenuForm(Model model){
        Menu menu = new Menu();
        model.addAttribute(menu);
        return "/createMenu";
    }

    @PostMapping("/menu/new")
    public String processMenuForm(Menu menu, BindingResult result, @RequestParam("image") MultipartFile image) throws IOException {
        if (result.hasErrors()) {
            return "/createMenu";
        }
        else {
            menu.setImageFile(image.getBytes());
            menuRepository.save(menu);
            return "/welcome";
        }
    }

    @GetMapping("/test2")
    public String testForm(Model model) throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encode(menuRepository.findMenuById(1).getImageFile());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("image", base64Encoded );

        return "test2";
    }



}
