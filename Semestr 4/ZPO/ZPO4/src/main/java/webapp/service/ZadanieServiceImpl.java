package webapp.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import webapp.model.Student;
import webapp.model.Zadanie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService{
    private static final Logger logger = LoggerFactory.getLogger(ProjektServiceImpl.class);
    private static final String RESOURCE_PATH = "/api/zadania";

    @Value("${rest.server.url}")

    private String serverUrl;
    private RestTemplate restTemplate;

    @Autowired
    public ZadanieServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, RESOURCE_PATH + "/" + zadanieId.toString())
                .build()
                .toUri();
        return Optional.ofNullable(restTemplate.getForObject(url, Zadanie.class));
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        if (zadanie.getZadanieId() != null) { // modyfikacja istniejącego projektu
            String url = getUriStringComponent(zadanie.getZadanieId());
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url, zadanie);
            return zadanie;
        } else {//utworzenie nowego projektu
            // po dodaniu projektu zwracany jest w nagłówku Location - link do utworzonego zasobu
            HttpEntity<Zadanie> request = new HttpEntity<>(zadanie);
            String url = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location = restTemplate.postForLocation(url, request);
            logger.info("REQUEST (location) -> GET {}", location);
            return restTemplate.getForObject(location, Zadanie.class);
        }
    }

    @Override
    public void deleteZadanie(Integer zadanieId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(zadanieId))
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);
    }

    @Override
    public Page<Zadanie> getZadania(Pageable pageable) {
        URI uri = ServiceUtil.getURI(serverUrl, RESOURCE_PATH, pageable);
        return ServiceUtil.getPage(uri, restTemplate,
                new ParameterizedTypeReference<RestResponsePage<Zadanie>>() {});
    }

    @Override
    public Page<Zadanie> searchByNazwa(Integer projektId, Pageable pageable) {
        return null;
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }
}
