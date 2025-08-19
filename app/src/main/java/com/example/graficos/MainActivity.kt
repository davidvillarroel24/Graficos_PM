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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.graficos.ui.theme.GraficosTheme
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.Pie



import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.RowChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line


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
fun grafCircular(){
    var data by remember {
        mutableStateOf(
            listOf(
                Pie(label = "Android", data = 20.0, color = Color.Red, selectedColor = Color.Green),
                Pie(label = "Windows", data = 45.0, color = Color.Cyan, selectedColor = Color.Blue),
                Pie(label = "Linux", data = 35.0, color = Color.Gray, selectedColor = Color.Yellow),
            )
        )
    }

    PieChart(
        modifier = Modifier.size(200.dp).padding(20.dp).fillMaxSize(),
        data = data,
        onPieClick = {
            println("${it.label} Clicked")
            val pieIndex = data.indexOf(it)
            data = data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
        },
        selectedScale = 1.2f,
        scaleAnimEnterSpec = spring<Float>(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
    )
}


@Composable
fun grafLinear(){
    LineChart(
        modifier = Modifier.padding(horizontal = 22.dp).size(200.dp).fillMaxSize(),
        data = remember {
            listOf(
                Line(
                    label = "Windows",
                    values = listOf(208.0, -41.0, 5.0, 10.0, 35.0),
                    color = SolidColor(Color(0xFF23af92)),
                    firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                    secondGradientFillColor = Color.Transparent,
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(width = 2.dp),
                )
            )
        },
        animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
        }),
    )
}

@Composable
fun grafBarraCol() {
    var data by remember { mutableStateOf(
        listOf(
            Bars(
                label = "Elecciones 2025",
                values = listOf(
                    Bars.Data(label = "Alianza Popular", value = 7.06, color = SolidColor(Color.Blue)),
                    Bars.Data(label = "ADN", value = 1.39, color = SolidColor(Color.Red)),
                    Bars.Data(label = "Súmate", value = 6.0, color = SolidColor(Color.Yellow)),
                    Bars.Data(label = "Libre", value = 29.29, color = SolidColor(Color.Green)),
                    Bars.Data(label = "Fuerza del Pueblo", value = 1.45, color = SolidColor(Color.Cyan)),
                    Bars.Data(label = "MAS", value = 3.09, color = SolidColor(Color.Magenta)),
                    Bars.Data(label = "Unidad", value = 21.09, color = SolidColor(Color.Gray)),
                    Bars.Data(label = "PDC", value = 30.62, color = SolidColor(Color.DarkGray)),
                    Bars.Data(label = "Nulos", value = 16.49, color = SolidColor(Color.LightGray)),
                    Bars.Data(label = "Blancos", value = 2.22, color = SolidColor(Color.Black))

                ),
            )
        )
    )
    }
    ColumnChart(
        modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp),
        data ,
        barProperties = BarProperties(
            spacing = 11.dp,
            thickness = 20.dp,
        ),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        maxValue=100.0,
    )
}

@Composable
fun grafBarraFil() {
    var data by remember { mutableStateOf(
        listOf(
            Bars(
                label = "Jan",
                values = listOf(
                    Bars.Data(label = "Linux", value = 50.0, color = SolidColor(Color.Green)),
                    Bars.Data(label = "Windows", value = 70.0, color = SolidColor(Color.Red)),
                ),
            ),
            Bars(
                label = "Feb",
                values = listOf(
                    Bars.Data(label = "Linux", value = 80.0, color = SolidColor(Color.Green)),
                    Bars.Data(label = "Windows", value = 60.0, color = SolidColor(Color.Red)),
                ),
            )
        ))
    }

    RowChart(
        modifier = Modifier.padding(horizontal = 22.dp).size(200.dp).fillMaxHeight(),
        data ,
        barProperties = BarProperties(
            spacing = 3.dp,
        ),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
    )
}

@Composable
fun graficoTorta(){
    var data:List<Pie> by remember { mutableStateOf(listOf(
        Pie("IOs",13.78,Color.Red, selectedColor = Color.DarkGray),
        Pie("android",72.13, Color.Green, selectedColor=Color.DarkGray)

    ))}
    PieChart(modifier = Modifier.fillMaxSize(),
    data=data, onPieClick = {
        val pieIndex=data.indexOf(it)
            data=data.mapIndexed{mapIndex,pie->pie.copy(selected=pieIndex==mapIndex)}
        })
}

@Preview(showBackground = true)
@Composable
fun GraficoTortaPreview() {
    grafBarraCol()
}
