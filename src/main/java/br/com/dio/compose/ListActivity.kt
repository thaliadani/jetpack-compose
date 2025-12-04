package br.com.dio.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.dio.compose.ui.theme.ComposeTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListScreen {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }
    }
}

data class Person(
    val name: String
)

@Composable
fun ListScreen(onNavigateToMain: () -> Unit) {
    val personList = listOf(Person("Ezequiel"), Person("Igor"), Person("Pedro"), Person("Venilton"))

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.background(Color.DarkGray).weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(personList) { item ->
                ItemList(name = item.name)
            }
        }
        Button(
            onClick = onNavigateToMain,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to MainActivity")
        }
    }
}

@Composable
fun ItemList(name: String) {
    Text(text = name, modifier = Modifier.background(Color.Blue))
}