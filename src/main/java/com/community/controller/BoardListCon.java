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
import java.util.Vector;

@WebServlet("/BoardListCon.do")
public class BoardListCon extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageSize = 10;


        String pageNum = request.getParameter("pageNum");


        if (pageNum == null) {
            pageNum = "1";
        }


        int count = 0;


        int number = 0;


        int currentPage = Integer.parseInt(pageNum);


        BoardDAO bdao = new BoardDAO();
        count = bdao.getAllCount();


        int startRow = (currentPage - 1) * pageSize + 1;
        int endRow = currentPage * pageSize;


        Vector<BoardBean> v = bdao.getAllBoard(startRow, endRow);
        number = count - (currentPage - 1) * pageSize;

        String msg = request.getParameter("msg");


        request.setAttribute("v", v);
        request.setAttribute("number", number);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("count", count);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("msg", msg);
        RequestDispatcher dis = request.getRequestDispatcher("BoardList.jsp");
        dis.forward(request, response);
    }
}
