package com.example.quizapp

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizapp.ui.theme.bottomTextColor
import com.example.quizapp.ui.theme.iconColor
import com.example.quizapp.ui.theme.iconColor2
import com.example.quizapp.ui.theme.labelColor
import com.example.quizapp.ui.theme.mainBGCColor
import com.example.whatsapp.VMs.API.QuizVM

@Composable
fun LoginComp(navController: NavController,model: QuizVM) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mainBGCColor)
        ) {
            //main holder
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top =  25.dp , bottom = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Quiz App",
                    fontWeight = FontWeight(800),
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White

                )

            }
            //input 1
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 54.dp, end = 54.dp)
            ) {
                //start big column

                //name
                TextField(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    textStyle = TextStyle(fontWeight = FontWeight(500)),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent

                    ),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = "Username",
                            color = labelColor,
                            fontSize = if (screenWidth < 400) 10.sp else 13.sp,
                            fontWeight = FontWeight(500)
                        )
                    },
                    value = model.enteredName.value,
                    onValueChange = { new -> model.enteredName.value = new })


                //spacer
                Spacer(modifier = Modifier.height(20.dp))
                //pass

                TextField(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    textStyle = TextStyle(fontWeight = FontWeight(500)),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = "Password",
                            color = labelColor,
                            fontSize = if (screenWidth < 400) 10.sp else 13.sp,
                            fontWeight = FontWeight(500)
                        )
                    },
                    value = model.enteredPass.value,
                    onValueChange = { new -> model.enteredPass.value = new },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (model.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { model.passwordVisible.value = !model.passwordVisible.value }) {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
                                tint = iconColor,
                                painter = painterResource(id = if (model.passwordVisible.value) R.drawable.eye else R.drawable.vector),
                                contentDescription = "toggle password show"
                            )

                        }
                    }


                )



                Spacer(modifier = Modifier.height(if(screenWidth<400)30.dp else 60.dp))

                val brushBTN = Brush.linearGradient(listOf(Color(0xFF330885), Color(0xFFDD3416) ))
                val brushBTN2 = Brush.linearGradient(listOf(Color(0xFF330885), Color(0xFFFF3D00)), start = Offset(x=-80f,y=20f), end = Offset(x=1000f,y= 0f))





                Button(
                    onClick = {
                        model.checkToContinue()
                        if(model.canContinue.value){
                            model.LoginFunctionallity()
                        }
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
                        text = "Login",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600)
                    )
                }
                //seperator
                Spacer(modifier = Modifier.height(30.dp) )

                Box (
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)){
                    Box(modifier = Modifier
                        .height(1.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                    )

                }


                //end big column
            }
            //end main holder
        }

        Row (
            Modifier.fillMaxWidth().padding(bottom =if(screenWidth<400)0.dp else 20.dp).align(
                Alignment.BottomCenter), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Text(
                text = "Don’t have an account? ",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight(300)
            )

            Text(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { navController.navigate("signupPage") },
                text = "Sign up",
                color = bottomTextColor,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
    }

}
