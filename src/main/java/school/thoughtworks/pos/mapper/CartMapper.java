package school.thoughtworks.pos.mapper;

import school.thoughtworks.pos.bean.Cart;

import java.util.List;

public interface CartMapper {

    List<Cart> findAll();

    int insertCart(Cart cart);

    int deleteCartById(Integer id);

    int updateCart(Cart cart);

    Cart findCartById(Integer id);
}