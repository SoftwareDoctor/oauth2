package it.softwaredoctort.oauth2;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

@RestController
public class JwkSetEndpoint {

    private final JWKSource<SecurityContext> jwkSource;

    public JwkSetEndpoint() {
        this.jwkSource = createJwkSource();
    }

    private JWKSource<SecurityContext> createJwkSource() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            RSAKey rsaKey = new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();

            return new JWKSource<SecurityContext>() {
                @Override
                public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) {
                    return List.of(rsaKey);
                }
            };
        } catch (Exception e) {
            throw new RuntimeException("Error generating RSA key", e);
        }
    }

    @GetMapping("/oauth2/jwks")
    public JWKSet jwks() throws KeySourceException {
        String keyId = jwkSource.get(new JWKSelector(new JWKMatcher.Builder().build()), null).get(0).getKeyID();
        JWKSelector jwkSelector = new JWKSelector(new JWKMatcher.Builder().keyID(keyId).build());
        List<JWK> jwkList = jwkSource.get(jwkSelector, null);
        return new JWKSet(jwkList);
    }
}