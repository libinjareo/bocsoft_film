package com.binsoft.film.service.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.binsoft.film.config.properties.OrderProperties;
import com.binsoft.film.controller.cinema.vo.CinemaFilmInfoVO;
import com.binsoft.film.controller.cinema.vo.FieldHallInfoVO;
import com.binsoft.film.controller.order.vo.response.OrderDetailResVO;
import com.binsoft.film.dao.entity.FilmOrderT;
import com.binsoft.film.dao.mapper.FilmOrderTMapper;
import com.binsoft.film.service.cinema.CinemaServiceAPI;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.order.bo.OrderPriceBO;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderServiceAPI {

    @Autowired
    private FilmOrderTMapper filmOrderTMapper;

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @Autowired
    private OrderProperties orderProperties;


    @Override
    public void checkSeats(String fieldId, String seats) throws CommonServiceException, IOException {

        //取得影厅信息
        FieldHallInfoVO fieldHallInfoVO = cinemaServiceAPI.describeHallInfoByFieldId(fieldId);
        if (fieldHallInfoVO == null || !StringUtils.hasText(fieldHallInfoVO.getSeatFile())) {
            throw new CommonServiceException(404, "场次编号错误！");
        }

        String seatsPath = orderProperties.getFilePathPre() + fieldHallInfoVO.getSeatFile();

        @Cleanup
        BufferedReader bufferedReader = new BufferedReader(new FileReader(seatsPath));

        StringBuffer stringBuffer = new StringBuffer();
        String temp = new String();

        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
        }

        JSONObject jsonObject = JSON.parseObject(stringBuffer.toString());
        //获取ids节点
        String idsStr = jsonObject.getString("ids");

        /*
          用户购买:3,11,12
          ids:1-24
         */
        List<String> idsList = Arrays.asList(idsStr.split(","));
        String[] seatArr = seats.split(",");

        for (String seadId : seatArr) {
            boolean contains = idsList.contains(seadId);
            if (!contains) {
                throw new CommonServiceException(500, "传入错误信息有误!");
            }

        }


    }

    /**
     * 检查待售的座位是否有已售座位信息
     *
     * @param fieldId
     * @param seats
     * @throws CommonServiceException
     */
    @Override
    public void checkSoldSeats(String fieldId, String seats) throws CommonServiceException {

        //取得已售座位信息
        String soldSeats = filmOrderTMapper.describeSoldSeats(fieldId);

        List<String> soldSeatsList = Arrays.asList(soldSeats.split(","));
        String[] seatArr = seats.split(",");

        for (String seadId : seatArr) {
            boolean contains = soldSeatsList.contains(seadId);
            if (contains) {
                throw new CommonServiceException(500, seadId + " 为已售座位，不能重复销售");
            }
        }


    }

    @Override
    public OrderDetailResVO saveOrder(String seatIds, String seatName, String fieldId, String userId) throws CommonServiceException {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        OrderPriceBO orderPriceBO = filmOrderTMapper.describeFilmPriceByFieldId(fieldId);
        //单个座位的票价
        double filmPrice = orderPriceBO.getFilmPrice();
        //销售的座位数->票数
        int seatNum = seatIds.split(",").length;
        //计算以后的总票价
        double totalPrice = getTotalPrice(filmPrice, seatNum);

        //取得电影信息
        CinemaFilmInfoVO cinemaFilmInfoVO = cinemaServiceAPI.describeFilmInfoByFieldId(fieldId);

        FilmOrderT filmOrderT = new FilmOrderT();
        filmOrderT.setUuid(uuid);
        filmOrderT.setSeatsName(seatName);
        filmOrderT.setSeatsIds(seatIds);
        filmOrderT.setOrderUser(Integer.parseInt(userId));
        filmOrderT.setOrderPrice(totalPrice);
        filmOrderT.setFilmPrice(filmPrice);
        filmOrderT.setFilmId(Integer.parseInt(cinemaFilmInfoVO.getFilmId()));
        filmOrderT.setFieldId(Integer.parseInt(fieldId));
        filmOrderT.setCinemaId(Integer.parseInt(orderPriceBO.getCinemaId()));

        filmOrderTMapper.insert(filmOrderT);

        final OrderDetailResVO orderDetailResVO = filmOrderTMapper.describeOrderDetailsById(uuid);

        return orderDetailResVO;
    }


    /**
     * 计算总票价
     *
     * @param filmPrice
     * @param seatNum
     * @return
     */
    private double getTotalPrice(double filmPrice, int seatNum) {
        BigDecimal b1 = new BigDecimal(filmPrice);
        BigDecimal b2 = new BigDecimal(seatNum);

        BigDecimal total = b1.multiply(b2);

        //小数点取两位，并四舍五入
        BigDecimal result = total.setScale(2, RoundingMode.HALF_UP);

        return result.doubleValue();
    }

    @Override
    public IPage<OrderDetailResVO> describeOrderInfoByUser(int nowPage, int pageSize, String userId) throws CommonServiceException {
        Page<FilmOrderT> page = new Page<>(nowPage, pageSize);
        return filmOrderTMapper.describeOrderDetailsByUser(page, userId);

    }
}
