package com.example.demo.service.impl;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.*;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final OrderMapper mapper;

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        Long userId = getCurrentUserId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User không tồn tại"));

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Giỏ hàng trống"));

        if (cart.getItems().isEmpty()) {
            throw new NotFoundException("Giỏ hàng không có sản phẩm");
        }

        // Tạo đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setPhoneNumber(request.getPhoneNumber());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();

        double total = 0;

        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());

            total += oi.getPrice() * oi.getQuantity();

            orderItems.add(oi);
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);

        orderRepo.save(order);
        orderItemRepo.saveAll(orderItems);

        // Xóa giỏ hàng sau khi đặt đơn
        cart.getItems().clear();
        cartRepo.save(cart);

        return mapper.toDTO(order);
    }

    @Override
    public List<OrderResponse> getOrdersOfUser() {
        Long userId = getCurrentUserId();
        return orderRepo.findByUserId(userId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public OrderResponse getById(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng"));
        return mapper.toDTO(order);
    }

    // =============================
    //       HÀM HỖ TRỢ
    // =============================
    private Long getCurrentUserId() {
        return 1L; // TODO: sau này thay bằng JWT
    }
}
