//package mk.ukim.finki.wp.lab.web.filter;

//import org.springframework.context.annotation.Profile;

//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;

//@WebFilter
//@Profile("servlet")
//public class ColorFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String color = (String)request.getSession().getAttribute("color");
//        if (color == null) color = request.getParameter("color");
//
//        String path = request.getServletPath();
//
//        List<String> allowedRoutesExact = new ArrayList<String>(Arrays.asList("/h2", "/balloons", "/balloon-forms/add-balloon-form", "/balloon-forms/delete-balloon-form", "/balloons/add", "/orders", "/balloons/search"));
//        List<String> allowedRoutesPartial = new ArrayList<String>(Arrays.asList("/balloons/delete/", "/balloon-forms/edit-balloon-form/"));
//        boolean pathIsInAllowedRoutes = allowedRoutesExact.contains(path);
//
//        if (!pathIsInAllowedRoutes) {
//            pathIsInAllowedRoutes = allowedRoutesPartial.stream().filter(path::contains).toArray().length > 0;
//        }
//
//        if (!pathIsInAllowedRoutes && (color == null || color.equals(""))) {
//            response.sendRedirect("/balloons");
//        }
//        else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }

//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
