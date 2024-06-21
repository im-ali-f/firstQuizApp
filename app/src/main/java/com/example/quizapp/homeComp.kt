package com.example.quizapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.ui.theme.adminAddColor
import com.example.quizapp.ui.theme.adminPanel
import com.example.quizapp.ui.theme.disabledText
import com.example.quizapp.ui.theme.enabledText
import com.example.quizapp.ui.theme.labelColor
import com.example.quizapp.ui.theme.mainBGCColor
import com.example.whatsapp.VMs.API.MainViewModel
import com.example.whatsapp.VMs.API.QuizVM

@Composable
fun HomePage(
    navController: NavController,
    model: QuizVM,
    mainViewModel: MainViewModel,
    owner: LifecycleOwner
) {

    val navControllerInner = rememberNavController()
    NavHost(navController = navControllerInner, startDestination = "homePage") {
        composable("homePage") {
            if (model.loggedInUser.value.role == "member") {
                val navStateInner = rememberNavController()
                NavHost(navController = navStateInner, startDestination = "homePage") {
                    composable("homePage") {
                        Column(Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(0, 0, 40, 40))
                                    //.background(mainGray)
                                    .padding(end = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                //horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .padding(start = 5.dp, end = 5.dp)
                                ) {
                                    Column(Modifier.padding(top = 10.dp)) {
                                        Text(
                                            text = "${model.loggedInUser.value.firstname} ${model.loggedInUser.value.lastname}",
                                            fontWeight = FontWeight(600),
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color.White
                                        )
                                        //seperator
                                        Spacer(modifier = Modifier.height(10.dp))

                                        Box(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .height(1.dp)
                                                    .background(Color.White)
                                                    .fillMaxWidth()
                                            )


                                        }
                                    }


                                }

                            }
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                                val scrollState = rememberScrollState()
                                Column(
                                    Modifier
                                        .fillMaxSize().padding(20.dp)
                                        .verticalScroll(scrollState),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val brushBTN = Brush.linearGradient(
                                        listOf(
                                            Color(0xFF330885),
                                            Color(0xFFDD3416)
                                        )
                                    )
                                    Button(
                                        onClick = {
                                            model.checkToContinue()
                                            navController.navigate("questionPage")
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp)
                                            .clip(RoundedCornerShape(5.dp))
                                            .background(brushBTN),
                                        contentPadding = PaddingValues(0.dp),
                                        shape = RoundedCornerShape(5.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent
                                        )
                                    ) {
                                        Text(
                                            modifier = Modifier.offset(y = -5.dp),
                                            textAlign = TextAlign.Center,
                                            text = "شروع!",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight(600)
                                        )
                                    }

                                }
                            }
                        }
                    }
                }

            } else {

                Column(
                    Modifier
                        .fillMaxSize()
                        .background(adminPanel)
                ) {
                    //upper Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(0, 0, 40, 40))
                            //.background(mainGray)
                            .padding(end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        //horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(start = 5.dp, end = 5.dp)
                        ) {
                            Column(Modifier.padding(top = 10.dp)) {
                                Text(
                                    text = "${model.loggedInUser.value.firstname} ${model.loggedInUser.value.lastname}",
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                                //seperator
                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(1.dp)
                                            .background(Color.White)
                                            .fillMaxWidth()
                                    )


                                }
                            }


                        }


                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        var scroolState = rememberScrollState()
                        Column(
                            Modifier
                                .fillMaxSize()
                                .verticalScroll(scroolState)
                                .padding(16.dp)
                        )
                        {//start admin quiz adder


                            //question text
                            Text(
                                text = "متن سوال را وارد کنید",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    // unfocusedIndicatorColor = Color.Transparent

                                ),
                                placeholder = {
                                    Text(
                                        text = "متن سوال",
                                        color = Color.White,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminText.value,
                                onValueChange = { new -> model.adminText.value = new }
                            )
                            Spacer(modifier = Modifier.height(40.dp))
                            //ans 1
                            Text(
                                text = "گزینه شماره 1",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    // unfocusedIndicatorColor = Color.Transparent

                                ),
                                placeholder = {
                                    Text(
                                        text = "گزینه 1",
                                        color = Color.White,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminAns1.value,
                                onValueChange = { new -> model.adminAns1.value = new }
                            )
                            //ans 2
                            Text(
                                text = "گزینه شماره 2",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    // unfocusedIndicatorColor = Color.Transparent

                                ),
                                placeholder = {
                                    Text(
                                        text = "گزینه 2",
                                        color = Color.White,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminAns2.value,
                                onValueChange = { new -> model.adminAns2.value = new }
                            )
                            //ans 3
                            Text(
                                text = "گزینه شماره 3",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    // unfocusedIndicatorColor = Color.Transparent

                                ),
                                placeholder = {
                                    Text(
                                        text = "گزینه 3",
                                        color = Color.White,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminAns3.value,
                                onValueChange = { new -> model.adminAns3.value = new }
                            )
                            //ans 4
                            Text(
                                text = "گزینه شماره 4",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedIndicatorColor = Color.Transparent,
                                    // unfocusedIndicatorColor = Color.Transparent

                                ),
                                placeholder = {
                                    Text(
                                        text = "گزینه 4",
                                        color = Color.White,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminAns4.value,
                                onValueChange = { new -> model.adminAns4.value = new }
                            )

                            Spacer(modifier = Modifier.height(40.dp))
                            //correct ans
                            Text(
                                text = "گزینه صحیح :",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { model.adminCorrectAns.value = "1" }
                                        .padding(5.dp),
                                    text = "گزینه 1",
                                    fontWeight = FontWeight(600),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    color = if (model.adminCorrectAns.value == "1") Color.Green else Color.White
                                )

                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { model.adminCorrectAns.value = "2" }
                                        .padding(5.dp),
                                    text = "گزینه 2",
                                    fontWeight = FontWeight(600),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    color = if (model.adminCorrectAns.value == "2") Color.Green else Color.White
                                )

                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { model.adminCorrectAns.value = "3" }
                                        .padding(5.dp),
                                    text = "گزینه 3",
                                    fontWeight = FontWeight(600),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    color = if (model.adminCorrectAns.value == "3") Color.Green else Color.White
                                )

                                Text(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { model.adminCorrectAns.value = "4" }
                                        .padding(5.dp),
                                    text = "گزینه 4",
                                    fontWeight = FontWeight(600),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    color = if (model.adminCorrectAns.value == "4") Color.Green else Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(25.dp))
                            Button(
                                onClick = {
                                    model.InsertQuestion()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(adminAddColor),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Add",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600)
                                )
                            }
                        }
                    }

                }

            }
        }
        composable("specificPage") {
            //SpecificPDF(filename = selectedPDF.value , navControllerInner)
        }
    }


}
