package com.example.courses
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.topic.Topic
import com.example.courses.ui.theme.CoursesListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesListTheme {
                App()
            }
        }
    }
}

@Composable
fun DrawTopic(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Image(
                painter = painterResource(id = topic.imageRecourseId),
                contentDescription = "imageOfTopic",
                modifier = modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(id = topic.topicResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.medium),
                        top = dimensionResource(R.dimen.medium),
                        end = dimensionResource(R.dimen.medium),
                        bottom = dimensionResource(R.dimen.small)
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "IconOfMenu",
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.medium))
                    )
                    Text(
                        text = topic.coursesResourceId.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier.padding(
                            start = dimensionResource(R.dimen.small)
                        )
                    )
                }

            }
        }
    }
}


@Composable
fun DrawList(list: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small)),
        modifier = modifier
    ) {
        items(list) { topic ->
            DrawTopic(topic)
        }
    }
}

@Composable
fun App() {
    val listOfTopic = DataSource.topics
    DrawList(
        listOfTopic,
        modifier = Modifier.padding(dimensionResource(R.dimen.small))
    )
}

@Preview
@Composable
fun DrawTopicPreview() {
    DrawTopic(
        topic = Topic(R.string.architecture, 58, R.drawable.architecture)
    )
}