package org.skender.testapp.domain.mapper

import org.skender.testapp.data.local.Bestseller
import org.skender.testapp.data.remote.BestsellerRemote
import javax.inject.Inject

class BestsellerRemoteToLocalMapper @Inject constructor(): AbstractMapper<BestsellerRemote, Bestseller>() {
    override fun map(value: BestsellerRemote): Bestseller {
        return Bestseller(
            value.name,
            value.image,
            value.description
        )
    }
}