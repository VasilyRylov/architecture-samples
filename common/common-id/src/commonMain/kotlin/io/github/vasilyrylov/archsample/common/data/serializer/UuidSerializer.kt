package io.github.vasilyrylov.archsample.common.data.serializer

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuidFrom

object UuidSerializer : kotlinx.serialization.KSerializer<Uuid> {
    override val descriptor: kotlinx.serialization.descriptors.SerialDescriptor =
        kotlinx.serialization.descriptors.PrimitiveSerialDescriptor(
            "Uuid",
            kotlinx.serialization.descriptors.PrimitiveKind.STRING
        )

    override fun serialize(encoder: kotlinx.serialization.encoding.Encoder, value: Uuid) =
        encoder.encodeString(value.toString())

    override fun deserialize(decoder: kotlinx.serialization.encoding.Decoder): Uuid =
        uuidFrom(decoder.decodeString())
}
