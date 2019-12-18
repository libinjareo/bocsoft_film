package com.binsoft.film.controller.film;

import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.film.vo.request.DescribeFilmListReqVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController {

    /**
     * 获取首页信息
     *
     * @return
     */
    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    public BaseResponseVO describeIndex() {
        return null;
    }

    /**
     * 查询条件列表
     *
     * @param cartId
     * @param sourceId
     * @param yearId
     * @return
     */
    @RequestMapping(value = "/getConditionList", method = RequestMethod.GET)
    public BaseResponseVO getConditionList(
            @RequestParam(name = "catId", required = false, defaultValue = "99") String cartId,
            @RequestParam(name = "sourceId", required = false, defaultValue = "99") String sourceId,
            @RequestParam(name = "yearId", required = false, defaultValue = "99") String yearId

    ) {
        return null;
    }

    /**
     * 获取电影列表
     *
     * @param requestVO
     * @return
     */
    @RequestMapping(value = "/getFilms", method = RequestMethod.GET)
    public BaseResponseVO getFilms(DescribeFilmListReqVO requestVO) {
        return null;
    }

    @RequestMapping(value = "/films/{searchStr}", method = RequestMethod.GET)
    public BaseResponseVO describeFilmDetails(String searchStr, String searchType) {
        return null;
    }

}
