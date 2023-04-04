package com.java.ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ecommerce.Model.Category;
import com.java.ecommerce.Repository.CategoryRepo;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepo categoryRepo;

    public void createNewCategory(Category category){
        categoryRepo.save(category);
    }

    public List<Category> fetchAllCategories(){
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, Category updateCategory){
        Optional<Category> category = categoryRepo.findById(categoryId);
        // if(category.isEmpty())throw new // add exception here
        category.get().setCategoryName(updateCategory.getCategoryName());
        category.get().setDescription(updateCategory.getDescription());
        categoryRepo.save(category.get());
    }

    public boolean isCategoryPresent(int id) {
        return categoryRepo.findById(id).isPresent();
    }

    public void deleteCategoryById(int id) {
        categoryRepo.deleteById(id);
    }
}
