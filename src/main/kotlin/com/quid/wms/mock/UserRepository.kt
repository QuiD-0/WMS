package com.quid.wms.mock

import com.quid.wms.order.domain.OrderCustomer
import org.springframework.stereotype.Repository

interface UserRepository {
    fun findUserById(id: Long): User

    @Repository
    class UserMock : UserRepository {
        override fun findUserById(id: Long): User {
            return User(id, "Quid", "quid@mail.com", "010-1234-5678")
        }
    }

    data class User(
        val id: Long,
        val name: String,
        val email: String,
        val phone: String,
    ) {
        fun toOrderCustomer(): OrderCustomer = OrderCustomer(
            name, email, phone
        )
    }
}

