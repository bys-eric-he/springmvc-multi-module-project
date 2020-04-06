package com.cnblogs.kmpp.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthenticationFilter extends OncePerRequestFilter {
    /*
    * (non-Javadoc)
    *
    * @see
    * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
    * javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[]{"login.html", "index.html"};

        // 请求的uri
        String uri = request.getRequestURI();

        // uri中包含background时才进行过滤
        if (uri.contains("background")) {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.contains(s)) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (doFilter) {
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = request.getSession().getAttribute("loginedUser");
                if (null == obj) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                    // 设置request和response的字符集，防止乱码
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    String loginPage = "....";
                    String builder = "<script type=\"text/javascript\">" +
                            "alert('网页过期，请重新登录！');" +
                            "window.top.location.href='" +
                            loginPage +
                            "';" +
                            "</script>";
                    out.print(builder);
                } else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含background，则继续
            filterChain.doFilter(request, response);
        }
    }
}
