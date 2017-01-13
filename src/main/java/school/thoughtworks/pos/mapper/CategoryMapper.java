package school.thoughtworks.pos.mapper;

import school.thoughtworks.pos.bean.Category;

import java.util.List;

public interface CategoryMapper {

    List<Category> findAll();

    int insertCategory(Category category);

    int deleteCategoryById(Integer id);

    int updateCategory(Category category);

    Category findCategoryById(Integer id);
}