package com.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 檢查用戶是否已經登入（你需要根據你的登入機制來實現這部分）
        // 如果已經登入，允許訪問，否則重定向到登入頁面
        
        // 這是一個簡單示例，假設你有一個名為 "user" 的Session屬性，表示用戶已經登入
        if (request.getSession().getAttribute("loggedInUser") != null) {
            // 用戶已經登入，允許訪問
            filterChain.doFilter(request, response);
        } else {
            // 用戶未登入，重定向到登入頁面
            response.sendRedirect(request.getContextPath() + "/Loginform.jsp");
        }
    }
}
