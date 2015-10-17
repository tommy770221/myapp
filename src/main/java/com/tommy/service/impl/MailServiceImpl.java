package com.tommy.service.impl;

import com.tommy.service.MailService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;



/**
 * <p>
 * Title: Send Mail Util
 * </p>
 * <p>
 * Description: Send Mail Util <
 * </p>
 * <p>
 * Copyright: 
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author tommy
 * @version 1.0 create time: 2015/04/29
 * 
 */

public class MailServiceImpl implements MailService {


	@Autowired
	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {

		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {

		this.mailSender = mailSender;
	}

	/**
	 * 寄Email
	 * 
	 * @param from
	 *            寄信人的mail address或名稱
	 * @param to
	 *            收信人的email address
	 * @param subject
	 *            email主旨
	 * @param msg
	 *            mail內容
	 */
	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"utf-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
			// 附加圖片檔案
			// helper.addInline("a", new File("/Volumes/APPLE/document.jpg"));

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}

	/**
	 * 寄Email
	 * 
	 * @param from
	 *            寄信人的mail address或名稱
	 * @param to
	 *            收信人的email address
	 * @param subject
	 *            email主旨
	 * @param msg
	 *            mail內容
	 * @param files
	 *            附件
	 */
	public void sendMail(String from, String to, String subject, String msg,
			List<File> files) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"utf-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg, true);
			if (files.size() > 0) {
				// 附加檔案
				for (File file : files) {
					// MimeUtility為了解決附件顯示中文亂碼
					helper.addAttachment(
							MimeUtility.encodeWord(file.getName()), file);
				}
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		mailSender.send(message);
	}

	/**
	 * 寄Email
	 * 
	 * @param from
	 *            寄信人的mail address或名稱
	 * @param toList
	 *            HashSet<String> 多個收信人的email address
	 * @param subject
	 *            email主旨
	 * @param msg
	 *            mail內容
	 */
	public void sendMail(String from, HashSet<String> toList, String subject,
			String msg) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"utf-8");

			helper.setFrom(from);
			for (String to : toList) {
				helper.addTo(to);
			}
			helper.setSubject(subject);
			helper.setText(msg, true);
			// 附加圖片檔案
			// helper.addInline("a", new File("/Volumes/APPLE/document.jpg"));

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}

	/**
	 * 寄Email
	 * 
	 * @param from
	 *            寄信人的mail address或名稱
	 * @param toList
	 *            HashSet<String> 多個收信人的email address
	 * @param subject
	 *            email主旨
	 * @param msg
	 *            mail內容
	 * @param files
	 *            附件
	 */
	public void sendMail(String from, HashSet<String> toList, String subject,
			String msg, List<File> files) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"utf-8");
			helper.setFrom(from);
			
			for (String to : toList) {
				helper.addTo(to);
			}
			
			helper.setSubject(subject);
			helper.setText(msg, true);
			
			if (files.size() > 0) {
				// 附加檔案
				for (File file : files) {
					// MimeUtility為了解決附件顯示中文亂碼
					helper.addAttachment(
							MimeUtility.encodeWord(file.getName()), file);
				}
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		mailSender.send(message);
	}
}