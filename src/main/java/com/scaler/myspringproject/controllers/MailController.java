package com.scaler.myspringproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.myspringproject.models.Csv;
import com.scaler.myspringproject.models.CsvList;
import com.scaler.myspringproject.models.Mail;
import com.scaler.myspringproject.services.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// import org.json.simple.JSONObject;

@RestController
public class MailController {
	MailService mailService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
  public MailController(MailService yourService) {
      this.mailService = yourService;
  }

	@GetMapping(value = "/yo")
	public String yo(@RequestParam("yoParam") String yoParam) throws MessagingException {
		System.out.println(yoParam);
		MimeMessage message = mailSender.createMimeMessage();

		// Create a MimeMessageHelper object to enable attaching a file
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		// Set the recipient, subject, and message body
		helper.setTo("adnan.raghib1308@gmail.com");
		helper.setSubject("randomSub");
		helper.setText("hey there how are you");

		mailSender.send(message);
		return "Uploaded";
	}

	@PostMapping("/upload")
	public String handleFileUpload(
			@RequestParam(name="files", required = false) List<MultipartFile> files, 
			@RequestParam(name = "csvs", required = false) String csvString,
			@RequestParam("mail") String mailString
		) throws Exception {

		Mail mail;
		List<Csv> csvs = new ArrayList<Csv>();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			mail = objectMapper.readValue(mailString, Mail.class);
			CsvList csvList = objectMapper.readValue(csvString, CsvList.class);
			csvs = csvList.csvList;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}

		String response;
		if(files != null && csvs == null) {
			List<Csv> emptyCsvs = new ArrayList<Csv>();
			response = mailService.sendMail(mail, files, emptyCsvs);
		}
		else if(files == null && csvs != null) {
			List<MultipartFile> emptyFiles = new ArrayList<MultipartFile>();
			response = mailService.sendMail(mail, emptyFiles, csvs);
		}
		else if(files == null && csvs == null) {
			List<Csv> emptyCsvs = new ArrayList<Csv>();
			List<MultipartFile> emptyFiles = new ArrayList<MultipartFile>();
			response = mailService.sendMail(mail, emptyFiles, emptyCsvs);
		}
		else {
			response = mailService.sendMail(mail, files, csvs);
		}
		
		return response;
	}
}
