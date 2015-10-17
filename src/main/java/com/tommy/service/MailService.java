package com.tommy.service;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public interface MailService {

	public void sendMail(String from, String to, String subject, String msg);
	public void sendMail(String from, String to, String subject, String msg,List<File> files);
	public void sendMail(String from, HashSet<String> toList, String subject,
			String msg);
	public void sendMail(String from, HashSet<String> toList, String subject,
			String msg, List<File> files);
}
