package com.example.guni

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Palette definitions matching both Dark and Light theme screenshots.
 */
data class GuniThemeColors(
    val isDark: Boolean,
    val background: Color,
    val cardBackground: Color,
    val cardBorder: Color?,
    val inputBackground: Color,
    val inputBorder: Color,
    val inputText: Color,
    val placeholderText: Color,
    val labelText: Color,
    val buttonBackground: Color,
    val buttonText: Color,
    val pinkAccent: Color,
    val footerText: Color,
    val forgotPasswordText: Color
)

fun getGuniColors(isDark: Boolean): GuniThemeColors {
    return if (isDark) {
        GuniThemeColors(
            isDark = true,
            background = Color(0xFF0F1019),
            cardBackground = Color(0xFF282A3A),
            cardBorder = Color(0xFF383A4C),
            inputBackground = Color(0xFF202230),
            inputBorder = Color(0xFF5D6277),
            inputText = Color(0xFFFFFFFF),
            placeholderText = Color(0xFF868B9E),
            labelText = Color(0xFFF0F1F5),
            buttonBackground = Color(0xFFA1BAF8),
            buttonText = Color(0xFF0D2552),
            pinkAccent = Color(0xFFE5207C),
            footerText = Color(0xFFB0B4C4),
            forgotPasswordText = Color(0xFFB0B4C4)
        )
    } else {
        GuniThemeColors(
            isDark = false,
            background = Color(0xFFFFFFFF),
            cardBackground = Color(0xFFECEFF6),
            cardBorder = null,
            inputBackground = Color(0xFFFFFFFF),
            inputBorder = Color(0xFFAAB3C4),
            inputText = Color(0xFF1A1C24),
            placeholderText = Color(0xFF8E95A5),
            labelText = Color(0xFF2C2F3D),
            buttonBackground = Color(0xFF0D5FD9),
            buttonText = Color(0xFFFFFFFF),
            pinkAccent = Color(0xFFE5207C),
            footerText = Color(0xFF4E5366),
            forgotPasswordText = Color(0xFF52576B)
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuniApp()
        }
    }
}

@Composable
fun GuniApp() {
    val systemInDark = isSystemInDarkTheme()
    var isDarkMode by rememberSaveable { mutableStateOf(systemInDark) }
    var showRegister by rememberSaveable { mutableStateOf(false) }

    val colors = getGuniColors(isDarkMode)

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colors.background
        ) {
            if (showRegister) {
                RegisterScreen(
                    colors = colors,
                    onLoginClick = { showRegister = false },
                    onToggleTheme = { isDarkMode = !isDarkMode }
                )
            } else {
                LoginScreen(
                    colors = colors,
                    onSignUpClick = { showRegister = true },
                    onToggleTheme = { isDarkMode = !isDarkMode }
                )
            }
        }
    }
}

/**
 * Top App Logo ("GUNI") with Theme Mode Toggle Button.
 */
@Composable
private fun TopHeader(
    colors: GuniThemeColors,
    onToggleTheme: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 22.dp)
    ) {
        // Centered GUNI logo
        Text(
            text = "GUNI",
            color = colors.pinkAccent,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        // Theme toggle icon button on top right
        IconButton(
            onClick = onToggleTheme,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = if (colors.isDark) Color(0xFFFFD54F) else Color(0xFF3B4054)
            ),
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = if (colors.isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = if (colors.isDark) "Switch to Light Mode" else "Switch to Dark Mode",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Form field row with left-aligned label and right-aligned styled input box.
 */
@Composable
private fun GuniFormRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    colors: GuniThemeColors,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = colors.labelText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 17.sp,
            modifier = Modifier.width(96.dp)
        )

        Spacer(Modifier.width(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = colors.inputText,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(colors.inputText),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onAny = { onImeAction() }
            ),
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .border(
                    width = 1.dp,
                    color = colors.inputBorder,
                    shape = RoundedCornerShape(7.dp)
                )
                .background(
                    color = colors.inputBackground,
                    shape = RoundedCornerShape(7.dp)
                )
                .padding(horizontal = 12.dp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = colors.placeholderText,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword) {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = colors.placeholderText,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

/**
 * Login Screen composable matching Reference Screenshots 1 (Dark) & 4 (Light).
 */
@Composable
fun LoginScreen(
    colors: GuniThemeColors,
    onSignUpClick: () -> Unit,
    onToggleTheme: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(colors = colors, onToggleTheme = onToggleTheme)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (!colors.isDark) {
                        Modifier.shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp),
                            spotColor = Color(0x33000000)
                        )
                    } else {
                        Modifier
                    }
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
            border = colors.cardBorder?.let { BorderStroke(1.dp, it) },
            elevation = CardDefaults.cardElevation(defaultElevation = if (colors.isDark) 0.dp else 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 26.dp)
            ) {
                GuniFormRow(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter Email",
                    colors = colors,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(18.dp))

                GuniFormRow(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter Password",
                    colors = colors,
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onImeAction = { focusManager.clearFocus() }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "Forgot Password?",
                        color = colors.forgotPasswordText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.clickable {
                            if (email.isNotBlank()) {
                                Toast.makeText(
                                    context,
                                    "Password reset link sent to $email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter your email to reset password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        when {
                            email.isBlank() -> {
                                Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                            }
                            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> {
                                Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                            }
                            password.isBlank() -> {
                                Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
                            }
                            password.length < 6 -> {
                                Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "Login Successful! Welcome back",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.buttonBackground,
                        contentColor = colors.buttonText
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(56.dp))

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                color = colors.footerText,
                fontSize = 14.sp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "SIGN UP",
                color = colors.pinkAccent,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(onClick = onSignUpClick)
            )
        }
    }
}

/**
 * Registration Screen composable matching Reference Screenshots 2 (Dark) & 3 (Light).
 */
@Composable
fun RegisterScreen(
    colors: GuniThemeColors,
    onLoginClick: () -> Unit,
    onToggleTheme: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeader(colors = colors, onToggleTheme = onToggleTheme)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (!colors.isDark) {
                        Modifier.shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp),
                            spotColor = Color(0x33000000)
                        )
                    } else {
                        Modifier
                    }
                ),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
            border = colors.cardBorder?.let { BorderStroke(1.dp, it) },
            elevation = CardDefaults.cardElevation(defaultElevation = if (colors.isDark) 0.dp else 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 26.dp)
            ) {
                GuniFormRow(
                    label = "Name",
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Enter Name",
                    colors = colors,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(14.dp))

                GuniFormRow(
                    label = "Phone Number",
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "Enter Phone Number",
                    colors = colors,
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(14.dp))

                GuniFormRow(
                    label = "City",
                    value = city,
                    onValueChange = { city = it },
                    placeholder = "Enter City",
                    colors = colors,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(14.dp))

                GuniFormRow(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter Email",
                    colors = colors,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(14.dp))

                GuniFormRow(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter Password",
                    colors = colors,
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )

                Spacer(Modifier.height(14.dp))

                GuniFormRow(
                    label = "Confirm\nPassword",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = "Enter Confirm Password",
                    colors = colors,
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onImeAction = { focusManager.clearFocus() }
                )

                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        when {
                            name.isBlank() || phone.isBlank() || city.isBlank() ||
                                email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                                Toast.makeText(
                                    context,
                                    "Please fill in all fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> {
                                Toast.makeText(
                                    context,
                                    "Please enter a valid email address",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            phone.trim().length < 10 -> {
                                Toast.makeText(
                                    context,
                                    "Please enter a valid 10-digit phone number",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            password.length < 6 -> {
                                Toast.makeText(
                                    context,
                                    "Password must be at least 6 characters",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            password != confirmPassword -> {
                                Toast.makeText(
                                    context,
                                    "Passwords do not match!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    context,
                                    "Registration Successful! Please login",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onLoginClick()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.buttonBackground,
                        contentColor = colors.buttonText
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Register",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(Modifier.height(42.dp))

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Do you have an account?",
                color = colors.footerText,
                fontSize = 14.sp
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "LOGIN",
                color = colors.pinkAccent,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(onClick = onLoginClick)
            )
        }
    }
}

// ==========================================
// Jetpack Compose Previews (Dark & Light)
// Matching all 4 reference screenshots
// ==========================================

@Preview(name = "1. Dark Login Screen", showBackground = true, widthDp = 392, heightDp = 800)
@Composable
fun LoginScreenDarkPreview() {
    Surface(color = getGuniColors(true).background) {
        LoginScreen(
            colors = getGuniColors(true),
            onSignUpClick = {},
            onToggleTheme = {}
        )
    }
}

@Preview(name = "2. Dark Register Screen", showBackground = true, widthDp = 392, heightDp = 800)
@Composable
fun RegisterScreenDarkPreview() {
    Surface(color = getGuniColors(true).background) {
        RegisterScreen(
            colors = getGuniColors(true),
            onLoginClick = {},
            onToggleTheme = {}
        )
    }
}

@Preview(name = "3. Light Register Screen", showBackground = true, widthDp = 392, heightDp = 800)
@Composable
fun RegisterScreenLightPreview() {
    Surface(color = getGuniColors(false).background) {
        RegisterScreen(
            colors = getGuniColors(false),
            onLoginClick = {},
            onToggleTheme = {}
        )
    }
}

@Preview(name = "4. Light Login Screen", showBackground = true, widthDp = 392, heightDp = 800)
@Composable
fun LoginScreenLightPreview() {
    Surface(color = getGuniColors(false).background) {
        LoginScreen(
            colors = getGuniColors(false),
            onSignUpClick = {},
            onToggleTheme = {}
        )
    }
}
