package com.haui.btl.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTop6AddressesWithMostCars() {
        String sql = "SELECT address, COUNT(*) AS car_count FROM car GROUP BY address ORDER BY car_count DESC LIMIT 6";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        // Làm tròn xuống đến hàng chục gần nhất
        for (Map<String, Object> result : results) {
            int carCount = (Integer) result.get("car_count");
            int roundedCount = (carCount / 10) * 10;
            result.put("car_count_rounded", roundedCount + "+");
        }
        return results;
    }
}
