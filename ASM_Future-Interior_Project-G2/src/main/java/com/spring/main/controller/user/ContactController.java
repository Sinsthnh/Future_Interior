package com.spring.main.controller.user;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.bean.MailInfo;
import com.spring.main.entity.TaiKhoan;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
public class ContactController {

	@Autowired
	JavaMailSender mailer;

	@Autowired
	ServletContext context;

	@PostMapping("/contact-page")
	public String send(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("subject") String subject, @RequestParam("body") String body,
			@RequestParam("attach") MultipartFile attach,@Valid @ModelAttribute("lienhe") MailInfo lienhe, BindingResult result) {
		
		
		
		try {
			if(result.hasErrors()){
				model.addAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
				return "contact";
	        }
			// Tạo mail
			MimeMessage mail = mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			if (!attach.isEmpty()) {
				String path = context.getRealPath("/images/" + attach.getOriginalFilename());
				attach.transferTo(new File(path));
				helper.addAttachment(attach.getOriginalFilename(), new File(path));
			}
			
			
			if(body.equals(",")) {
				model.addAttribute("error", "Vui lòng nhập SDT!");
				System.out.println(body);
				return "contact";
			}else if(!body.equals(body)) {
				model.addAttribute("error", "Vui lòng nhập lời nhắn!");
				return "contact";
				
            }else {
				// Gửi mail
				mailer.send(mail);
			}
			// Gửi mail
			//mailer.send(mail);
		    System.out.println(body);
			model.addAttribute("message", "Gửi email thành công!");
		} catch (Exception ex) {
		
			model.addAttribute("error", "Gửi email thất bại!");
		}
		
		return "contact";
	}

}
