//package com.ngo_connect.gen1.security;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class JwtUtil {
//	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
///**
// * creating a secret key for token, can be changed to anything
// */
//	private String secretkey = "ngo";
//
//	/**
//	 * This method is used to extract the username from the token
//	 *
//	 * @param token in the string format
//	 * @return
//	 */
//	public String extractUsername(String token) {
//		LOGGER.info("START");
//		LOGGER.info("END");
//
//		return extractClaim(token, Claims::getSubject);
//
//	}
//
//	/**
//	 * This method is used to extract a particular claim for the token
//	 *
//	 * @param <T>
//	 * @param token
//	 * @param claimsResolver
//	 * @return
//	 */
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		LOGGER.info("START");
//
//		final Claims claims = extractAllClaims(token);
//		LOGGER.info("END");
//
//		return claimsResolver.apply(claims);
//
//	}
//
//	/**
//	 * This method is used to extract claims for the token
//	 *
//	 * @param token
//	 * @return
//	 */
//	private Claims extractAllClaims(String token) {
//		LOGGER.info("START");
//		LOGGER.info("END");
//
//		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
//
//	}
//
//
//	public String generateToken(UserDetails userDetails) {
//		LOGGER.info("START");
//
//		Map<String, Object> claims = new HashMap<>();
//		LOGGER.info("END");
//
//		return createToken(claims, userDetails.getUsername());
//	}
//
//	/**
//	 * This method is used to create token based on the claims and subject given as
//	 * parameter. It will add a signature to the jwt token based on the algorithm
//	 * HS256.
//	 *
//	 * @param claims
//	 * @param subject
//	 * @return
//	 */
//	private String createToken(Map<String, Object> claims, String subject) {
//		LOGGER.info("START");
//
//		String compact = Jwts.builder().setClaims(claims).setSubject(subject)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + (1000*60*15)))
//				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
//		LOGGER.info("END");
//
//		return compact;
//	}
//
//	/**
//	 * This method is used to validate token based on the given token and
//	 * userDetails as parameter. First from the token we will extract the username
//	 * and then will check in the database whether the token extracted username and
//	 * the user residing in database is same or not and also will check whether the
//	 * token has been expired or not
//	 *
//	 * @param token
//	 * @param userDetails
//	 * @return
//	 */
//	public Boolean validateToken(String token) {
//		LOGGER.info("START");
//
//		try {
//			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
//			LOGGER.info("END");
//
//			return true;
//		} catch (Exception e) {
//			LOGGER.info("EXCEPTION");
//			return false;
//		}
//
//	}
//}