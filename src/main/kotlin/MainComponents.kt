import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun prevComponents(){
    Header()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        menuButton("Loans")
        menuButton("Movies")
    }
}

val logoPaht = "logoTienda.png"
val storeName = "Movies Store"
val lBlue = Color(0xff3F53D9)
val dBlue = Color(0Xff050038)

@Composable
fun Header(){

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(dBlue)
    ){
        Box(){
            Text(
                text = storeName,
                style = TextStyle(fontFamily = FontFamily.Default,fontSize = 100.sp, color = Color.White, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding( start = 110.dp, top = 10.dp)
            )
            Image(
                painter = painterResource(logoPaht),
                contentDescription = "Logo de la tienda",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        }
    }
}


@Composable
fun menuButton(buttonText : String){
    Column(modifier = Modifier.padding(10.dp)) {
        Button(onClick = {

        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier.border(2.dp,Color.Gray)
        ){
            Text(
                text = buttonText,
                textAlign = TextAlign.Center,
                style = TextStyle(fontFamily = FontFamily.Default,fontSize = 60.sp, color = lBlue, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(30.dp)
                    .background(Color.Transparent)

            )
        }
    }
}
