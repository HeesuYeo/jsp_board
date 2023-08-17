package com.community.controller;

import com.community.board.model.BoardDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/BoardUpdateProcCon.do")
public class BoardUpdateProcCon extends HttpServlet {
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
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");


        if (password.equals(pass)) {
            BoardDAO bdao = new BoardDAO();
            bdao.updateBoard(num, subject, content);

            RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
            dis.forward(request, response);
        } else {

            RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
            dis.forward(request, response);
        }
    }
}
