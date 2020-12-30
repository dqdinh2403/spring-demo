package com.example.springdemo.service;

import com.example.springdemo.domain.Design;
import com.example.springdemo.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderDesignInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JDBCOrderRepository(JdbcTemplate jdbc){
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("TOrder")
                .usingGeneratedKeyColumns("id");

        this.orderDesignInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("TOrder_Designs");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Design> designs = order.getDesigns();
        for (Design design : designs)
            saveDesignToOrder(design, orderId);

        return order;
    }

    private long saveOrderDetails(Order order){
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        long orderId = orderInserter.executeAndReturnKey(values).longValue();

        return orderId;
    }

    private void saveDesignToOrder(Design design, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("Torder", orderId);
        values.put("design", design.getId());
        orderDesignInserter.execute(values);
    }

}
