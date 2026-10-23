package br.edu.fatec.delivery.produto.config;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class SecurityCallbackHandler implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callback;
                
                // Valida o utilizador e fornece a palavra-passe em texto limpo para o WSS4J.
                // O WSS4J calculará o Hash (Nonce + Timestamp + Palavra-passe) e comparará com a requisição.
                if ("admin".equals(pc.getIdentifier())) {
                    pc.setPassword("senhaSegura123");
                }
            }
        }
    }
}