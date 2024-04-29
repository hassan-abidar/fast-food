package com.shanks.service;

import com.shanks.model.Cart;
import com.shanks.model.CartItem;
import com.shanks.model.Food;
import com.shanks.model.User;
import com.shanks.repository.CartItemRepository;
import com.shanks.repository.CartRepository;
import com.shanks.repository.FoodRepository;
import com.shanks.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if(optionalCartItem.isEmpty()){
            throw new Exception("Cart item not found !!");
        }
        CartItem cartItem = optionalCartItem.get();
        cartItem.setQuantity(quantity);
        //
        cartItem.setTotalPrice(cartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemfromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if(optionalCartItem.isEmpty()){
            throw new Exception("Cart item not found !!");
        }
        CartItem item = optionalCartItem.get();
        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;
        for(CartItem cartItem : cart.getItems() )
            total+= cartItem.getFood().getPrice()*cartItem.getQuantity();
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found with id : "+id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotal(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartById(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
