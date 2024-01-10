package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
