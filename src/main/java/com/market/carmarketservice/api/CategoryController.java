package com.market.carmarketservice.api;

import com.market.carmarketservice.model.category.Category;
import com.market.carmarketservice.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@PropertySource("classpath:notify.properties")
@RequestMapping(value = "/api/auth")
public class CategoryController {
    private final CategoryService categoryService;
    private final Environment env;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCategory() {
        try {
            return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategory(@PathVariable("id") int id) {
        if (categoryService.getCategory(id) != null)
            return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("NotFound"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        if (categoryService.createCategory(category))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable("id") int id) {
        if (categoryService.updateCategory(category, id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int id) {
        if (categoryService.deleteCategory(id))
            return new ResponseEntity<>(env.getProperty("Success"), HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("Fail"), HttpStatus.BAD_REQUEST);
    }
}
