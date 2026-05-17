Rakta-Seva Connect 🩸
Android App Development using GenAI – Healthcare Emergency Blood Donation System
📌 Overview

Rakta-Seva Connect is an Android healthcare application designed to help hospitals and patients quickly find nearby blood donors during emergency situations. The app uses real-time notifications, location-based filtering, and Firebase services to connect voluntary blood donors with people in need.

The system focuses on reducing the delay in blood availability and improving emergency healthcare response.

🚨 Problem Statement

In medical emergencies, finding the required blood group within a short time is extremely difficult. Existing blood bank systems do not provide real-time donor availability at the local level, which can delay treatment during critical situations.

Rakta-Seva Connect solves this issue by providing:

Instant donor notifications
Nearby donor search
Emergency request management
Privacy-protected donor communication
🎯 Objectives
Reduce emergency blood search time
Connect hospitals with nearby donors instantly
Provide secure donor communication
Improve healthcare emergency management
Encourage voluntary blood donation
✨ Features
👤 Donor Registration
Register with:
Name
Blood Group
Contact Details
Location
Last Donation Date
🏥 Emergency Blood Requests
Hospitals or users can create urgent blood requests
Request includes:
Blood Group
Hospital Name
Location
Priority Level
📍 Location-Based Filtering
Nearby donors within a selected radius are prioritized
🔔 Real-Time Notifications
Firebase Cloud Messaging (FCM) sends instant alerts
🔒 Privacy Protection
Donor contact details remain hidden until request acceptance
⏳ Donation Eligibility Check
Donors who donated within the last 90 days are automatically marked unavailable
🛠️ Technologies Used
Technology	Purpose
Android Studio	Android Development
Java / Kotlin	Application Logic
Firebase Authentication	User Authentication
Firebase Realtime Database	Data Storage
Firebase Cloud Messaging (FCM)	Push Notifications
Google Maps API	Location Services
XML	UI Design
Git & GitHub	Version Control
GenAI	Smart Healthcare Assistance
🏗️ System Architecture
Frontend
XML Layouts
Android UI Components
Backend
Firebase Realtime Database
Firebase Authentication
Services
Google Maps API
Firebase Cloud Messaging
📲 Working Flow
Donor registers in the application
User/Hospital creates emergency request
System filters nearby matching donors
Instant notifications are sent
Donor accepts the request
Contact information becomes visible
📊 Advantages
Faster emergency response
Real-time donor availability
Secure and reliable communication
Easy-to-use interface
Supports healthcare infrastructure
🔮 Future Enhancements
AI-based donor recommendation system
Multi-language support
Hospital and blood bank integration
Appointment scheduling
Donor reward system
📁 Project Structure
FinalProject/
│── app/
│── gradle/
│── .idea/
│── build.gradle.kts
│── settings.gradle.kts
│── gradlew
│── gradlew.bat
🚀 Installation Steps
Clone Repository
git clone https://github.com/Madiwal349/Rakta-seva.git
Open Project
Open Android Studio
Select "Open Existing Project"
Choose the project folder
Run Application
Connect Android device or emulator
Click Run ▶️
