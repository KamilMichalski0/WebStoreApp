package michalski.kamil.service;

import michalski.kamil.dao.CategoryDao;
import michalski.kamil.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.findAll();
    }
}
