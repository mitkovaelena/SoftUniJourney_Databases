package app.services.impl;

import app.models.Book;
import app.models.Category;
import app.repositories.CategoryRepository;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BasicService<Category> implements CategoryService{
    @Autowired
    public CategoryServiceImpl(CrudRepository<Category, Long> repository) {
        super(repository);
    }
}
