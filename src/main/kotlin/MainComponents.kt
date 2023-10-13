import Controller.DAOCategorias
import Model.DTOCategorias
import Model.DTOMovies
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import navController.SharedScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path

@Composable
@Preview
fun prevComponents(){
    MiniHeader("Movies")
}

// Constantes de estilos de la aplicacion
val logoPaht = "logoTienda.png"
val storeName = "Movies Store"
val lBlue = Color(0xff3F53D9)
val dBlue = Color(0Xff050038)
val semidBlue = Color(0Xff414BB2)

/**
 * @author Ismael Matiz
 * @since Este elemento agrega el header principal de la app con el logo y el titulo
 */
@Composable
fun Header(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(dBlue)
    ){
        Box {
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

/**
 * @author Ismael Matiz
 * @since Este elemento representa el titulo de la vista junto con el boton volver
 * @param title el titulo de la ventana a mostrar
 */
@Composable
fun MiniHeader(title: String){
    val navigator = LocalNavigator.currentOrThrow
    val homeScreen = rememberScreen(SharedScreen.HomeV)
    Row (
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default
        )
        ViewButton("Volver",{
            navigator.push(homeScreen)
        })

    }
}

/**
 * @author Ismael Matiz
 * Esta elemento recibe el texto y la funcion para cambiar de ventana del menu principal
 * @param buttonText el titulo del boton, por lo general el nombre de la vista a la que lleva
 * @param moveTo la funcion que cambia hacia la ventana deseada
 */
@Composable
fun menuButton(buttonText : String,moveTo: () -> Unit){
    Column(modifier = Modifier.padding(10.dp)) {
        Button(onClick = {
            moveTo()
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            shape = CutCornerShape(30.dp),
            border = BorderStroke(2.dp,Color.Gray)

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

/**
 * @author Ismael Matiz
 * @since Este elemento es son los botones dentro de las vistas, los cuales reciben
 * el texto a mostrar en el boton y la funcion a realizar
 * @param buttonText el texto a mostrar dentro del boton
 * @param buttonFunction la funcion a ejecutar cuando se oprima el boton
 */
@Composable
fun ViewButton(buttonText: String, buttonFunction: () -> Unit){
    Button(
        onClick = {buttonFunction()},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = semidBlue
        ),
        shape = CutCornerShape(8.dp)
    ){
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

/**
 * @author Ismael Matiz
 * @since Este elemento es son los campos donde el usuario ingresa informacion de tipo texto
 * puedes recuperar la informacion guardando en una variable de tipo string, ademas de eso
 * si lo deseas puedes agregar un metodo para cuando el usuario cambie a otro campo
 * @param labelField es el texto azul que aparece arriba del field
 * @param insideText es el texto que esta dentro del field, que dice algo como ingresa texto aqui
 * @param funLostFocus es la funcion que desas que ocurra si el usuario cambia de campo
 * @return lo que ingreso el usuario como un String
 */
@Composable
fun ViewTextField(labelField: String, insideText: String,funLostFocus: () -> Unit): String{
    var text by remember { mutableStateOf("") }
    var countClick by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
    ){
        Text(
            text = labelField,
            fontWeight = FontWeight.SemiBold,
            color = lBlue,
            fontSize = 25.sp
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(text = insideText, fontSize = 12.sp)},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.onFocusChanged {
                if(!it.isFocused && countClick != 0){
                    funLostFocus()
                }
                countClick++
            }
        )
    }
    return text
}

/**
 * @author Ismael Matiz
 * @since Este elemento es igual a ViewTextField pero este elemento es para solo lectura, es decir,
 * el usuario no podra escribir en el
 * @param labelField es el texto azul que aparece arriba del field
 * @param insideText es el texto que esta dentro del field, no sera editable
 * @return lo que ingreso el usuario como un String
 */
@Composable
fun ViewTextFieldReadOnly(labelField: String, insideText: String): String{
    var text by remember { mutableStateOf(insideText) }
    Column(
        modifier = Modifier.size(200.dp)
            .padding(10.dp)
    ){
        Text(
            text = labelField,
            fontWeight = FontWeight.SemiBold,
            color = lBlue,
            fontSize = 25.sp
        )
        OutlinedTextField(
            value = insideText,
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.DarkGray,
                unfocusedIndicatorColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp),
            readOnly = true,
            enabled = false
        )
    }
    return text
}

/**
 * @author Ismael Matiz
 * @since Este componente muestra la lista que le pases como un campo de seleccion multiple
 * al final devulve la eleccion del usuario como String, aunque solo permite Categorias
 * es facilmente escalable haciendo uso de sobrecarga de metodo y pasando
 * en lugar de DTOCategorias el DTO del objeto a mostrar y abria que cambiar
 * en el foreach el item.nombre por item.<el campo a mostrar>
 * @param labelField el texto sobre el campo y calor inicial
 * @param list la lista a mostrar
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun  ViewDropDownField (defaulVal: String, list: MutableList<DTOCategorias>): String{

    var isExpanded by remember {  mutableStateOf(false) }
    var userElection by remember { mutableStateOf(defaulVal) }


    Box(){
        Column(
            modifier = Modifier.size(200.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = "Categories",
                fontWeight = FontWeight.SemiBold,
                color = lBlue,
                fontSize = 20.sp
            )
            OutlinedTextField(
                value = userElection,
                onValueChange = {},
                modifier = Modifier.onClick { isExpanded = true },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp),
                readOnly = true,
                enabled = false
            )
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false}
        ){
            for(item in list){
                DropdownMenuItem(
                    onClick = {
                        userElection = item.nombre
                        isExpanded = false
                              },
                    enabled = true
                ){
                    Text(text = item.nombre)
                }
            }
        }
    }

    return userElection
}

/**
 * @author Ismael Matiz
 * @since Este componente muestra una lista scroleable en pantalla de movies
 * si se desea cambia una simple sobrecarga de metodo cambiando el tipo del dato
 * y como se accede a la info de ese tipo de dato mas abajo bastara
 * @param listMovies La lista de peliculas a mostrar
 */
@Composable
fun ViewTable(listMovies: MutableList<DTOMovies>,
              editFun2: () -> Unit,
              deleteFun: () -> Unit,
              listCategories: MutableList<DTOCategorias>
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        for (movie in listMovies){
            var listInfoMovie = arrayListOf(
                movie.nombre.toString(),
                movie.categoria.nombre.toString(),
                movie.descripcion.toString(),
                movie.unidadesDisponibles.toString(),
                movie.categoria.nombre)
            var editionMode = remember { mutableStateOf(false) }
            ViewItemTable(movie.nombre,
                movie.categoria.nombre,
                movie.descripcion,
                {editionMode.value = true},
                {editFun2()},
                {editionMode.value = false},
                {deleteFun()},
                editionMode.value,
                listInfoMovie,
                listCategories)
        }
    }
}

@Composable
fun ViewItemTable(lMainText: String,
                  lSubText: String,
                  lDescription: String,
                  editFun: () -> Unit,
                  editFun2: () -> Unit,
                  editFun3: () -> Unit,
                  deleteFun: ()->Unit,
                  editionMode: Boolean,
                  infoFields: ArrayList<String>,
                  listCategories: MutableList<DTOCategorias>){
    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .border(2.dp, Color.Gray)
                .padding(15.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                if (editionMode) {
                    Row {
                        Column {
                            Row { ViewTextField("Nombre",infoFields.get(0),{})
                                ViewTextField("Categoria",infoFields.get(1),{})
                                ViewTextField("Descripcion",infoFields.get(2),{})
                                ViewTextField("Units",infoFields.get(3),{})
                                ViewDropDownField(infoFields.get(4),listCategories) }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                ViewButton("Confirmar",{editFun2()})
                                ViewButton("Cancelar",{editFun3()})
                            }
                        }
                    }

                } else {
                    Row {
                        Column {
                            Row {
                                Text(
                                    text = lMainText,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = lSubText,
                                    fontSize = 10.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 8.dp, start = 5.dp)
                                )
                            }
                            Text(lDescription)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        )
                        {
                            IconButton(onClick = {
                                editFun()
                            }) {
                                Icon(
                                    modifier = Modifier.size(size = 30.dp),
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Icono de editar"
                                )
                            }
                            IconButton(onClick = {
                                deleteFun()
                            }) {
                                Icon(
                                    modifier = Modifier.size(size = 30.dp),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Icono de borrar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlertView(title: String, categorias : MutableList<DTOCategorias>,closeFun: () -> Unit){
    AlertDialog(
        title = {
            Text(title)
        },
        text = {
            Column {
                ViewTextField("ISBN","Enter ISBN value",{})
                ViewTextField("Name","Enter Name value",{})
                ViewTextField("Description","Enter description value",{})
                ViewTextField("Units","Enter Units value",{})
                ViewDropDownField("Categorias",categorias)
            }
        },
        onDismissRequest = { closeFun() },
        confirmButton = {},
        dismissButton = {
            ViewButton("Cerrar",{ closeFun() })
        },
        modifier = Modifier.size(200.dp)
    )
}

