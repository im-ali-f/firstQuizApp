package com.example.whatsapp.VMs.API

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.quizapp.VMs.Room.RoomModel
import kotlin.random.Random

class QuizVM(
    private val mainViewModel: MainViewModel,
    private val owner: LifecycleOwner,
    private val navController: NavController
) : ViewModel() {

    val canContinue = mutableStateOf(false)
    val canContinue2 = mutableStateOf(false)
    val userpassError = mutableStateOf(false)
    var userExsitanceError = mutableStateOf(false)
    val passwordError = mutableStateOf(false)
    val enteredName = mutableStateOf("")
    val enteredPass = mutableStateOf("")

    val enteredName2 = mutableStateOf("")
    val enteredPass2 = mutableStateOf("")
    val enteredRePass = mutableStateOf("")
    val enteredfirstName = mutableStateOf("")
    val enteredlastName = mutableStateOf("")
    val enteredRole = mutableStateOf("member")
    val passwordVisible = mutableStateOf(false)
    val passwordVisible2 = mutableStateOf(false)


     val loggedInUser = mutableStateOf(
         UserInfoResponseListItem(
             role = "",
             id = "",
             password = "",
             name = "",
             firstname = "",
             lastname = ""
         )
     )


    //for test
    /*
    val loggedInUser = mutableStateOf(
        UserInfoResponseListItem(
            //role = "member",
            role = "admin",
            id = "1",
            password = "123",
            name = "ali",
            firstname = "ali",
            lastname = "farhad"
        )
    )

     */


    fun LoginFunctionallity() {
        mainViewModel.GetUsersList()
        mainViewModel.viewModelGetUsersListResponse.observe(owner, Observer { response ->
            if (response.isSuccessful) {

                Log.d("SuccessfulResponse", response.body().toString())
                response.body()?.forEach { item ->
                    if (item.name == enteredName.value) {
                        if (item.password == enteredPass.value) {
                            loggedInUser.value = item
                            navigateToHome()
                        }
                    }

                }
                if (loggedInUser.value.name == "") {
                    userpassError.value = true

                }

                mainViewModel.viewModelGetUsersListResponse = MutableLiveData()

            } else {
                Log.d("errorResponse", response.errorBody()?.string() as String)
            }
        })
    }
    fun LogoutFunctionallity() {
        loggedInUser.value =
            UserInfoResponseListItem(
                role = "",
                id = "",
                password = "",
                name = "",
                firstname = "",
                lastname = ""
            )
        canContinue.value = false
        canContinue2.value = false
        userpassError.value = false
        userExsitanceError.value = false
        passwordError.value = false
        enteredName.value = ""
        enteredPass.value = ""

        enteredName2.value = ""
        enteredPass2.value = ""
        enteredRePass.value = ""
        enteredfirstName.value = ""
        enteredlastName.value =""
        enteredRole.value ="member"
        passwordVisible.value = false
        passwordVisible2.value = false
        navController.navigate("signupPage")

    }

    fun SignupFunctionallity() {
        Log.d("TAG", "SignupFunctionallity: used ")

        if (enteredPass2.value == enteredRePass.value) {
            Log.d("TAG", "SignupFunctionallity: started ")
            mainViewModel.GetUsersList()
            mainViewModel.viewModelGetUsersListResponse.observe(owner, Observer { response ->
                if (response.isSuccessful) {
                    var userExistance = false
                    Log.d("SuccessfulResponse", response.body().toString())
                    response.body()?.forEach { item ->
                        if (item.name == enteredName2.value) {
                            userExsitanceError.value = true
                            userExistance = true
                        }

                    }
                    if (userExistance == false) {
                        userExsitanceError.value = false
                        //inja user bsaze
                        val bodyToSendAPI = UserInfoResponseListItem(
                            name = enteredName2.value,
                            password = enteredPass2.value,
                            id = "",
                            role = enteredRole.value,
                            firstname = enteredfirstName.value,
                            lastname = enteredlastName.value
                        )
                        mainViewModel.CreateUser(bodyToSendAPI)
                        mainViewModel.viewModelCreateUserResponse.observe(
                            owner,
                            Observer { response ->
                                if (response.isSuccessful) {

                                    Log.d("SuccessfulResponse", response.body().toString())

                                    navController.navigate("loginPage")

                                    mainViewModel.viewModelCreateUserResponse = MutableLiveData()

                                } else {
                                    Log.d("errorResponse", response.errorBody()?.string() as String)
                                }
                            })
                    }
                    mainViewModel.viewModelGetUsersListResponse = MutableLiveData()

                } else {
                    Log.d("errorResponse", response.errorBody()?.string() as String)
                }
            })
        } else {
            passwordError.value = true
        }


    }

    fun add10Question(){
        var counter = 1
        while(counter <= 10){
            val bodyToSend = RoomModel(
                id = 0,
                text = "default question number ${counter}",
                correctAnswer = "${Random.nextInt(1, 5)}",
                answer1 = "ans 1",
                answer2 = "ans 2",
                answer3 = "ans 3",
                answer4 = "ans 4"
            )
            mainViewModel.InsertQuestion(bodyToSend)
            counter +=1
        }

    }

    fun checkToContinue() {
        if (enteredPass.value != "" && enteredName.value != "") {
            canContinue.value = true
        } else {
            canContinue.value = false
        }
    }

    fun checkToContinue2() {
        if (enteredPass2.value != "" && enteredName2.value != "" && enteredRePass.value != "") {
            canContinue2.value = true
        } else {
            canContinue2.value = false
        }
    }

    fun navigateToHome() {
        if (loggedInUser.value.name != "") {
            navController.navigate("homePage")
        }
    }

    //admin

    val adminText = mutableStateOf("")
    val adminAns1 = mutableStateOf("")
    val adminAns2 = mutableStateOf("")
    val adminAns3 = mutableStateOf("")
    val adminAns4 = mutableStateOf("")
    val adminCorrectAns = mutableStateOf("")


    fun InsertQuestion() {
        if (
            adminText.value != "" &&
            adminAns1.value != "" &&
            adminAns2.value != "" &&
            adminAns3.value != "" &&
            adminAns4.value != "" &&
            adminCorrectAns.value != ""
        ) {

            val bodyToSend = RoomModel(
                id = 0,
                text = adminText.value,
                correctAnswer = adminCorrectAns.value,
                answer1 = adminAns1.value,
                answer2 = adminAns2.value,
                answer3 = adminAns3.value,
                answer4 = adminAns4.value
            )
            mainViewModel.InsertQuestion(bodyToSend)



            adminText.value = ""
            adminAns1.value = ""
            adminAns2.value = ""
            adminAns3.value = ""
            adminAns4.value = ""
            adminCorrectAns.value = ""




        }


    }

    //answar
    val correctAns = mutableStateOf(0)


    /*
     fun UpdatePDF(body:RoomModel){

         mainViewModel.UpdatePDF(body)
     }

      */


}