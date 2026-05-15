////package com.example.finalproject
////
////import android.content.Context
////import android.content.SharedPreferences
////import android.os.Bundle
////import android.widget.Toast
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.animation.core.*
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.lazy.LazyColumn
////import androidx.compose.foundation.lazy.grid.GridCells
////import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
////import androidx.compose.foundation.lazy.grid.items
////import androidx.compose.foundation.lazy.items
////import androidx.compose.foundation.shape.RoundedCornerShape
////import androidx.compose.material.icons.Icons
////import androidx.compose.material.icons.filled.*
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.draw.alpha
////import androidx.compose.ui.draw.clip
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.foundation.text.KeyboardOptions
////import androidx.compose.ui.text.input.KeyboardType
////import androidx.compose.ui.text.input.PasswordVisualTransformation
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////import androidx.navigation.NavHostController
////import androidx.navigation.compose.NavHost
////import androidx.navigation.compose.composable
////import androidx.navigation.compose.rememberNavController
////import com.google.gson.Gson
////import kotlinx.coroutines.delay
////import java.text.SimpleDateFormat
////import androidx.compose.material3.ExperimentalMaterial3Api
////import java.util.*
////
////// --------------------------------------------------------------
////// Data Models
////// --------------------------------------------------------------
////data class User(
////    val name: String,
////    val email: String,
////    val password: String,
////    val bloodGroup: String,
////    val phone: String,
////    val location: String,
////    val isAvailable: Boolean = false
////)
////
////data class Donor(
////    val name: String,
////    val bloodGroup: String,
////    val location: String,
////    val isAvailable: Boolean,
////    val phone: String
////)
////
////data class EmergencyRequest(
////    val patientName: String,
////    val bloodGroup: String,
////    val hospitalName: String,
////    val unitsRequired: Int,
////    val urgency: String,
////    val status: String,
////    val timestamp: String
////)
////
////// --------------------------------------------------------------
////// Dummy Data
////// --------------------------------------------------------------
////object DummyData {
////    val donors = listOf(
////        Donor("Rajesh Kumar", "A+", "Mumbai", true, "9876543210"),
////        Donor("Priya Sharma", "B+", "Delhi", true, "9876543211"),
////        Donor("Amit Patel", "O+", "Ahmedabad", false, "9876543212"),
////        Donor("Sneha Reddy", "AB-", "Hyderabad", true, "9876543213"),
////        Donor("Vikram Singh", "A-", "Jaipur", true, "9876543214"),
////        Donor("Neha Gupta", "B-", "Lucknow", false, "9876543215"),
////        Donor("Rahul Verma", "O-", "Pune", true, "9876543216"),
////        Donor("Anjali Nair", "AB+", "Chennai", true, "9876543217")
////    )
////}
////
////// --------------------------------------------------------------
////// In-memory Request History (local)
////// --------------------------------------------------------------
////object RequestHistoryRepository {
////    private val _requests = mutableStateListOf<EmergencyRequest>()
////    val requests: List<EmergencyRequest> = _requests
////
////    fun addRequest(request: EmergencyRequest) {
////        _requests.add(request)
////    }
////}
////
////// --------------------------------------------------------------
////// SharedPreferences Manager (for user data)
////// --------------------------------------------------------------
////class PreferencesManager(context: Context) {
////    private val prefs: SharedPreferences = context.getSharedPreferences("RaktaSevaPrefs", Context.MODE_PRIVATE)
////    private val gson = Gson()
////
////    fun saveUser(user: User) {
////        prefs.edit().putString("user", gson.toJson(user)).apply()
////    }
////
////    fun getUser(): User? {
////        val json = prefs.getString("user", null) ?: return null
////        return gson.fromJson(json, User::class.java)
////    }
////
////    fun setLoggedIn(isLoggedIn: Boolean) {
////        prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
////    }
////
////    fun isLoggedIn(): Boolean = prefs.getBoolean("isLoggedIn", false)
////}
////
////// --------------------------------------------------------------
////// Main Activity & Compose UI
////// --------------------------------------------------------------
////class MainActivity : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContent {
////            RaktaSevaConnectTheme {
////                Surface(
////                    modifier = Modifier.fillMaxSize(),
////                    color = MaterialTheme.colorScheme.background
////                ) {
////                    val navController = rememberNavController()
////                    AppNavGraph(navController = navController, context = this)
////                }
////            }
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// Navigation Graph
////// --------------------------------------------------------------
////@Composable
////fun AppNavGraph(navController: NavHostController, context: Context) {
////    NavHost(navController, startDestination = "splash") {
////        composable("splash") { SplashScreen(navController) }
////        composable("login") { LoginScreen(navController, context) }
////        composable("register") { RegistrationScreen(navController, context) }
////        composable("home") { HomeScreen(navController, context) }
////        composable("findDonors") { FindDonorsScreen() }
////        composable("emergency") { EmergencyRequestScreen(navController) }
////        composable("history") { RequestHistoryScreen() }
////        composable("profile") { MyProfileScreen(context, navController) }
////    }
////}
////
////// --------------------------------------------------------------
////// Splash Screen
////// --------------------------------------------------------------
////@Composable
////fun SplashScreen(navController: NavHostController) {
////    var startAnim by remember { mutableStateOf(false) }
////    val alpha = animateFloatAsState(
////        targetValue = if (startAnim) 1f else 0f,
////        animationSpec = tween(1000)
////    )
////    LaunchedEffect(Unit) {
////        startAnim = true
////        delay(2000)
////        navController.popBackStack()
////        navController.navigate("login")
////    }
////    Box(
////        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
////        contentAlignment = Alignment.Center
////    ) {
////        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha.value)) {
////            Text("🩸", fontSize = 80.sp, color = Color.White)
////            Spacer(modifier = Modifier.height(16.dp))
////            Text("Rakta-Seva Connect", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
////            Text("Blood Donation Network", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// Login Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun LoginScreen(navController: NavHostController, context: Context) {
////    var email by remember { mutableStateOf("") }
////    var password by remember { mutableStateOf("") }
////    val prefs = PreferencesManager(context)
////
////    Column(
////        modifier = Modifier.fillMaxSize().padding(24.dp),
////        horizontalAlignment = Alignment.CenterHorizontally,
////        verticalArrangement = Arrangement.Center
////    ) {
////        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
////        Spacer(modifier = Modifier.height(32.dp))
////
////        OutlinedTextField(
////            value = email, onValueChange = { email = it },
////            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(12.dp), singleLine = true
////        )
////        Spacer(modifier = Modifier.height(16.dp))
////
////        OutlinedTextField(
////            value = password, onValueChange = { password = it },
////            label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(12.dp),
////            visualTransformation = PasswordVisualTransformation(), singleLine = true
////        )
////        Spacer(modifier = Modifier.height(24.dp))
////
////        Button(
////            onClick = {
////                when {
////                    email.isBlank() || password.isBlank() -> Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
////                    else -> {
////                        val user = prefs.getUser()
////                        if (user != null && user.email == email && user.password == password) {
////                            prefs.setLoggedIn(true)
////                            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
////                            navController.navigate("home") { popUpTo("login") { inclusive = true } }
////                        } else {
////                            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
////                        }
////                    }
////                }
////            },
////            modifier = Modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(12.dp)
////        ) { Text("Login") }
////
////        Spacer(modifier = Modifier.height(12.dp))
////        TextButton(onClick = { navController.navigate("register") }) {
////            Text("Create New Account", color = MaterialTheme.colorScheme.primary)
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// Registration Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun RegistrationScreen(navController: NavHostController, context: Context) {
////    var name by remember { mutableStateOf("") }
////    var email by remember { mutableStateOf("") }
////    var password by remember { mutableStateOf("") }
////    var bloodGroup by remember { mutableStateOf("A+") }
////    var phone by remember { mutableStateOf("") }
////    var location by remember { mutableStateOf("") }
////    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
////    val prefs = PreferencesManager(context)
////    var expanded by remember { mutableStateOf(false) }
////
////    Column(
////        modifier = Modifier.fillMaxSize().padding(24.dp),
////        horizontalAlignment = Alignment.CenterHorizontally,
////        verticalArrangement = Arrangement.Center
////    ) {
////        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
////        Spacer(modifier = Modifier.height(24.dp))
////
////        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////        Spacer(modifier = Modifier.height(12.dp))
////        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////        Spacer(modifier = Modifier.height(12.dp))
////        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////        Spacer(modifier = Modifier.height(12.dp))
////
////        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
////            OutlinedTextField(
////                value = bloodGroup, onValueChange = {},
////                readOnly = true, label = { Text("Blood Group") },
////                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
////                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
////            )
////            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
////                bloodGroups.forEach { group ->
////                    DropdownMenuItem(text = { Text(group) }, onClick = { bloodGroup = group; expanded = false })
////                }
////            }
////        }
////
////        Spacer(modifier = Modifier.height(12.dp))
////        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////        Spacer(modifier = Modifier.height(12.dp))
////        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////        Spacer(modifier = Modifier.height(24.dp))
////
////        Button(
////            onClick = {
////                if (name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || location.isBlank()) {
////                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
////                } else {
////                    val user = User(name, email, password, bloodGroup, phone, location)
////                    prefs.saveUser(user)
////                    Toast.makeText(context, "Registered! Please login", Toast.LENGTH_SHORT).show()
////                    navController.popBackStack()
////                }
////            },
////            modifier = Modifier.fillMaxWidth(),
////            shape = RoundedCornerShape(12.dp)
////        ) { Text("Register") }
////    }
////}
////
////// --------------------------------------------------------------
////// Home Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun HomeScreen(navController: NavHostController, context: Context) {
////    val prefs = PreferencesManager(context)
////    val menuItems = listOf(
////        Triple("Find Donors", Icons.Default.Search, "findDonors"),
////        Triple("Emergency Request", Icons.Default.Warning, "emergency"),
////        Triple("My Profile", Icons.Default.Person, "profile"),
////        Triple("Request History", Icons.Default.History, "history"),
////        Triple("Logout", Icons.Default.Logout, "logout")
////    )
////
////    Scaffold(topBar = { TopAppBar(title = { Text("Rakta-Seva Connect", color = MaterialTheme.colorScheme.primary) }) }) { padding ->
////        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
////            Text("Welcome!", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
////            Spacer(modifier = Modifier.height(24.dp))
////            LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
////                items(menuItems) { (title, icon, route) ->
////                    Card(
////                        modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(16.dp)),
////                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
////                        onClick = {
////                            if (route == "logout") {
////                                prefs.setLoggedIn(false)
////                                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
////                                navController.navigate("login") { popUpTo("home") { inclusive = true } }
////                            } else {
////                                navController.navigate(route)
////                            }
////                        }
////                    ) {
////                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
////                            Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
////                            Spacer(modifier = Modifier.height(8.dp))
////                            Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
////                        }
////                    }
////                }
////            }
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// Find Donors Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun FindDonorsScreen() {
////    val context = LocalContext.current
////    var selectedBloodGroup by remember { mutableStateOf("All") }
////    val donors = DummyData.donors
////    val filtered = if (selectedBloodGroup == "All") donors else donors.filter { it.bloodGroup == selectedBloodGroup }
////    val groups = listOf("All") + listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
////    var expanded by remember { mutableStateOf(false) }
////
////    Scaffold(topBar = { TopAppBar(title = { Text("Find Donors") }) }) { padding ->
////        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
////            Text("Filter by Blood Group", style = MaterialTheme.typography.titleMedium)
////            Spacer(modifier = Modifier.height(8.dp))
////            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
////                OutlinedTextField(value = selectedBloodGroup, onValueChange = {}, readOnly = true, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
////                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
////                    groups.forEach { group ->
////                        DropdownMenuItem(text = { Text(group) }, onClick = { selectedBloodGroup = group; expanded = false })
////                    }
////                }
////            }
////            Spacer(modifier = Modifier.height(16.dp))
////            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
////                items(filtered) { donor ->
////                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
////                        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
////                            Column {
////                                Text(donor.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
////                                Text("${donor.bloodGroup} | ${donor.location}", style = MaterialTheme.typography.bodyMedium)
////                                Text(if (donor.isAvailable) "✅ Available" else "❌ Not Available", style = MaterialTheme.typography.bodySmall, color = if (donor.isAvailable) Color.Green else Color.Red)
////                            }
////                            Button(onClick = { Toast.makeText(context, "Calling ${donor.name}", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(12.dp)) {
////                                Icon(Icons.Default.Call, null)
////                                Spacer(modifier = Modifier.width(4.dp))
////                                Text("Call")
////                            }
////                        }
////                    }
////                }
////            }
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// Emergency Request Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun EmergencyRequestScreen(navController: NavHostController) {
////    val context = LocalContext.current
////    var patientName by remember { mutableStateOf("") }
////    var bloodGroup by remember { mutableStateOf("A+") }
////    var hospitalName by remember { mutableStateOf("") }
////    var unitsRequired by remember { mutableStateOf("") }
////    var urgency by remember { mutableStateOf("Normal") }
////    var showDialog by remember { mutableStateOf(false) }
////    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
////    val urgencyLevels = listOf("Normal", "Urgent", "Emergency")
////    var expandedBlood by remember { mutableStateOf(false) }
////    var expandedUrgency by remember { mutableStateOf(false) }
////
////    Scaffold(topBar = { TopAppBar(title = { Text("Emergency Request") }) }) { padding ->
////        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
////            OutlinedTextField(value = patientName, onValueChange = { patientName = it }, label = { Text("Patient Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////            Spacer(modifier = Modifier.height(12.dp))
////            ExposedDropdownMenuBox(expanded = expandedBlood, onExpandedChange = { expandedBlood = it }) {
////                OutlinedTextField(value = bloodGroup, onValueChange = {}, readOnly = true, label = { Text("Blood Group") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedBlood) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
////                ExposedDropdownMenu(expanded = expandedBlood, onDismissRequest = { expandedBlood = false }) {
////                    bloodGroups.forEach { group ->
////                        DropdownMenuItem(text = { Text(group) }, onClick = { bloodGroup = group; expandedBlood = false })
////                    }
////                }
////            }
////            Spacer(modifier = Modifier.height(12.dp))
////            OutlinedTextField(value = hospitalName, onValueChange = { hospitalName = it }, label = { Text("Hospital Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
////            Spacer(modifier = Modifier.height(12.dp))
////            OutlinedTextField(
////                value = unitsRequired, onValueChange = { unitsRequired = it },
////                label = { Text("Units Required") }, modifier = Modifier.fillMaxWidth(),
////                shape = RoundedCornerShape(12.dp), singleLine = true,
////                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
////            )
////            Spacer(modifier = Modifier.height(12.dp))
////            ExposedDropdownMenuBox(expanded = expandedUrgency, onExpandedChange = { expandedUrgency = it }) {
////                OutlinedTextField(value = urgency, onValueChange = {}, readOnly = true, label = { Text("Urgency") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedUrgency) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
////                ExposedDropdownMenu(expanded = expandedUrgency, onDismissRequest = { expandedUrgency = false }) {
////                    urgencyLevels.forEach { level ->
////                        DropdownMenuItem(text = { Text(level) }, onClick = { urgency = level; expandedUrgency = false })
////                    }
////                }
////            }
////            Spacer(modifier = Modifier.height(24.dp))
////            Button(onClick = {
////                if (patientName.isBlank() || hospitalName.isBlank() || unitsRequired.isBlank()) {
////                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
////                } else {
////                    val request = EmergencyRequest(
////                        patientName = patientName,
////                        bloodGroup = bloodGroup,
////                        hospitalName = hospitalName,
////                        unitsRequired = unitsRequired.toIntOrNull() ?: 1,
////                        urgency = urgency,
////                        status = "Pending",
////                        timestamp = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
////                    )
////                    RequestHistoryRepository.addRequest(request)
////                    showDialog = true
////                }
////            }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
////                Text("Submit Emergency Request")
////            }
////        }
////    }
////
////    if (showDialog) {
////        AlertDialog(
////            onDismissRequest = { showDialog = false },
////            title = { Text("Request Submitted") },
////            text = { Text("Your emergency request has been submitted.") },
////            confirmButton = { TextButton(onClick = { showDialog = false; navController.popBackStack() }) { Text("OK") } }
////        )
////    }
////}
////
////// --------------------------------------------------------------
////// Request History Screen
////// --------
////// ------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun RequestHistoryScreen() {
////    val requests = RequestHistoryRepository.requests
////    Scaffold(topBar = { TopAppBar(title = { Text("Request History") }) }) { padding ->
////        if (requests.isEmpty()) {
////            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
////                Text("No requests yet", style = MaterialTheme.typography.bodyLarge)
////            }
////        } else {
////            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
////                items(requests.reversed()) { req ->
////                    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
////                        Column(modifier = Modifier.padding(16.dp)) {
////                            Text(req.patientName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
////                            Text("Blood: ${req.bloodGroup} | Hospital: ${req.hospitalName}", style = MaterialTheme.typography.bodyMedium)
////                            Text("Units: ${req.unitsRequired} | Urgency: ${req.urgency}", style = MaterialTheme.typography.bodyMedium)
////                            Text("Status: ${req.status}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFFF9800))
////                            Text(req.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
////                        }
////                    }
////                }
////            }
////        }
////    }
////}
////
////// --------------------------------------------------------------
////// My Profile Screen
////// --------------------------------------------------------------
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun MyProfileScreen(context: Context, navController: NavHostController) {
////    val prefs = PreferencesManager(context)
////    var user by remember { mutableStateOf(prefs.getUser()) }
////    var isAvailable by remember { mutableStateOf(user?.isAvailable ?: false) }
////
////    Scaffold(topBar = { TopAppBar(title = { Text("My Profile") }) }) { padding ->
////        if (user == null) {
////            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
////                Text("No user data. Please login again.")
////            }
////        } else {
////            Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
////                Box(
////                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.primary),
////                    contentAlignment = Alignment.Center
////                ) {
////                    Text(user!!.name.take(1).uppercase(), fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)
////                }
////                Spacer(modifier = Modifier.height(24.dp))
////                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(4.dp)) {
////                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
////                        ProfileRow("Name", user!!.name)
////                        ProfileRow("Email", user!!.email)
////                        ProfileRow("Blood Group", user!!.bloodGroup)
////                        ProfileRow("Phone", user!!.phone)
////                        ProfileRow("Location", user!!.location)
////                        Divider()
////                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
////                            Text("Available for Donation", style = MaterialTheme.typography.bodyLarge)
////                            Switch(checked = isAvailable, onCheckedChange = { newValue ->
////                                isAvailable = newValue
////                                user = user!!.copy(isAvailable = newValue)
////                                prefs.saveUser(user!!)
////                            })
////                        }
////                    }
////                }
////                Spacer(modifier = Modifier.height(24.dp))
////                Button(onClick = { navController.navigateUp() }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
////                    Text("Back to Home")
////                }
////            }
////        }
////    }
////}
////
////@Composable
////fun ProfileRow(label: String, value: String) {
////    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
////        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
////        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
////    }
////}
////
////// --------------------------------------------------------------
////// Theme (inline)
////// --------------------------------------------------------------
////@Composable
////fun RaktaSevaConnectTheme(content: @Composable () -> Unit) {
////    val colorScheme = lightColorScheme(
////        primary = Color(0xFFD32F2F),
////        onPrimary = Color.White,
////        primaryContainer = Color(0xFFFFCDD2),
////        onPrimaryContainer = Color(0xFFB71C1C),
////        secondary = Color(0xFFF44336),
////        background = Color.White,
////        surface = Color.White,
////        error = Color(0xFFD32F2F)
////    )
////    MaterialTheme(colorScheme = colorScheme, typography = Typography(), content = content)
////}
//
//package com.example.finalproject
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.core.*
//import androidx.compose.animation.*
//import androidx.compose.foundation.background
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.interaction.collectIsPressedAsState
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.google.gson.Gson
//import kotlinx.coroutines.delay
//import java.text.SimpleDateFormat
//import java.util.*
//
//// ------------------------ Data Models ------------------------
//data class User(
//    val name: String,
//    val email: String,
//    val password: String,
//    val bloodGroup: String,
//    val phone: String,
//    val location: String,
//    val isAvailable: Boolean = false
//)
//
//data class Donor(
//    val name: String,
//    val bloodGroup: String,
//    val location: String,
//    val isAvailable: Boolean,
//    val phone: String
//)
//
//data class EmergencyRequest(
//    val patientName: String,
//    val bloodGroup: String,
//    val hospitalName: String,
//    val unitsRequired: Int,
//    val urgency: String,
//    val status: String,
//    val timestamp: String
//)
//
//// ------------------------ Dummy Data ------------------------
//object DummyData {
//    val donors = listOf(
//        Donor("Rajesh Kumar", "A+", "Mumbai", true, "9876543210"),
//        Donor("Priya Sharma", "B+", "Delhi", true, "9876543211"),
//        Donor("Amit Patel", "O+", "Ahmedabad", false, "9876543212"),
//        Donor("Sneha Reddy", "AB-", "Hyderabad", true, "9876543213"),
//        Donor("Vikram Singh", "A-", "Jaipur", true, "9876543214"),
//        Donor("Neha Gupta", "B-", "Lucknow", false, "9876543215"),
//        Donor("Rahul Verma", "O-", "Pune", true, "9876543216"),
//        Donor("Anjali Nair", "AB+", "Chennai", true, "9876543217"),
//        Donor("Sanjay Mehta", "A+", "Kolkata", true, "9876543218"),
//        Donor("Kavita Iyer", "B+", "Bengaluru", true, "9876543219")
//    )
//}
//
//// ------------------------ Request History ------------------------
//object RequestHistoryRepository {
//    private val _requests = mutableStateListOf<EmergencyRequest>()
//    val requests: List<EmergencyRequest> = _requests
//    fun addRequest(request: EmergencyRequest) { _requests.add(request) }
//}
//
//// ------------------------ Preferences Manager ------------------------
//class PreferencesManager(context: Context) {
//    private val prefs: SharedPreferences = context.getSharedPreferences("RaktaSevaPrefs", Context.MODE_PRIVATE)
//    private val gson = Gson()
//    fun saveUser(user: User) { prefs.edit().putString("user", gson.toJson(user)).apply() }
//    fun getUser(): User? { val json = prefs.getString("user", null) ?: return null; return gson.fromJson(json, User::class.java) }
//    fun setLoggedIn(isLoggedIn: Boolean) { prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply() }
//    fun isLoggedIn(): Boolean = prefs.getBoolean("isLoggedIn", false)
//}
//
//// ------------------------ Main Activity ------------------------
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            RaktaSevaConnectTheme(darkTheme = isSystemInDarkTheme()) {
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    val navController = rememberNavController()
//                    AppNavGraph(navController, this)
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Navigation ------------------------
//@Composable
//fun AppNavGraph(navController: NavHostController, context: Context) {
//    NavHost(
//        navController = navController,
//        startDestination = "splash",
//        enterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { it } },
//        exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { -it } },
//        popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { -it } },
//        popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { it } }
//    ) {
//        composable("splash") { SplashScreen(navController) }
//        composable("login") { LoginScreen(navController, context) }
//        composable("register") { RegistrationScreen(navController, context) }
//        composable("home") { HomeScreen(navController, context) }
//        composable("findDonors") { FindDonorsScreen() }
//        composable("emergency") { EmergencyRequestScreen(navController) }
//        composable("history") { RequestHistoryScreen() }
//        composable("profile") { MyProfileScreen(context, navController) }
//    }
//}
//
//// ------------------------ Splash Screen ------------------------
//@Composable
//fun SplashScreen(navController: NavHostController) {
//    var startAnim by remember { mutableStateOf(false) }
//    val infiniteTransition = rememberInfiniteTransition()
//    val scale by infiniteTransition.animateFloat(
//        initialValue = 1f,
//        targetValue = 1.2f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(800, easing = FastOutSlowInEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//    val alpha = animateFloatAsState(
//        targetValue = if (startAnim) 1f else 0f,
//        animationSpec = tween(1000)
//    )
//    LaunchedEffect(Unit) {
//        startAnim = true
//        delay(2500)
//        navController.popBackStack()
//        navController.navigate("login")
//    }
//    Box(
//        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha.value)) {
//            Text("🩸", fontSize = 100.sp, color = Color.White, modifier = Modifier.scale(scale))
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Rakta-Seva Connect", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
//            Text("Blood Donation Network", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
//        }
//    }
//}
//
//// ------------------------ Login Screen ------------------------
//@Composable
//fun LoginScreen(navController: NavHostController, context: Context) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val prefs = PreferencesManager(context)
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
//        Spacer(modifier = Modifier.height(32.dp))
//
//        OutlinedTextField(
//            value = email, onValueChange = { email = it },
//            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp), singleLine = true
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = password, onValueChange = { password = it },
//            label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            visualTransformation = PasswordVisualTransformation(), singleLine = true
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        AnimatedButton(onClick = {
//            when {
//                email.isBlank() || password.isBlank() -> Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//                else -> {
//                    val user = prefs.getUser()
//                    if (user != null && user.email == email && user.password == password) {
//                        prefs.setLoggedIn(true)
//                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
//                        navController.navigate("home") { popUpTo("login") { inclusive = true } }
//                    } else Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }, text = "Login")
//
//        Spacer(modifier = Modifier.height(12.dp))
//        TextButton(onClick = { navController.navigate("register") }) {
//            Text("Create New Account", color = MaterialTheme.colorScheme.primary)
//        }
//    }
//}
//
//// ------------------------ Registration Screen (no experimental) ------------------------
//@Composable
//fun RegistrationScreen(navController: NavHostController, context: Context) {
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var bloodGroup by remember { mutableStateOf("A+") }
//    var phone by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }
//    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    val prefs = PreferencesManager(context)
//    var showDropdown by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(24.dp))
//
//        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//
//        // Blood group picker using Button + Dialog (no experimental)
//        OutlinedTextField(
//            value = bloodGroup,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Blood Group") },
//            trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            enabled = false
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Button(
//            onClick = { showDropdown = true },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
//        ) {
//            Text("Select Blood Group")
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(24.dp))
//
//        AnimatedButton(onClick = {
//            if (name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || location.isBlank()) {
//                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                val user = User(name, email, password, bloodGroup, phone, location)
//                prefs.saveUser(user)
//                Toast.makeText(context, "Registered! Please login", Toast.LENGTH_SHORT).show()
//                navController.popBackStack()
//            }
//        }, text = "Register")
//    }
//
//    if (showDropdown) {
//        Dialog(onDismissRequest = { showDropdown = false }) {
//            Card(
//                modifier = Modifier.fillMaxWidth().padding(16.dp),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    bloodGroups.forEach { group ->
//                        TextButton(
//                            onClick = {
//                                bloodGroup = group
//                                showDropdown = false
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Home Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavHostController, context: Context) {
//    val prefs = PreferencesManager(context)
//    val menuItems = listOf(
//        Triple("Find Donors", Icons.Default.Search, "findDonors"),
//        Triple("Emergency Request", Icons.Default.Warning, "emergency"),
//        Triple("My Profile", Icons.Default.Person, "profile"),
//        Triple("Request History", Icons.Default.History, "history"),
//        Triple("Logout", Icons.Default.Logout, "logout")
//    )
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Rakta-Seva Connect", color = MaterialTheme.colorScheme.primary) }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("Welcome!", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
//            Spacer(modifier = Modifier.height(24.dp))
//            LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
//                items(menuItems) { (title, icon, route) ->
//                    Card(
//                        modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(16.dp)),
//                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                        onClick = {
//                            if (route == "logout") {
//                                prefs.setLoggedIn(false)
//                                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
//                                navController.navigate("login") { popUpTo("home") { inclusive = true } }
//                            } else navController.navigate(route)
//                        }
//                    ) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
//                            Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
//                            Spacer(modifier = Modifier.height(8.dp))
//                            Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Find Donors Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FindDonorsScreen() {
//    val context = LocalContext.current
//    var selectedBloodGroup by remember { mutableStateOf("All") }
//    val donors = DummyData.donors
//    val filtered = if (selectedBloodGroup == "All") donors else donors.filter { it.bloodGroup == selectedBloodGroup }
//    val groups = listOf("All") + listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    var showFilterDialog by remember { mutableStateOf(false) }
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Find Donors") }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
//            Text("Filter by Blood Group", style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = selectedBloodGroup,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                enabled = false
//            )
//            Button(
//                onClick = { showFilterDialog = true },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text("Change Filter")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                items(filtered) { donor ->
//                    AnimatedCardEnter {
//                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
//                                Column {
//                                    Text(donor.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
//                                    Text("${donor.bloodGroup} | ${donor.location}", style = MaterialTheme.typography.bodyMedium)
//                                    Text(if (donor.isAvailable) "✅ Available" else "❌ Not Available", style = MaterialTheme.typography.bodySmall, color = if (donor.isAvailable) Color.Green else Color.Red)
//                                }
//                                Button(onClick = { Toast.makeText(context, "Calling ${donor.name}", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(12.dp)) {
//                                    Icon(Icons.Default.Call, null, modifier = Modifier.size(20.dp))
//                                    Spacer(modifier = Modifier.width(4.dp))
//                                    Text("Call")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    if (showFilterDialog) {
//        Dialog(onDismissRequest = { showFilterDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    groups.forEach { group ->
//                        TextButton(
//                            onClick = {
//                                selectedBloodGroup = group
//                                showFilterDialog = false
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Emergency Request Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EmergencyRequestScreen(navController: NavHostController) {
//    val context = LocalContext.current
//    var patientName by remember { mutableStateOf("") }
//    var bloodGroup by remember { mutableStateOf("A+") }
//    var hospitalName by remember { mutableStateOf("") }
//    var unitsRequired by remember { mutableStateOf("") }
//    var urgency by remember { mutableStateOf("Normal") }
//    var showDialog by remember { mutableStateOf(false) }
//    var showBloodDialog by remember { mutableStateOf(false) }
//    var showUrgencyDialog by remember { mutableStateOf(false) }
//    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    val urgencyLevels = listOf("Normal", "Urgent", "Emergency")
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Emergency Request") }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//            OutlinedTextField(value = patientName, onValueChange = { patientName = it }, label = { Text("Patient Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Blood Group picker
//            OutlinedTextField(value = bloodGroup, onValueChange = {}, readOnly = true, label = { Text("Blood Group") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
//            Button(onClick = { showBloodDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Blood Group") }
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = hospitalName, onValueChange = { hospitalName = it }, label = { Text("Hospital Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = unitsRequired, onValueChange = { unitsRequired = it }, label = { Text("Units Required") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
//            Spacer(modifier = Modifier.height(12.dp))
//
//            // Urgency picker
//            OutlinedTextField(value = urgency, onValueChange = {}, readOnly = true, label = { Text("Urgency") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
//            Button(onClick = { showUrgencyDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Urgency") }
//            Spacer(modifier = Modifier.height(24.dp))
//
//            AnimatedButton(onClick = {
//                if (patientName.isBlank() || hospitalName.isBlank() || unitsRequired.isBlank()) {
//                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//                } else {
//                    val request = EmergencyRequest(
//                        patientName, bloodGroup, hospitalName,
//                        unitsRequired.toIntOrNull() ?: 1, urgency, "Pending",
//                        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
//                    )
//                    RequestHistoryRepository.addRequest(request)
//                    showDialog = true
//                }
//            }, text = "Submit Emergency Request", colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error))
//        }
//    }
//
//    // Blood group dialog
//    if (showBloodDialog) {
//        Dialog(onDismissRequest = { showBloodDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    bloodGroups.forEach { group ->
//                        TextButton(onClick = { bloodGroup = group; showBloodDialog = false }, modifier = Modifier.fillMaxWidth()) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//
//    // Urgency dialog
//    if (showUrgencyDialog) {
//        Dialog(onDismissRequest = { showUrgencyDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Urgency", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    urgencyLevels.forEach { level ->
//                        TextButton(onClick = { urgency = level; showUrgencyDialog = false }, modifier = Modifier.fillMaxWidth()) {
//                            Text(level, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text("Request Submitted") },
//            text = { Text("Your emergency request has been submitted.") },
//            confirmButton = { TextButton(onClick = { showDialog = false; navController.popBackStack() }) { Text("OK") } }
//        )
//    }
//}
//
//// ------------------------ Request History Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RequestHistoryScreen() {
//    val requests = RequestHistoryRepository.requests
//    Scaffold(topBar = { TopAppBar(title = { Text("Request History") }) }) { padding ->
//        if (requests.isEmpty()) {
//            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
//                Text("No requests yet", style = MaterialTheme.typography.bodyLarge)
//            }
//        } else {
//            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                items(requests.reversed()) { req ->
//                    AnimatedCardEnter {
//                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                            Column(modifier = Modifier.padding(16.dp)) {
//                                Text(req.patientName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
//                                Text("Blood: ${req.bloodGroup} | Hospital: ${req.hospitalName}", style = MaterialTheme.typography.bodyMedium)
//                                Text("Units: ${req.unitsRequired} | Urgency: ${req.urgency}", style = MaterialTheme.typography.bodyMedium)
//                                Text("Status: ${req.status}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFFF9800))
//                                Text(req.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ My Profile Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyProfileScreen(context: Context, navController: NavHostController) {
//    val prefs = PreferencesManager(context)
//    var user by remember { mutableStateOf(prefs.getUser()) }
//    var isAvailable by remember { mutableStateOf(user?.isAvailable ?: false) }
//
//    Scaffold(topBar = { TopAppBar(title = { Text("My Profile") }) }) { padding ->
//        if (user == null) {
//            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
//                Text("No user data. Please login again.")
//            }
//        } else {
//            Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(
//                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.primary),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(user!!.name.take(1).uppercase(), fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)
//                }
//                Spacer(modifier = Modifier.height(24.dp))
//                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                        ProfileRow("Name", user!!.name)
//                        ProfileRow("Email", user!!.email)
//                        ProfileRow("Blood Group", user!!.bloodGroup)
//                        ProfileRow("Phone", user!!.phone)
//                        ProfileRow("Location", user!!.location)
//                        Divider()
//                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
//                            Text("Available for Donation", style = MaterialTheme.typography.bodyLarge)
//                            Switch(checked = isAvailable, onCheckedChange = { newValue ->
//                                isAvailable = newValue
//                                user = user!!.copy(isAvailable = newValue)
//                                prefs.saveUser(user!!)
//                            })
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(24.dp))
//                AnimatedButton(onClick = { navController.navigateUp() }, text = "Back to Home")
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileRow(label: String, value: String) {
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
//        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
//    }
//}
//
//// ------------------------ Reusable Animated Components ------------------------
//@Composable
//fun AnimatedButton(
//    onClick: () -> Unit,
//    text: String,
//    colors: ButtonColors = ButtonDefaults.buttonColors()
//) {
//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed by interactionSource.collectIsPressedAsState()
//    val scale by animateFloatAsState(
//        targetValue = if (isPressed) 0.95f else 1f,
//        animationSpec = spring(stiffness = Spring.StiffnessLow)
//    )
//    Button(
//        onClick = onClick,
//        modifier = Modifier.fillMaxWidth().scale(scale),
//        shape = RoundedCornerShape(12.dp),
//        colors = colors,
//        interactionSource = interactionSource
//    ) {
//        Text(text, modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Composable
//fun AnimatedCardEnter(content: @Composable () -> Unit) {
//    val alpha by animateFloatAsState(
//        targetValue = 1f,
//        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
//    )
//    Box(modifier = Modifier.alpha(alpha)) {
//        content()
//    }
//}
//
//// ------------------------ Theme (Dark/Light) ------------------------
//@Composable
//fun RaktaSevaConnectTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colorScheme = if (darkTheme) {
//        darkColorScheme(
//            primary = Color(0xFFFF5252),
//            onPrimary = Color.Black,
//            primaryContainer = Color(0xFFB71C1C),
//            onPrimaryContainer = Color(0xFFFFCDD2),
//            secondary = Color(0xFFFF7961),
//            background = Color(0xFF121212),
//            surface = Color(0xFF1E1E1E),
//            error = Color(0xFFCF6679)
//        )
//    } else {
//        lightColorScheme(
//            primary = Color(0xFFD32F2F),
//            onPrimary = Color.White,
//            primaryContainer = Color(0xFFFFCDD2),
//            onPrimaryContainer = Color(0xFFB71C1C),
//            secondary = Color(0xFFF44336),
//            background = Color.White,
//            surface = Color.White,
//            error = Color(0xFFD32F2F)
//        )
//    }
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography(),
//        content = content
//    )
//}

//package com.example.finalproject
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.core.*
//import androidx.compose.animation.*
//import androidx.compose.foundation.background
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.interaction.collectIsPressedAsState
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.google.gson.Gson
//import kotlinx.coroutines.delay
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.math.max
//
//// ------------------------ Data Models ------------------------
//data class User(
//    val name: String,
//    val email: String,
//    val password: String,
//    val bloodGroup: String,
//    val phone: String,
//    val location: String,
//    val isAvailable: Boolean = false
//)
//
//data class Donor(
//    val name: String,
//    val bloodGroup: String,
//    val location: String,
//    val isAvailable: Boolean,
//    val phone: String
//)
//
//data class EmergencyRequest(
//    val patientName: String,
//    val bloodGroup: String,
//    val hospitalName: String,
//    val unitsRequired: Int,
//    val urgency: String,
//    val status: String,
//    val timestamp: String
//)
//
//// ------------------------ Dummy Data ------------------------
//object DummyData {
//    val donors = listOf(
//        Donor("Rajesh Kumar", "A+", "Mumbai", true, "9876543210"),
//        Donor("Priya Sharma", "B+", "Delhi", true, "9876543211"),
//        Donor("Amit Patel", "O+", "Ahmedabad", false, "9876543212"),
//        Donor("Sneha Reddy", "AB-", "Hyderabad", true, "9876543213"),
//        Donor("Vikram Singh", "A-", "Jaipur", true, "9876543214"),
//        Donor("Neha Gupta", "B-", "Lucknow", false, "9876543215"),
//        Donor("Rahul Verma", "O-", "Pune", true, "9876543216"),
//        Donor("Anjali Nair", "AB+", "Chennai", true, "9876543217"),
//        Donor("Sanjay Mehta", "A+", "Kolkata", true, "9876543218"),
//        Donor("Kavita Iyer", "B+", "Bengaluru", true, "9876543219")
//    )
//}
//
//// ------------------------ Request History ------------------------
//object RequestHistoryRepository {
//    private val _requests = mutableStateListOf<EmergencyRequest>()
//    val requests: List<EmergencyRequest> = _requests
//    fun addRequest(request: EmergencyRequest) { _requests.add(request) }
//}
//
//// ------------------------ Preferences Manager (extended) ------------------------
//class PreferencesManager(context: Context) {
//    private val prefs: SharedPreferences = context.getSharedPreferences("RaktaSevaPrefs", Context.MODE_PRIVATE)
//    private val gson = Gson()
//
//    fun saveUser(user: User) { prefs.edit().putString("user", gson.toJson(user)).apply() }
//    fun getUser(): User? { val json = prefs.getString("user", null) ?: return null; return gson.fromJson(json, User::class.java) }
//    fun setLoggedIn(isLoggedIn: Boolean) { prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply() }
//    fun isLoggedIn(): Boolean = prefs.getBoolean("isLoggedIn", false)
//
//    // 90-day cooldown functions
//    fun saveLastDonationTime(timestamp: Long) {
//        prefs.edit().putLong("lastDonationTime", timestamp).apply()
//    }
//    fun getLastDonationTime(): Long = prefs.getLong("lastDonationTime", 0L)
//    fun isInCooldown(): Boolean {
//        val last = getLastDonationTime()
//        if (last == 0L) return false
//        val daysSince = (System.currentTimeMillis() - last) / (24 * 60 * 60 * 1000)
//        return daysSince < 90
//    }
//    fun getDaysRemaining(): Int {
//        val last = getLastDonationTime()
//        if (last == 0L) return 0
//        val daysSince = (System.currentTimeMillis() - last) / (24 * 60 * 60 * 1000)
//        return max(0, 90 - daysSince.toInt())
//    }
//}
//
//// ------------------------ Main Activity ------------------------
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            RaktaSevaConnectTheme(darkTheme = isSystemInDarkTheme()) {
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    val navController = rememberNavController()
//                    AppNavGraph(navController, this)
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Navigation ------------------------
//@Composable
//fun AppNavGraph(navController: NavHostController, context: Context) {
//    NavHost(
//        navController = navController,
//        startDestination = "splash",
//        enterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { it } },
//        exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { -it } },
//        popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { -it } },
//        popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { it } }
//    ) {
//        composable("splash") { SplashScreen(navController) }
//        composable("login") { LoginScreen(navController, context) }
//        composable("register") { RegistrationScreen(navController, context) }
//        composable("home") { HomeScreen(navController, context) }
//        composable("findDonors") { FindDonorsScreen() }
//        composable("emergency") { EmergencyRequestScreen(navController) }
//        composable("history") { RequestHistoryScreen() }
//        composable("profile") { MyProfileScreen(context, navController) }
//    }
//}
//
//// ------------------------ Splash Screen ------------------------
//@Composable
//fun SplashScreen(navController: NavHostController) {
//    var startAnim by remember { mutableStateOf(false) }
//    val infiniteTransition = rememberInfiniteTransition()
//    val scale by infiniteTransition.animateFloat(
//        initialValue = 1f,
//        targetValue = 1.2f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(800, easing = FastOutSlowInEasing),
//            repeatMode = RepeatMode.Reverse
//        )
//    )
//    val alpha = animateFloatAsState(
//        targetValue = if (startAnim) 1f else 0f,
//        animationSpec = tween(1000)
//    )
//    LaunchedEffect(Unit) {
//        startAnim = true
//        delay(2500)
//        navController.popBackStack()
//        navController.navigate("login")
//    }
//    Box(
//        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha.value)) {
//            Text("🩸", fontSize = 100.sp, color = Color.White, modifier = Modifier.scale(scale))
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Rakta-Seva Connect", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
//            Text("Blood Donation Network", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
//        }
//    }
//}
//
//// ------------------------ Login Screen ------------------------
//@Composable
//fun LoginScreen(navController: NavHostController, context: Context) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    val prefs = PreferencesManager(context)
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
//        Spacer(modifier = Modifier.height(32.dp))
//
//        OutlinedTextField(
//            value = email, onValueChange = { email = it },
//            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp), singleLine = true
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = password, onValueChange = { password = it },
//            label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            visualTransformation = PasswordVisualTransformation(), singleLine = true
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        AnimatedButton(onClick = {
//            when {
//                email.isBlank() || password.isBlank() -> Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//                else -> {
//                    val user = prefs.getUser()
//                    if (user != null && user.email == email && user.password == password) {
//                        prefs.setLoggedIn(true)
//                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
//                        navController.navigate("home") { popUpTo("login") { inclusive = true } }
//                    } else Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }, text = "Login")
//
//        Spacer(modifier = Modifier.height(12.dp))
//        TextButton(onClick = { navController.navigate("register") }) {
//            Text("Create New Account", color = MaterialTheme.colorScheme.primary)
//        }
//    }
//}
//
//// ------------------------ Registration Screen ------------------------
//@Composable
//fun RegistrationScreen(navController: NavHostController, context: Context) {
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var bloodGroup by remember { mutableStateOf("A+") }
//    var phone by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }
//    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    val prefs = PreferencesManager(context)
//    var showDropdown by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(24.dp))
//
//        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = bloodGroup,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Blood Group") },
//            trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            enabled = false
//        )
//        Spacer(modifier = Modifier.height(4.dp))
//        Button(
//            onClick = { showDropdown = true },
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(12.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
//        ) {
//            Text("Select Blood Group")
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(12.dp))
//        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//        Spacer(modifier = Modifier.height(24.dp))
//
//        AnimatedButton(onClick = {
//            if (name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || location.isBlank()) {
//                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                val user = User(name, email, password, bloodGroup, phone, location)
//                prefs.saveUser(user)
//                Toast.makeText(context, "Registered! Please login", Toast.LENGTH_SHORT).show()
//                navController.popBackStack()
//            }
//        }, text = "Register")
//    }
//
//    if (showDropdown) {
//        Dialog(onDismissRequest = { showDropdown = false }) {
//            Card(
//                modifier = Modifier.fillMaxWidth().padding(16.dp),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    bloodGroups.forEach { group ->
//                        TextButton(
//                            onClick = {
//                                bloodGroup = group
//                                showDropdown = false
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Home Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavHostController, context: Context) {
//    val prefs = PreferencesManager(context)
//    val menuItems = listOf(
//        Triple("Find Donors", Icons.Default.Search, "findDonors"),
//        Triple("Emergency Request", Icons.Default.Warning, "emergency"),
//        Triple("My Profile", Icons.Default.Person, "profile"),
//        Triple("Request History", Icons.Default.History, "history"),
//        Triple("Logout", Icons.Default.Logout, "logout")
//    )
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Rakta-Seva Connect", color = MaterialTheme.colorScheme.primary) }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("Welcome!", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
//            Spacer(modifier = Modifier.height(24.dp))
//            LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
//                items(menuItems) { (title, icon, route) ->
//                    Card(
//                        modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(16.dp)),
//                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
//                        onClick = {
//                            if (route == "logout") {
//                                prefs.setLoggedIn(false)
//                                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
//                                navController.navigate("login") { popUpTo("home") { inclusive = true } }
//                            } else navController.navigate(route)
//                        }
//                    ) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
//                            Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
//                            Spacer(modifier = Modifier.height(8.dp))
//                            Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Find Donors Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FindDonorsScreen() {
//    val context = LocalContext.current
//    var selectedBloodGroup by remember { mutableStateOf("All") }
//    val donors = DummyData.donors
//    val filtered = if (selectedBloodGroup == "All") donors else donors.filter { it.bloodGroup == selectedBloodGroup }
//    val groups = listOf("All") + listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    var showFilterDialog by remember { mutableStateOf(false) }
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Find Donors") }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
//            Text("Filter by Blood Group", style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = selectedBloodGroup,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                enabled = false
//            )
//            Button(
//                onClick = { showFilterDialog = true },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text("Change Filter")
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                items(filtered) { donor ->
//                    AnimatedCardEnter {
//                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
//                                Column {
//                                    Text(donor.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
//                                    Text("${donor.bloodGroup} | ${donor.location}", style = MaterialTheme.typography.bodyMedium)
//                                    Text(if (donor.isAvailable) "✅ Available" else "❌ Not Available", style = MaterialTheme.typography.bodySmall, color = if (donor.isAvailable) Color.Green else Color.Red)
//                                }
//                                Button(onClick = { Toast.makeText(context, "Calling ${donor.name}", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(12.dp)) {
//                                    Icon(Icons.Default.Call, null, modifier = Modifier.size(20.dp))
//                                    Spacer(modifier = Modifier.width(4.dp))
//                                    Text("Call")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    if (showFilterDialog) {
//        Dialog(onDismissRequest = { showFilterDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    groups.forEach { group ->
//                        TextButton(
//                            onClick = {
//                                selectedBloodGroup = group
//                                showFilterDialog = false
//                            },
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ Emergency Request Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EmergencyRequestScreen(navController: NavHostController) {
//    val context = LocalContext.current
//    var patientName by remember { mutableStateOf("") }
//    var bloodGroup by remember { mutableStateOf("A+") }
//    var hospitalName by remember { mutableStateOf("") }
//    var unitsRequired by remember { mutableStateOf("") }
//    var urgency by remember { mutableStateOf("Normal") }
//    var showDialog by remember { mutableStateOf(false) }
//    var showBloodDialog by remember { mutableStateOf(false) }
//    var showUrgencyDialog by remember { mutableStateOf(false) }
//    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
//    val urgencyLevels = listOf("Normal", "Urgent", "Emergency")
//
//    Scaffold(topBar = { TopAppBar(title = { Text("Emergency Request") }) }) { padding ->
//        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//            OutlinedTextField(value = patientName, onValueChange = { patientName = it }, label = { Text("Patient Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = bloodGroup, onValueChange = {}, readOnly = true, label = { Text("Blood Group") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
//            Button(onClick = { showBloodDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Blood Group") }
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = hospitalName, onValueChange = { hospitalName = it }, label = { Text("Hospital Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = unitsRequired, onValueChange = { unitsRequired = it }, label = { Text("Units Required") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(value = urgency, onValueChange = {}, readOnly = true, label = { Text("Urgency") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
//            Button(onClick = { showUrgencyDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Urgency") }
//            Spacer(modifier = Modifier.height(24.dp))
//
//            AnimatedButton(onClick = {
//                if (patientName.isBlank() || hospitalName.isBlank() || unitsRequired.isBlank()) {
//                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
//                } else {
//                    val request = EmergencyRequest(
//                        patientName, bloodGroup, hospitalName,
//                        unitsRequired.toIntOrNull() ?: 1, urgency, "Pending",
//                        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
//                    )
//                    RequestHistoryRepository.addRequest(request)
//                    showDialog = true
//                }
//            }, text = "Submit Emergency Request", colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error))
//        }
//    }
//
//    if (showBloodDialog) {
//        Dialog(onDismissRequest = { showBloodDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    bloodGroups.forEach { group ->
//                        TextButton(onClick = { bloodGroup = group; showBloodDialog = false }, modifier = Modifier.fillMaxWidth()) {
//                            Text(group, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//
//    if (showUrgencyDialog) {
//        Dialog(onDismissRequest = { showUrgencyDialog = false }) {
//            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Select Urgency", style = MaterialTheme.typography.titleMedium)
//                    Spacer(modifier = Modifier.height(8.dp))
//                    urgencyLevels.forEach { level ->
//                        TextButton(onClick = { urgency = level; showUrgencyDialog = false }, modifier = Modifier.fillMaxWidth()) {
//                            Text(level, modifier = Modifier.fillMaxWidth())
//                        }
//                        Divider()
//                    }
//                }
//            }
//        }
//    }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text("Request Submitted") },
//            text = { Text("Your emergency request has been submitted.") },
//            confirmButton = { TextButton(onClick = { showDialog = false; navController.popBackStack() }) { Text("OK") } }
//        )
//    }
//}
//
//// ------------------------ Request History Screen ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RequestHistoryScreen() {
//    val requests = RequestHistoryRepository.requests
//    Scaffold(topBar = { TopAppBar(title = { Text("Request History") }) }) { padding ->
//        if (requests.isEmpty()) {
//            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
//                Text("No requests yet", style = MaterialTheme.typography.bodyLarge)
//            }
//        } else {
//            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                items(requests.reversed()) { req ->
//                    AnimatedCardEnter {
//                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                            Column(modifier = Modifier.padding(16.dp)) {
//                                Text(req.patientName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
//                                Text("Blood: ${req.bloodGroup} | Hospital: ${req.hospitalName}", style = MaterialTheme.typography.bodyMedium)
//                                Text("Units: ${req.unitsRequired} | Urgency: ${req.urgency}", style = MaterialTheme.typography.bodyMedium)
//                                Text("Status: ${req.status}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFFF9800))
//                                Text(req.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// ------------------------ My Profile Screen (with 90‑day counter) ------------------------
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyProfileScreen(context: Context, navController: NavHostController) {
//    val prefs = PreferencesManager(context)
//    var user by remember { mutableStateOf(prefs.getUser()) }
//    var isAvailable by remember { mutableStateOf(user?.isAvailable ?: false) }
//    var daysRemaining by remember { mutableStateOf(prefs.getDaysRemaining()) }
//    var inCooldown by remember { mutableStateOf(prefs.isInCooldown()) }
//
//    // Refresh cooldown status every minute (to update days remaining display)
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(60_000L)
//            daysRemaining = prefs.getDaysRemaining()
//            inCooldown = prefs.isInCooldown()
//            // If cooldown just ended, auto‑set availability to true
//            if (!inCooldown && user != null && user!!.isAvailable == false) {
//                user = user!!.copy(isAvailable = true)
//                prefs.saveUser(user!!)
//                isAvailable = true
//                Toast.makeText(context, "You are now eligible to donate again!", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
//    Scaffold(topBar = { TopAppBar(title = { Text("My Profile") }) }) { padding ->
//        if (user == null) {
//            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
//                Text("No user data. Please login again.")
//            }
//        } else {
//            Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//                // Avatar
//                Box(
//                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.primary),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(user!!.name.take(1).uppercase(), fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)
//                }
//                Spacer(modifier = Modifier.height(24.dp))
//
//                // Profile Info Card
//                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(4.dp)) {
//                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                        ProfileRow("Name", user!!.name)
//                        ProfileRow("Email", user!!.email)
//                        ProfileRow("Blood Group", user!!.bloodGroup)
//                        ProfileRow("Phone", user!!.phone)
//                        ProfileRow("Location", user!!.location)
//                        Divider()
//
//                        // 90‑day cooldown card
//                        Card(
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = CardDefaults.cardColors(
//                                containerColor = if (inCooldown) MaterialTheme.colorScheme.errorContainer
//                                else MaterialTheme.colorScheme.primaryContainer
//                            )
//                        ) {
//                            Column(modifier = Modifier.padding(12.dp)) {
//                                if (inCooldown) {
//                                    Text(
//                                        text = "⏳ Cooldown Period",
//                                        style = MaterialTheme.typography.titleSmall,
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                    Text(
//                                        text = "You can donate again in $daysRemaining days.",
//                                        style = MaterialTheme.typography.bodyMedium
//                                    )
//                                    LinearProgressIndicator(
//                                        progress = { (90f - daysRemaining) / 90f },
//                                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
//                                        color = MaterialTheme.colorScheme.primary,
//                                        trackColor = MaterialTheme.colorScheme.surfaceVariant
//                                    )
//                                } else {
//                                    Text(
//                                        text = "✅ You are eligible to donate!",
//                                        style = MaterialTheme.typography.bodyLarge,
//                                        fontWeight = FontWeight.Bold,
//                                        color = MaterialTheme.colorScheme.primary
//                                    )
//                                }
//                            }
//                        }
//
//                        // Donation record button (starts 90‑day cooldown)
//                        Button(
//                            onClick = {
//                                if (!inCooldown) {
//                                    prefs.saveLastDonationTime(System.currentTimeMillis())
//                                    inCooldown = true
//                                    daysRemaining = 90
//                                    // Set user unavailable
//                                    isAvailable = false
//                                    user = user!!.copy(isAvailable = false)
//                                    prefs.saveUser(user!!)
//                                    Toast.makeText(context, "Donation recorded! You will be unavailable for 90 days.", Toast.LENGTH_LONG).show()
//                                } else {
//                                    Toast.makeText(context, "You are still in cooldown. Wait $daysRemaining more days.", Toast.LENGTH_SHORT).show()
//                                }
//                            },
//                            modifier = Modifier.fillMaxWidth(),
//                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
//                        ) {
//                            Icon(Icons.Default.Favorite, contentDescription = null)
//                            Spacer(modifier = Modifier.width(8.dp))
//                            Text(if (inCooldown) "Donation Already Recorded" else "Record Donation (I donated today)")
//                        }
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        // Availability toggle (disabled during cooldown)
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text("Available for Donation", style = MaterialTheme.typography.bodyLarge)
//                            Switch(
//                                checked = isAvailable,
//                                onCheckedChange = { newValue ->
//                                    if (inCooldown) {
//                                        Toast.makeText(context, "You cannot become available during the 90‑day cooldown.", Toast.LENGTH_SHORT).show()
//                                    } else {
//                                        isAvailable = newValue
//                                        user = user!!.copy(isAvailable = newValue)
//                                        prefs.saveUser(user!!)
//                                    }
//                                },
//                                enabled = !inCooldown
//                            )
//                        }
//                        if (inCooldown) {
//                            Text(
//                                text = "🔒 Cooldown active – availability locked",
//                                color = MaterialTheme.colorScheme.error,
//                                style = MaterialTheme.typography.bodySmall
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//                AnimatedButton(onClick = { navController.navigateUp() }, text = "Back to Home")
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileRow(label: String, value: String) {
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
//        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
//    }
//}
//
//// ------------------------ Reusable Animated Components ------------------------
//@Composable
//fun AnimatedButton(
//    onClick: () -> Unit,
//    text: String,
//    colors: ButtonColors = ButtonDefaults.buttonColors()
//) {
//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed by interactionSource.collectIsPressedAsState()
//    val scale by animateFloatAsState(
//        targetValue = if (isPressed) 0.95f else 1f,
//        animationSpec = spring(stiffness = Spring.StiffnessLow)
//    )
//    Button(
//        onClick = onClick,
//        modifier = Modifier.fillMaxWidth().scale(scale),
//        shape = RoundedCornerShape(12.dp),
//        colors = colors,
//        interactionSource = interactionSource
//    ) {
//        Text(text, modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Composable
//fun AnimatedCardEnter(content: @Composable () -> Unit) {
//    val alpha by animateFloatAsState(
//        targetValue = 1f,
//        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
//    )
//    Box(modifier = Modifier.alpha(alpha)) {
//        content()
//    }
//}
//
//// ------------------------ Theme (Dark/Light) ------------------------
//@Composable
//fun RaktaSevaConnectTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colorScheme = if (darkTheme) {
//        darkColorScheme(
//            primary = Color(0xFFFF5252),
//            onPrimary = Color.Black,
//            primaryContainer = Color(0xFFB71C1C),
//            onPrimaryContainer = Color(0xFFFFCDD2),
//            secondary = Color(0xFFFF7961),
//            background = Color(0xFF121212),
//            surface = Color(0xFF1E1E1E),
//            error = Color(0xFFCF6679)
//        )
//    } else {
//        lightColorScheme(
//            primary = Color(0xFFD32F2F),
//            onPrimary = Color.White,
//            primaryContainer = Color(0xFFFFCDD2),
//            onPrimaryContainer = Color(0xFFB71C1C),
//            secondary = Color(0xFFF44336),
//            background = Color.White,
//            surface = Color.White,
//            error = Color(0xFFD32F2F)
//        )
//    }
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography(),
//        content = content
//    )
//}
package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max

// ------------------------ Data Models ------------------------
data class User(
    val name: String,
    val email: String,
    val password: String,
    val bloodGroup: String,
    val phone: String,
    val location: String,
    val isAvailable: Boolean = false
)

data class Donor(
    val name: String,
    val bloodGroup: String,
    val location: String,
    val isAvailable: Boolean,
    val phone: String
)

data class EmergencyRequest(
    val patientName: String,
    val bloodGroup: String,
    val hospitalName: String,
    val unitsRequired: Int,
    val urgency: String,
    val status: String,
    val timestamp: String
)

// ------------------------ Dummy Data ------------------------
object DummyData {
    val donors = listOf(
        Donor("Rajesh Kumar", "A+", "Mumbai", true, "9876543210"),
        Donor("Priya Sharma", "B+", "Delhi", true, "9876543211"),
        Donor("Amit Patel", "O+", "Ahmedabad", false, "9876543212"),
        Donor("Sneha Reddy", "AB-", "Hyderabad", true, "9876543213"),
        Donor("Vikram Singh", "A-", "Jaipur", true, "9876543214"),
        Donor("Neha Gupta", "B-", "Lucknow", false, "9876543215"),
        Donor("Rahul Verma", "O-", "Pune", true, "9876543216"),
        Donor("Anjali Nair", "AB+", "Chennai", true, "9876543217"),
        Donor("Sanjay Mehta", "A+", "Kolkata", true, "9876543218"),
        Donor("Kavita Iyer", "B+", "Bengaluru", true, "9876543219")
    )
}

// ------------------------ Request History (global, observable) ------------------------
object RequestHistoryRepository {
    private val _requests = mutableStateListOf<EmergencyRequest>()
    val requests: List<EmergencyRequest> = _requests
    fun addRequest(request: EmergencyRequest) { _requests.add(request) }
}

// ------------------------ Preferences Manager (extended) ------------------------
class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("RaktaSevaPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUser(user: User) { prefs.edit().putString("user", gson.toJson(user)).apply() }
    fun getUser(): User? { val json = prefs.getString("user", null) ?: return null; return gson.fromJson(json, User::class.java) }
    fun setLoggedIn(isLoggedIn: Boolean) { prefs.edit().putBoolean("isLoggedIn", isLoggedIn).apply() }
    fun isLoggedIn(): Boolean = prefs.getBoolean("isLoggedIn", false)

    // 90-day cooldown functions
    fun saveLastDonationTime(timestamp: Long) {
        prefs.edit().putLong("lastDonationTime", timestamp).apply()
    }
    fun getLastDonationTime(): Long = prefs.getLong("lastDonationTime", 0L)
    fun isInCooldown(): Boolean {
        val last = getLastDonationTime()
        if (last == 0L) return false
        val daysSince = (System.currentTimeMillis() - last) / (24 * 60 * 60 * 1000)
        return daysSince < 90
    }
    fun getDaysRemaining(): Int {
        val last = getLastDonationTime()
        if (last == 0L) return 0
        val daysSince = (System.currentTimeMillis() - last) / (24 * 60 * 60 * 1000)
        return max(0, 90 - daysSince.toInt())
    }

    // Last time donor viewed requests (to show new request badge)
    fun setLastRequestViewTime(time: Long) {
        prefs.edit().putLong("lastRequestView", time).apply()
    }
    fun getLastRequestViewTime(): Long = prefs.getLong("lastRequestView", 0L)
}

// ------------------------ Main Activity ------------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaktaSevaConnectTheme(darkTheme = isSystemInDarkTheme()) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(navController, this)
                }
            }
        }
    }
}

// ------------------------ Navigation ------------------------
@Composable
fun AppNavGraph(navController: NavHostController, context: Context) {
    NavHost(
        navController = navController,
        startDestination = "splash",
        enterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { it } },
        exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { -it } },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideInHorizontally { -it } },
        popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutHorizontally { it } }
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController, context) }
        composable("register") { RegistrationScreen(navController, context) }
        composable("home") { HomeScreen(navController, context) }
        composable("findDonors") { FindDonorsScreen() }
        composable("emergency") { EmergencyRequestScreen(navController) }
        composable("history") { RequestHistoryScreen() }
        composable("profile") { MyProfileScreen(context, navController) }
    }
}

// ------------------------ Splash Screen ------------------------
@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnim by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val alpha = animateFloatAsState(
        targetValue = if (startAnim) 1f else 0f,
        animationSpec = tween(1000)
    )
    LaunchedEffect(Unit) {
        startAnim = true
        delay(2500)
        navController.popBackStack()
        navController.navigate("login")
    }
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha.value)) {
            Text("🩸", fontSize = 100.sp, color = Color.White, modifier = Modifier.scale(scale))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Rakta-Seva Connect", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text("Blood Donation Network", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
        }
    }
}

// ------------------------ Login Screen ------------------------
@Composable
fun LoginScreen(navController: NavHostController, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val prefs = PreferencesManager(context)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp), singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation(), singleLine = true
        )
        Spacer(modifier = Modifier.height(24.dp))

        AnimatedButton(onClick = {
            when {
                email.isBlank() || password.isBlank() -> Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                else -> {
                    val user = prefs.getUser()
                    if (user != null && user.email == email && user.password == password) {
                        prefs.setLoggedIn(true)
                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                        navController.navigate("home") { popUpTo("login") { inclusive = true } }
                    } else Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }, text = "Login")

        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text("Create New Account", color = MaterialTheme.colorScheme.primary)
        }
    }
}

// ------------------------ Registration Screen ------------------------
@Composable
fun RegistrationScreen(navController: NavHostController, context: Context) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("A+") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
    val prefs = PreferencesManager(context)
    var showDropdown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create Account", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = bloodGroup,
            onValueChange = {},
            readOnly = true,
            label = { Text("Blood Group") },
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            enabled = false
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { showDropdown = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Select Blood Group")
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
        Spacer(modifier = Modifier.height(24.dp))

        AnimatedButton(onClick = {
            if (name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank() || location.isBlank()) {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(name, email, password, bloodGroup, phone, location)
                prefs.saveUser(user)
                Toast.makeText(context, "Registered! Please login", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }, text = "Register")
    }

    if (showDropdown) {
        Dialog(onDismissRequest = { showDropdown = false }) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    bloodGroups.forEach { group ->
                        TextButton(
                            onClick = {
                                bloodGroup = group
                                showDropdown = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(group, modifier = Modifier.fillMaxWidth())
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

// ------------------------ Home Screen (with badge for new requests) ------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, context: Context) {
    val prefs = PreferencesManager(context)
    val user = prefs.getUser()
    val isDonor = true  // In a real app, you'd check user?.role, but here we keep as donor for demo
    // For simplicity, we treat all users as donors for the badge demo.
    // If you want separate roles, uncomment and use `user?.role == "Donor"`

    // Count new requests (those added after last view time)
    val lastViewTime = prefs.getLastRequestViewTime()
    val newRequestsCount = remember(RequestHistoryRepository.requests) {
        RequestHistoryRepository.requests.count { request ->
            // Parse timestamp of request: format "dd/MM/yyyy HH:mm"
            try {
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val requestTime = sdf.parse(request.timestamp)?.time ?: 0L
                requestTime > lastViewTime
            } catch (e: Exception) { false }
        }
    }

    val menuItems = mutableListOf<Triple<String, androidx.compose.ui.graphics.vector.ImageVector, String>>()
    menuItems.add(Triple("Find Donors", Icons.Default.Search, "findDonors"))
    menuItems.add(Triple("Emergency Request", Icons.Default.Warning, "emergency"))
    // Request History with badge
    menuItems.add(Triple("Request History", Icons.Default.History, "history"))
    menuItems.add(Triple("My Profile", Icons.Default.Person, "profile"))
    menuItems.add(Triple("Logout", Icons.Default.Logout, "logout"))

    Scaffold(topBar = { TopAppBar(title = { Text("Rakta-Seva Connect", color = MaterialTheme.colorScheme.primary) }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome!", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(24.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(menuItems) { (title, icon, route) ->
                    Card(
                        modifier = Modifier.fillMaxWidth().aspectRatio(1f).clip(RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        onClick = {
                            if (route == "logout") {
                                prefs.setLoggedIn(false)
                                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                                navController.navigate("login") { popUpTo("home") { inclusive = true } }
                            } else navController.navigate(route)
                        }
                    ) {
                        Box {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                                Icon(icon, contentDescription = title, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                            }
                            // Badge for new requests (only on Request History button)
                            if (title == "Request History" && newRequestsCount > 0) {
                                Badge(
                                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                                    containerColor = MaterialTheme.colorScheme.error
                                ) {
                                    Text(text = "$newRequestsCount", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ------------------------ Find Donors Screen (unchanged) ------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindDonorsScreen() {
    val context = LocalContext.current
    var selectedBloodGroup by remember { mutableStateOf("All") }
    val donors = DummyData.donors
    val filtered = if (selectedBloodGroup == "All") donors else donors.filter { it.bloodGroup == selectedBloodGroup }
    val groups = listOf("All") + listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
    var showFilterDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text("Find Donors") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Filter by Blood Group", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = selectedBloodGroup,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = false
            )
            Button(onClick = { showFilterDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
                Text("Change Filter")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filtered) { donor ->
                    AnimatedCardEnter {
                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Column {
                                    Text(donor.name, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                                    Text("${donor.bloodGroup} | ${donor.location}", style = MaterialTheme.typography.bodyMedium)
                                    Text(if (donor.isAvailable) "✅ Available" else "❌ Not Available", style = MaterialTheme.typography.bodySmall, color = if (donor.isAvailable) Color.Green else Color.Red)
                                }
                                Button(onClick = { Toast.makeText(context, "Calling ${donor.name}", Toast.LENGTH_SHORT).show() }, shape = RoundedCornerShape(12.dp)) {
                                    Icon(Icons.Default.Call, null, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Call")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showFilterDialog) {
        Dialog(onDismissRequest = { showFilterDialog = false }) {
            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    groups.forEach { group ->
                        TextButton(onClick = { selectedBloodGroup = group; showFilterDialog = false }, modifier = Modifier.fillMaxWidth()) {
                            Text(group, modifier = Modifier.fillMaxWidth())
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

// ------------------------ Emergency Request Screen (unchanged) ------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyRequestScreen(navController: NavHostController) {
    val context = LocalContext.current
    var patientName by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("A+") }
    var hospitalName by remember { mutableStateOf("") }
    var unitsRequired by remember { mutableStateOf("") }
    var urgency by remember { mutableStateOf("Normal") }
    var showDialog by remember { mutableStateOf(false) }
    var showBloodDialog by remember { mutableStateOf(false) }
    var showUrgencyDialog by remember { mutableStateOf(false) }
    val bloodGroups = listOf("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")
    val urgencyLevels = listOf("Normal", "Urgent", "Emergency")

    Scaffold(topBar = { TopAppBar(title = { Text("Emergency Request") }) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(value = patientName, onValueChange = { patientName = it }, label = { Text("Patient Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = bloodGroup, onValueChange = {}, readOnly = true, label = { Text("Blood Group") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
            Button(onClick = { showBloodDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Blood Group") }
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = hospitalName, onValueChange = { hospitalName = it }, label = { Text("Hospital Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = unitsRequired, onValueChange = { unitsRequired = it }, label = { Text("Units Required") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = urgency, onValueChange = {}, readOnly = true, label = { Text("Urgency") }, trailingIcon = { Icon(Icons.Default.ArrowDropDown, null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), enabled = false)
            Button(onClick = { showUrgencyDialog = true }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) { Text("Select Urgency") }
            Spacer(modifier = Modifier.height(24.dp))

            AnimatedButton(onClick = {
                if (patientName.isBlank() || hospitalName.isBlank() || unitsRequired.isBlank()) {
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    val request = EmergencyRequest(
                        patientName, bloodGroup, hospitalName,
                        unitsRequired.toIntOrNull() ?: 1, urgency, "Pending",
                        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                    )
                    RequestHistoryRepository.addRequest(request)
                    showDialog = true
                }
            }, text = "Submit Emergency Request", colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error))
        }
    }

    if (showBloodDialog) {
        Dialog(onDismissRequest = { showBloodDialog = false }) {
            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Blood Group", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    bloodGroups.forEach { group ->
                        TextButton(onClick = { bloodGroup = group; showBloodDialog = false }, modifier = Modifier.fillMaxWidth()) {
                            Text(group, modifier = Modifier.fillMaxWidth())
                        }
                        Divider()
                    }
                }
            }
        }
    }

    if (showUrgencyDialog) {
        Dialog(onDismissRequest = { showUrgencyDialog = false }) {
            Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Select Urgency", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    urgencyLevels.forEach { level ->
                        TextButton(onClick = { urgency = level; showUrgencyDialog = false }, modifier = Modifier.fillMaxWidth()) {
                            Text(level, modifier = Modifier.fillMaxWidth())
                        }
                        Divider()
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Request Submitted") },
            text = { Text("Your emergency request has been submitted.") },
            confirmButton = { TextButton(onClick = { showDialog = false; navController.popBackStack() }) { Text("OK") } }
        )
    }
}

// ------------------------ Request History Screen (updates last view time on open) ------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestHistoryScreen() {
    val context = LocalContext.current
    val prefs = PreferencesManager(context)
    val requests = RequestHistoryRepository.requests

    // When this screen opens, update last view time to the current moment
    LaunchedEffect(Unit) {
        prefs.setLastRequestViewTime(System.currentTimeMillis())
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Request History") }) }) { padding ->
        if (requests.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No requests yet", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(requests.reversed()) { req ->
                    AnimatedCardEnter {
                        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(req.patientName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                                Text("Blood: ${req.bloodGroup} | Hospital: ${req.hospitalName}", style = MaterialTheme.typography.bodyMedium)
                                Text("Units: ${req.unitsRequired} | Urgency: ${req.urgency}", style = MaterialTheme.typography.bodyMedium)
                                Text("Status: ${req.status}", style = MaterialTheme.typography.bodySmall, color = Color(0xFFFF9800))
                                Text(req.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ------------------------ My Profile Screen (with 90‑day counter, unchanged) ------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(context: Context, navController: NavHostController) {
    val prefs = PreferencesManager(context)
    var user by remember { mutableStateOf(prefs.getUser()) }
    var isAvailable by remember { mutableStateOf(user?.isAvailable ?: false) }
    var daysRemaining by remember { mutableStateOf(prefs.getDaysRemaining()) }
    var inCooldown by remember { mutableStateOf(prefs.isInCooldown()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60_000L)
            daysRemaining = prefs.getDaysRemaining()
            inCooldown = prefs.isInCooldown()
            if (!inCooldown && user != null && user!!.isAvailable == false) {
                user = user!!.copy(isAvailable = true)
                prefs.saveUser(user!!)
                isAvailable = true
                Toast.makeText(context, "You are now eligible to donate again!", Toast.LENGTH_LONG).show()
            }
        }
    }

    Scaffold(topBar = { TopAppBar(title = { Text("My Profile") }) }) { padding ->
        if (user == null) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No user data. Please login again.")
            }
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(50.dp)).background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(user!!.name.take(1).uppercase(), fontSize = 40.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(24.dp))

                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(4.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        ProfileRow("Name", user!!.name)
                        ProfileRow("Email", user!!.email)
                        ProfileRow("Blood Group", user!!.bloodGroup)
                        ProfileRow("Phone", user!!.phone)
                        ProfileRow("Location", user!!.location)
                        Divider()

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = if (inCooldown) MaterialTheme.colorScheme.errorContainer
                                else MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                if (inCooldown) {
                                    Text(
                                        text = "⏳ Cooldown Period",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "You can donate again in $daysRemaining days.",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    LinearProgressIndicator(
                                        progress = { (90f - daysRemaining) / 90f },
                                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                                        color = MaterialTheme.colorScheme.primary,
                                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                                    )
                                } else {
                                    Text(
                                        text = "✅ You are eligible to donate!",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                if (!inCooldown) {
                                    prefs.saveLastDonationTime(System.currentTimeMillis())
                                    inCooldown = true
                                    daysRemaining = 90
                                    isAvailable = false
                                    user = user!!.copy(isAvailable = false)
                                    prefs.saveUser(user!!)
                                    Toast.makeText(context, "Donation recorded! You will be unavailable for 90 days.", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(context, "You are still in cooldown. Wait $daysRemaining more days.", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(if (inCooldown) "Donation Already Recorded" else "Record Donation (I donated today)")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Available for Donation", style = MaterialTheme.typography.bodyLarge)
                            Switch(
                                checked = isAvailable,
                                onCheckedChange = { newValue ->
                                    if (inCooldown) {
                                        Toast.makeText(context, "You cannot become available during the 90‑day cooldown.", Toast.LENGTH_SHORT).show()
                                    } else {
                                        isAvailable = newValue
                                        user = user!!.copy(isAvailable = newValue)
                                        prefs.saveUser(user!!)
                                    }
                                },
                                enabled = !inCooldown
                            )
                        }
                        if (inCooldown) {
                            Text(
                                text = "🔒 Cooldown active – availability locked",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                AnimatedButton(onClick = { navController.navigateUp() }, text = "Back to Home")
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

// ------------------------ Reusable Animated Components ------------------------
@Composable
fun AnimatedButton(
    onClick: () -> Unit,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().scale(scale),
        shape = RoundedCornerShape(12.dp),
        colors = colors,
        interactionSource = interactionSource
    ) {
        Text(text, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun AnimatedCardEnter(content: @Composable () -> Unit) {
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )
    Box(modifier = Modifier.alpha(alpha)) {
        content()
    }
}

// ------------------------ Theme (Dark/Light) ------------------------
@Composable
fun RaktaSevaConnectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFFF5252),
            onPrimary = Color.Black,
            primaryContainer = Color(0xFFB71C1C),
            onPrimaryContainer = Color(0xFFFFCDD2),
            secondary = Color(0xFFFF7961),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E),
            error = Color(0xFFCF6679)
        )
    } else {
        lightColorScheme(
            primary = Color(0xFFD32F2F),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFFFCDD2),
            onPrimaryContainer = Color(0xFFB71C1C),
            secondary = Color(0xFFF44336),
            background = Color.White,
            surface = Color.White,
            error = Color(0xFFD32F2F)
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}