package com.community.controller;


import com.community.board.model.BoardBean;
import com.community.board.model.BoardDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/BoardReWriteProcCon.do")
public class BoardReWriteProcCon extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        BoardBean bean = new BoardBean();
        bean.setWrite(request.getParameter("write"));
        bean.setSubject(request.getParameter("subject"));
        bean.setEmail(request.getParameter("email"));
        bean.setPassword(request.getParameter("password"));
        bean.setContent(request.getParameter("content"));

        bean.setRef(Integer.parseInt(request.getParameter("ref")));
        bean.setRe_stop(Integer.parseInt(request.getParameter("re_stop")));
        bean.setRe_level(Integer.parseInt(request.getParameter("re_level")));


        BoardDAO bdao = new BoardDAO();
        bdao.reWriteBoard(bean);

        RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
        dis.forward(request, response);
    }

}
