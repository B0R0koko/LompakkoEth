package ru.lompakko.app.tasks.ethereum

import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.http.HttpService
import java.io.File
import java.util.concurrent.TimeUnit

class EthereumClient {
    // Connect to ethereum node
    private val client = Web3j.build(
        HttpService("https://mainnet.infura.io/v3/d013180f73544e0b9311029b899fd679")
    )
    private val address = "0xe60aaFF1Ea0786979049787Ff9D734168bcA0791"
    private val response = client.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).sendAsync().get(
        10, TimeUnit.SECONDS
    )
    fun response(): String {
        return response.balance.toString()
    }
}