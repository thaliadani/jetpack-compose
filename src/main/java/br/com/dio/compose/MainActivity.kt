package br.com.dio.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.dio.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val activity = (LocalContext.current as? Activity)
                    MainScreen(
                        onBack = {
                            activity?.finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(onBack: () -> Unit) {
    var clicks by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ClickCounter(clicks = clicks) {
            clicks++
        }

        HelloContent()

        DogCard(dog = Dog("Chico", "Vira-lata"))

        TestingColumns()

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Name")
            }
        )
    }
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClick, modifier = Modifier.padding(top = 56.dp)) {
            Text("I've been clicked $clicks times")
        }
    }
}

data class Dog(
    val name: String,
    val breed: String
)

@Composable
fun DogCard(dog: Dog, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)

            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_dog),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(Color.Green)
                .padding(8.dp)
        )

        Column (
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(start = 16.dp)
        ){
            Text(text = dog.name)
            Text(text = dog.breed)
        }
    }
}

@Composable
private fun TestingColumns() {
    Column {
        Text(
            text = "Hello Word!!",
            maxLines = 3,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Text(
            text = "Outro texto qualquer",
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        )

        Row {
            Text(text = "Text 3", modifier = Modifier.padding(end = 30.dp))

            Text(text = "Text 4")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen { }
}
