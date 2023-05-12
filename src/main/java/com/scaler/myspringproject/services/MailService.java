package com.scaler.myspringproject.services;

import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scaler.myspringproject.models.Attachment;
import com.scaler.myspringproject.models.Csv;
import com.scaler.myspringproject.models.Mail;

@Service
public class MailService {

  @Autowired
  private JavaMailSender mailSender;

  public String sendMailHelper(Mail mail, List<Attachment> attachments) {
    try {
      MimeMessage message = mailSender.createMimeMessage();

      // Create a MimeMessageHelper object to enable attaching a file
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      // Set the recipient, subject, and message body
      helper.setTo(mail.getToMail());
      helper.setSubject(mail.getMailSubject());
      helper.setText(mail.getMailBody());

      for (Attachment attachment : attachments) {
        // Process the element
        helper.addAttachment(attachment.getFileName(), attachment.getDataSource());
      }

      // Send the email
      mailSender.send(message);

      return "Mail Sent";
      
    } catch (Exception e) {
      e.printStackTrace();
      return "Error";
    }
  }

  public String sendMailCSVOnly(Mail mail, List<Csv> csvs) {

    List<Attachment> attachments = new ArrayList<Attachment>();

    for (Csv csv : csvs) {
      attachments.add(new Attachment(csv));
    }

    return sendMailHelper(mail, attachments);
  }

  public String sendMailFilesOnly(Mail mail, List<MultipartFile> files) {

    List<Attachment> attachments = new ArrayList<Attachment>();

    for(MultipartFile file: files) {
      attachments.add(new Attachment(file));
    }

    return sendMailHelper(mail, attachments);
  }

  public String sendMail (Mail mail, List<MultipartFile> files, List<Csv> csvs) {

    List<Attachment> attachments = new ArrayList<Attachment>();

    for(MultipartFile file: files) {
      attachments.add(new Attachment(file));
    }

    for(Csv csv: csvs) {
      attachments.add(new Attachment(csv));
    }

    return sendMailHelper(mail, attachments);
  }
}
