package com.example.restaurant.order;

import com.example.restaurant.content.Content;
import com.example.restaurant.content.ContentRepository;
import com.example.restaurant.menu.MenuRepository;
import com.example.restaurant.sale.Sale;
import com.example.restaurant.sale.SaleRepository;
import com.example.restaurant.sale.TotalSale;
import com.example.restaurant.sale.TotalSaleRepository;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;
    private final ContentRepository contentRepository;
    private final MenuRepository menuRepository;
    private final SaleRepository saleRepository;
    private final TotalSaleRepository totalSaleRepository;


    public OrderController(OrderRepository orderRepo, ContentRepository contentRepo, MenuRepository menuRepo, SaleRepository saleRepo, TotalSaleRepository totalSaleRepo){
        orderRepository = orderRepo;
        contentRepository = contentRepo;
        menuRepository = menuRepo;
        saleRepository = saleRepo;
        totalSaleRepository = totalSaleRepo;
    }

    @GetMapping("/getOrdersSorted")
    public List<Order> getOrders(){
        List<Order> orders = orderRepository.findAll();
        for(Order order : orders){
            // skip already cooked orders
            if(order.getIsCooked()) continue;
            List<Content> contents = contentRepository.findByOrderId(order.getId());
            for(Content content : contents){
                order.addContent(content);
            }
        }
        PropertyComparator.sort(orders, new MutableSortDefinition("orderDateTime", true, true));

        return orders;
    }

    @GetMapping("/getTakeoutOrders")
    public List<Order> getTakeoutOrders(){
        List<Order> orders = orderRepository.findByTableId(0);
        for(Order order : orders){
            // skip already paid orders
            if(order.getIsPaid()) continue;

            List<Content> contents = contentRepository.findByOrderId(order.getId());
            for(Content content : contents){
                order.addContent(content);
            }
        }
        return orders;
    }

    @GetMapping("/getTableOrders/{tableId}")
    public List<Order> getTableOrders(@PathVariable("tableId") int tableId){
        List<Order> orders = orderRepository.findByTableId(tableId);
        for(Order order : orders){
            // skip already paid orders
            if(order.getIsPaid()) continue;

            List<Content> contents = contentRepository.findByOrderId(order.getId());
            for(Content content : contents){
                order.addContent(content);
            }
        }
        return orders;
    }

    @PostMapping("/createOrder/{tableId}")
    void createOrder(@RequestBody OrderDTO orderDTO, @PathVariable("tableId") int tableId) {
        Order order = new Order();
        order.setOrderDateTime(LocalDateTime.now());

        Integer totalPrice = 0;
        for(int i = 0; i < orderDTO.menuNames.size(); i++){
            Content content = new Content();
            content.setOrder(order);

            String menuName = orderDTO.menuNames.get(i);
            content.setMenuName(menuName);
            totalPrice += menuRepository.findPriceByName(menuName);

            content.setMenuCount(orderDTO.menuCounts.get(i));
            contentRepository.save(content);
            order.addContent(content);
        }
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
    }

    @PostMapping("/deleteOrder/{tableId}")
    void deleteOrder(@PathVariable("tableId") int tableId) {
        List<Order> orders = orderRepository.findByTableId(tableId);
        for(Order order : orders){
            // skip already paid orders
            if(order.getIsPaid()) continue;

            orderRepository.deleteById(order.getId());
        }
    }

    @PostMapping("/payment/{tableId}")
    void payment(@PathVariable("tableId") int tableId) {
        List<Order> orders = orderRepository.findByTableId(tableId);
        for(Order order : orders){
            // skip already paid orders
            if(order.getIsPaid()) continue;

            // save order to sale
            Sale sale = new Sale();
            sale.setOrderId(order.getId());
            sale.setOrderDate(order.getOrderDateTime().toLocalDate());
            sale.setPrice(order.getTotalPrice());
            order.setIsPaid(true);
            saleRepository.save(sale);

            // update total sale
            TotalSale totalSale = totalSaleRepository.findTotalSaleById(0);
            int total = totalSale.getTotal() + order.getTotalPrice();
            totalSale.setTotal(total);
            totalSaleRepository.save(totalSale);
        }
    }

    @PostMapping("/cooked/{orderId}")
    void cooking(@PathVariable("orderId") int orderId){
        Order order = orderRepository.findOrderById(orderId);
        order.setIsCooked(true);
        orderRepository.save(order);
    }

}
