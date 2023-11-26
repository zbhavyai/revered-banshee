# Revered Banshee

Utility wrapper project to generate RSA keys and X509 certs using Bouncy Castle.

## Build and run locally

Run the project on `localhost:8080`

```shell
./gradlew build -Dquarkus.package.type=uber-jar
java -jar build/*-runner.jar
```

## How to use

- Generate a new RSA public-private key pair and X509 certificate with given subject and issuer:

   ```shell
   curl --silent --request POST --header 'Content-Type: application/json' --location 'localhost:8080/api/v1/generate' --data '{"subject": "www.mycompany.com", "issuer": "My Company"}'
   ```

- Generate a new RSA public-private key pair

   ```shell
   curl --silent --request POST --header 'Content-Type: application/json' --location 'localhost:8080/api/v1/generate-keypair'
   ```

- Convert a given RSA public-private key pair from base64 to PEM format

   ```shell
   curl --silent --request POST --header 'Content-Type: application/json' --location 'localhost:8080/api/v1/keypair-base64-pem' --data '{
      "keyID": "29fb5f8b-91a2-420a-a710-73f77ec8216f",
      "publicKey": "<rsa public key in base 64>",
      "privateKey": "<rsa private key in base 64>",
      "subject": "subject",
      "issuer": "issuer"
   }'
   ```
