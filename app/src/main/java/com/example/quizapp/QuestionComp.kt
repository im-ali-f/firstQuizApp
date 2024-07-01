package com.example.quizapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.VMs.Room.RoomModel
import com.example.quizapp.ui.theme.mainFontColor
import com.example.whatsapp.VMs.API.MainViewModel
import com.example.whatsapp.VMs.API.QuizVM
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun QuestionComp(
    navController: NavController,
    model: QuizVM,
    mainViewModel: MainViewModel,
    owner: LifecycleOwner
) {
    var showPopUp by remember {
        mutableStateOf(true)
    }
    val questionList = remember {
        mutableStateOf(emptyList<RoomModel>())
    }
    mainViewModel.GetallQuestions()
    LaunchedEffect(Unit) {
        showPopUp = true
        delay(3000)
        showPopUp = false
    }


    mainViewModel.GetAllQuestionsResponse.observe(owner, Observer { response ->
        Log.d("TAG", "GetAllQuestionsFunctionallity:started ")
        val listToSend = mutableListOf<RoomModel>()
        response.forEach { model ->
            listToSend.add(model)
        }
        questionList.value = listToSend
    })

    if (questionList.value.size >= 10) {

        val randomElements =
            remember { questionList.value.shuffled(Random(System.currentTimeMillis())).take(10) }


        var questionCounter by remember {
            mutableStateOf(0)
        }
        Log.d("TAG $questionCounter", "QuestionComp: started")
        if (showPopUp) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Loading !",
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = mainFontColor
                )
            }
        }
        else {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                val correctBrush = Brush.linearGradient(
                    listOf(
                        Color(0xFF5DC039),
                        Color(0xFFE6E1E0)
                    )
                )
                val wrongBrush = Brush.linearGradient(
                    listOf(
                        Color(0xFF94284B),
                        Color(0xFFE6E1E0)
                    )
                )
                val unselectedBrush = Brush.linearGradient(
                    listOf(
                        Color(0xFF737FA3),
                        Color(0xFFE6E1E0)
                    )
                )

                var selectedBTN by remember {
                    mutableStateOf(0)
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Question :${questionCounter + 1}",
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        color = mainFontColor
                    )



                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, mainFontColor, RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        text = "${randomElements[questionCounter].text}",
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = mainFontColor
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    //ans 1
                    Button(
                        onClick = {
                            if (selectedBTN == 0) {
                                selectedBTN = 1
                                if (randomElements[questionCounter].correctAnswer == "1") {
                                    model.correctAns.value += 1
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(if (selectedBTN == 0) unselectedBrush else if (selectedBTN == 1 || randomElements[questionCounter].correctAnswer == "1") if (randomElements[questionCounter].correctAnswer == "1") correctBrush else wrongBrush else unselectedBrush),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            modifier = Modifier.offset(y = -5.dp),
                            textAlign = TextAlign.Center,
                            text = "گزینه اول : ${randomElements[questionCounter].answer1} ",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //ans 2
                    Button(
                        onClick = {
                            if (selectedBTN == 0) {
                                selectedBTN = 2
                                if (randomElements[questionCounter].correctAnswer == "2") {
                                    model.correctAns.value += 1
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(if (selectedBTN == 0) unselectedBrush else if (selectedBTN == 2 || randomElements[questionCounter].correctAnswer == "2") if (randomElements[questionCounter].correctAnswer == "2") correctBrush else wrongBrush else unselectedBrush),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            modifier = Modifier.offset(y = -5.dp),
                            textAlign = TextAlign.Center,
                            text = "گزینه دوم : ${randomElements[questionCounter].answer2} ",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //ans 3
                    Button(
                        onClick = {
                            if (selectedBTN == 0) {
                                selectedBTN = 3
                                if (randomElements[questionCounter].correctAnswer == "3") {
                                    model.correctAns.value += 1
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(if (selectedBTN == 0) unselectedBrush else if (selectedBTN == 3 || randomElements[questionCounter].correctAnswer == "3") if (randomElements[questionCounter].correctAnswer == "3") correctBrush else wrongBrush else unselectedBrush),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            modifier = Modifier.offset(y = -5.dp),
                            textAlign = TextAlign.Center,
                            text = "گزینه سوم : ${randomElements[questionCounter].answer3} ",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    //ans 4
                    Button(
                        onClick = {
                            if (selectedBTN == 0) {
                                selectedBTN = 4
                                if (randomElements[questionCounter].correctAnswer == "4") {
                                    model.correctAns.value += 1
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(if (selectedBTN == 0) unselectedBrush else if (selectedBTN == 4 || randomElements[questionCounter].correctAnswer == "4") if (randomElements[questionCounter].correctAnswer == "4") correctBrush else wrongBrush else unselectedBrush),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            modifier = Modifier.offset(y = -5.dp),
                            textAlign = TextAlign.Center,
                            text = "گزینه چهار : ${randomElements[questionCounter].answer4} ",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            if (questionCounter < 9) {
                                questionCounter += 1
                            } else {
                                showPopUp = true
                                navController.navigate("showScorePage")
                            }

                            selectedBTN = 0
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(60.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(

                        )
                    ) {
                        Text(
                            modifier = Modifier.offset(y = -5.dp),
                            textAlign = TextAlign.Center,
                            text = "next",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    }

                }
            }
        }
    } else {
        model.add10Question()
        if (showPopUp) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Loading !",
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = mainFontColor
                )
            }
        }
        else{
            navController.navigate("questionPage")
        }
        /*
        else{
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "10 ta soal nadarim !",
                    color = mainFontColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        navController.navigate("homePage")
                        model.correctAns.value = 0
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1E88E5)
                    )
                ) {
                    Text(
                        text = "back home",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }


        }
    */
    }


}
