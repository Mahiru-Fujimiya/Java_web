package com.example.demo.service.impl;

import com.example.demo.dto.CartResponse;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.CartService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final CartMapper mapper;

    @Override
    public CartResponse getCart() {
        Cart cart = getUserCart();
        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse addProduct(Long productId, int quantity) {
        Cart cart = getUserCart();

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm"));

        // Kiểm tra sản phẩm đã có trong giỏ chưa
        CartItem item = cart.getItems()
                .stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            // Tạo item mới
            item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);

            cart.getItems().add(item);
        } else {
            // Tăng số lượng nếu item đã tồn tại
            item.setQuantity(item.getQuantity() + quantity);
        }

        cartRepo.save(cart);

        return mapper.toCartResponse(cart);
    }

    @Override
    public CartResponse removeProduct(Long productId) {
        Cart cart = getUserCart();

        // Xóa item ra khỏi list của cart
        cart.getItems().removeIf(i -> i.getProduct().getId().equals(productId));

        cartRepo.save(cart);

        return mapper.toCartResponse(cart);
    }

    // =============================
    //         HÀM HỖ TRỢ
    // =============================

    private Cart getUserCart() {
        Long userId = getCurrentUserId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User không tồn tại"));

        // Nếu user chưa có cart
        Cart cart = cartRepo.findByUserId(userId).orElse(null);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new java.util.ArrayList<>());
            cartRepo.save(cart);
        }

        return cart;
    }

    // ⚠️ Tạm thời mock userId
    private Long getCurrentUserId() {
        return 1L; // TODO: sau này lấy từ JWT
    }
}
