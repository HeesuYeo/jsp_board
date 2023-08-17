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

@WebServlet("/BoardInfoControl.do")
public class BoardInfoControl extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int num = Integer.parseInt(request.getParameter("num").trim());


        BoardDAO bdao = new BoardDAO();


        BoardBean bean = bdao.getOneBoard(num);

        request.setAttribute("bean", bean);

        RequestDispatcher dis = request.getRequestDispatcher("BoardInfo.jsp");
        dis.forward(request, response);
    }

}
