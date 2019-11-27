package com.binsoft.film.controller.common;

import lombok.Data;

/**
 * 通用响应实体
 *
 * @param <D>
 */

@Data
public final class BaseResponseVO<D> {

    private BaseResponseVO() {
    }

    /**
     * 返回状态：0-成功，1-失败，999-系统异常
     */
    private int status;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据实体
     */
    private D data;

    /**
     * 图片前缀
     */
    private String imgPre;

    /**
     * 分页信息
     */
    private int nowPage;

    private int totalPage;

    public static <D> BaseResponseVO success() {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        return baseResponseVO;
    }

    public static <D> BaseResponseVO success(String msg) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static <D> BaseResponseVO success(D data) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static <D> BaseResponseVO success(int nowPage, int totalPage, String imgPre, D d) {
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(d);
        responseVO.setImgPre(imgPre);
        responseVO.setTotalPage(totalPage);
        responseVO.setNowPage(nowPage);

        return responseVO;
    }

    public static <D> BaseResponseVO success(String imgPre, D d) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(d);
        baseResponseVO.setImgPre(imgPre);

        return baseResponseVO;
    }

    public static <D> BaseResponseVO noLogin() {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(700);
        baseResponseVO.setMsg("用户未登陆");

        return baseResponseVO;
    }

    public static <D> BaseResponseVO failed(String msg) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static <D> BaseResponseVO failed(int status,String msg) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(status);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }


    public static <D> BaseResponseVO failed(String msg, D data) {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        baseResponseVO.setData(data);

        return baseResponseVO;
    }

    public static <D> BaseResponseVO systemError() {
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(999);
        baseResponseVO.setMsg("系统异常，请联系管理员");
        return baseResponseVO;
    }
}
