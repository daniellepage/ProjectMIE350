package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.CharityDao;

import com.mie.model.Charity;

public class AddCharityController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private CharityDao cdao;
	
	public AddCharityController (){
		super();
		cdao = new CharityDao();
	}
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			Charity charity = new Charity();
			
			charity.setCharityName(request.getParameter("charname"));
			charity.setCharityCategory(request.getParameter("chartype"));
			charity.setCity(request.getParameter("city"));
			charity.setAddress(request.getParameter("address"));
			charity.setHours(request.getParameter("hours"));
			charity.setPhoneNum(request.getParameter("phonenum"));
			
			cdao.addCharity(charity);
			response.sendRedirect("addedCharity.jsp"); 
			
		

	}

}
