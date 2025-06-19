package com.sumanth.FoodieGo.Controller;

import com.sumanth.FoodieGo.Dto.BatchOrderResponseDto;
import com.sumanth.FoodieGo.Dto.OrderRequestDto;
import com.sumanth.FoodieGo.Dto.OrderResponseDto;
import com.sumanth.FoodieGo.Dto.PlaceBatchOrderDto;
import com.sumanth.FoodieGo.Entity.BatchOrder;
import com.sumanth.FoodieGo.Entity.Order;
import com.sumanth.FoodieGo.Mapper.BatchOrderResponse;
import com.sumanth.FoodieGo.Mapper.OrderMapper;
import com.sumanth.FoodieGo.Service.BatchOrderService;
import com.sumanth.FoodieGo.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final BatchOrderService batchOrderService;

    private final BatchOrderResponse batchOrderResponse;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try{
            Order order = this.orderService.placeOrder(orderRequestDto);
            OrderResponseDto responseDto = this.orderMapper.modelToDto(order);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getByOrderId(@PathVariable int orderId){
        try{
            Order order = this.orderService.getByOrderId(orderId);
            OrderResponseDto responseDto = this.orderMapper.modelToDto(order);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<?> batchOrder(@RequestBody PlaceBatchOrderDto dto){
        try{
            BatchOrderResponseDto batchOrderResponseDto = this.batchOrderResponse.convertToBatchOrderResponseDto(this.batchOrderService.placeOrder(dto));
            return ResponseEntity.ok(batchOrderResponseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));
        }
    }
}
