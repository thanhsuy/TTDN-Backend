package com.example.SpringBootLearning.service;

import com.example.SpringBootLearning.dto.request.AuthenticationRequest;
import com.example.SpringBootLearning.dto.request.IntrospectRequest;
import com.example.SpringBootLearning.dto.respone.AuthenticationRespone;
import com.example.SpringBootLearning.dto.respone.IntrospectRespone;
import com.example.SpringBootLearning.entity.User;
import com.example.SpringBootLearning.exception.AppException;
import com.example.SpringBootLearning.exception.ErrorCode;
import com.example.SpringBootLearning.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
public class AuthenticationService {
    UserRepository userRepository;
    private static final String SIGNER_KEY = "0aPglnnROU/zGjIuvAA32LpDzmqEY2O7J4fgQ4Eh+4KuJaSCXQIFQgBv6a69Pvkt";

    public AuthenticationRespone authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(()
                -> new AppException(ErrorCode.USER_NOTFOUND));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = bCryptPasswordEncoder.matches(request.getPassword(),user.getPassword());
        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = genToken(request.getUsername());
        AuthenticationRespone authenticationRespone = new AuthenticationRespone().builder()
                .token(token)
                .authenticated(authenticated)
                .build();
        return authenticationRespone;
    }

    public String genToken(String username){
        Optional<User> user =  userRepository.findByUsername(username);
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.get().getUsername())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);


//        Ky token(Thuat toan ky, )

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
    public IntrospectRespone introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signJWT = SignedJWT.parse(token);
        boolean verified = signJWT.verify(verifier);
        Date expityTime = signJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectRespone.builder()
                .authenticated(verified && expityTime.after(new Date()))
                .build();
    }
    private String buildScope(Optional<User> user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.get().getRoles())){
            user.get().getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}