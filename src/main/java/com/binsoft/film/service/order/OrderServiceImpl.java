package com.binsoft.film.service.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.controller.order.vo.response.OrderDetailResVO;
import com.binsoft.film.dao.mapper.FilmOrderTMapper;
import com.binsoft.film.service.common.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderServiceImpl implements  OrderServiceAPI {

    @Autowired
    private FilmOrderTMapper filmOrderTMapper;




    @Override
    public void checkSeats(String fieldId, String seats) throws CommonServiceException, IOException {

    }

    @Override
    public void checkSoldSeats(String fieldId, String seats) throws CommonServiceException {

    }

    @Override
    public OrderDetailResVO saveOrder(String seatIds, String seatName, String fieldId, String userId) throws CommonServiceException {
        return null;
    }

    @Override
    public IPage<OrderDetailResVO> describeOrderInfoByUser(String userId) throws CommonServiceException {
        return null;
    }
}
