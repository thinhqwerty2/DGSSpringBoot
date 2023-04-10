package com.dgs.test.email

// Java Program to Illustrate EmailDetails Class

// Importing required classes
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor // Class

class EmailDetails {
    // Class data members
     val recipient: String = ""
     val msgBody: String = ""
     val subject: String = ""
     val attachment: String = ""
}
