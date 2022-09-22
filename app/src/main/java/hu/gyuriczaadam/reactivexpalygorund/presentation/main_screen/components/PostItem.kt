package hu.gyuriczaadam.reactivexpalygorund.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import hu.gyuriczaadam.reactivexpalygorund.data.dto.Post
import hu.gyuriczaadam.reactivexpalygorund.util.LocalSpacing


@Composable
fun PostItem(
    post: Post,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .padding(end = spacing.spaceMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {

            Row(
                modifier = Modifier.weight(1f)
            ) {
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Column(
                    modifier = Modifier.align(CenterVertically)
                ) {

                    Text(
                        text = post.title!!,
                        style = MaterialTheme.typography.h4,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    Text(
                        text = post.body!!,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}