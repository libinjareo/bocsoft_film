package com.binsoft.film.controller.common.filter;

import com.alibaba.fastjson.JSON;
import com.binsoft.film.config.properties.JwtProperties;
import com.binsoft.film.controller.auth.util.JwtTokenUtil;
import com.binsoft.film.controller.common.BaseResponseVO;
import com.binsoft.film.controller.common.TraceUtil;
import com.binsoft.film.service.common.exception.CommonServiceException;
import com.binsoft.film.service.user.UserServiceAPI;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * jwt token验证过滤器
 */
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    //private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserServiceAPI userServiceAPI;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if(request.getServletPath().equals("/auth")
                || request.getServletPath().startsWith("/cinema")
                || request.getServletPath().startsWith("/film")
                || request.getServletPath().equals("/film/user/register")
                || request.getServletPath().equals("/film/user/check")
                || request.getServletPath().equals("/swagger-ui.html")
                || request.getServletPath().startsWith("/swagger-resources")
                || request.getServletPath().startsWith("/v2/api-docs")
                || request.getServletPath().startsWith("/webjars/springfox-swagger-ui/")){
            chain.doFilter(request,response);
            return;
        }

        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if(requestHeader != null && requestHeader.startsWith("Bearer")){
            authToken = requestHeader.substring(7);

            log.info("username : {}",jwtTokenUtil.getUserNameFromToken(authToken));

            try {
                String userName = jwtTokenUtil.getUserNameFromToken(authToken);
                String userId = userServiceAPI.getUserIdByName(userName);
                //对于每一个请求初始化线程上下文
                TraceUtil.initThread(userId,userName);

            } catch (CommonServiceException e) {
                e.printStackTrace();
            }

            //验证token
            try{
                boolean tokenExpired = jwtTokenUtil.isTokenExpired(authToken);
                if(tokenExpired){//过期
                    renderJson(response, BaseResponseVO.noLogin());
                    return;
                }
            }catch(JwtException e){
                renderJson(response,BaseResponseVO.noLogin());
                return;
            }


        }else{
            //header没有带Bearer字段
            renderJson(response,BaseResponseVO.noLogin());//用户未登录
            return;
        }
        chain.doFilter(request,response);

    }


    /**
     * 渲染json对象
     * @param response
     * @param jsonObject
     */
    public static void renderJson(HttpServletResponse response,Object jsonObject){

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
