package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraIMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculadoraIMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    var pou by remember {
        mutableStateOf("")
    }
    Column( modifier = modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        //header
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(color = colorResource(R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "musculos",
                modifier = Modifier.size(80.dp)
                    .padding(vertical = 16.dp)
            )
            Text(text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
        //form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Seus dados", fontSize = 23.sp, fontWeight = FontWeight.Bold, color = colorResource(R.color.cor_app))
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(width = 1.dp,color = colorResource(R.color.cor_app), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)),
                        value = altura,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                        placeholder = {Text(text = "Insira uma altura")},
                        onValueChange = {
                            escrito ->
                            altura = escrito
                        }
                    )
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(width = 1.dp,color = colorResource(R.color.cor_app), RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)),
                        value = peso,
                        placeholder = { Text(text = "Peso") },
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
                        onValueChange = {
                            escrito2 ->
                            peso = escrito2
                        }
                    )
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.cor_app)),
                        onClick = {
                            imc = peso.toDouble() / (Math.pow((altura.toDouble() / 100), 2.0))
                            pou = "%.2f".format(imc)
                        }
                    ) {
                        Text(text = "CALCULAR")
                    }

                }
            }

        //result
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = CardDefaults.cardColors(containerColor = if (imc < 18.5){
                    Color.Green
                }else if(imc > 18.5 && imc < 25){
                    Color.Yellow
                }else{
                    Color.Red
                }),
                elevation = CardDefaults.cardElevation(4.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imc < 18.5){
                        Text(text = "$pou Abaixo do peso", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }else if (imc > 18.5 && imc < 25){
                        Text(text = "$pou Peso ideal", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }else if (imc >= 25 && imc < 30){
                        Text(text = "$pou Levemente acima do peso", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }else if (imc >= 30 && imc < 35){
                        Text(text = "$pou Obesidade grau |", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }else if (imc >= 35 && imc < 40){
                        Text(text = "$pou Obesidade grau ||", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }else if(imc > 40){
                        Text(text = "$pou Obesidade grau |||", fontSize = 27.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier
                .height(30.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.cor_app)),
                onClick = {
                    peso = ""
                    altura = ""
                }
            ) {
                Text(text = "APAGAR")
            }
        }


    }
}


