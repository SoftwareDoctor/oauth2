# OAuth2 Authorization e Resource Server

## Cosa è stato implementato

In questo progetto sono stati implementati due componenti principali:

### Authorization Server
Gestisce l'autenticazione e la generazione dei token JWT (JSON Web Token). Fornisce un endpoint per ottenere le chiavi pubbliche (JWK - JSON Web Keys) e consente ai client di autenticarsi tramite il protocollo OAuth2.

### Resource Server
Protegge un'API e consente l'accesso solo a client che forniscono un token JWT valido. Il Resource Server verifica il token ricevuto utilizzando le chiavi pubbliche fornite dall'Authorization Server.

## Flusso di autenticazione

1. L'Authorization Server fornisce una chiave pubblica tramite un endpoint `/oauth2/jwks`.
2. Un client può autenticarsi e ottenere un token JWT dall'Authorization Server.
3. Il client invia il token JWT come parte della richiesta per accedere a un endpoint protetto del Resource Server.
4. Il Resource Server verifica la validità del token JWT utilizzando la chiave pubblica dell'Authorization Server e permette o nega l'accesso in base alla validità del token.

Richiesta token → POST http://localhost:8080/oauth2/token
L'Authorization Server genera un JWT firmato con la sua chiave privata e lo restituisce al client
Si usa il token per accedere al Resource Server → GET http://localhost:8081/public/hello
Il Resource Server riceve il token e lo deve verificare per garantire che sia valido
Spring Security, configurato come Resource Server, interroga automaticamente l'Authorization Server per ottenere la chiave pubblica, chiamando http://localhost:8080/oauth2/jwks
Se la firma del token corrisponde alla chiave pubblica ricevuta, l'accesso viene consentito 

## Tecnologie utilizzate

- **Spring Boot**: Framework utilizzato per sviluppare le applicazioni sia dell'Authorization Server che del Resource Server, che semplifica la configurazione e lo sviluppo.
- **OAuth2**: Protocollo di autorizzazione utilizzato per gestire l'autenticazione e autorizzazione dei client.
- **JWT (JSON Web Token)**: Formato di token utilizzato per gestire l'autenticazione stateless tra il client e il server.
- **RSA**: Algoritmo di cifratura utilizzato per firmare e verificare i token JWT.
- **Spring Security**: Fornisce i meccanismi di sicurezza per implementare la gestione dell'autenticazione e la protezione delle risorse.
- **Docker**: Utilizzato per containerizzare le applicazioni, semplificando il processo di distribuzione e avvio.
- **Docker Compose**: Strumento che consente di gestire più container Docker, utilizzato per orchestrare l'esecuzione dei vari componenti (Authorization Server e Resource Server).

# Autore

Andrea Italiano