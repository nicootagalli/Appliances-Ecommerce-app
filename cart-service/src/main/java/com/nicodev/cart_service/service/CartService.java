package com.nicodev.cart_service.service;

import com.nicodev.cart_service.dto.CartDTO;
import com.nicodev.cart_service.dto.CartToUserDTO;
import com.nicodev.cart_service.dto.ProductDTO;
import com.nicodev.cart_service.dto.UserDTO;
import com.nicodev.cart_service.exception.BadRequestException;
import com.nicodev.cart_service.exception.NotFoundException;
import com.nicodev.cart_service.mapper.CartMapper;
import com.nicodev.cart_service.model.Cart;
import com.nicodev.cart_service.model.Item;
import com.nicodev.cart_service.repository.ICartRepository;
import com.nicodev.cart_service.repository.IProductAPI;
import com.nicodev.cart_service.repository.IUserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductAPI productAPI;
    @Autowired
    private IUserAPI userAPI;

    // POST
    @Override
    public void saveCart(Cart cart) {

        // Verify if user exist using their API
        if (!userAPI.userExist(cart.getUser_id())){
            throw new NotFoundException("User with ID: " + cart.getUser_id() + " not found");
        }
        if (cart.getUser_id() == null || cart.getUser_id() <= 0){
            throw new BadRequestException("User ID required");
        }

        List<Item> myItems = cart.getItems();

        // Set total using the method totalHandler
        Double total = totalHandler(myItems);
        cart.setTotal(total);

        cartRepository.save(cart);
    }

    // FIND ALL
    @Override
    public List<Cart> findCarts() {
        if (cartRepository.findAll().isEmpty()){
            throw new NotFoundException("The list of Carts is empty");
        }

        return cartRepository.findAll();
    }

    // FIND BY ID
    @Override
    public Cart findCart(Long cart_id) {
        return cartRepository.findById(cart_id)
                .orElseThrow(() -> new NotFoundException("Cart with ID: " + cart_id + " not found"));
    }

    // FIND CART DTO
    @Override
    public CartDTO findCartDTO(Long cart_id) {

        Cart cart = this.findCart(cart_id); // find the Entity Cart
        CartDTO dto = CartMapper.toDTO(cart);  // Convert it to DTO

        return dto;
    }

    // FIND CART TO USER
    @Override
    public CartToUserDTO findCartToUser(Long cart_id) {

        Cart cart = this.findCart(cart_id);
        CartToUserDTO dtoToUser = CartMapper.toUserDTO(cart);

        return dtoToUser;
    }

    // EDIT CART
    @Override
    public CartDTO editCart(Long cart_id, CartToUserDTO newCart) {

        // FIND THE CART TO EDIT
        Cart cartToEdit = this.findCart(cart_id);

        // SET THE NEW VALUES
        cartToEdit.setItems(newCart.getItems());
        Double total = totalHandler(newCart.getItems());
        cartToEdit.setTotal(total);

        // SAVE THE CART WITH THE NEW VALUES
        this.saveCart(cartToEdit);

        // Convert to response a CartDTO
        CartDTO cartDTO = CartMapper.toDTO(cartToEdit);

        return cartDTO;
    }

    // DELETE
    @Override
    public void deleteCart(Long cart_id) {
        if (!cartRepository.existsById(cart_id)){
            throw new NotFoundException("Cart with ID: " + cart_id + " not found");
        }

        cartRepository.deleteById(cart_id);

    }

    // CART EXIST
    @Override
    public Boolean cartExist(Long cart_id) {
        return cartRepository.existsById(cart_id);
    }

    public Double totalHandler(List<Item> myItems){

        ProductDTO proDTO = new ProductDTO();
        Double total = 0.0;

        for (Item i : myItems) {
            // verify if the product exist usin its API
            if (!productAPI.productExist(i.getProduct_id())){
                throw new NotFoundException("Product with ID: " + i.getProduct_id() + " not found");
            }

            // find the productDTO using its API and take its unit price
            proDTO = productAPI.findProductDTO(i.getProduct_id());

            // Verify the quantity is not 0
            if (i.getQuantity() <= 0) {
                throw new BadRequestException("Product queantity can not be 0");
            }

            // set the unit price in my Item bd
            i.setUnit_price(proDTO.getUnit_price());

            // Increment the Total value
            total = total + (proDTO.getUnit_price() * i.getQuantity());
        }

        return total;
    }

}
