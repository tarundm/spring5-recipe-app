package guru.springframework.controllers;

import guru.springframework.models.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping(value = {"/", "/index"})
    public String getRecipes(Model model){
        model.addAttribute("recipeName", "Chicken Tikka Masala");
        System.out.println("Index Page");
        System.out.println("Category ID is : " + categoryRepository.findByDescription("American").get().getId());
        System.out.println("UoM ID Is : " + unitOfMeasureRepository.findByDescription("Teaspoon").get().getId());
        return "recipe";
    }
}