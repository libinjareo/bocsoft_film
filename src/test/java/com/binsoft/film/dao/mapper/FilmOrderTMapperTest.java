package com.binsoft.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.controller.order.vo.response.OrderDetailResVO;
import com.binsoft.film.dao.entity.FilmOrderT;
import com.binsoft.film.service.order.bo.OrderPriceBO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilmOrderTMapperTest {

    @Autowired
    private FilmOrderTMapper orderTMapper;

    @Test
    void describeOrderDetailsById() {
        String orderId = "415sdf58ew12ds5fe1";
        OrderDetailResVO orderDetailResVO = orderTMapper.describeOrderDetailsById(orderId);
        System.out.println("orderDetailResVO=" + orderDetailResVO);

    }

    @Test
    void describeOrderDetailsByUser() {
        Page<FilmOrderT> page = new Page<>(1, 10);
        IPage<OrderDetailResVO> results = orderTMapper.describeOrderDetailsByUser(page, "1");
        results.getRecords().stream().forEach(System.out::println);

    }

    @Test
    void describeFilmPriceByFieldId() {
        OrderPriceBO orderPriceBO = orderTMapper.describeFilmPriceByFieldId("1");
        System.out.println("orderPriceBO=" + orderPriceBO);

    }

    @Test
    void describeSoldSeats() {
        String soldSeats = orderTMapper.describeSoldSeats("1");
        System.out.println("soldSeats=" + soldSeats);
    }

}