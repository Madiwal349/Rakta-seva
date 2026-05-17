# Rakta-Seva Connect 🩸
### Android App Development using GenAI – Healthcare Emergency Blood Donation System

---

# 📌 Overview
Rakta-Seva Connect is an Android healthcare application designed to help hospitals and patients quickly find nearby blood donors during emergency situations.

The app uses:
- Real-time notifications
- Location-based filtering
- Firebase services

to connect voluntary blood donors with people in need.

The system focuses on reducing blood availability delays and improving emergency healthcare response.

---

# 🚨 Problem Statement
In medical emergencies, finding the required blood group within a short time is extremely difficult.

Existing blood bank systems do not provide:
- Real-time donor availability
- Nearby donor filtering
- Instant emergency communication

This delay can affect critical treatments.

Rakta-Seva Connect solves this issue by providing:
- Instant donor notifications
- Nearby donor search
- Emergency request management
- Privacy-protected donor communication

---

# 🎯 Objectives
- Reduce emergency blood search time
- Connect hospitals with nearby donors instantly
- Provide secure donor communication
- Improve healthcare emergency management
- Encourage voluntary blood donation

---

# ✨ Features

## 👤 Donor Registration
Users can register with:
- Name
- Blood Group
- Contact Details
- Location
- Last Donation Date

---

## 🏥 Emergency Blood Requests
Hospitals or users can create urgent blood requests.

Request includes:
- Blood Group
- Hospital Name
- Location
- Priority Level

---

## 📍 Location-Based Filtering
- Nearby donors within a selected radius are prioritized
- Faster donor discovery during emergencies

---

## 🔔 Real-Time Notifications
- Firebase Cloud Messaging (FCM) sends instant alerts
- Donors receive emergency notifications immediately

---

## 🔒 Privacy Protection
- Donor contact details remain hidden
- Details become visible only after request acceptance

---

## ⏳ Donation Eligibility Check
- Donors who donated within the last 90 days are automatically marked unavailable

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Android Studio | Android Development |
| Java / Kotlin | Application Logic |
| Firebase Authentication | User Authentication |
| Firebase Realtime Database | Data Storage |
| Firebase Cloud Messaging (FCM) | Push Notifications |
| Google Maps API | Location Services |
| XML | UI Design |
| Git & GitHub | Version Control |
| GenAI | Smart Healthcare Assistance |

---

# 🏗️ System Architecture

## Frontend
- XML Layouts
- Android UI Components

## Backend
- Firebase Realtime Database
- Firebase Authentication

## Services
- Google Maps API
- Firebase Cloud Messaging

---

# 📲 Working Flow

1. Donor registers in the application
2. User/Hospital creates emergency request
3. System filters nearby matching donors
4. Instant notifications are sent
5. Donor accepts the request
6. Contact information becomes visible

---

# 📊 Advantages
- Faster emergency response
- Real-time donor availability
- Secure and reliable communication
- Easy-to-use interface
- Supports healthcare infrastructure

---

# 🔮 Future Enhancements
- AI-based donor recommendation system
- Multi-language support
- Hospital and blood bank integration
- Appointment scheduling
- Donor reward system

---

# 📁 Project Structure

```bash
FinalProject/
│── app/
│── gradle/
│── .idea/
│── build.gradle.kts
│── settings.gradle.kts
│── gradlew
│── gradlew.bat
