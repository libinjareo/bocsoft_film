package com.binsoft.film.service.order;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.binsoft.film.controller.order.vo.response.OrderDetailResVO;
import com.binsoft.film.service.common.exception.CommonServiceException;

import java.io.IOException;

public interface OrderServiceAPI {

    /**
     * 检查座位是否符合现状
     *
     * @param fieldId
     * @param seats
     * @throws CommonServiceException
     * @throws IOException
     */
    void checkSeats(String fieldId, String seats) throws CommonServiceException, IOException;

    /**
     * 检查座位是否为已售座位
     *
     * @param fieldId
     * @param seats
     * @throws CommonServiceException
     */
    void checkSoldSeats(String fieldId, String seats) throws CommonServiceException;

    /**
     * 保存订单信息
     *
     * @param seatIds
     * @param seatName
     * @param fieldId
     * @param userId
     * @return
     * @throws CommonServiceException
     */
    OrderDetailResVO saveOrder(String seatIds, String seatName, String fieldId, String userId) throws CommonServiceException;


    /**
     * 根据用户编号，获取该用户购买过的电影票订单信息
     *
     * @param userId
     * @return
     * @throws CommonServiceException
     */
    IPage<OrderDetailResVO> describeOrderInfoByUser(int nowPage,int pageSize,String userId) throws CommonServiceException;
}
