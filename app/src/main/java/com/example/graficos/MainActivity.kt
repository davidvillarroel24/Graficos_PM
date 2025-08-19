package com.example.graficos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.graficos.ui.theme.GraficosTheme
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            graficoTorta()


        }
    }
}

@Composable
fun graficoTorta(){
    val data:List<Pie> by remember { mutableStateOf(listOf(
        Pie("IOs",13.78,Color.Red, Color.Green),
        Pie("android",72.13, Color.Green, Color.DarkGray)

    ))}
    PieChart(modifier = Modifier.fillMaxSize(),
    data=data,)
}

@Preview(showBackground = true)
@Composable
fun GraficoTortaPreview() {
    graficoTorta()
}
