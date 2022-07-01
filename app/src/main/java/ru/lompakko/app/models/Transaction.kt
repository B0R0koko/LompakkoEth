package ru.lompakko.app.models

// Simple Ethereum transaction data class
data class Transaction (
    val blockchain: String,
    val type: String,
    val amount: Double,
    val fiatAmount: String,
    val addressFrom: String,
    val addressTo: String,
    // String to be changed to Int further on
    val timeStamp: String
)

var transactionList = mutableListOf<Transaction>(
    Transaction("Ethereum", "IN",0.23, "RUB 10,300.70", "", "", "Sent 7 months ago"),
    Transaction("Ethereum", "IN", 0.2, "RUB 10,300.70", "", "", "Sent a year ago"),
    Transaction("Ethereum", "IN", 0.3, "RUB 10,300.70", "", "", "Sent 2 years ago"),
    Transaction("Ethereum", "OUT",-0.73, "RUB 10,300.70", "", "", "Sent 2 years ago"),
    Transaction("Ethereum", "OUT",-0.63, "RUB 10,300.70", "", "", "Sent 2 years ago")
)
