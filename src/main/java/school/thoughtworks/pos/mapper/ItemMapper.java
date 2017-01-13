package school.thoughtworks.pos.mapper;

import school.thoughtworks.pos.bean.Item;

import java.util.List;

public interface ItemMapper {

    List<Item> findAll();

    Item findItemById(Integer id);

    int insertItem(Item item);

    int deleteItemById(Integer id);

    int updateItem(Item item);
}