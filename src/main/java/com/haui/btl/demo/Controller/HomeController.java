package com.haui.btl.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index(Model model) {
        String sql = "SELECT address, COUNT(*) AS car_count, MIN(images) AS image " +
                "FROM car " +
                "GROUP BY address " +
                "ORDER BY car_count DESC " +
                "LIMIT 6";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> result : results) {
            Object carCountObj = result.get("car_count");
            if (carCountObj instanceof Number) {
                Number carCountNumber = (Number) carCountObj;
                int carCount = carCountNumber.intValue();
                int roundedCount = (carCount / 10) * 10;
                result.put("car_count_rounded", roundedCount + "+");
            } else {
                result.put("car_count_rounded", "Error");
            }
        }
        String sql2 = "SELECT u.name AS `UserName`, f.content AS `FeedbackContent`, f.rate AS `Rating`, f.dateTime AS `Date` " +
                "FROM feedback f " +
                "JOIN booking b ON b.FeedBack_idFeedBack = f.idFeedBack " +
                "JOIN user u ON u.idUser = b.User_idUser " +
                "WHERE f.rate = 5 " +
                "AND f.content IS NOT NULL " +
                "ORDER BY f.dateTime DESC " +
                "LIMIT 4;";
        List<Map<String, Object>> results2 = jdbcTemplate.queryForList(sql2);
        // Thêm dữ liệu vào mô hình để truyền cho view
        model.addAttribute("carData", results);
        model.addAttribute("feedbackData", results2);
        return "index";
    }
}
