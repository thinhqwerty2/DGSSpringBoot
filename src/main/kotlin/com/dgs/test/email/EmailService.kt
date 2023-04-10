package com.dgs.test.email

// Java Program to Illustrate Creation Of
// Service Interface

// Importing required classes


// Interface
interface EmailService {
    // Method
    // To send a simple email
    fun sendSimpleMail(details: EmailDetails): String?

    // Method
    // To send an email with attachment
    fun sendMailWithAttachment(details: EmailDetails): String?

    //Send html email

    fun sendMailHtmlFormat(details: EmailDetails):String?
}
