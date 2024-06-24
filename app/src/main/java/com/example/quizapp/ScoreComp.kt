package com.example.quizapp

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsapp.VMs.API.QuizVM

@Composable
fun ScoreComp(navController: NavController,model:QuizVM) {
    Column {
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

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "total Score ${model.correctAns.value} /10 ",
                fontWeight = FontWeight(600),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate("homePage")
                    model.correctAns.value = 0
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
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
                    text = "back home",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600)
                )
            }
        }


    }

}