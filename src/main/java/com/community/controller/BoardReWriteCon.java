package com.community.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/BoardReWriteCon.do")
public class BoardReWriteCon extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reqPro(request, response);
    }

    protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int ref = Integer.parseInt(request.getParameter("ref"));
        int re_stop = Integer.parseInt(request.getParameter("re_stop"));
        int re_level = Integer.parseInt(request.getParameter("re_level"));

        request.setAttribute("ref", ref);
        request.setAttribute("re_stop", re_stop);
        request.setAttribute("re_level", re_level);


        RequestDispatcher dis = request.getRequestDispatcher("BoardReWriteForm.jsp");
        dis.forward(request, response);
    }

}
