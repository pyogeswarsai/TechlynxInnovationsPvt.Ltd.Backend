package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Feedback;
import com.example.demo.entity.GraduateApplication;
import com.example.demo.entity.JobApplication;
import com.example.demo.entity.RequestService;
import com.example.demo.entity.StudentInternApplication;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${hr.email}")
    private String hrEmail;
    
    public EmailService(JavaMailSender mailSender) {
    	this.mailSender = mailSender;
    }

    // For Student/Intern form - Email Service
    public void sendToHRIntern(StudentInternApplication app) {
        String subject = "New Intern Application Received";
        String body = formatHRBodyIntern(app);

        File resumeFile = new File(app.getResumePath());
        if (!resumeFile.exists()) {
            System.out.println("Resume file not found: " + app.getResumePath());
            return;
        }

        try {
            sendEmailWithAttachment(hrEmail, subject, body, resumeFile);
            System.out.println("Intern email sent to HR with resume attached.");
        } catch (MessagingException e) {
            System.out.println("Failed to send intern email: " + e.getMessage());
        }
    }
    public void sendToApplicantIntern(StudentInternApplication app) {
        String subject = "Your Internship Application Was Received";
        String body = "Dear " + app.getFirstName() + " " + app.getLastName() + ",\n\n" +
                      "Thank you for applying for an internship at Techlynx Innovations. We’ve received your application and will be in touch soon.\n\n" +
                      "Regards,\nTechlynx Innovations Pvt. Ltd";

        try {
            sendEmail(app.getEmail(), subject, body);
            System.out.println("Confirmation email sent to intern applicant.");
        } catch (MessagingException e) {
            System.out.println("Failed to send intern confirmation email: " + e.getMessage());
        }
    }
    private String formatHRBodyIntern(StudentInternApplication app) {
        return "New Intern Application Details:\n\n" +
               "Name: " + app.getFirstName() + " " + app.getLastName() + "\n" +
               "Email: " + app.getEmail() + "\n" +
               "Phone: " + app.getPhoneNumber() + "\n" +
               "College: " + app.getCollegeName() + "\n" +
               "Branch: " + app.getBranch() + "\n" +
               "Year of Study: " + app.getYearOfStudy() + "\n" +
               "CGPA: " + app.getCgpa() + "\n" +
               "Motivation: " + app.getMotivation();
//               "Resume Path: " + app.getResumePath();
    }
    //End of Student form - Email Service
    
    // For Freshers/ Graduates form - Email Service
    public void sendToHR(GraduateApplication app) {
        String subject = "New Fresher Application Received";
        String body = formatHRBody(app);

        File resumeFile = new File(app.getResumePath()); // Make sure this path is accessible

        if (!resumeFile.exists()) {
        	System.out.println("Resume file not found: " + app.getResumePath());
        	return;
        }
        try {
            sendEmailWithAttachment(hrEmail, subject, body, resumeFile);
            System.out.println("Email sent successfully to HR with resume attached.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendToApplicant(GraduateApplication app) {
        String subject = "Your Application Was Received";
        String body = "Dear " + app.getFirstName() + " " + app.getLastName() + ",\n\n" +
                      "Thank you for applying to Techlynx Innovations. We’ve received your application and will be in touch soon.\n\n" +
                      "Regards,\nTechlynx Innovations Pvt. Ltd";
        try {
        	sendEmail(app.getEmail(), subject, body);
        	System.out.println("Confirmation email sent to application.");
        }catch (MessagingException e) {
        	System.out.println("Failed to send confirmation email: " + e.getMessage());
        }
    }
    private String formatHRBody(GraduateApplication app) {
        return "New Graduate Application Details:\n\n" +
               "Name: " + app.getFirstName() + " " + app.getLastName() + "\n" +
               "Email: " + app.getEmail() + "\n" +
               "Phone: " + app.getPhone() + "\n" +
               "Gender: " + app.getGender() + "\n" +
               "College: " + app.getCollege() + "\n" +
               "Graduation Year: " + app.getGraduationYear() + "\n" +
               "CGPA: " + app.getCgpa() + "\n" +
               "Skills: " + app.getSkills() + "\n" +
               "Motivation: " + app.getMotivation();
//               "Resume Path: " + app.getResumePath();
    }
    //End of graduates form - Email Service
    
    // For Experienced form - Email Service
    public void sendToHRExperienced(JobApplication app) {
        String subject = "New Experienced Application Received";
        String body = formatHRBodyExperienced(app);

        File resumeFile = new File(app.getResumePath());
        if (!resumeFile.exists()) {
            System.out.println("Resume file not found: " + app.getResumePath());
            return;
        }

        try {
            sendEmailWithAttachment(hrEmail, subject, body, resumeFile);
            System.out.println("Experienced resume sent to HR.");
        } catch (MessagingException e) {
            System.out.println("Failed to send experienced email: " + e.getMessage());
        }
    }
    public void sendToApplicantExperienced(JobApplication app) {
        String subject = "Your Application Was Received";
        String body = "Dear " + app.getFirstName() + " " + app.getLastName() + ",\n\n" +
                      "Thank you for applying for the " + app.getRoleApplied() + " role at Techlynx Innovations. We’ve received your application and will be in touch soon.\n\n" +
                      "Regards,\nTechlynx Innovations Pvt. Ltd";

        try {
            sendEmail(app.getEmail(), subject, body);
            System.out.println("Confirmation email sent to experienced applicant.");
        } catch (MessagingException e) {
            System.out.println("Failed to send confirmation email: " + e.getMessage());
        }
    }
    private String formatHRBodyExperienced(JobApplication app) {
        StringBuilder sb = new StringBuilder();
        sb.append("Experienced Application Details:\n\n");
        sb.append("Name: ").append(app.getFirstName()).append(" ").append(app.getLastName()).append("\n");
        sb.append("Email: ").append(app.getEmail()).append("\n");
        sb.append("Mobile: ").append(app.getMobile()).append("\n");
        sb.append("Gender: ").append(app.getGender()).append("\n");
        sb.append("College: ").append(app.getCollege()).append("\n");
        sb.append("Graduation Year: ").append(app.getGraduationYear()).append("\n");
        sb.append("Role Applied: ").append(app.getRoleApplied()).append("\n");
        sb.append("Preferred Location: ").append(app.getPreferredLocation()).append("\n");
        sb.append("Skills: ").append(app.getSkills()).append("\n\n");

        sb.append("Experience Details:\n");
        for (var exp : app.getExperienceDetails()) {
            sb.append("- ").append(exp.getCompanyName()).append(" | ").append(exp.getRolePosition())
              .append(" | ").append(exp.getYearsOfExperience()).append(" years\n");
        }

        sb.append("\nEducation Details:\n");
        for (var edu : app.getEducationDetails()) {
            sb.append("- ").append(edu.getInstitutionName()).append(" | ").append(edu.getDegreeQualification())
              .append(" | ").append(edu.getGraduationYear()).append("\n");
        }

//        sb.append("\nResume Path: ").append(app.getResumePath());

        return sb.toString();
    }
    //End of experienced form - Email service
    
    
    // Starting of Request Service contact form - emailService
    public void sendToHRServiceRequest(RequestService request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(hrEmail); // Replace with actual HR email
        message.setSubject("New Service Request from " + request.getFirstName() + " " + request.getLastName());

        String body = "📝 New Service Request Received:\n\n" +
                "Name: " + request.getFirstName() + " " + request.getLastName() + "\n" +
                "Email: " + request.getEmail() + "\n" +
                "Mobile: " + request.getMobileNumber() + "\n" +
                "Country: " + request.getCountry() + "\n" +
                "Industry: " + request.getIndustry() + "\n" +
                "Requested Service: " + request.getService() + "\n\n" +
                "📥 Please follow up with the applicant.";

        message.setText(body);
        mailSender.send(message);
    }
    public void sendToApplicantServiceRequest(RequestService request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("Thank you for your service request!");

        String body = "Dear " + request.getFirstName() + ",\n\n" +
                "Thank you for reaching out to Techlynx Innovations.\n" +
                "We’ve received your request for \"" + request.getService() + "\" and our team will get in touch with you shortly.\n\n" +
                "If you have any questions, feel free to reply to this email.\n\n" +
                "Warm regards,\n" +
                "Techlynx Innovations Pvt. Ltd.";

        message.setText(body);
        mailSender.send(message);
    }

    // End of request service contact form - emailservice
    
    
    //Start of feedback contact form - emailService
    public void sendToHRFeedback(Feedback feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(hrEmail); // Replace with actual HR email
        message.setSubject("New Website Feedback from " + feedback.getFirstName() + " " + feedback.getLastName());

        String body = "🗣️ New Feedback Received:\n\n" +
                      "Name: " + feedback.getFirstName() + " " + feedback.getLastName() + "\n" +
                      "Email: " + feedback.getEmail() + "\n" +
                      "Mobile: " + feedback.getMobileNumber() + "\n\n" +
                      "Feedback:\n" + feedback.getFeedbackText();

        message.setText(body);
        mailSender.send(message);
    }

    public void sendToUserFeedback(Feedback feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(feedback.getEmail());
        message.setSubject("Thank you for your feedback!");

        String body = "Dear " + feedback.getFirstName() + ",\n\n" +
                      "Thank you for sharing your thoughts with Techlynx Innovations.\n" +
                      "We appreciate your input and will use it to improve our website experience.\n\n" +
                      "Warm regards,\nTechlynx Innovations Pvt. Ltd.";

        message.setText(body);
        mailSender.send(message);
    }
    //end of feedback contact form - emailservice
    
    //start of book an appointment contact form - emailService
    public void sendToHRAppointment(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(hrEmail); // Replace with actual HR email
        message.setSubject("New Appointment Booking");

        String body = "📅 New Appointment Request:\n\n" +
                      "Name: " + appointment.getFullName() + "\n" +
                      "Email: " + appointment.getEmail() + "\n" +
                      "Date: " + appointment.getAppointmentDate() + "\n" +
                      "Time Slot: " + appointment.getTimeSlot();

        message.setText(body);
        mailSender.send(message);
    }

    public void sendToUserAppointment(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appointment.getEmail());
        message.setSubject("Appointment Confirmation");

        String body = "Dear " + appointment.getFullName() + ",\n\n" +
                      "Your appointment has been successfully booked for " +
                      appointment.getAppointmentDate() + " at " + appointment.getTimeSlot() + ".\n\n" +
                      "We look forward to connecting with you.\n\n" +
                      "Warm regards,\nTechlynx Innovations Pvt. Ltd.";

        message.setText(body);
        mailSender.send(message);
    }
    //end of book an appointment contact form - emailService
    
    
    //Help content for all forms
    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false); // false = no attachment

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, false); // false = plain text

        mailSender.send(message);
    }

    // Main method for attaching resume to sending HR mail
    private void sendEmailWithAttachment(String to, String subject, String body, File attachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        // Attach the resume file
        helper.addAttachment("Resume.pdf", attachment);

        mailSender.send(message);
    }

    
}