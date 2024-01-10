package nl.fontys.s3.museumticket.business;

import nl.fontys.s3.museumticket.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}