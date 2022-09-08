# Ethereum Balance App

## API Documentation
https://docs.etherscan.io/

## API
The API I used to check balance is 
https://api.etherscan.io/api?module=account&action=balance&address=0xde0b295669a9fd93d5f28d9ec85e40f4cb697bae&tag=latest&apikey=YourApiKeyToken

The API I used to check internal transactions 
https://api.etherscan.io/api?module=account&action=txlistinternal&txhash=0x40eb908387324f2b575b4879cd9d7188f69c8fc9d87c901b9e2daaea4b442170&apikey=YourApiKeyToken

The API I used to check transaction reports
https://api.etherscan.io/api?module=account&action=txlist&address=0xc5102fE9359FD9a28f877a67E36B0F050d81a3CC&startblock=0&endblock=99999999&page=1&offset=10&sort=asc&apikey=YourApiKeyToken

## Notable Features
* Check balance in ETH when public blockchain address given
* Check transaction reports when public blockchain address given
* Check list of internal transactions within a transaction when transaction hash given

## Android environment
- Compile SDK 31
- Build tool 30.0.2
- Android Gradle plugin 4.2.1
- Gradle wrapper 6.8.3
