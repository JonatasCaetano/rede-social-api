package com.jonatas.socialnetworkapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonatas.socialnetworkapi.services.ReportService;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
}
