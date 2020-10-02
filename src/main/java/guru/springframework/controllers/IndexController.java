package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String getRecipes(Model model){

        model.addAttribute("recipeName", "Chicken Tikka Masala");
        System.out.println("Index Page");
        return "recipe";

    }
}