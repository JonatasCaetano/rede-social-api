package com.jonatas.socialnetworkapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatas.socialnetworkapi.repositories.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;
}
