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

@WebServlet("/BoardWriteProcCon.do")
public class BoardWriteProcCon extends HttpServlet {


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

        BoardDAO bdao = new BoardDAO();

        bdao.insertBoard(bean);

        RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
        dis.forward(request, response);
    }
}
