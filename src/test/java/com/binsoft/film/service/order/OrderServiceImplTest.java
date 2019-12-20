package com.binsoft.film.service.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.controller.order.vo.response.OrderDetailResVO;
import com.binsoft.film.service.common.exception.CommonServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderServiceAPI orderServiceAPI;

    @Test
    void checkSeats() throws IOException, CommonServiceException {
        String fieldId = "1";
        //  String seats = "1,3,0";
        String seats = "1,3,4";
        orderServiceAPI.checkSeats(fieldId, seats);
    }

    @Test
    void checkSoldSeats() {
    }

    @Test
    void saveOrder() throws CommonServiceException {
        String fieldId = "1";
        String seatIds = "10,11,12";
        String seatNames = "1排5座";
        String userId = "1";

        System.out.println(orderServiceAPI.saveOrder(seatIds, seatNames, fieldId, userId));

    }

    @Test
    void describeOrderInfoByUser() throws CommonServiceException {
        String userId = "1";
        int nowPage = 1;
        int pageSize = 10;

        IPage<OrderDetailResVO> orderDetailResVOIPage = orderServiceAPI.describeOrderInfoByUser(nowPage, pageSize, userId);
        orderDetailResVOIPage.getRecords().stream().forEach(
                System.out::println
        );

    }

}