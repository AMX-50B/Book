package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.utils.PaymentUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/4/3.
 */
@WebServlet("/payOnlineServlet")
public class PayOnlineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("orderid");
        String money = request.getParameter("money");
        String yh = request.getParameter("yh");

        String p0_Cmd = "Buy";
        String p1_MerId = "10001126856";
        String p2_Order = id;
        String p3_Amt = money;
        String p4_Cur = "CNY";
        String p5_Pid = "unknow";
        String p6_Pcat ="unknow";
        String p7_Pdesc ="unknow";
        String p8_Url="http://localhost/book/callBackServlet";
        String p9_SAF="1";
        String pa_MP ="nuknow";
        String pd_FrpId= yh;
        String pr_NeedResponse ="1";
        String keyValue ="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac( p0_Cmd, p1_MerId,
                 p2_Order,  p3_Amt,  p4_Cur, p5_Pid,  p6_Pcat,
                 p7_Pdesc, p8_Url,  p9_SAF, pa_MP, pd_FrpId,
                 pr_NeedResponse, keyValue);
        request.setAttribute("pd_FrpId",pd_FrpId);
        request.setAttribute("p0_Cmd",p0_Cmd);
        request.setAttribute("p1_MerId",p1_MerId);
        request.setAttribute("p2_Order",p2_Order);
        request.setAttribute("p3_Amt",p3_Amt);
        request.setAttribute("p4_Cur",p4_Cur);
        request.setAttribute("p5_Pid",p5_Pid);
        request.setAttribute("p6_Pcat",p6_Pcat);
        request.setAttribute("p7_Pdesc",p7_Pdesc);
        request.setAttribute("p8_Url",p8_Url);
        request.setAttribute("p9_SAF",p9_SAF);
        request.setAttribute("pa_MP",pa_MP);
        request.setAttribute("pr_NeedResponse",pr_NeedResponse);
        request.setAttribute("hmac",hmac);
        request.getRequestDispatcher("/confirm.jsp").forward(request,response);
    }
}
