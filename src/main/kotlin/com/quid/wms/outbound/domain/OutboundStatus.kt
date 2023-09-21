package com.quid.wms.outbound.domain

enum class OutboundStatus {
    CREATED,
    PICKING,
    PICKED,
    PACKING,
    PACKED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED,
    FAILED,
    ;
}