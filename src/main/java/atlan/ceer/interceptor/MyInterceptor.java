package atlan.ceer.interceptor;

import atlan.ceer.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    /*
    预处理。controller方法前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("MyInterceptor pre");
        //request.getRequestDispatcher("/WEB-INF/jsp/error,jsp").forward(request,response);
        String token=request.getHeader("token");
        TokenUtil tokenUtil=new TokenUtil();
        //如果验证不成功
        if (!tokenUtil.verifyToken(token)){
            response.setStatus(201);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("请先登录！");
            response.getWriter().close();
            return false;
        }
        return true;
    }

    /*
      后处理方法，controller方法执行后，success.jsp执行前
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor post");
    }

    /*success.jsp页面执行完成之后，该方法会执行*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("MyInterceptor after");
    }
}
