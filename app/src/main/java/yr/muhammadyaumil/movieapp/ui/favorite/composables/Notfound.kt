package yr.muhammadyaumil.movieapp.ui.favorite.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yr.muhammadyaumil.movieapp.R

@Composable
fun Notfound() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier =
                Modifier
                    .width(200.dp)
                    .height(200.dp),
            painter = painterResource(R.drawable.notfound),
            contentDescription = "Notfound",
        )
        Spacer(modifier=Modifier.height(15.dp))
        Text("Favorite Not Found", fontSize = 20.sp, fontWeight = FontWeight.W700)
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun NotfoundPreview() {
    Notfound()
}
