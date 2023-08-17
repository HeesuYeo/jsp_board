package com.community.controller;


import com.community.board.model.BoardDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/BoardDeleteProCon.do")
public class BoardDeleteProCon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        int num = Integer.parseInt(request.getParameter("num"));
        String password = request.getParameter("password");
        String pass = request.getParameter("pass");


        if (pass.equals(password)) {
            BoardDAO bdao = new BoardDAO();

            bdao.deleteBoard(num);


            RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
            dis.forward(request, response);
        } else {
            RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
            dis.forward(request, response);
        }
    }

}
