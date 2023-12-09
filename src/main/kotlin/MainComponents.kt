import Controller.DAOCategorias
import Controller.DAOLoans
import Controller.DAOMovies
import Model.DTOCategorias
import Model.DTOLoans
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
import androidx.compose.runtime.*
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class projectObjects(){
    MOVIES,
    LOANS
}


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
fun ViewButton(buttonText: String, buttonFunction: () -> Unit)
{
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
fun ViewTextField(labelField: String, insideText: String,funLostFocus: () -> Unit): String
{
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

@Composable
fun ViewTextField(labelField: String, insideText: String, insideValue: String,funLostFocus: () -> Unit): String
{
    var text by remember { mutableStateOf(insideValue) }
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
fun ViewTextFieldReadOnly(labelField: String, insideText: String): String
{
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
fun  ViewDropDownField (defaulVal: String, list: MutableList<DTOCategorias>, defaulValId: Int): DTOCategorias
{

    var defaulCat:DTOCategorias? = if (DAOCategorias.GetCategoriaById(defaulValId).size == 0) DTOCategorias() else
        DAOCategorias.GetCategoriaById(defaulValId)[0]?:DTOCategorias()
    var isExpanded by remember { mutableStateOf(false) }
    var userElection by remember { mutableStateOf(defaulVal) }
    var userElection2 by remember { mutableStateOf<DTOCategorias>(defaulCat?:DTOCategorias()) }


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
                        userElection2 = item
                              },
                    enabled = true
                ){
                    Text(text = item.nombre)
                }
            }
        }
    }

    return userElection2
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
              listCategories: MutableList<DTOCategorias>
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        for (movie in listMovies){
            var infoMovie = hashMapOf<String,String>(
                "id" to movie.id.toString(),
                "ISBN" to movie.isbn,
                "nombre" to movie.nombre,
                "descripcion" to movie.descripcion,
                "unidades_disponibles" to movie.unidadesDisponibles.toString(),
                "categoria_nombre" to movie.categoria.nombre,
                "categoria_id" to movie.categoria.id.toString()
            )

            var editionMode = remember { mutableStateOf(false) }

            ViewItemTable(movie.nombre,
                movie.categoria.nombre,
                movie.descripcion,
                { editionMode.value = true },
                { editionMode.value = false },
                editionMode.value,
                infoMovie,
                listCategories,
                movie.categoria
            )
        }
    }
}

//VIEW TABLE LOANS
@Composable
fun ViewTable(listLoans: MutableList<DTOLoans>
){

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        for (Loan in listLoans){
            var infoMovie = hashMapOf<String,String>(
                "id_prestamo" to Loan.id.toString(),
                "fecha_prestamos" to Loan.fecha_prestamo.toString(),
                "fecha_devolucion" to Loan.fecha_devolucion.toString(),
                "precio" to Loan.precio.toString(),
                "peliculas_id" to Loan.peliculas_id.toString(),
                "peliculas_clientes_id_cliente" to Loan.peliculas_clientes_id_cliente.toString()
            )

            var editionMode = remember { mutableStateOf(false) }

            ViewItemTable(Loan.peliculas_id.toString(),
                Loan.fecha_prestamo.toString(),
                Loan.fecha_devolucion.toString(),
                { editionMode.value = true },
                { editionMode.value = false },
                editionMode.value,
                infoMovie
            )
        }
    }
}


fun deleteObjectFun(id : String, objectToBeAffect : projectObjects) {
    when (objectToBeAffect){
        projectObjects.MOVIES -> DAOMovies.DeleteMovie(id.toInt())
        else -> println("No object set to be edited")
    }
}

fun editObjectFun(id: String, movieInfo : DTOMovies) {
        println(movieInfo.toString())
        DAOMovies.UpdateMovie(id.toInt(),movieInfo)
}

@Composable
fun ViewItemTable(lMainText: String,
                  lSubText: String,
                  lDescription: String,
                  editFun: () -> Unit,
                  editFun3: () -> Unit,
                  editionMode: Boolean,
                  infoFields: HashMap<String,String>,
                  listCategories: MutableList<DTOCategorias>,
                  defaultCategory: DTOCategorias
                  )
{

    val openDialog = remember { mutableStateOf(false) }
    val newInfoMovie = remember { mutableStateMapOf(
        "id" to infoFields.get("id"),
        "ISBN" to infoFields.get("ISBN"),
        "nombre" to infoFields.get("nombre"),
        "descripcion" to infoFields.get("descripcion"),
        "unidades_disponibles" to infoFields.get("unidades_disponibles")
    ) }

    val newCategoryInfo = remember { mutableStateOf<DTOCategorias>(defaultCategory) }

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
            if (openDialog.value){
                AlertView("Confirm deletion",
                    "Are you sure that u want to delete '"+newInfoMovie["nombre"] +"' movie?",
                    { openDialog.value = false },
                    {
                        deleteObjectFun(infoFields.get("id").toString(),projectObjects.MOVIES)
                        openDialog.value = false
                    })
            }
            Column {
                if (editionMode) {
                    Row {
                        Column {
                            Row { newInfoMovie["nombre"] =  ViewTextField("Nombre", infoFields["nombre"].toString(),infoFields["nombre"].toString(),{})
                                newInfoMovie["ISBN"] = ViewTextField("ISBN", infoFields["ISBN"].toString(),infoFields["ISBN"].toString(),{})
                                newInfoMovie["descripcion"] = ViewTextField("Descripcion", infoFields["descripcion"].toString(), infoFields["descripcion"].toString(),{})
                                newInfoMovie["unidades_disponibles"] = ViewTextField("Units", infoFields["unidades_disponibles"].toString(), infoFields["unidades_disponibles"].toString(),{})
                                newCategoryInfo.value = ViewDropDownField(infoFields["categoria_nombre"].toString(),listCategories,newCategoryInfo.value.id)
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                ViewButton("Confirmar",{
                                    val newMovie = DTOMovies()
                                    newMovie.id = infoFields["id"]?.toInt() ?: 0
                                    newMovie.isbn = newInfoMovie["ISBN"]
                                    newMovie.nombre = newInfoMovie["nombre"]
                                    newMovie.descripcion = newInfoMovie["descripcion"]
                                    newMovie.unidadesDisponibles = newInfoMovie["unidades_disponibles"]?.toInt() ?: 0
                                    newMovie.categoria = newCategoryInfo.value
                                    editObjectFun(infoFields["id"].toString(),newMovie)

                                    editFun3()
                                }
                                )
                                ViewButton("Cancelar",{editFun3()})
                            }
                        }
                    }

                } else {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
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
                                    openDialog.value = true
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

// Itemtable de Loans

@Composable
fun ViewItemTable(fecha_prestamos: String,
                  fecha_devolucion: String,
                  lDescription: String,
                  editFun: () -> Unit,
                  editFun3: () -> Unit,
                  editionMode: Boolean,
                  infoFields: HashMap<String,String>
)
{

    val openDialog = remember { mutableStateOf(false) }
    val newInfoMovie = remember { mutableStateMapOf(
        "id_prestamo" to infoFields.get("id_prestamo"),
        "fecha_prestamos" to infoFields.get("fecha_prestamos"),
        "fecha_devolucion" to infoFields.get("fecha_devolucion"),
        "precio" to infoFields.get("precio"),
        "peliculas_id" to infoFields.get("peliculas_id"),
        "peliculas_clientes_id_cliente" to infoFields.get("peliculas_clientes_id_cliente")
    ) }


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
            if (openDialog.value){
                AlertView("Confirm deletion",
                    "Are you sure that u want to delete '"+newInfoMovie["id_prestamo"] +"' loan?",
                    { openDialog.value = false },
                    {
                        DAOLoans.DeleteLoan(newInfoMovie["id_prestamo"]?.toInt())
                        openDialog.value = false
                    })
            }
            Column {
                if (editionMode) {
                    Row {
                        Column {
                            Row { newInfoMovie["fecha_prestamos"] = ViewTextField("fecha_prestamos", infoFields["fecha_prestamos"].toString(),infoFields["fecha_prestamos"].toString(),{})
                                newInfoMovie["fecha_devolucion"] = ViewTextField("fecha_devolucion", infoFields["fecha_devolucion"].toString(), infoFields["fecha_devolucion"].toString(),{})
                                newInfoMovie["precio"] = ViewTextField("precio", infoFields["precio"].toString(), infoFields["precio"].toString(),{})
                                newInfoMovie["peliculas_id"] = ViewTextField("peliculas_id", infoFields["peliculas_id"].toString(), infoFields["peliculas_id"].toString(),{})
                                newInfoMovie["peliculas_clientes_id_cliente"] = ViewTextField("Id Cliente", infoFields["peliculas_clientes_id_cliente"].toString(), infoFields["peliculas_clientes_id_cliente"].toString(),{})
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                ViewButton("Confirmar",{
                                    val newMovie = DTOLoans()
                                    val fechaP = LocalDate.parse(newInfoMovie["fecha_prestamos"])
                                    val fechaD = LocalDate.parse(newInfoMovie["fecha_devolucion"])
                                    newMovie.id = infoFields["id_prestamo"]?.toInt() ?: 0
                                    newMovie.fecha_prestamo = fechaP
                                    newMovie.fecha_devolucion = fechaD
                                    newMovie.precio = newInfoMovie["precio"]?.toDouble()
                                    newMovie.peliculas_id = newInfoMovie["peliculas_id"]?.toInt() ?: 0
                                    newMovie.peliculas_clientes_id_cliente = newInfoMovie["id_prestamo"]?.toInt()

                                    DAOLoans.UpdateLoan(newMovie.id, newMovie)
                                }
                                )
                                ViewButton("Cancelar",{editFun3()})
                            }
                        }
                    }

                } else {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column {
                            Row {
                                Text(
                                    text = fecha_prestamos,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = fecha_devolucion,
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
                                openDialog.value = true
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
fun AlertView(title: String, innerText : String,closeFun: () -> Unit, confirmFun: () -> Unit){
    AlertDialog(
        title = {
            Text(
                text =  title,
                fontWeight = FontWeight.SemiBold,
                color = lBlue,
                fontSize = 18.sp
            )
        },
        text = {
            Column {
                Text(innerText)
            }
        },
        onDismissRequest = { closeFun() },
        confirmButton = {
            ViewButton("Confirm",{ confirmFun() })
        },
        dismissButton = {
            ViewButton("Cancel",{ closeFun() })
        },
        modifier = Modifier.size(200.dp)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlertView(title: String, innerText : String,closeFun: () -> Unit){
    AlertDialog(
        title = {
            Text(
                text =  title,
                fontWeight = FontWeight.SemiBold,
                color = lBlue,
                fontSize = 18.sp
            )
        },
        text = {
            Column {
                Text(innerText)
            }
        },
        onDismissRequest = { closeFun() },
        confirmButton = {},
        modifier = Modifier.size(200.dp)
    )
}




