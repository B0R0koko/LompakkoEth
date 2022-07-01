package ru.lompakko.app.models

data class Account (
    val accountName: String,
    val blockchain: String,
    val accountBalance: Double,
    val onChainAddress: String
)

// accountList - list of accounts added to RecyclerView
var accountList = mutableListOf<Account>(
    Account("Ethereum Vault", "", 2.3, ""),
    Account("Cardano Vault", "", 1.54, ""),
)