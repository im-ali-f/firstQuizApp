package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ScoreComp
import com.example.quizapp.VMs.Room.db
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.ui.theme.labelColor
import com.example.quizapp.ui.theme.mainBGCColor
import com.example.whatsapp.VMs.API.MainViewModel
import com.example.whatsapp.VMs.API.QuizVM
import com.example.whatsapp.VMs.API.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navState = rememberNavController()
                    val owner = this
                    val contex = LocalContext.current
                    val db = db.getInstance(contex)
                    val repo = Repository(db)
                    val viewModel = MainViewModel(repo)
                    val model = QuizVM(viewModel,this , navController = navState )

                    NavHost(navController = navState, startDestination = "signupPage" ){
                        composable(route="loginPage"){
                            LoginComp(navController = navState, model = model)
                        }
                        composable(route = "signupPage"){
                            SignupComp(navController = navState, model = model )
                        }
                        composable(route = "homePage"){
                            HomePage(navController = navState, model =model , mainViewModel =viewModel , owner = owner)
                        }
                        composable(route = "questionPage"){
                            BackHandler(true) {
                                // do nothing
                            }
                            QuestionComp(navController = navState, model =model, mainViewModel =viewModel , owner = owner)
                        }
                        composable(route = "showScorePage"){
                            BackHandler(true) {
                                // do nothing
                            }
                            ScoreComp(navState,model)
                        }
                    }
                }
            }
        }
    }
}
