package com.dgs.test.email


// Java Program to Illustrate Creation Of
// Service implementation class

// Importing required classes
import jakarta.mail.MessagingException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import java.io.File

//import javax.mail.MessagingException


// Annotation
@Service // Class
// Implementing EmailService interface

class EmailServiceImpl(
    @Autowired
    private val javaMailSender: JavaMailSender
) : EmailService {

    @Value("\${spring.mail.username}")
    private val sender: String = ""

    // Method 1
    // To send a simple email
    override fun sendSimpleMail(details: EmailDetails): String? {

        // Try block to check for exceptions
        return try {

            // Creating a simple mail message
            val mailMessage = SimpleMailMessage()

            // Setting up necessary details
//            mailMessage.from = "abc@gmail.com"
            mailMessage.setTo(details.recipient)
            mailMessage.text = details.msgBody
            mailMessage.subject = details.subject

            // Sending the mail
            javaMailSender.send(mailMessage)
            "Mail Sent Successfully..."
        } // Catch block to handle the exceptions
        catch (e: Exception) {
            "Error while Sending Mail"
        }
    }

    // Method 2
    // To send an email with attachment
    override fun sendMailWithAttachment(details: EmailDetails): String? {
        // Creating a mime message
        val mimeMessage = javaMailSender.createMimeMessage()
        val mimeMessageHelper: MimeMessageHelper
        return try {

            // Setting multipart as true for attachments to
            // be sent
            mimeMessageHelper = MimeMessageHelper(mimeMessage, true)
            mimeMessageHelper.setFrom(sender)
            mimeMessageHelper.setTo(details.recipient)
            mimeMessageHelper.setText(details.msgBody)
            mimeMessageHelper.setSubject(details.subject)

            // Adding the attachment
            val file: FileSystemResource = FileSystemResource(
                File(details.attachment)
            )
            mimeMessageHelper.addAttachment(
                file.filename, file
            )

            // Sending the mail
            javaMailSender.send(mimeMessage)
            "Mail sent Successfully"
        } // Catch block to handle MessagingException
        catch (e: MessagingException) {

            // Display message when exception occurred
            "Error while sending mail!!!"
        }
    }

    override fun sendMailHtmlFormat(details: EmailDetails): String? {
        val mimeMessage = javaMailSender.createMimeMessage()
        val mimeMessageHelper: MimeMessageHelper
        return try {
            val file: File =
                File("C:\\Users\\Admin\\Downloads\\HTMLTESTEMAIL.html")

            mimeMessageHelper = MimeMessageHelper(mimeMessage, true)
            mimeMessageHelper.setFrom(sender)
            mimeMessageHelper.setTo(details.recipient)
            mimeMessageHelper.setSubject(details.subject)
            val htmlTest= file.readText()
            mimeMessageHelper.setText(htmlTest,true)

            javaMailSender.send(mimeMessage)
            "Successfully"
        } catch (e: MessagingException) {
            "Error"
        }

    }


}
