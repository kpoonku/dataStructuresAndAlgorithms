package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

/*
Leetcode
Easy: 828 problems

Medium: 1,733 problems

Hard: 752 problems

 */
public class AuthenticationManager {
    private int timeToLive;
    private Map<String, Integer> tokenExpiryMap;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.tokenExpiryMap = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        tokenExpiryMap.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        Integer expirationTime = tokenExpiryMap.getOrDefault(tokenId, 0);
        if( expirationTime > currentTime) {
            generate(tokenId, currentTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for(int expirationTime: tokenExpiryMap.values()) {
            if(expirationTime > currentTime) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        AuthenticationManager  authenticationManager = new AuthenticationManager(5);

    }
}
