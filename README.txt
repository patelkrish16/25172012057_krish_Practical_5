GUNI - LOGIN & REGISTRATION APP (JETPACK COMPOSE)
=====================================================

AIM:
Create an Android Application that includes Login and Registration screens using Jetpack Compose.

STUDY:
Jetpack Compose Course:
https://developer.android.com/courses/jetpack-compose/course

FEATURES IMPLEMENTED:
1. GUNI Login Screen:
   - "GUNI" bold header logo in magenta/pink (#E5207C)
   - Left-aligned labels with right-aligned single-line outlined input boxes
   - Fields: Email ("Enter Email") and Password ("Enter Password")
   - Forgot Password action with user feedback Toast
   - Pill-shaped "Login" action button
   - "Don't have an account? SIGN UP" navigation to switch to Registration

2. GUNI Registration Screen:
   - "GUNI" bold header logo
   - Form fields:
     * Name ("Enter Name")
     * Phone Number ("Enter Phone Number")
     * City ("Enter City")
     * Email ("Enter Email")
     * Password ("Enter Password")
     * Confirm Password ("Enter Confirm Password")
   - Pill-shaped "Register" action button
   - "Do you have an account? LOGIN" navigation to switch back to Login

3. Dual Theme Support (Dark Mode & Light Mode):
   - Styled to pixel-perfect fidelity with all 4 reference screenshots:
     * Dark Mode: Deep navy canvas (#0F1019), slate card (#282A3A), pastel blue button (#A1BAF8) with dark navy text (#0D2552).
     * Light Mode: White canvas (#FFFFFF), light lavender card (#ECEFF6) with soft drop shadow, royal blue button (#0D5FD9) with white text (#FFFFFF).
   - Dynamic Theme Toggle button (Sun / Moon icon) in top right to switch themes instantly.
   - Automatically respects system dark/light theme on startup.

4. Form Validations & Quality of Life:
   - Email format validation using Android Patterns
   - Phone number validation (at least 10 digits)
   - Password minimum length verification
   - Password vs Confirm Password equality check
   - Password visibility toggle (eye icon)
   - Helpful Toast feedback on success and invalid inputs
   - Responsive layout adapting cleanly to any phone width without overflow or clipping
   - IME padding & smooth vertical scrolling for keyboards

5. Android Studio Compose Previews:
   - 1. Dark Login Screen
   - 2. Dark Register Screen
   - 3. Light Register Screen
   - 4. Light Login Screen

HOW TO OPEN & RUN:
1. Open Android Studio.
2. Select File > Open and choose this folder: "25172012057_practical 5".
3. Allow Gradle Sync to finish.
4. Run the app on an Android Emulator or physical device (Run > Run 'app').
5. Built APK is located at: app/build/outputs/apk/debug/app-debug.apk

PACKAGE:
com.example.guni
