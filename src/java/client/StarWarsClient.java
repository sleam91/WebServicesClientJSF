package client;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import model.AllPlanets;
import model.StarWarsPlanet;

@Stateless
public class StarWarsClient {

    TrustManager[] trustManager;
    Client client;
    WebTarget target;
    SSLContext sslContext;

    public StarWarsClient() {
        trustManager = new X509TrustManager[]{new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {

            }
        }};

        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, null);
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {

        }

        client = ClientBuilder.newBuilder().sslContext(sslContext).build();
        target = client.target("https://swapi.dev/api/planets/{i}/");
    }

    public StarWarsPlanet findStarWarsPlanetById(String id) {

        try {
            StarWarsPlanet p = target.resolveTemplate("i", id)
                    .request(MediaType.APPLICATION_JSON).get(StarWarsPlanet.class);
            return id.trim().isEmpty() ? null : p;
        } catch (NotFoundException e) {
            return null;
        }

    }

    public List<StarWarsPlanet> getAll() {
        String next = "https://swapi.dev/api/planets/";
        List<StarWarsPlanet> planetResult = new ArrayList<>();

        while (next != null) {
            WebTarget localTarget = client.target(next);
            AllPlanets planets = localTarget.request(MediaType.APPLICATION_JSON).get(AllPlanets.class);

            try {
                next = planets.getNext().replace("http", "https");
            } catch (NullPointerException e) {
                next = null;
            }

            planetResult.addAll(planets.getResults());

        }

        return planetResult;
    }

    public List<StarWarsPlanet> getAllSearched(String search) {
        String next = "https://swapi.dev/api/planets/?search={i}";

        WebTarget localTarget = client.target(next).resolveTemplate("i", search);

        List<StarWarsPlanet> planetResult = new ArrayList<>();

        while (next != null) {

            AllPlanets planets = localTarget.request(MediaType.APPLICATION_JSON).get(AllPlanets.class);

            try {
                next = planets.getNext().replace("http", "https");
                localTarget = client.target(next);
            } catch (NullPointerException e) {
                next = null;
            }

            planetResult.addAll(planets.getResults());

        }

        return planetResult;
    }

}
