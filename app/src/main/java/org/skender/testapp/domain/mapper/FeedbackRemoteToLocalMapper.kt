package org.skender.testapp.domain.mapper

import org.skender.testapp.data.local.Feedback
import org.skender.testapp.data.remote.FeedbackRemote
import javax.inject.Inject

class FeedbackRemoteToLocalMapper @Inject constructor(): AbstractMapper<FeedbackRemote, Feedback>(){
    override fun map(value: FeedbackRemote): Feedback {
        return Feedback(
            value.isPositive,
            value.message,
            value.userName,
            value.date,
            value.restaurantName
        )
    }
}